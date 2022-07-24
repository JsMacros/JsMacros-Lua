package xyz.wagyourtail.jsmacros.luaj.language.impl;

import org.luaj.vm2.Globals;
import xyz.wagyourtail.jsmacros.core.event.BaseEvent;
import xyz.wagyourtail.jsmacros.core.language.BaseScriptContext;

import java.io.File;

public class LuajScriptContext extends BaseScriptContext<Globals> {
    public LuajScriptContext(BaseEvent event, File file) {
        super(event, file);
    }

    @Override
    public boolean isMultiThreaded() {
        return true;
    }

}
