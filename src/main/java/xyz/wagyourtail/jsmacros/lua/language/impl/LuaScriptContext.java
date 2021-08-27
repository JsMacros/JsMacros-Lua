package xyz.wagyourtail.jsmacros.lua.language.impl;

import org.luaj.vm2.Globals;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.BaseScriptContext;

import java.io.File;

public class LuaScriptContext extends BaseScriptContext<Globals> {
    public LuaScriptContext(BaseEvent event, File file) {
        super(event, file);
    }
    
}
