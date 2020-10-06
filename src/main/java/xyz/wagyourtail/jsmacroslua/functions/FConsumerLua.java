package xyz.wagyourtail.jsmacroslua.functions;

import java.util.List;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import xyz.wagyourtail.jsmacros.extensionbase.Functions;
import xyz.wagyourtail.jsmacros.extensionbase.IFConsumer;
import xyz.wagyourtail.jsmacros.extensionbase.MethodWrapper;

public class FConsumerLua extends Functions implements IFConsumer<LuaClosure, LuaClosure, LuaClosure> {
    
    
    public FConsumerLua(String libName) {
        super(libName);
    }

    public FConsumerLua(String libName, List<String> exclude) {
        super(libName, exclude);
    }
    
    @Override
    public MethodWrapper<Object, Object> autoWrap(LuaClosure c) {
        return new MethodWrapper<Object, Object>() {

            @Override
            public void accept(Object arg0) {
                c.call(CoerceJavaToLua.coerce(arg0));
            }

            @Override
            public void accept(Object arg0, Object arg1) {
                c.call(CoerceJavaToLua.coerce(arg0), CoerceJavaToLua.coerce(arg1));
            }
            
        };
    }
    
    @Override
    public MethodWrapper<Object, Object> autoWrapAsync(LuaClosure c) {
        return new MethodWrapper<Object, Object>() {
            
            @Override
            public void accept(Object arg0) {
                Thread t = new Thread(() -> {
                    c.call(CoerceJavaToLua.coerce(arg0));
                });
                t.start();
            }

            @Override
            public void accept(Object arg0, Object arg1) {
                Thread t = new Thread(() -> {
                    c.call(CoerceJavaToLua.coerce(arg0), CoerceJavaToLua.coerce(arg1));
                });
                t.start();
            }
            
        };
    }

    @Override
    public MethodWrapper<Object, Object> toAsyncBiConsumer(LuaClosure arg0) {
        return autoWrapAsync(arg0);
    }

    @Override
    public MethodWrapper<Object, Object> toAsyncConsumer(LuaClosure arg0) {
        return autoWrapAsync(arg0);
    }

    @Override
    public MethodWrapper<Object, Object> toBiConsumer(LuaClosure arg0) {
        return autoWrap(arg0);
    }

    @Override
    public MethodWrapper<Object, Object> toConsumer(LuaClosure arg0) {
        return autoWrap(arg0);
    }
}
