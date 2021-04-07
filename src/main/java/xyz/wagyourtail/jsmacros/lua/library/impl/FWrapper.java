package xyz.wagyourtail.jsmacros.lua.library.impl;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import xyz.wagyourtail.jsmacros.client.JsMacros;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.MethodWrapper;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.library.IFWrapper;
import xyz.wagyourtail.jsmacros.core.library.Library;
import xyz.wagyourtail.jsmacros.core.library.PerLanguageLibrary;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaScriptContext;

@Library(value = "JavaWrapper", languages = LuaLanguageDefinition.class)
public class FWrapper extends PerLanguageLibrary implements IFWrapper<LuaClosure> {
    
    public FWrapper(Class<? extends BaseLanguage<?>> language) {
        super(language);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> methodToJava(LuaClosure luaClosure) {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        return new MethodWrapper<A, B, R>() {
            @Override
            public void accept(A a) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                luaClosure.call(CoerceJavaToLua.coerce(a));
                Core.instance.threadContext.remove(Thread.currentThread());
            }
    
            @Override
            public void accept(A a, B b) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b));
                Core.instance.threadContext.remove(Thread.currentThread());
            }
    
            @Override
            public R apply(A a) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a)), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public R apply(A a, B b) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public boolean test(A a) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(a)).toboolean();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public boolean test(A a, B b) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)).toboolean();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public void run() {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    luaClosure.call();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public int compare(A o1, A o2) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2)).toint();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
    
            @Override
            public R get() {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        };
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> methodToJavaAsync(LuaClosure luaClosure) {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        return new MethodWrapper<A, B, R>() {
            @Override
            public void accept(A a) {
                new Thread(() -> {
                    synchronized (currentContext) {
                        if (currentContext.closed) throw new RuntimeException("Context Closed");
                        Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                    }
                    luaClosure.call(CoerceJavaToLua.coerce(a));
                    Core.instance.threadContext.remove(Thread.currentThread());
                }).start();
            }
        
            @Override
            public void accept(A a, B b) {
                new Thread(() -> {
                    synchronized (currentContext) {
                        if (currentContext.closed) throw new RuntimeException("Context Closed");
                        Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                    }
                    luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b));
                    Core.instance.threadContext.remove(Thread.currentThread());
                }).start();
            }
        
            @Override
            public R apply(A a) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a)), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        
            @Override
            public R apply(A a, B b) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        
            @Override
            public boolean test(A a) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(a)).toboolean();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        
            @Override
            public boolean test(A a, B b) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(a), CoerceJavaToLua.coerce(b)).toboolean();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        
            @Override
            public void run() {
                new Thread(() -> {
                    synchronized (currentContext) {
                        if (currentContext.closed) throw new RuntimeException("Context Closed");
                        Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                    }
                    luaClosure.call();
                    Core.instance.threadContext.remove(Thread.currentThread());
                }).start();
            }
        
            @Override
            public int compare(A o1, A o2) {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return luaClosure.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2)).toint();
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        
            @Override
            public R get() {
                synchronized (currentContext) {
                    if (currentContext.closed) throw new RuntimeException("Context Closed");
                    Core.instance.threadContext.put(Thread.currentThread(), currentContext);
                }
                try {
                    return (R) CoerceLuaToJava.coerce(luaClosure.call(), Object.class);
                } finally {
                    Core.instance.threadContext.remove(Thread.currentThread());
                }
            }
        };
    }
    
    @Override
    public void stop() {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        currentContext.closeContext();
    }
    
}
