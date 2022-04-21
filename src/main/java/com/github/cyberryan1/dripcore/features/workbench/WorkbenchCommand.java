package com.github.cyberryan1.dripcore.features.workbench;

import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WorkbenchCommand extends BaseCommand {

    public WorkbenchCommand() {
        super( "workbench", Permissions.WORKBENCH, PermissionMessages.WORKBENCH, null );
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

        if ( permissionsAllowed( sender ) ) {
            Player player = ( Player ) sender;
            player.openWorkbench( null, true );
        }

        else {
            sendPermissionMsg( sender );
        }

        return true;
    }
}