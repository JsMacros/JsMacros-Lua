package xyz.wagyourtail.jsmacros.luaj.config;

import xyz.wagyourtail.jsmacros.core.config.Option;

public class LuajConfig {
    @Option(translationKey = "jsmacroslua.globalcontext", group = {"jsmacros.settings.languages", "jsmacroslua.settings.languages.lua"})
    public boolean useGlobalContext = false;
}
