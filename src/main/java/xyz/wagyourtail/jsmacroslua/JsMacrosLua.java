package xyz.wagyourtail.jsmacroslua;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import net.fabricmc.api.ClientModInitializer;
import xyz.wagyourtail.jsmacros.config.RawMacro;
import xyz.wagyourtail.jsmacros.runscript.RunScript;
import xyz.wagyourtail.jsmacros.runscript.functions.Functions;
import xyz.wagyourtail.jsmacroslua.functions.consumerFunctions;

public class JsMacrosLua implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {

        
        // register language
        RunScript.addLanguage(new RunScript.Language() {
            private Functions consumerFix = new consumerFunctions("consumer");
            
            @Override
            public void exec(RawMacro macro, File file, String event, Map<String, Object> args) throws Exception {
                Globals globals = JsePlatform.standardGlobals();
                for (Functions f : RunScript.standardLib) {
                    if (!f.excludeLanguages.contains(".js"))
                        globals.set(f.libName, CoerceJavaToLua.coerce(f));
                }
                globals.set(consumerFix.libName, CoerceJavaToLua.coerce(consumerFix));
                globals.set("event", CoerceJavaToLua.coerce(event));
                globals.set("args", CoerceJavaToLua.coerce(args));
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
                
                
                globals.load(script);
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
