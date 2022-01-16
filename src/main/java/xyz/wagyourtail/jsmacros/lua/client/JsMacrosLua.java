package xyz.wagyourtail.jsmacros.lua.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.client.JsMacros;
import xyz.wagyourtail.jsmacros.lua.config.LuaConfig;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;
import xyz.wagyourtail.jsmacros.lua.library.impl.FWrapper;

public class JsMacrosLua implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        JsMacros.core.addLanguage(new LuaLanguageDefinition(".lua", JsMacros.core));
        JsMacros.core.libraryRegistry.addLibrary(FWrapper.class);
    
        try {
            JsMacros.core.config.addOptions("lua", LuaConfig.class);
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

}
