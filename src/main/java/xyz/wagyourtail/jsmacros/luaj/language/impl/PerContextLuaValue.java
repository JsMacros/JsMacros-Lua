package xyz.wagyourtail.jsmacros.luaj.language.impl;

import org.luaj.vm2.*;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.language.BaseScriptContext;

import java.util.Map;
import java.util.WeakHashMap;

public class PerContextLuaValue extends LuaValue {
    Map<BaseScriptContext<?>, LuaValue> instances = new WeakHashMap<>();
    
    public PerContextLuaValue() {
        super();
    }
    
    public void addContext(BaseScriptContext<?> ctx, LuaValue javaInstance) {
        instances.put(ctx, javaInstance);
    }

    private BaseScriptContext<?> getCtx() {
        return Core.getInstance().getContexts().stream().filter(e -> e.getBoundThreads().contains(Thread.currentThread())).findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean isboolean() {
        return  instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isboolean();
    }
    
    @Override
    public boolean isclosure() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isclosure();
    }
    
    @Override
    public boolean isfunction() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isfunction();
    }
    
    @Override
    public boolean isint() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isint();
    }
    
    @Override
    public boolean isinttype() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isinttype();
    }
    
    @Override
    public boolean islong() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).islong();
    }
    
    @Override
    public boolean isnil() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isnil();
    }
    
    @Override
    public boolean isnumber() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isnumber();
    }
    
    @Override
    public boolean isstring() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isstring();
    }
    
    @Override
    public boolean isthread() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isthread();
    }
    
    @Override
    public boolean istable() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).istable();
    }
    
    @Override
    public boolean isuserdata() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isuserdata();
    }
    
    @Override
    public boolean isuserdata(Class c) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isuserdata(c);
    }
    
    @Override
    public boolean toboolean() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toboolean();
    }
    
    @Override
    public byte tobyte() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tobyte();
    }
    
    @Override
    public char tochar() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tochar();
    }
    
    @Override
    public double todouble() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).todouble();
    }
    
    @Override
    public float tofloat() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tofloat();
    }
    
    @Override
    public int toint() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toint();
    }
    
    @Override
    public long tolong() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tolong();
    }
    
    @Override
    public short toshort() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toshort();
    }
    
    @Override
    public String tojstring() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tojstring();
    }
    
    @Override
    public Object touserdata() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).touserdata();
    }
    
    @Override
    public Object touserdata(Class c) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).touserdata(c);
    }
    
    @Override
    public String toString() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toString();
    }
    
    @Override
    public LuaValue tonumber() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tonumber();
    }
    
    @Override
    public LuaValue tostring() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tostring();
    }
    
    @Override
    public boolean optboolean(boolean defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optboolean(defval);
    }
    
    @Override
    public LuaClosure optclosure(LuaClosure defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optclosure(defval);
    }
    
    @Override
    public double optdouble(double defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optdouble(defval);
    }
    
    @Override
    public LuaFunction optfunction(LuaFunction defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optfunction(defval);
    }
    
    @Override
    public int optint(int defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optint(defval);
    }
    
    @Override
    public LuaInteger optinteger(LuaInteger defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optinteger(defval);
    }
    
    @Override
    public long optlong(long defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optlong(defval);
    }
    
    @Override
    public LuaNumber optnumber(LuaNumber defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optnumber(defval);
    }
    
    @Override
    public String optjstring(String defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optjstring(defval);
    }
    
    @Override
    public LuaString optstring(LuaString defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optstring(defval);
    }
    
    @Override
    public LuaTable opttable(LuaTable defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).opttable(defval);
    }
    
    @Override
    public LuaThread optthread(LuaThread defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optthread(defval);
    }
    
    @Override
    public Object optuserdata(Object defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optuserdata(defval);
    }
    
    @Override
    public Object optuserdata(Class c, Object defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optuserdata(c, defval);
    }
    
    @Override
    public LuaValue optvalue(LuaValue defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optvalue(defval);
    }
    
    @Override
    public boolean checkboolean() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkboolean();
    }
    
    @Override
    public LuaClosure checkclosure() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkclosure();
    }
    
    @Override
    public double checkdouble() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkdouble();
    }
    
    @Override
    public LuaFunction checkfunction() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkfunction();
    }
    
    @Override
    public Globals checkglobals() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkglobals();
    }
    
    @Override
    public int checkint() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkint();
    }
    
    @Override
    public LuaInteger checkinteger() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkinteger();
    }
    
    @Override
    public long checklong() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checklong();
    }
    
    @Override
    public LuaNumber checknumber() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checknumber();
    }
    
    @Override
    public LuaNumber checknumber(String msg) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checknumber(msg);
    }
    
    @Override
    public String checkjstring() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkjstring();
    }
    
    @Override
    public LuaString checkstring() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkstring();
    }
    
    @Override
    public LuaTable checktable() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checktable();
    }
    
    @Override
    public LuaThread checkthread() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkthread();
    }
    
    @Override
    public Object checkuserdata() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkuserdata();
    }
    
    @Override
    public Object checkuserdata(Class c) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkuserdata(c);
    }
    
    @Override
    public LuaValue checknotnil() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checknotnil();
    }
    
    @Override
    public boolean isvalidkey() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isvalidkey();
    }
    
    @Override
    public LuaValue get(LuaValue key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).get(key);
    }
    
    @Override
    public LuaValue get(int key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).get(key);
    }
    
    @Override
    public LuaValue get(String key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).get(key);
    }
    
    @Override
    public void set(LuaValue key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(int key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(int key, String value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(String key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(String key, double value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(String key, int value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public void set(String key, String value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).set(key, value);
    }
    
    @Override
    public LuaValue rawget(LuaValue key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawget(key);
    }
    
    @Override
    public LuaValue rawget(int key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawget(key);
    }
    
    @Override
    public LuaValue rawget(String key) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawget(key);
    }
    
    @Override
    public void rawset(LuaValue key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(int key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(int key, String value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(String key, LuaValue value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(String key, double value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(String key, int value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawset(String key, String value) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawset(key, value);
    }
    
    @Override
    public void rawsetlist(int key0, Varargs values) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawsetlist(key0, values);
    }
    
    @Override
    public void presize(int i) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).presize(i);
    }
    
    @Override
    public Varargs next(LuaValue index) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).next(index);
    }
    
    @Override
    public Varargs inext(LuaValue index) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).inext(index);
    }
    
    @Override
    public LuaValue load(LuaValue library) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).load(library);
    }
    
    @Override
    public LuaValue arg(int index) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).arg(index);
    }
    
    @Override
    public int narg() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).narg();
    }
    
    @Override
    public LuaValue arg1() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).arg1();
    }
    
    @Override
    public LuaValue getmetatable() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).getmetatable();
    }
    
    @Override
    public LuaValue setmetatable(LuaValue metatable) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).setmetatable(metatable);
    }
    
    @Override
    public LuaValue call() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).call();
    }
    
    @Override
    public LuaValue call(LuaValue arg) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).call(arg);
    }
    
    @Override
    public LuaValue call(String arg) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).call(arg);
    }
    
    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).call(arg1, arg2);
    }
    
    @Override
    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).call(arg1, arg2, arg3);
    }
    
    @Override
    public LuaValue method(String name) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name);
    }
    
    @Override
    public LuaValue method(LuaValue name) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name);
    }
    
    @Override
    public LuaValue method(String name, LuaValue arg) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name, arg);
    }
    
    @Override
    public LuaValue method(LuaValue name, LuaValue arg) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name, arg);
    }
    
    @Override
    public LuaValue method(String name, LuaValue arg1, LuaValue arg2) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name, arg1, arg2);
    }
    
    @Override
    public LuaValue method(LuaValue name, LuaValue arg1, LuaValue arg2) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).method(name, arg1, arg2);
    }
    
    @Override
    public Varargs invoke() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke();
    }
    
    @Override
    public Varargs invoke(Varargs args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke(args);
    }
    
    @Override
    public Varargs invoke(LuaValue arg, Varargs varargs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke(arg, varargs);
    }
    
    @Override
    public Varargs invoke(LuaValue arg1, LuaValue arg2, Varargs varargs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke(arg1, arg2, varargs);
    }
    
    @Override
    public Varargs invoke(LuaValue[] args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke(args);
    }
    
    @Override
    public Varargs invoke(LuaValue[] args, Varargs varargs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invoke(args, varargs);
    }
    
    @Override
    public Varargs invokemethod(String name) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name);
    }
    
    @Override
    public Varargs invokemethod(String name, Varargs args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name, Varargs args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(String name, LuaValue[] args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name, args);
    }
    
    @Override
    public Varargs invokemethod(LuaValue name, LuaValue[] args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).invokemethod(name, args);
    }
    
    @Override
    public LuaValue not() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).not();
    }
    
    @Override
    public LuaValue neg() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).neg();
    }
    
    @Override
    public LuaValue len() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).len();
    }
    
    @Override
    public int length() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).length();
    }
    
    @Override
    public int rawlen() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).rawlen();
    }
    
    @Override
    public boolean equals(Object obj) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).equals(obj);
    }
    
    @Override
    public LuaValue eq(LuaValue val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).eq(val);
    }
    
    @Override
    public boolean eq_b(LuaValue val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).eq_b(val);
    }
    
    @Override
    public LuaValue neq(LuaValue val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).neq(val);
    }
    
    @Override
    public boolean neq_b(LuaValue val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).neq_b(val);
    }
    
    @Override
    public boolean raweq(LuaValue val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).raweq(val);
    }
    
    @Override
    public boolean raweq(LuaUserdata val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).raweq(val);
    }
    
    @Override
    public boolean raweq(LuaString val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).raweq(val);
    }
    
    @Override
    public boolean raweq(double val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).raweq(val);
    }
    
    @Override
    public boolean raweq(int val) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).raweq(val);
    }
    
    @Override
    public LuaValue add(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).add(rhs);
    }
    
    @Override
    public LuaValue add(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).add(rhs);
    }
    
    @Override
    public LuaValue add(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).add(rhs);
    }
    
    @Override
    public LuaValue sub(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).sub(rhs);
    }
    
    @Override
    public LuaValue sub(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).sub(rhs);
    }
    
    @Override
    public LuaValue sub(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).sub(rhs);
    }
    
    @Override
    public LuaValue subFrom(double lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).subFrom(lhs);
    }
    
    @Override
    public LuaValue subFrom(int lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).subFrom(lhs);
    }
    
    @Override
    public LuaValue mul(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mul(rhs);
    }
    
    @Override
    public LuaValue mul(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mul(rhs);
    }
    
    @Override
    public LuaValue mul(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mul(rhs);
    }
    
    @Override
    public LuaValue pow(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).pow(rhs);
    }
    
    @Override
    public LuaValue pow(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).pow(rhs);
    }
    
    @Override
    public LuaValue pow(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).pow(rhs);
    }
    
    @Override
    public LuaValue powWith(double lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).powWith(lhs);
    }
    
    @Override
    public LuaValue powWith(int lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).powWith(lhs);
    }
    
    @Override
    public LuaValue div(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).div(rhs);
    }
    
    @Override
    public LuaValue div(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).div(rhs);
    }
    
    @Override
    public LuaValue div(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).div(rhs);
    }
    
    @Override
    public LuaValue divInto(double lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).divInto(lhs);
    }
    
    @Override
    public LuaValue mod(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mod(rhs);
    }
    
    @Override
    public LuaValue mod(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mod(rhs);
    }
    
    @Override
    public LuaValue mod(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).mod(rhs);
    }
    
    @Override
    public LuaValue modFrom(double lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).modFrom(lhs);
    }
    
    @Override
    public LuaValue lt(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt(rhs);
    }
    
    @Override
    public LuaValue lt(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt(rhs);
    }
    
    @Override
    public LuaValue lt(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt(rhs);
    }
    
    @Override
    public boolean lt_b(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt_b(rhs);
    }
    
    @Override
    public boolean lt_b(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt_b(rhs);
    }
    
    @Override
    public boolean lt_b(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lt_b(rhs);
    }
    
    @Override
    public LuaValue lteq(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq(rhs);
    }
    
    @Override
    public LuaValue lteq(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq(rhs);
    }
    
    @Override
    public LuaValue lteq(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq(rhs);
    }
    
    @Override
    public boolean lteq_b(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq_b(rhs);
    }
    
    @Override
    public boolean lteq_b(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq_b(rhs);
    }
    
    @Override
    public boolean lteq_b(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).lteq_b(rhs);
    }
    
    @Override
    public LuaValue gt(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt(rhs);
    }
    
    @Override
    public LuaValue gt(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt(rhs);
    }
    
    @Override
    public LuaValue gt(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt(rhs);
    }
    
    @Override
    public boolean gt_b(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt_b(rhs);
    }
    
    @Override
    public boolean gt_b(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt_b(rhs);
    }
    
    @Override
    public boolean gt_b(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gt_b(rhs);
    }
    
    @Override
    public LuaValue gteq(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq(rhs);
    }
    
    @Override
    public LuaValue gteq(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq(rhs);
    }
    
    @Override
    public LuaValue gteq(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq(rhs);
    }
    
    @Override
    public boolean gteq_b(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq_b(rhs);
    }
    
    @Override
    public boolean gteq_b(int rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq_b(rhs);
    }
    
    @Override
    public boolean gteq_b(double rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).gteq_b(rhs);
    }
    
    @Override
    public LuaValue comparemt(LuaValue tag, LuaValue op1) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).comparemt(tag, op1);
    }
    
    @Override
    public int strcmp(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).strcmp(rhs);
    }
    
    @Override
    public int strcmp(LuaString rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).strcmp(rhs);
    }
    
    @Override
    public LuaValue concat(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concat(rhs);
    }
    
    @Override
    public LuaValue concatTo(LuaValue lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concatTo(lhs);
    }
    
    @Override
    public LuaValue concatTo(LuaNumber lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concatTo(lhs);
    }
    
    @Override
    public LuaValue concatTo(LuaString lhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concatTo(lhs);
    }
    
    @Override
    public Buffer buffer() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).buffer();
    }
    
    @Override
    public Buffer concat(Buffer rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concat(rhs);
    }
    
    @Override
    public LuaValue concatmt(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).concatmt(rhs);
    }
    
    @Override
    public LuaValue and(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).and(rhs);
    }
    
    @Override
    public LuaValue or(LuaValue rhs) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).or(rhs);
    }
    
    @Override
    public boolean testfor_b(LuaValue limit, LuaValue step) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).testfor_b(limit, step);
    }
    
    @Override
    public LuaString strvalue() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).strvalue();
    }
    
    @Override
    public LuaValue strongvalue() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).strongvalue();
    }
    
    @Override
    public LuaValue metatag(LuaValue tag) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).metatag(tag);
    }

    @Override
    public Varargs onInvoke(Varargs args) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).onInvoke(args);
    }
    
    @Override
    public void initupvalue1(LuaValue env) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).initupvalue1(env);
    }
    
    @Override
    public Varargs subargs(int start) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).subargs(start);
    }
    
    @Override
    public Varargs eval() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).eval();
    }
    
    @Override
    public boolean isTailcall() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isTailcall();
    }
    
    @Override
    public int type(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).type(i);
    }
    
    @Override
    public boolean isnil(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isnil(i);
    }
    
    @Override
    public boolean isfunction(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isfunction(i);
    }
    
    @Override
    public boolean isnumber(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isnumber(i);
    }
    
    @Override
    public boolean isstring(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isstring(i);
    }
    
    @Override
    public boolean istable(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).istable(i);
    }
    
    @Override
    public boolean isthread(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isthread(i);
    }
    
    @Override
    public boolean isuserdata(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isuserdata(i);
    }
    
    @Override
    public boolean isvalue(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isvalue(i);
    }
    
    @Override
    public boolean optboolean(int i, boolean defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optboolean(i, defval);
    }
    
    @Override
    public LuaClosure optclosure(int i, LuaClosure defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optclosure(i, defval);
    }
    
    @Override
    public double optdouble(int i, double defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optdouble(i, defval);
    }
    
    @Override
    public LuaFunction optfunction(int i, LuaFunction defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optfunction(i, defval);
    }
    
    @Override
    public int optint(int i, int defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optint(i, defval);
    }
    
    @Override
    public LuaInteger optinteger(int i, LuaInteger defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optinteger(i, defval);
    }
    
    @Override
    public long optlong(int i, long defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optlong(i, defval);
    }
    
    @Override
    public LuaNumber optnumber(int i, LuaNumber defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optnumber(i, defval);
    }
    
    @Override
    public String optjstring(int i, String defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optjstring(i, defval);
    }
    
    @Override
    public LuaString optstring(int i, LuaString defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optstring(i, defval);
    }
    
    @Override
    public LuaTable opttable(int i, LuaTable defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).opttable(i, defval);
    }
    
    @Override
    public LuaThread optthread(int i, LuaThread defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optthread(i, defval);
    }
    
    @Override
    public Object optuserdata(int i, Object defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optuserdata(i, defval);
    }
    
    @Override
    public Object optuserdata(int i, Class c, Object defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optuserdata(i, c, defval);
    }
    
    @Override
    public LuaValue optvalue(int i, LuaValue defval) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).optvalue(i, defval);
    }
    
    @Override
    public boolean checkboolean(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkboolean(i);
    }
    
    @Override
    public LuaClosure checkclosure(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkclosure(i);
    }
    
    @Override
    public double checkdouble(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkdouble(i);
    }
    
    @Override
    public LuaFunction checkfunction(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkfunction(i);
    }
    
    @Override
    public int checkint(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkint(i);
    }
    
    @Override
    public LuaInteger checkinteger(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkinteger(i);
    }
    
    @Override
    public long checklong(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checklong(i);
    }
    
    @Override
    public LuaNumber checknumber(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checknumber(i);
    }
    
    @Override
    public String checkjstring(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkjstring(i);
    }
    
    @Override
    public LuaString checkstring(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkstring(i);
    }
    
    @Override
    public LuaTable checktable(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checktable(i);
    }
    
    @Override
    public LuaThread checkthread(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkthread(i);
    }
    
    @Override
    public Object checkuserdata(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkuserdata(i);
    }
    
    @Override
    public Object checkuserdata(int i, Class c) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkuserdata(i, c);
    }
    
    @Override
    public LuaValue checkvalue(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checkvalue(i);
    }
    
    @Override
    public LuaValue checknotnil(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).checknotnil(i);
    }
    
    @Override
    public void argcheck(boolean test, int i, String msg) {
        instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).argcheck(test, i, msg);
    }
    
    @Override
    public boolean isnoneornil(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).isnoneornil(i);
    }
    
    @Override
    public boolean toboolean(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toboolean(i);
    }
    
    @Override
    public byte tobyte(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tobyte(i);
    }
    
    @Override
    public char tochar(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tochar(i);
    }
    
    @Override
    public double todouble(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).todouble(i);
    }
    
    @Override
    public float tofloat(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tofloat(i);
    }
    
    @Override
    public int toint(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toint(i);
    }
    
    @Override
    public long tolong(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tolong(i);
    }
    
    @Override
    public String tojstring(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).tojstring(i);
    }
    
    @Override
    public short toshort(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).toshort(i);
    }
    
    @Override
    public Object touserdata(int i) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).touserdata(i);
    }
    
    @Override
    public Object touserdata(int i, Class c) {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).touserdata(i, c);
    }
    
    @Override
    public int type() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).type();
    }
    
    @Override
    public String typename() {
        return instances.computeIfAbsent(getCtx(), (ctx) -> LuaValue.NIL).typename();
    }
    
}