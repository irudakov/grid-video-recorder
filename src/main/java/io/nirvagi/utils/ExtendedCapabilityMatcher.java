package io.nirvagi.utils;

import com.google.common.collect.ImmutableSet;
import org.openqa.grid.internal.utils.DefaultCapabilityMatcher;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Map;

public class ExtendedCapabilityMatcher extends DefaultCapabilityMatcher{

    private boolean any(Object requestedApp) {
        return requestedApp == null ||
                ImmutableSet.of("any", "", "*").contains(requestedApp.toString().toLowerCase());
    }
    @Override
    public boolean matches(Map<String, Object> providedCapabilities, Map<String, Object> requestedCapabilities) {
        if (super.matches(providedCapabilities, requestedCapabilities)){
            Object requestedApp = requestedCapabilities.get(CapabilityType.APPLICATION_NAME);
            Object providedApp = providedCapabilities.get(CapabilityType.APPLICATION_NAME);


            if(any(requestedApp) && providedApp.equals("Debugger"))
            return false;
            else return true;
        }
        return false;
    }
}
