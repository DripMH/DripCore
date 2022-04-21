package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportToggleCommand extends BaseCommand {

    public TeleportToggleCommand() {
        super( "tptoggle", Permissions.TELEPORT_TOGGLE, PermissionMessages.TELEPORT_TOGGLE, Usages.TELEPORT_TOGGLE );
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

        else if ( demandPlayer( sender ) ) {
            Player player = ( Player ) sender;

            if ( TeleportUtils.hasTeleportEnabled( player ) == false ) {
                // enabling teleports
                YMLUtils.getData().set( "teleport.toggle." + player.getUniqueId().toString(), null );
                YMLUtils.getData().save();
                sender.sendMessage( getColorizedStr( "&aEnabled&u other players to teleport to you" ) );
            }

            else {
                // disabling teleports
                YMLUtils.getData().set( "teleport.toggle." + player.getUniqueId().toString() + ".disabled", true );
                YMLUtils.getData().save();
                sender.sendMessage( getColorizedStr( "&cDisabled&u other players from teleporting to you" ) );
            }
        }

        return true;
    }
}
