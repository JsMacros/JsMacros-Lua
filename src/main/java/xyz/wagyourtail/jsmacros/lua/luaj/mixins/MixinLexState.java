package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.compiler.LexState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.wagyourtail.jsmacros.lua.luaj.ILuaError;

import java.io.File;

@Mixin(value = LexState.class, remap = false)
public class MixinLexState {
    
    @Shadow private int linenumber;
    
    @Shadow private LuaString source;
    
    @Inject(method = "lexerror", at = @At(value = "INVOKE", target = "Lorg/luaj/vm2/LuaError;<init>(Ljava/lang/String;)V", remap = false), remap = false)
    void lexError(String msg, int token, CallbackInfo ci) {
        LuaError e = new LuaError(msg);
        ((ILuaError) e).setLine(linenumber);
        ((ILuaError) e).setFile(new File(Lua.chunkid(source.tojstring())));
        throw e;
    }
    
}
