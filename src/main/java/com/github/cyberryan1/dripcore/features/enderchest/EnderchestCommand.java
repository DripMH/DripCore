package com.github.cyberryan1.dripcore.features.enderchest;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderchestCommand extends BaseCommand {

    public EnderchestCommand() {
        super( "enderchest", Permissions.ENDERCHEST, PermissionMessages.ENDERCHEST, Usages.ENDERCHEST );
    }


    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( demandPlayer( sender ) == false ) {
            return true;
        }

        Player player = ( Player ) sender;

        if ( permissionsAllowed( sender ) ) {
            if ( args.length == 0 ) {
                player.openInventory( player.getEnderChest() );
            }

            else if ( VaultUtils.hasPerms( sender, Permissions.ENDERCHEST_OTHERS ) ) {
                if ( CoreUtils.isValidUsername( args[0] ) ) {
                    Player target = Bukkit.getPlayer( args[0] );
                    if ( target != null ) {
                        player.openInventory( target.getEnderChest() );
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
                sender.sendMessage( PermissionMessages.ENDERCHEST_OTHERS );
            }
        }

        else {
            sendPermissionMsg( sender );
        }

        return true;
    }
}