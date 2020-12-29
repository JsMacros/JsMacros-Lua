package xyz.wagyourtail.jsmacros.lua.luaj.mixins;

import org.luaj.vm2.LuaError;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.wagyourtail.jsmacros.lua.luaj.ILuaError;

import java.io.File;

@Mixin(value = LuaError.class, remap = false)
public class MixinLuaError extends RuntimeException implements ILuaError {
    @Shadow(remap = false) protected String traceback;
    @Unique
    private File file = null;
    
    @Unique
    private int line = 0;
    
    @Override
    public void setFile(File file) {
        this.file = file;
    }
    
    @Override
    public File getFile() {
        return file;
    }
    
    @Override
    public void setLine(int line) {
        this.line = line;
    }
    
    @Override
    public int getLine() {
        return line;
    }
    
    @Override
    public String getErrorMessage() {
        if (traceback != null)
            return traceback;
        else
            return super.getMessage();
    }
    
}
