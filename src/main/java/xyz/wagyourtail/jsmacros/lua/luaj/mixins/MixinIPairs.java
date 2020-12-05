package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.wagyourtail.jsmacros.lua.luaj.Util;

@Mixin(targets = "org.luaj.vm2.lib.BaseLib$ipairs", remap = false)
public class MixinIPairs {

    @Inject(at = @At("HEAD"), method = "invoke", cancellable = true, remap = false)
    public void invoke(Varargs args, CallbackInfoReturnable<Varargs> cir) {
        LuaValue meta = args.arg1().getmetatable();
        LuaValue __ipairs;
        if(meta!=null && (__ipairs = meta.get(Util.IPAIRS)).isfunction()) {
            cir.setReturnValue(__ipairs.invoke(args));
            cir.cancel();
        }
    }

}
