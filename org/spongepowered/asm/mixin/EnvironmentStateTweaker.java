package org.spongepowered.asm.mixin;

import org.spongepowered.asm.launch.MixinBootstrap;
import net.minecraft.launchwrapper.LaunchClassLoader;
import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;

public class EnvironmentStateTweaker implements ITweaker
{
    public EnvironmentStateTweaker() {
        super();
    }
    
    public void acceptOptions(final List<String> list, final File file, final File file2, final String s) {
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader launchClassLoader) {
        MixinBootstrap.getPlatform().inject();
    }
    
    public String getLaunchTarget() {
        return "";
    }
    
    public String[] getLaunchArguments() {
        MixinEnvironment.gotoPhase(MixinEnvironment.Phase.DEFAULT);
        return new String[0];
    }
}
