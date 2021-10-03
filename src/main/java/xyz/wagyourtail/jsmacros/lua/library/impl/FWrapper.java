package xyz.wagyourtail.jsmacros.lua.library.impl;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.MethodWrapper;
import xyz.wagyourtail.jsmacros.core.language.BaseScriptContext;
import xyz.wagyourtail.jsmacros.core.language.EventContainer;
import xyz.wagyourtail.jsmacros.core.library.IFWrapper;
import xyz.wagyourtail.jsmacros.core.library.Library;
import xyz.wagyourtail.jsmacros.core.library.PerExecLanguageLibrary;
import xyz.wagyourtail.jsmacros.lua.language.impl.LuaLanguageDefinition;

import java.util.concurrent.Semaphore;

@Library(value = "JavaWrapper", languages = LuaLanguageDefinition.class)
public class FWrapper extends PerExecLanguageLibrary<Globals> implements IFWrapper<LuaClosure> {

    public FWrapper(BaseScriptContext context, Class language) {
        super(context, language);
    }

    @Override
    public <A, B, R> MethodWrapper<A, B, R, BaseScriptContext<Globals>> methodToJava(LuaClosure luaClosure) {
        return new LuaMethodWrapper<>(luaClosure, true, ctx);
    }

    @Override
    public <A, B, R> MethodWrapper<A, B, R, BaseScriptContext<Globals>> methodToJavaAsync(LuaClosure luaClosure) {
        return new LuaMethodWrapper<>(luaClosure, false, ctx);
    }
    
    @Override
    public void stop() {
        ctx.closeContext();
    }


    private static class LuaMethodWrapper<T, U, R> extends MethodWrapper<T, U, R, BaseScriptContext<Globals>> {
        private final LuaClosure fn;
        private final boolean await;

        LuaMethodWrapper(LuaClosure fn, boolean await, BaseScriptContext<Globals> ctx) {
            super(ctx);
            this.fn = fn;
            this.await = await;
        }

        private void internal_accept(Runnable accepted, boolean await) {
            Throwable[] error = {null};
            Semaphore lock = new Semaphore(0);
            boolean joinedThread = Core.instance.profile.checkJoinedThreadStack();

            // if in the same lua context and not async...
            if (await) {
                if (ctx.getBoundThreads().contains(Thread.currentThread())) {
                    accepted.run();
                    return;
                }

                ctx.bindThread(Thread.currentThread());
            }

            Thread th = new Thread(() -> {
                ctx.bindThread(Thread.currentThread());
                try {
                    if (await && joinedThread) {
                        Core.instance.profile.joinedThreadStack.add(Thread.currentThread());
                    }
                    accepted.run();
                } catch (Throwable ex) {
                    if (!await) {
                        Core.instance.profile.logError(ex);
                    }
                    error[0] = ex;
                } finally {
                    ctx.unbindThread(Thread.currentThread());
                    Core.instance.profile.joinedThreadStack.remove(Thread.currentThread());

                    ctx.releaseBoundEventIfPresent(Thread.currentThread());

                    lock.release();
                }
            });
            th.start();

            if (await) {
                try {
                    lock.acquire();
                    if (error[0] != null) throw new RuntimeException(error[0]);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    ctx.unbindThread(Thread.currentThread());
                }
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
    }
}
