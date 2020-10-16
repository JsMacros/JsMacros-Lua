package xyz.wagyourtail.jsmacroslua;

import net.fabricmc.api.ClientModInitializer;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import xyz.wagyourtail.jsmacros.api.sharedinterfaces.IEvent;
import xyz.wagyourtail.jsmacros.config.RawMacro;
import xyz.wagyourtail.jsmacros.extensionbase.Functions;
import xyz.wagyourtail.jsmacros.extensionbase.ILanguage;
import xyz.wagyourtail.jsmacros.runscript.RunScript;
import xyz.wagyourtail.jsmacroslua.functions.FConsumerLua;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class JsMacrosLua implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {

        
        // register language
        RunScript.addLanguage(new ILanguage() {
            private final Functions consumerFix = new FConsumerLua("consumer");
            
            @Override
            public void exec(RawMacro macro, File file, IEvent event) throws Exception {
                Globals globals = JsePlatform.standardGlobals();
                for (Functions f : RunScript.standardLib) {
                    if (!f.excludeLanguages.contains(".js"))
                        globals.set(f.libName, CoerceJavaToLua.coerce(f));
                }
                globals.set(consumerFix.libName, CoerceJavaToLua.coerce(consumerFix));
                globals.set("event", CoerceJavaToLua.coerce(event));
                globals.set("file", CoerceJavaToLua.coerce(file));
                
                globals.loadfile(file.getCanonicalPath()).call();
            }

            
            @Override
            public void exec(String script, Map<String, Object> global, Path path) throws Exception {
                Globals globals = JsePlatform.standardGlobals();
                for (Functions f : RunScript.standardLib) {
                    if (!f.excludeLanguages.contains(".js"))
                        globals.set(f.libName, CoerceJavaToLua.coerce(f));
                }
                globals.set(consumerFix.libName, CoerceJavaToLua.coerce(consumerFix));
                
                if (global != null) for (Map.Entry<String, Object> e : global.entrySet()) {
                    globals.set(e.getKey(), CoerceJavaToLua.coerce(e.getValue()));
                }
                
                
                globals.load(script).call();
            }

            @Override
            public String extension() {
                return ".lua";
            }


        });

        RunScript.sortLanguages();
        
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
