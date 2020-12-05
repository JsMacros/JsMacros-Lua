package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.wagyourtail.jsmacros.lua.luaj.Util;

@Mixin(targets = "org.luaj.vm2.lib.BaseLib$pairs", remap = false)
public class MixinPairs {

    @Inject(at = @At("HEAD"), method = "invoke", cancellable = true, remap = false)
    public void invoke(Varargs args, CallbackInfoReturnable<Varargs> cir) {
        LuaValue meta = args.arg1().getmetatable();
        LuaValue __pairs;
        if(meta!=null && (__pairs = meta.get(Util.PAIRS)).isfunction()) {
            cir.setReturnValue(__pairs.invoke(args));
            cir.cancel();
        }
    }
}