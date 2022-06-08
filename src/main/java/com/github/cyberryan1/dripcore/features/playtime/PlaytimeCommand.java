package com.github.cyberryan1.dripcore.features.playtime;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaytimeCommand extends BaseCommand {
    public PlaytimeCommand() {
        super("playtime", null, null, CoreUtils.getColored("&8/&7playtime"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if ( args.length == 0 || args[0].length() == 0 ) {
            return CommandUtils.getAllOnlinePlayerNames();
        }
        else if ( args.length == 1 ) {
            CommandUtils.matchOnlinePlayers( args[0] );
        }

        return List.of();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (demandPlayer(sender) == false) {
            return true;
        }

        if ( args.length == 0 ) {
            Player player = (Player) sender;
            long senderPlaytime = PlaytimeManager.getTotalPlaytime(player);
            sender.sendMessage( getColorizedStr( "&uYour playtime is &y" + PlaytimeManager.getPlaytimeString(senderPlaytime) ) );
        }

        else if ( CoreUtils.isValidUsername( args[0] ) ) {
            OfflinePlayer target = Bukkit.getOfflinePlayer( args[0] );
            if ( target != null ) {
                long targetPlaytime = PlaytimeManager.getTotalPlaytime(target);
                sender.sendMessage( getColorizedStr( "&y" + target.getName() + "&u's playtime is &y" + PlaytimeManager.getPlaytimeString(targetPlaytime) ) );
            }

            else {
                sendInvalidPlayerArg( sender, args[0] );
            }
        }

        else {
            sendInvalidPlayerArg( sender, args[0] );
        }
        return true;
    }
}
