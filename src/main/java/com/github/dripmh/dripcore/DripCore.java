package com.github.dripmh.dripcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DripCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage( "hi" );

    }
}
