package xyz.wagyourtail.jsmacros.lua.config;

import xyz.wagyourtail.jsmacros.core.config.Option;

public class LuaConfig {
    @Option(translationKey = "jsmacroslua.globalcontext", group = {"jsmacros.settings.languages", "jsmacroslua.settings.languages.lua"})
    public boolean useGlobalContext = false;
}
