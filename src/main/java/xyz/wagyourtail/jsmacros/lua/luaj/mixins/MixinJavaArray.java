package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

    @Inject(method = "<init>", at = @At(value = "RETURN", remap = false))
    public void setMetatableRedirect(Object instance, CallbackInfo ci) {
        LuaTable t = new LuaTable();
        t.rawset(LuaValue.LEN, array_metatable.rawget(LuaValue.LEN));
        t.rawset(Util.PAIRS, new JavaArrayIPairs());
        t.rawset(Util.IPAIRS, new JavaArrayIPairs());
        setmetatable(t);
    }

    /*
    * make java interpreter/compiler happy,
    * you can ignore this
    */
    public MixinJavaArray(Object obj) {
        super(obj);
    }
}
