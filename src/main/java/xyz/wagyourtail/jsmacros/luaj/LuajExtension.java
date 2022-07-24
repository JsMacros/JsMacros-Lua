package xyz.wagyourtail.jsmacros.luaj;

import com.google.common.collect.Sets;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.client.JsMacros;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.extensions.Extension;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.language.BaseWrappedException;
import xyz.wagyourtail.jsmacros.core.library.BaseLibrary;
import xyz.wagyourtail.jsmacros.luaj.config.LuajConfig;
import xyz.wagyourtail.jsmacros.luaj.language.impl.LuajLanguageDefinition;
import xyz.wagyourtail.jsmacros.luaj.library.impl.FWrapper;

import java.io.File;
import java.util.Set;

public class LuajExtension implements Extension {

    LuajLanguageDefinition languageDescription;

    @Override
    public void init() {
    
        try {
            Core.getInstance().config.addOptions("lua", LuajConfig.class);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    
        // pre-init
        Thread t = new Thread(() -> {
            try {
                Globals globals = JsePlatform.standardGlobals();
                globals.load("print(\"lua pre-loaded\")").call();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        
        t.start();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getLanguageImplName() {
        return "luaj";
    }

    @Override
    public ExtMatch extensionMatch(File file) {
        if (file.getName().endsWith(".lua")) {
            if (file.getName().contains(getLanguageImplName())) {
                return ExtMatch.MATCH_WITH_NAME;
            } else {
                return ExtMatch.MATCH;
            }
        }
        return ExtMatch.NOT_MATCH;
    }

    @Override
    public String defaultFileExtension() {
        return "lua";
    }

    @Override
    public BaseLanguage<?, ?> getLanguage(Core<?, ?> core) {
        if (languageDescription == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(LuajLanguageDefinition.class.getClassLoader());
            languageDescription = new LuajLanguageDefinition(this, core);
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        return languageDescription;
    }

    @Override
    public Set<Class<? extends BaseLibrary>> getLibraries() {
        return Sets.newHashSet(FWrapper.class);
    }

    @Override
    public BaseWrappedException<?> wrapException(Throwable ex) {
        if (ex instanceof LuaError) {
            File file = ((LuaError) ex).getFile();
            int line = ((LuaError) ex).getLine();
            String error = ((LuaError) ex).getErrorMessage();
            BaseWrappedException.SourceLocation loc = null;
            if (file != null) {
                loc = new BaseWrappedException.GuestLocation(file, -1, -1, line, -1);
            }
            Throwable cause = ex.getCause();
            BaseWrappedException<?> causewrap = null;
            if (cause != null) {
                causewrap = Core.getInstance().wrapException(cause);
            }
            return new BaseWrappedException<>(ex, error, loc, causewrap);
        }
        return null;
    }

    @Override
    public boolean isGuestObject(Object o) {
        return o instanceof LuaValue;
    }

}
