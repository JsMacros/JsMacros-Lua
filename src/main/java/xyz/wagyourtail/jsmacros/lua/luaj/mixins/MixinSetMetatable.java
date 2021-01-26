package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.BaseLib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.luaj.vm2.lib.BaseLib$setmetatable", remap = false)
public class MixinSetMetatable {
    
    @Inject(at = @At("HEAD"), method = "call(Lorg/luaj/vm2/LuaValue;Lorg/luaj/vm2/LuaValue;)Lorg/luaj/vm2/LuaValue;", cancellable = true, remap = false)
    private void checkTable(LuaValue table, LuaValue metatable, CallbackInfoReturnable<LuaValue> cir) {
        final LuaValue mt0 = table.getmetatable();
        if ( mt0!=null && !mt0.rawget(LuaValue.METATABLE).isnil() )
            BaseLib.error("cannot change a protected metatable");
        cir.setReturnValue(table.setmetatable(metatable.isnil()? null: metatable.checktable()));
    }
}
