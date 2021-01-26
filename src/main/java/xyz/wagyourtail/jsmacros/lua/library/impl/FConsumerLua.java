package xyz.wagyourtail.jsmacros.lua.library.impl;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import xyz.wagyourtail.jsmacros.core.MethodWrapper;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.library.IFConsumer;
import xyz.wagyourtail.jsmacros.core.library.Library;
import xyz.wagyourtail.jsmacros.core.library.PerLanguageLibrary;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;

@Library(value = "consumer", languages = LuaLanguageDefinition.class)
public class FConsumerLua extends PerLanguageLibrary implements IFConsumer<LuaClosure, LuaClosure, LuaClosure> {
    
    public FConsumerLua(Class<? extends BaseLanguage> language) {
        super(language);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> toConsumer(LuaClosure luaClosure) {
        return autoWrap(luaClosure);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> toBiConsumer(LuaClosure luaClosure) {
        return autoWrap(luaClosure);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> toAsyncConsumer(LuaClosure luaClosure) {
        return autoWrapAsync(luaClosure);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> toAsyncBiConsumer(LuaClosure luaClosure) {
        return autoWrapAsync(luaClosure);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> autoWrap(LuaClosure luaClosure) {
        return new MethodWrapper<A, B, R>() {
            @Override
            public void accept(A a) {
                luaClosure.call(CoerceJavaToLua.coerce(a));
            }
    
            @Override
            public void accept(A a, B b) {
                luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b));
            }
    
            @Override
            public R apply(A a) {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a)), Object.class);
            }
    
            @Override
            public R apply(A a, B b) {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)), Object.class);
            }
    
            @Override
            public boolean test(A a) {
                return luaClosure.call(CoerceJavaToLua.coerce(a)).toboolean();
            }
    
            @Override
            public boolean test(A a, B b) {
                return luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)).toboolean();
            }
    
            @Override
            public void run() {
                luaClosure.call();
            }
    
            @Override
            public int compare(A o1, A o2) {
                return luaClosure.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2)).toint();
            }
    
            @Override
            public R get() {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(), Object.class);
            }
        };
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> autoWrapAsync(LuaClosure luaClosure) {
        return new MethodWrapper<A, B, R>() {
            @Override
            public void accept(A a) {
                new Thread(() -> {
                    luaClosure.call(CoerceJavaToLua.coerce(a));
                }).start();
            }
        
            @Override
            public void accept(A a, B b) {
                new Thread(() -> {
                    luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b));
                }).start();
            }
        
            @Override
            public R apply(A a) {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a)), Object.class);
            }
        
            @Override
            public R apply(A a, B b) {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)), Object.class);
            }
        
            @Override
            public boolean test(A a) {
                return luaClosure.call(CoerceJavaToLua.coerce(a)).toboolean();
            }
        
            @Override
            public boolean test(A a, B b) {
                return luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)).toboolean();
            }
        
            @Override
            public void run() {
                new Thread(() -> {
                    luaClosure.call();
                }).start();
            }
        
            @Override
            public int compare(A o1, A o2) {
                return luaClosure.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2)).toint();
            }
        
            @Override
            public R get() {
                return (R) CoerceLuaToJava.coerce(luaClosure.call(), Object.class);
            }
        };
    }
    
}
