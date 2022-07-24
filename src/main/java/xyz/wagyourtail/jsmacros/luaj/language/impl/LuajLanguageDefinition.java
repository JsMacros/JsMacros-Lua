package xyz.wagyourtail.jsmacros.luaj.language.impl;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.config.ScriptTrigger;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.language.BaseScriptContext;
import xyz.wagyourtail.jsmacros.core.language.BaseWrappedException;
import xyz.wagyourtail.jsmacros.core.language.EventContainer;
import xyz.wagyourtail.jsmacros.luaj.LuajExtension;
import xyz.wagyourtail.jsmacros.luaj.config.LuajConfig;

import java.io.File;

public class LuajLanguageDefinition extends BaseLanguage<Globals, LuajScriptContext> {

    public final Globals globalGlobals = JsePlatform.debugGlobals();

    public LuajLanguageDefinition(LuajExtension extension, Core runner) {
        super(extension, runner);
    }
    
    protected void execContext(EventContainer<LuajScriptContext> ctx, Executor e) throws Exception {
        Globals globals;
        
        if (runner.config.getOptions(LuajConfig.class).useGlobalContext) globals = globalGlobals;
        else globals = JsePlatform.debugGlobals();
        ctx.getCtx().setContext(globals);
    
        retrieveLibs(ctx.getCtx()).forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
        
        e.accept(globals);
    }
    
    private void setPerExecVar(BaseScriptContext<?> ctx, Globals globals, String name, LuaValue val) {
        boolean put = globals.rawget(name) instanceof PerContextLuaValue;
        PerContextLuaValue pclv = put ? (PerContextLuaValue) globals.rawget(name) : new PerContextLuaValue();
        pclv.addContext(ctx, val);
        if (!put) globals.rawset(name, pclv);
    }
    
    @Override
    protected void exec(EventContainer<LuajScriptContext> ctx, ScriptTrigger macro, BaseEvent event) throws Exception {
        execContext(ctx, (globals) -> {
            setPerExecVar(ctx.getCtx(), globals, "event", CoerceJavaToLua.coerce(event));
            setPerExecVar(ctx.getCtx(), globals, "file", CoerceJavaToLua.coerce(ctx.getCtx().getFile()));
            setPerExecVar(ctx.getCtx(), globals, "context", CoerceJavaToLua.coerce(ctx));
            
            retrieveOnceLibs().forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
            
            retrievePerExecLibs(ctx.getCtx()).forEach((name, lib) -> setPerExecVar(ctx.getCtx(), globals, name, CoerceJavaToLua.coerce(lib)));
            
            LuaClosure current = (LuaClosure) globals.loadfile(ctx.getCtx().getFile().getCanonicalPath());
            current.call();
        });
    }

    @Override
    protected void exec(EventContainer<LuajScriptContext> ctx, String lang, String script, BaseEvent event) throws Exception {
        execContext(ctx, (globals) -> {
            setPerExecVar(ctx.getCtx(), globals, "event", CoerceJavaToLua.coerce(event));
            setPerExecVar(ctx.getCtx(), globals, "file", CoerceJavaToLua.coerce(ctx.getCtx().getFile()));
            setPerExecVar(ctx.getCtx(), globals, "context", CoerceJavaToLua.coerce(ctx));

            LuaValue current = globals.load(script);
            current.call();
        });
    }
    
    @Override
    public LuajScriptContext createContext(BaseEvent event, File file) {
        return new LuajScriptContext(event, file);
    }
    
    private interface Executor {
        void accept(Globals globals) throws Exception;
    }
}
