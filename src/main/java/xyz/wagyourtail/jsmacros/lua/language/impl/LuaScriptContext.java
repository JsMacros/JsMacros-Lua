package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.Globals;
import xyz.wagyourtail.jsmacros.core.language.ScriptContext;

public class LuaScriptContext extends ScriptContext<Globals> {
    public boolean closed = false;
    
    @Override
    public boolean isContextClosed() {
        return super.isContextClosed() || closed;
    }
    
    @Override
    public synchronized void closeContext() {
        closed = true;
    }
    
}
