package xyz.wagyourtail.jsmacros.lua.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JavaMap extends LuaUserdata {

    static final LuaTable map_metatable = new LuaTable();
    static {
        map_metatable.rawset(Util.PAIRS, new MapPairs());
    }
    
    public JavaMap(Map<Object, Object> obj) {
        super(obj);
        setmetatable(map_metatable);
    }

    @Override
    public LuaValue get(LuaValue key) {
        return CoerceJavaToLua.coerce(((Map<Object,Object>)m_instance).get(Util.autoCoerceLuaToJava(key)));
    }

    @Override
    public void set(LuaValue key, LuaValue value) {
        ((Map<Object,Object>)m_instance).put(Util.autoCoerceLuaToJava(key), Util.autoCoerceLuaToJava(value));
    }

    public static final class MapPairs extends VarArgFunction {

        @Override
        public Varargs invoke(Varargs args) {
            Map<Object, Object> map  = (Map<Object, Object>) ((JavaMap) args.arg1()).m_instance;
            List<Object> keyset = Arrays.asList(map.keySet().toArray());

            return varargsOf(new VarArgFunction() {
                @Override
                public Varargs invoke(Varargs args) {
                    Map<Object, Object> map  = (Map<Object, Object>) ((JavaMap) args.arg1()).m_instance;
                    int index = keyset.indexOf(Util.autoCoerceLuaToJava(args.arg(2)));
                    if (++index < keyset.size()) {
                        Object next = keyset.get(index);
                        return varargsOf(CoerceJavaToLua.coerce(next), CoerceJavaToLua.coerce(map.get(next)));
                    }
                    return NIL;
                }
            }, args.arg1(), NIL);
        }

    }

}
