package xyz.wagyourtail.jsmacros.lua.library.impl;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import xyz.wagyourtail.jsmacros.client.JsMacros;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.MethodWrapper;
import xyz.wagyourtail.jsmacros.core.language.BaseLanguage;
import xyz.wagyourtail.jsmacros.core.language.ContextContainer;
import xyz.wagyourtail.jsmacros.core.library.IFWrapper;
import xyz.wagyourtail.jsmacros.core.library.Library;
import xyz.wagyourtail.jsmacros.core.library.PerLanguageLibrary;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaScriptContext;

import java.util.concurrent.Semaphore;

@Library(value = "JavaWrapper", languages = LuaLanguageDefinition.class)
public class FWrapper extends PerLanguageLibrary implements IFWrapper<LuaClosure> {
    
    public FWrapper(Class<? extends BaseLanguage<?>> language) {
        super(language);
    }
    
    @Override
    public <A, B, R> MethodWrapper<A, B, R> methodToJava(LuaClosure luaClosure) {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        currentContext.nonGCdMethodWrappers.incrementAndGet();
        return new LuaMethodWrapper<>(luaClosure, true, currentContext);
    }

    @Override
    public <A, B, R> MethodWrapper<A, B, R> methodToJavaAsync(LuaClosure luaClosure) {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        currentContext.nonGCdMethodWrappers.incrementAndGet();
        return new LuaMethodWrapper<>(luaClosure, false, currentContext);
    }
    
    @Override
    public void stop() {
        LuaScriptContext currentContext = (LuaScriptContext) JsMacros.core.threadContext.get(Thread.currentThread());
        currentContext.closeContext();
    }


    private static class LuaMethodWrapper<T, U, R> extends MethodWrapper<T, U, R> {
        private final LuaClosure fn;
        private final boolean await;
        private final LuaScriptContext ctx;

        LuaMethodWrapper(LuaClosure fn, boolean await, LuaScriptContext ctx) {
            this.fn = fn;
            this.await = await;
            this.ctx = ctx;
        }

        private void internal_accept(Runnable accepted, boolean await) {
            Throwable[] error = {null};
            Semaphore lock = new Semaphore(0);

            // if in the same lua context and not async...
            if (Core.instance.threadContext.get(Thread.currentThread()) == ctx && await) {
                accepted.run();
                return;
            }

            Thread th = new Thread(() -> {
                try {
                    Core.instance.threadContext.put(Thread.currentThread(), ctx);
                    accepted.run();
                } catch (Throwable ex) {
                    if (!await) {
                        Core.instance.profile.logError(ex);
                    }
                    error[0] = ex;
                } finally {
                    ContextContainer<?> cc = Core.instance.eventContexts.get(Thread.currentThread());
                    if (cc != null) cc.releaseLock();

                    lock.release();
                }
            });
            th.start();

            if (await) {
                try {
                    lock.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if (error[0] != null) throw new RuntimeException(error[0]);
            }
        }

        @Override
        public void accept(T t) {
            internal_accept(() -> fn.call(CoerceJavaToLua.coerce(t)), await);
        }

        @Override
        public void accept(T t, U u) {
            internal_accept(() -> fn.call(CoerceJavaToLua.coerce(t), CoerceJavaToLua.coerce(u)), await);
        }

        @Override
        public R apply(T t) {
            Object[] retval = {null};
            internal_accept(() -> retval[0] = CoerceLuaToJava.coerce(fn.call(CoerceJavaToLua.coerce(t)), Object.class), true);
            return (R) retval[0];
        }

        @Override
        public R apply(T t, U u) {
            Object[] retval = {null};
            internal_accept(() -> retval[0] = CoerceLuaToJava.coerce(fn.call(CoerceJavaToLua.coerce(t), CoerceJavaToLua.coerce(u)), Object.class), true);
            return (R) retval[0];
        }

        @Override
        public boolean test(T t) {
            boolean[] retval = {false};
            internal_accept(() -> retval[0] = fn.call(CoerceJavaToLua.coerce(t)).toboolean(), true);
            return retval[0];
        }

        @Override
        public boolean test(T t, U u) {
            boolean[] retval = {false};
            internal_accept(() -> retval[0] = fn.call(CoerceJavaToLua.coerce(t), CoerceJavaToLua.coerce(u)).toboolean(), true);
            return retval[0];
        }

        @Override
        public void run() {
            internal_accept(fn::call, await);
        }

        @Override
        public int compare(T o1, T o2) {
            int[] retval = {0};
            internal_accept(() -> retval[0] = fn.call(CoerceJavaToLua.coerce(o1), CoerceJavaToLua.coerce(o2)).toint(), true);
            return retval[0];
        }

        @Override
        public R get() {
            Object[] retval = {null};
            internal_accept(() -> retval[0] = CoerceLuaToJava.coerce(fn.call(), Object.class), true);
            return (R) retval[0];
        }

        @Override
        protected void finalize() throws Throwable {
            int val = ((LuaScriptContext) ctx).nonGCdMethodWrappers.decrementAndGet();
            if (val == 0) ctx.closeContext();
        }
    }
}
