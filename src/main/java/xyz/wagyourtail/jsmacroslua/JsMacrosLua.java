package xyz.wagyourtail.jsmacroslua;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import net.fabricmc.api.ClientModInitializer;
import xyz.wagyourtail.jsmacros.config.RawMacro;
import xyz.wagyourtail.jsmacros.runscript.RunScript;
import xyz.wagyourtail.jsmacros.runscript.functions.Functions;

public class JsMacrosLua implements ClientModInitializer {
    public static Consumer<String> log = (s) -> {System.out.println(s);};

    @Override
    public void onInitializeClient() {

        
        // register language
        RunScript.addLanguage(new RunScript.Language() {

            @Override
            public void exec(RawMacro macro, File file, String event, Map<String, Object> args) throws Exception {
                Globals globals = JsePlatform.standardGlobals();
                for (Functions f : RunScript.standardLib) {
                    if (!f.excludeLanguages.contains(".js"))
                        globals.set(f.libName, CoerceJavaToLua.coerce(f));
                }
                
                globals.loadfile(file.getCanonicalPath()).call();
                
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
