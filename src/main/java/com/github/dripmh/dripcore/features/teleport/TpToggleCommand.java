package com.github.dripmh.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.dripmh.dripcore.helpers.BaseCommand;
import com.github.dripmh.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TpToggleCommand extends BaseCommand {

    public TpToggleCommand() {
        super( "tptoggle", YMLUtils.getConfig().getStr( "teleport.tptoggle-perm" ), "&8/&7tptoggle" );
        register( false );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( demandPlayer( sender ) == false ) { return true; }

        else if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        else {
            List<String> disabledList = new ArrayList<String>( List.of( YMLUtils.getData().getStrList( "teleport.disabled" ) ) );
            Player player = ( Player ) sender;

            if ( disabledList.contains( player.getUniqueId().toString() ) == false ) {
                disabledList.add( player.getUniqueId().toString() );
                YMLUtils.getData().set( "teleport.disabled", disabledList );
                YMLUtils.getData().save();

                player.sendMessage( CoreUtils.getColored( "&7You have &cdisabled&7 teleportation to you" ) );
            }

            else {
                disabledList.remove( player.getUniqueId().toString() );
                YMLUtils.getData().set( "teleport.disabled", disabledList );
                YMLUtils.getData().save();

                player.sendMessage( CoreUtils.getColored( "&7You have &aenabled&7 teleportation to you" ) );
            }
        }

        return true;
    }
}