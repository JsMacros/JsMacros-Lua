package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.config.ScriptTrigger;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.language.BaseWrappedException;
import xyz.wagyourtail.jsmacros.core.language.ContextContainer;
import xyz.wagyourtail.jsmacros.core.language.ScriptContext;
import xyz.wagyourtail.jsmacros.lua.config.LuaConfig;
import xyz.wagyourtail.jsmacros.lua.luaj.ILuaError;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class LuaLanguageDefinition extends BaseLanguage<Globals> {

    public final Globals globalGlobals = JsePlatform.standardGlobals();

    public LuaLanguageDefinition(String extension, Core runner) {
        super(extension, runner);
    }
    
    protected void execContext(ContextContainer<Globals> ctx, Executor e) throws Exception {
        Globals globals;
        
        if (runner.config.getOptions(LuaConfig.class).useGlobalContext) globals = globalGlobals;
        else globals = JsePlatform.standardGlobals();
        ctx.getCtx().setContext(globals);
    
        retrieveLibs(ctx).forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
        
        e.accept(globals);
    }
    
    private void setPerExecVar(ScriptContext<?> ctx, Globals globals, String name, LuaValue val) {
        boolean put = globals.rawget(name) instanceof PerContextLuaValue;
        PerContextLuaValue pclv = put ? (PerContextLuaValue) globals.rawget(name) : new PerContextLuaValue();
        pclv.addContext(ctx, val);
        if (!put) globals.rawset(name, pclv);
    }
    
    @Override
    protected void exec(ContextContainer<Globals> ctx, ScriptTrigger scriptTrigger, File file, BaseEvent baseEvent) throws Exception {
        execContext(ctx, (globals) -> {
            setPerExecVar(ctx.getCtx(), globals, "event", CoerceJavaToLua.coerce(baseEvent));
            setPerExecVar(ctx.getCtx(), globals, "file", CoerceJavaToLua.coerce(file));
            setPerExecVar(ctx.getCtx(), globals, "context", CoerceJavaToLua.coerce(ctx));
            
            retrieveOnceLibs().forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
            
            retrievePerExecLibs(ctx).forEach((name, lib) -> setPerExecVar(ctx.getCtx(), globals, name, CoerceJavaToLua.coerce(lib)));
            
            LuaClosure current = (LuaClosure) globals.loadfile(file.getCanonicalPath());
            current.call();
        });
    }
    
    @Override
    protected void exec(ContextContainer<Globals> ctx, String s, Map<String, Object> map, Path path) throws Exception {
        execContext(ctx, (globals) -> {
        
            map.forEach((name, lib) -> setPerExecVar(ctx.getCtx(), globals, name, CoerceJavaToLua.coerce(lib)));
            setPerExecVar(ctx.getCtx(), globals, "context", CoerceJavaToLua.coerce(ctx));
    
            LuaValue current = globals.load(s);
            current.call();
        });
    }
    
    @Override
    public BaseWrappedException<?> wrapException(Throwable ex) {
        if (ex instanceof LuaError) {
            File file = ((ILuaError) ex).getFile();
            int line = ((ILuaError) ex).getLine();
            String error = ((ILuaError) ex).getErrorMessage();
            BaseWrappedException.SourceLocation loc = null;
            if (file != null) {
                loc = new BaseWrappedException.GuestLocation(file, -1, -1, line, -1);
            }
            Throwable cause = ex.getCause();
            BaseWrappedException<?> causewrap = null;
            if (cause != null) {
                causewrap = Core.instance.wrapException(cause);
            }
            return new BaseWrappedException<>(ex, error, loc, causewrap);
        }
        return null;
    }
    
    @Override
    public ScriptContext<Globals> createContext() {
        return new LuaScriptContext();
    }
    
    private interface Executor {
        void accept(Globals globals) throws Exception;
    }
}
