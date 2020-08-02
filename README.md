# JsMacros-Lua

This extension adds `lua 5.2` support to [JsMacros](https://github.com/wagyourtail/JsMacros) `1.2.2+`

# issues/notes

##
All libraries provided by JsMacros need a `:` rather than a `.` because of how methods of classes are accessed in lua.
ie. `chat:log("test")`
##
Lua doesn't auto-coerce functions to consumers, so use the extra library: `consumer`

### toConsumer(function) *1.0.1+*
*Example:* `consumer:toConsumer(function(arg) chat:log(arg) end)`

### toBiConsumer(function) *1.0.1+*
*Example:* `consumer:toBiConsumer(function(arg1, arg2) chat:log(arg1) end)`
