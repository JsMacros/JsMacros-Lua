package xyz.wagyourtail.jsmacroslua.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.List;

public class JavaList extends LuaUserdata {

    static final LuaValue LENGTH = valueOf("length");
    static final LuaTable list_metatable = new LuaTable();

    static {
        list_metatable.rawset(LuaValue.LEN, new LenFunction());
        list_metatable.rawset(Util.IPAIRS, new ListIPairs());
        list_metatable.rawset(Util.PAIRS, new ListIPairs());
    }

    public JavaList(List<Object> obj) {
        super(obj);
        setmetatable(list_metatable);
    }

    public LuaValue get(LuaValue key) {
        if (key.equals(LENGTH))
            return valueOf(((List<Object>) m_instance).size());
        if (key.isint()) {
            int i = key.toint() - 1;
            return i >= 0 && i < ((List<Object>) m_instance).size() ?
            CoerceJavaToLua.coerce(((List<Object>) m_instance).get(i)) :
            NIL;
        }
        return super.get(key);
    }

    public void set(LuaValue key, LuaValue value) {
        List<Object> list = (List<Object>) m_instance;
        if (key.isint()) {
            int i = key.toint() - 1;
            if (i >= 0) {
                if (i < list.size())
                    list.set(i, Util.autoCoerceLuaToJava(value));
                else
                    list.add(Util.autoCoerceLuaToJava(value));
            }
        } else
            super.set(key, value);
    }

    private static final class ListIPairs extends VarArgFunction {

        @Override
        public Varargs invoke(Varargs args) {

            return varargsOf(new VarArgFunction() {

                @Override
                public Varargs invoke(Varargs args) {
                    List<Object> list = (List<Object>) ((JavaList)args.arg1()).m_instance;
                    int index = args.arg(2).toint();
                    if (index == list.size()) return NIL;
                    return varargsOf(LuaValue.valueOf(index + 1), CoerceJavaToLua.coerce(list.get(index)));
                }

            }, args.arg1(), LuaValue.valueOf(0));
        }

    }

    private static final class LenFunction extends OneArgFunction {

        public LuaValue call(LuaValue u) {
            return LuaValue.valueOf(((List<Object>)((LuaUserdata)u).m_instance).size());
        }

    }

}
