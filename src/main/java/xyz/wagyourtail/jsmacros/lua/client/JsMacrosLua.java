package xyz.wagyourtail.jsmacros.lua.client;

import net.fabricmc.api.ClientModInitializer;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.client.JsMacros;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;
import xyz.wagyourtail.jsmacros.lua.library.impl.FConsumerLua;

public class JsMacrosLua implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        JsMacros.core.addLanguage(new LuaLanguageDefinition(".lua", JsMacros.core));
        JsMacros.core.sortLanguages();
        JsMacros.core.libraryRegistry.addLibrary(FConsumerLua.class);
        
        // pre-init
        Thread t = new Thread(() -> {
            try {
                Globals globals = JsePlatform.standardGlobals();
                globals.load("print(\"lua loaded\")").call();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        
        t.start();
    }

}
