package com.sun.jna;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;

private static class AWT
{
    private AWT() {
        super();
    }
    
    static long getWindowID(final Window window) throws HeadlessException {
        return getComponentID(window);
    }
    
    static long getComponentID(final Object o) throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("No native windows when headless");
        }
        final Component component = (Component)o;
        if (component.isLightweight()) {
            throw new IllegalArgumentException("Component must be heavyweight");
        }
        if (!component.isDisplayable()) {
            throw new IllegalStateException("Component must be displayable");
        }
        if (Platform.isX11() && System.getProperty("java.version").startsWith("1.4") && !component.isVisible()) {
            throw new IllegalStateException("Component must be visible");
        }
        return Native.getWindowHandle0(component);
    }
}
