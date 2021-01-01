# JsMacros-Lua

This extension adds `lua 5.2` support to [JsMacros](https://github.com/wagyourtail/JsMacros) `1.2.2+`

# issues/notes

##
All libraries provided by JsMacros need a `:` rather than a `.` because of how methods of classes are accessed in lua.
ie. `chat:log("test")`
