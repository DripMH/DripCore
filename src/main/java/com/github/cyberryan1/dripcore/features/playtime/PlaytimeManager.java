package com.github.cyberryan1.dripcore.features.playtime;

import com.github.cyberryan1.dripcore.utils.Utils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlaytimeManager {

    public static HashMap<UUID, Long> playtime = new HashMap<>();

    public static void addPlayer(Player player) {
        playtime.put(player.getUniqueId(), Utils.getCurrentTimestamp());
    }

    public static void removePlayer(Player player) {
        playtime.remove(player.getUniqueId());
    }

    public static long getSessionPlaytime(Player player) {
        return Utils.getCurrentTimestamp() - playtime.get(player.getUniqueId());
    }

    public static long getTotalPlaytime(Player player) {
        saveSessionPlaytime(player);
        return YMLUtils.getData().getConfig().getLong( "playtime." + player.getUniqueId().toString() );
    }

    public static long getTotalPlaytime(OfflinePlayer player) {
        if ( player.isOnline() ) {
            return getTotalPlaytime(player.getPlayer());
        } else {
            return YMLUtils.getData().getConfig().getLong( "playtime." + player.getUniqueId().toString() );
        }
    }

    public static void saveSessionPlaytime(Player player) {
        long existingPlaytime = YMLUtils.getData().getConfig().getLong( "playtime." + player.getUniqueId().toString() );
        YMLUtils.getData().set( "playtime." + player.getUniqueId().toString(), existingPlaytime + getSessionPlaytime(player) );
        YMLUtils.getData().save();
    }

    public static String getPlaytimeString(long playtime) {
        long days = playtime / 86400;
        long hours = (playtime % 86400) / 3600;
        long minutes = (playtime % 3600) / 60;
        long seconds = playtime % 60;
        String playtimeString = "";
        if ( days > 0 ) {
            playtimeString += days + " days ";
        }
        if ( hours > 0 ) {
            playtimeString += hours + " hours ";
        }
        if ( minutes > 0 ) {
            playtimeString += minutes + " minutes ";
        }
        if ( seconds > 0 ) {
            playtimeString += seconds + " seconds";
        }
        return playtimeString;
    }

}
