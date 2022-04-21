package com.github.cyberryan1.dripcore.features.ping;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PingCommand extends BaseCommand {

    public PingCommand() {
        super( "ping", null, null, null );
    }


    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( args.length == 0 ) {
            return CommandUtils.getAllOnlinePlayerNames();
        }
        else if ( args.length == 1 ) {
            return CommandUtils.matchOnlinePlayers( args[0] );
        }

        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( demandPlayer( sender ) == false ) {
            return true;
        }

        if ( args.length == 0 ) {
            Player player = ( Player ) sender;
            sender.sendMessage( getColorizedStr( "&uYour ping is &y" + player.getPing() ) );
        }

        else if ( CoreUtils.isValidUsername( args[0] ) ) {
            Player target = Bukkit.getPlayer( args[0] );
            if ( target != null ) {
                sender.sendMessage( getColorizedStr( "&y" + target.getName() + "&u's ping is &y" + target.getPing() ) );
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