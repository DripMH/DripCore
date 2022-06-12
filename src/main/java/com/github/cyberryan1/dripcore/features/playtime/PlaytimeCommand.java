package com.github.cyberryan1.dripcore.features.playtime;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.github.cyberryan1.dripcore.utils.CommandUtils.getAllOnlinePlayerNames;
import static com.github.cyberryan1.dripcore.utils.CommandUtils.matchOnlinePlayers;

public class PlaytimeCommand extends BaseCommand {

    public PlaytimeCommand() {
        super( "playtime", null, null, null );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( args.length == 0 || args[0].length() == 0 ) { return getAllOnlinePlayerNames(); }
        else if ( args.length == 1 ) { return matchOnlinePlayers( args[0] ); }

        return List.of();
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( demandPlayer( sender ) == false ) { return true; }

        Player player = ( Player ) sender;
        OfflinePlayer target = player;

        if ( args.length >= 1 ) {
            if ( CoreUtils.isValidUsername( args[0] ) ) {
                target = Bukkit.getOfflinePlayer( args[0] );
                if ( target == null ) {
                    sendInvalidPlayerArg( sender, args[0] );
                    return true;
                }
            }

            else {
                sendInvalidPlayerArg( sender, args[0] );
                return true;
            }
        }

        long playtime = PlaytimeManager.getPlaytimeSeconds( target );

        final long DAYS = playtime / 86400;
        playtime -= DAYS * 86400;
        final long HOURS = playtime / 3600;
        playtime -= HOURS * 3600;
        final long MINUTES = playtime / 60;
        playtime -= MINUTES * 60;
        final long SECONDS = playtime;

        CoreUtils.sendMessage( sender,
                "&8",
                "&b" + target.getName() + "&7's Playtime &8- ",
                "&8",
                " &7Days: &b" + DAYS,
                " &7Hours: &b" + HOURS,
                " &7Minutes: &b" + MINUTES,
                " &7Seconds: &b" + SECONDS,
                "&8"
        );

        return true;
    }
}