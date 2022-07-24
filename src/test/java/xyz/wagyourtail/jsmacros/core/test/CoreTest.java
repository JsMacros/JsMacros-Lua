package xyz.wagyourtail.jsmacros.core.test;

import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import xyz.wagyourtail.jsmacros.core.Core;
import xyz.wagyourtail.jsmacros.core.event.impl.EventCustom;
import xyz.wagyourtail.jsmacros.core.language.EventContainer;
import xyz.wagyourtail.jsmacros.stubs.CoreInstanceCreator;
import xyz.wagyourtail.jsmacros.stubs.EventRegistryStub;
import xyz.wagyourtail.jsmacros.stubs.ProfileStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoreTest {

    @Language("lua")
    private final String TEST_SCRIPT = """
        event:putString("rp1", "Hello World!")
        JavaWrapper:methodToJava(function() event:putString("rp2", "Hello World!") end):run()
        JavaWrapper:methodToJavaAsync(function() event:putString("rp3", "Hello World!") end):run()
        """;

    @Test
    public void test() throws InterruptedException {
        Core<ProfileStub, EventRegistryStub> core = CoreInstanceCreator.createCore();
        EventCustom event = new EventCustom("test");
        EventContainer<?> ev = core.exec("lua", TEST_SCRIPT, null, event, null, null);
        ev.awaitLock(() -> {});
        Thread.sleep(100);
        assertEquals("{rp1=Hello World!, rp3=Hello World!, rp2=Hello World!}", event.getUnderlyingMap().toString());

    }
}
