package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.Globals;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.ScriptContext;

import java.util.concurrent.atomic.AtomicInteger;

public class LuaScriptContext extends ScriptContext<Globals> {
    public boolean closed = false;
    public final AtomicInteger nonGCdMethodWrappers = new AtomicInteger(0);

    public LuaScriptContext(BaseEvent event) {
        super(event);
    }

    @Override
    public boolean isContextClosed() {
        return super.isContextClosed() || closed;
    }
    
    @Override
    public synchronized void closeContext() {
        closed = true;
        Core.instance.threadContext.entrySet().stream().filter(e -> e.getValue() == this).forEach(e -> e.getKey().interrupt());
    }
    
}
