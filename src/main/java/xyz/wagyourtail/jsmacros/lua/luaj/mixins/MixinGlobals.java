package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Globals.class, remap = false)
public class MixinGlobals {
    @Inject(method = "loadfile", at = @At(value = "INVOKE", target = "Lorg/luaj/vm2/Globals;error(Ljava/lang/String;)Lorg/luaj/vm2/LuaValue;", remap = false), locals = LocalCapture.CAPTURE_FAILHARD, remap = false, cancellable = true)
    public void loadFile(String filename, CallbackInfoReturnable<LuaValue> cir, Exception var2) throws Exception {
        throw var2;
    }
}
