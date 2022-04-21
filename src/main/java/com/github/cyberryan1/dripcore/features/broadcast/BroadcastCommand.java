package com.github.cyberryan1.dripcore.features.broadcast;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BroadcastCommand extends BaseCommand {

    public BroadcastCommand() {
        super( "broadcast", Permissions.BROADCAST, PermissionMessages.BROADCAST, Usages.BROADCAST );
    }


    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        if ( args.length == 0 ) {
            sendUsage( sender );
            return true;
        }

        String combined = CommandUtils.combineRemainingArgs( args, 0 );
        String msg = YMLUtils.getConfig().getColoredStr( "commands.broadcast.broadcast-message" );
        msg = CoreUtils.getColored( msg.replace( "[MESSAGE]", combined ) );
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            p.sendMessage( "\n", msg, "\n" );
            p.playSound( p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
        }

        return true;
    }
}