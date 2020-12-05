package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.wagyourtail.jsmacros.lua.luaj.JavaArrayIPairs;
import xyz.wagyourtail.jsmacros.lua.luaj.Util;

@Mixin(targets = "org.luaj.vm2.lib.jse.JavaArray", remap = false)
public abstract class MixinJavaArray extends LuaUserdata {

    @Shadow
    @Final
    static LuaValue LENGTH;

    @Shadow
    @Final
    static LuaTable array_metatable;

    static {
        array_metatable.rawset(Util.PAIRS, new JavaArrayIPairs());
        array_metatable.rawset(Util.IPAIRS, new JavaArrayIPairs());
    }

    /*
    * make java interpreter/compiler happy,
    * you can ignore this
    */
    public MixinJavaArray(Object obj) {
        super(obj);
    }
}
