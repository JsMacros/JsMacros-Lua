package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.config.ScriptTrigger;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.language.BaseWrappedException;
import xyz.wagyourtail.jsmacros.lua.luaj.ILuaError;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class LuaLanguageDefinition extends BaseLanguage {

    public LuaLanguageDefinition(String extension, Core runner) {
        super(extension, runner);
    }
    
    protected void execContext(Executor e) throws Exception {
        Globals globals = JsePlatform.standardGlobals();
        retrieveLibs(globals).forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
        
        e.accept(globals);
    }
    
    @Override
    public void exec(ScriptTrigger scriptTrigger, File file, BaseEvent baseEvent) throws Exception {
        execContext((globals) -> {
            globals.set("event", CoerceJavaToLua.coerce(baseEvent));
            globals.set("file", CoerceJavaToLua.coerce(file));
    
            globals.loadfile(file.getCanonicalPath()).call();
        });
    }
    
    @Override
    public void exec(String s, Map<String, Object> map, Path path) throws Exception {
        execContext((globals) -> {
            map.forEach((name, lib) -> globals.set(name, CoerceJavaToLua.coerce(lib)));
        
            globals.load(s).call();
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
    
    private interface Executor {
        void accept(Globals globals) throws Exception;
    }
}
