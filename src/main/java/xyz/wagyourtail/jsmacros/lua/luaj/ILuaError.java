package xyz.wagyourtail.jsmacros.lua.luaj;

import java.io.File;

public interface ILuaError {

    void setFile(File file);
    
    File getFile();
    
    void setLine(int line);
    
    int getLine();
    
    String getErrorMessage();
}
