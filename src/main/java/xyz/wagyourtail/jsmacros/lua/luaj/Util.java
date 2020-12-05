package xyz.wagyourtail.jsmacros.lua.luaj;

import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;

public class Util {
    public static final LuaString PAIRS = LuaValue.valueOf("__pairs");
    public static final LuaString IPAIRS = LuaValue.valueOf("__ipairs");

    static Object autoCoerceLuaToJava(LuaValue key) {
        switch (key.type()) {
            case LuaValue.TNIL:
                return null;
            case LuaValue.TBOOLEAN:
                return key.toboolean();
            case LuaValue.TNUMBER:
                if (key.isint()) {
                    return key.toint();
                }
                if (key.islong()) {
                    return key.tolong();
                }
                return key.todouble();
            case LuaValue.TSTRING:
                return key.toString();
            case LuaValue.TUSERDATA:
                return ((LuaUserdata) key).m_instance;
            default:
                LuaValue.error("failed to autocoerce lua value to java");
                return null;
        }
    }
}
