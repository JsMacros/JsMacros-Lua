package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.language.ScriptContext;

import java.util.Map;
import java.util.WeakHashMap;

public class PerContextLuaValue extends LuaValue {
    Map<ScriptContext<?>, LuaValue> instances = new WeakHashMap<>();
    
    
    public PerContextLuaValue() {
        super();
    }
    
    public void addContext(ScriptContext<?> ctx, LuaValue javaInstance) {
        instances.put(ctx, javaInstance);
    }
    
    @Override
    public int type() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).type();
    }
    
    @Override
    public String typename() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).typename();
    }
    
    @Override
    public LuaValue tonumber() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).tonumber();
    }
    
    @Override
    public LuaValue tostring() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).tostring();
    }
    
    @Override
    public LuaValue get(LuaValue key) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).get(key);
    }
    
    @Override
    public void set(LuaValue key, LuaValue value) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        instances.get(current).set(key, value);
    }
    
    @Override
    public Object checkuserdata() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).checkuserdata();
    }
    
    
    //rest doesn't matter for userData and is untested/unfinished/missing
    
    @Override
    public Varargs next(LuaValue index) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).next(index);
    }
    
    @Override
    public Varargs inext(LuaValue index) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).inext(index);
    }
    
    @Override
    public LuaValue call() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).call();
    }
    
    @Override
    public LuaValue call(LuaValue arg) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).call(arg);
    }
    
    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).call(arg1, arg2);
    }
    
    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).call(arg1, arg2, arg3);
    }
    
    @Override
    public LuaValue method(String name) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name);
    }
    
    @Override
    public LuaValue method(LuaValue name) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name);
    }
    
    @Override
    public LuaValue method(String name, LuaValue arg) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name, arg);
    }
    
    @Override
    public LuaValue method(LuaValue name, LuaValue arg) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name, arg);
    }
    
    @Override
    public LuaValue method(String name, LuaValue arg1, LuaValue arg2) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name, arg1, arg2);
    }
    
    @Override
    public LuaValue method(LuaValue name, LuaValue arg1, LuaValue arg2) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).method(name, arg1, arg2);
    }
    
    @Override
    public Varargs invoke() {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke();
    }
    
    @Override
    public Varargs invoke(Varargs args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke(args);
    }
    
    @Override
    public Varargs invoke(LuaValue arg, Varargs varargs) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke(arg, varargs);
    }
    
    @Override
    public Varargs invoke(LuaValue arg1, LuaValue arg2, Varargs varargs) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke(arg1, arg2, varargs);
    }
    
    @Override
    public Varargs invoke(LuaValue[] args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke(args);
    }
    
    @Override
    public Varargs invoke(LuaValue[] args, Varargs varargs) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invoke(args, varargs);
    }
    
    @Override
    public Varargs invokemethod(String name) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name);
    }
    
    @Override
    public Varargs invokemethod(String name, Varargs args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name, Varargs args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(String name, LuaValue[] args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name, LuaValue[] args) {
        ScriptContext<?> current = Core.instance.threadContext.get(Thread.currentThread());
        return instances.get(current).invokemethod(name, args);
    }
}