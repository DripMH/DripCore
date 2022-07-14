package com.github.cyberryan1.dripcore.features.playtime;

import com.github.cyberryan1.dripcore.utils.Utils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlaytimeManager {

    private static Map<Player, Long> playerJoinTimes = new HashMap<>();

    public static void initializeAllPlayers() {
        for ( Player player : Bukkit.getOnlinePlayers() ) { initializePlayer( player ); }
    }

    public static void initializePlayer( Player player ) {
        playerJoinTimes.put( player, Utils.getCurrentTimestamp() );
    }

    public static long getPlaytimeSeconds( OfflinePlayer player ) {
        final long saved = YMLUtils.getData().getConfig().getLong( "playtime." + player.getUniqueId() );
        if ( player.isOnline() == false ) { return saved; }

        Player onlinePlayer = player.getPlayer();
        final long now = Utils.getCurrentTimestamp();
        final long playtime = now - playerJoinTimes.get( onlinePlayer );
        return saved + playtime;
    }

    public static void saveAllPlayers() {
        for ( Player player : Bukkit.getOnlinePlayers() ) { savePlayer( player ); }
    }

    public static void savePlayer( Player player ) {
        if ( playerJoinTimes.containsKey( player ) == false ) { return; }
        final long join = playerJoinTimes.get( player );
        final long now = Utils.getCurrentTimestamp();
        final long playtimeAdded = now - join;
        final long saved = YMLUtils.getData().getConfig().getLong( "playtime." + player.getUniqueId() );

        YMLUtils.getData().set( "playtime." + player.getUniqueId(), saved + playtimeAdded );
        YMLUtils.getData().save();
    }
}