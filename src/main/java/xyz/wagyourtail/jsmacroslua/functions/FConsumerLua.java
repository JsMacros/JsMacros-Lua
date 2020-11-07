package xyz.wagyourtail.jsmacroslua.functions;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import xyz.wagyourtail.jsmacros.extensionbase.Functions;
import xyz.wagyourtail.jsmacros.extensionbase.IFConsumer;
import xyz.wagyourtail.jsmacros.extensionbase.MethodWrapper;

import java.util.List;

public class FConsumerLua extends Functions implements IFConsumer<LuaClosure, LuaClosure, LuaClosure> {
    
    
    public FConsumerLua(String libName) {
        super(libName);
    }

    public FConsumerLua(String libName, List<String> exclude) {
        super(libName, exclude);
    }
    
    @Override
    public MethodWrapper<Object, Object, Object> autoWrap(LuaClosure c) {
        return new MethodWrapper<Object, Object, Object>() {
    
            @Override
            public Object get() {
                return CoerceLuaToJava.coerce(c.call(), Object.class);
            }
    
            @Override
            public boolean test(Object o) {
                return c.call(CoerceJavaToLua.coerce(o)).toboolean();
            }
    
            @Override
            public Object apply(Object o) {
                return CoerceLuaToJava.coerce(c.call(CoerceJavaToLua.coerce(o)), Object.class);
            }
    
            @Override
            public boolean test(Object o, Object o2) {
                return c.call(CoerceJavaToLua.coerce(o), CoerceJavaToLua.coerce(o2)).toboolean();
            }
    
            @Override
            public Object apply(Object o, Object o2) {
                return CoerceLuaToJava.coerce(c.call(CoerceJavaToLua.coerce(o), CoerceJavaToLua.coerce(o2)), Object.class);
            }
    
            @Override
            public int compare(Object o1, Object o2) {
                LuaValue val = c.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2));
                if (val.isnumber()) {
                    return val.toint();
                }
                throw new RuntimeException("could not convert lua value to integer");
            }
    
            @Override
            public void run() {
                c.call();
            }
    
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
    public MethodWrapper<Object, Object, Object> autoWrapAsync(LuaClosure c) {
        return new MethodWrapper<Object, Object, Object>() {
    
            @Override
            public Object get() {
                return CoerceLuaToJava.coerce(c.call(), Object.class);
            }
    
            @Override
            public boolean test(Object o) {
                return c.call(CoerceJavaToLua.coerce(o)).toboolean();
            }
    
            @Override
            public Object apply(Object o) {
                return CoerceLuaToJava.coerce(c.call(CoerceJavaToLua.coerce(o)), Object.class);
            }
    
            @Override
            public boolean test(Object o, Object o2) {
                return c.call(CoerceJavaToLua.coerce(o), CoerceJavaToLua.coerce(o2)).toboolean();
            }
    
            @Override
            public Object apply(Object o, Object o2) {
                return CoerceLuaToJava.coerce(c.call(CoerceJavaToLua.coerce(o), CoerceJavaToLua.coerce(o2)), Object.class);
            }
    
            @Override
            public int compare(Object o1, Object o2) {
                LuaValue val = c.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2));
                if (val.isnumber()) {
                    return val.toint();
                }
                throw new RuntimeException("could not convert lua value to integer");
            }
    
            @Override
            public void run() {
                Thread t = new Thread(() -> {
                    c.call();
                });
                t.start();
            }
    
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
    public MethodWrapper<Object, Object, Object> toAsyncBiConsumer(LuaClosure arg0) {
        return autoWrapAsync(arg0);
    }

    @Override
    public MethodWrapper<Object, Object, Object> toAsyncConsumer(LuaClosure arg0) {
        return autoWrapAsync(arg0);
    }

    @Override
    public MethodWrapper<Object, Object, Object> toBiConsumer(LuaClosure arg0) {
        return autoWrap(arg0);
    }

    @Override
    public MethodWrapper<Object, Object, Object> toConsumer(LuaClosure arg0) {
        return autoWrap(arg0);
    }
}
