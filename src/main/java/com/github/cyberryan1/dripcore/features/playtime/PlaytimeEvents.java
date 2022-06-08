package com.github.cyberryan1.dripcore.features.playtime;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlaytimeEvents implements Listener {
    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event ) {
        PlaytimeManager.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit( PlayerQuitEvent event ) {
        PlaytimeManager.saveSessionPlaytime(event.getPlayer());
        PlaytimeManager.removePlayer(event.getPlayer());
    }
}
