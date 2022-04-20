package com.github.dripmh.dripcore;

import com.github.cyberryan1.cybercore.CyberCore;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.dripmh.dripcore.utils.yml.YMLUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class DripCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Setup things related to CyberCore
        CyberCore.setPlugin( this );
        new VaultUtils();

        // Update and reload config/data files
        YMLUtils.getConfig().getYMLManager().reloadConfig();
    }
}
