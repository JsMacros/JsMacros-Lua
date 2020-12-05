package xyz.wagyourtail.jsmacros.lua.luaj;


import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.lang.reflect.Array;

public class JavaArrayIPairs extends VarArgFunction {

    @Override
    public Varargs invoke(Varargs args) {

        return varargsOf(new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                Object list = (Object) ((LuaUserdata)args.arg1()).m_instance;
                int index = args.arg(2).toint();
                if (index == Array.getLength(list)) return NIL;
                return varargsOf(LuaValue.valueOf(index + 1), CoerceJavaToLua.coerce(Array.get(list, index)));
            }
        }, args.arg1(), LuaValue.valueOf(0));
    }

}

