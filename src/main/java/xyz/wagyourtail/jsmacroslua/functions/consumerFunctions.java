package xyz.wagyourtail.jsmacroslua.functions;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import xyz.wagyourtail.jsmacros.runscript.functions.Functions;

public class consumerFunctions extends Functions {
    
    
    public consumerFunctions(String libName) {
        super(libName);
    }

    public consumerFunctions(String libName, List<String> exclude) {
        super(libName, exclude);
    }
    
    public Consumer<Object> toConsumer(LuaClosure c) {
        return new Consumer<Object>() {
            @Override
            public void accept(Object arg0) {
                c.call(CoerceJavaToLua.coerce(arg0));
            }
        };
    }
    
    public BiConsumer<Object, Object> toBiConsumer(LuaClosure c) {
        return new BiConsumer<Object, Object>() {
            @Override
            public void accept(Object arg0, Object arg1) {
                c.call(CoerceJavaToLua.coerce(arg0), CoerceJavaToLua.coerce(arg1));
            }
        };
    }
}
