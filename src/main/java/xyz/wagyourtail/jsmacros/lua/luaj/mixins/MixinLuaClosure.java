package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.Prototype;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.wagyourtail.jsmacros.lua.luaj.ILuaError;

import java.io.File;

@Mixin(value = LuaClosure.class, remap = false)
public class MixinLuaClosure {
    
    @Inject(method = "processErrorHooks", at = @At("TAIL"), remap = false)
    private void onProcessErrorHooks(LuaError le, Prototype p, int pc, CallbackInfo ci) {
        if (p.source != null) {
            String cid = chunkId(p.source.tojstring());
            if (cid != null) {
                ((ILuaError) le).setFile(p.source != null ? new File(cid) : null);
            }
        }
        ((ILuaError) le).setLine(p.lineinfo != null && pc > 0 && pc < p.lineinfo.length ? p.lineinfo[pc] : -1);
    }
    
    @Unique
    private String chunkId(String source) {
        if (source.startsWith("=") || source.startsWith("@")) {
            return source.substring(1);
        } else {
            return null;
        }
    }
}
