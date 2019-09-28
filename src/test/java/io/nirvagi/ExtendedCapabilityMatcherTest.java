package io.nirvagi;
import static org.junit.Assert.assertFalse;

import com.google.common.collect.ImmutableMap;

import io.nirvagi.utils.ExtendedCapabilityMatcher;

import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Map;

public class ExtendedCapabilityMatcherTest extends MyCapabilityMatcherTest{

    private final ExtendedCapabilityMatcher matcher = new ExtendedCapabilityMatcher();

    @Test
    public void nullvalueTest(){

        Map<String, Object> firefox2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.VERSION, "3.6");
        Map<String, Object> tl2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.APPLICATION_NAME, "Debugger",
                CapabilityType.VERSION, "3.6");
        assertFalse(matcher.matches(tl2, firefox2));
        assertFalse(matcher.matches(firefox2,tl2));
    }

    @Test
    public void noNullValueTest(){

        Map<String, Object> firefox2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.APPLICATION_NAME, "Bullshit",
                CapabilityType.VERSION, "3.6");
        Map<String, Object> tl2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.APPLICATION_NAME, "Debugger",
                CapabilityType.VERSION, "3.6");
        assertFalse(matcher.matches(tl2, firefox2));
        assertFalse(matcher.matches(firefox2,tl2));

    }

    @Test
    public void emptyStringTest(){
        Map<String, Object> firefox2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.APPLICATION_NAME, "",
                CapabilityType.VERSION, "3.6");
        Map<String, Object> tl2 = ImmutableMap.of(
                CapabilityType.BROWSER_NAME, "Test",
                CapabilityType.PLATFORM_NAME, "linux",
                CapabilityType.APPLICATION_NAME, "Debugger",
                CapabilityType.VERSION, "3.6");
        assertFalse(matcher.matches(tl2, firefox2));
        assertFalse(matcher.matches(firefox2,tl2));

    }

}
