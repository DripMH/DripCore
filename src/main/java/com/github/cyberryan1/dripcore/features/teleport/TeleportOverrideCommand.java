package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportOverrideCommand extends BaseCommand {

    public TeleportOverrideCommand() {
        super( "tpo", Permissions.TELEPORT_OVERRIDE, PermissionMessages.TELEPORT_OVERRIDE, Usages.TELEPORT_OVERRIDE );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( permissionsAllowed( sender ) ) {
            if ( args.length == 0 ) {
                return CommandUtils.getAllOnlinePlayerNames();
            }
            else if ( args.length == 1 ) {
                return CommandUtils.matchOnlinePlayers( args[0] );
            }
        }
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        else if ( demandPlayer( sender ) ) {
            if ( args.length >= 1 ) {
                if ( CoreUtils.isValidUsername( args[0] ) ) {
                    Player target = Bukkit.getPlayer( args[0] );
                    if ( target != null ) {
                        Player player = ( Player ) sender;
                        player.teleport( target.getLocation() );
                        player.sendMessage( getColorizedStr( "&uYou have been forcefully teleported to &y" + target.getName() ) );
                        TeleportUtils.sendTeleportNotif( player, target, TeleportUtils.TPO_NOTIF );
                    }

                    else {
                        sendInvalidPlayerArg( sender, args[0] );
                    }
                }

                else {
                    sendInvalidPlayerArg( sender, args[0] );
                }
            }

            else {
                sendUsage( sender );
            }
        }

        return true;
    }
}