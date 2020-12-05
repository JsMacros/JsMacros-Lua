package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.wagyourtail.jsmacros.lua.luaj.JavaList;
import xyz.wagyourtail.jsmacros.lua.luaj.JavaMap;

import java.util.List;
import java.util.Map;

@Mixin(value = CoerceJavaToLua.class, remap = false)
public class MixinCoerceJavaToLua {

    @Inject(at = @At("HEAD"), method = "coerce", remap = false, cancellable = true)
    private static void coerce(Object o, CallbackInfoReturnable<LuaValue> info) {
        if (o instanceof Map) {
            info.setReturnValue(new JavaMap((Map<Object, Object>) o));
            info.cancel();
        } else if (o instanceof List) {
            info.setReturnValue(new JavaList((List<Object>) o));
            info.cancel();
        }
    }

}
