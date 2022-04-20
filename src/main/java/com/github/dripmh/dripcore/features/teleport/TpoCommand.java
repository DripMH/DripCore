package com.github.dripmh.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.dripmh.dripcore.helpers.BaseCommand;
import com.github.dripmh.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpoCommand extends BaseCommand {

    public TpoCommand() {
        super( "tpo", YMLUtils.getConfig().getStr( "teleport.tpo-perm" ), "&8/&7tpo &b(player)" );
        register( true );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( permissionsAllowed( sender ) ) {
            if ( args.length == 0 || args[0].length() == 0 ) {
                return getAllOnlinePlayerNames();
            }
            else if ( args.length == 1 && args[0].length() >= 1 ) {
                return matchOnlinePlayers( args[0] );
            }
        }

        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( demandPlayer( sender ) == false ) { return true; }

        else if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        else if ( args.length >= 1 ) {
            if ( CoreUtils.isValidUsername( args[0] ) ) {
                Player target = Bukkit.getPlayer( args[0] );
                if ( target != null ) {
                    Player staff = ( Player ) sender;
                    staff.teleport( target );

                    for ( Player p : Bukkit.getOnlinePlayers() ) {
                        if ( VaultUtils.hasPerms( p, YMLUtils.getConfig().getStr( "teleport.tpnotifs-perm" ) ) && p.getName().equals( staff.getName() ) == false ) {
                            p.sendMessage( CoreUtils.getColored( String.format( "&7[%s force teleported to %s]", staff.getName(), target.getName() ) ) );
                        }
                    }

                    staff.sendMessage( CoreUtils.getColored( "&7You have force teleported to &b" + target.getName() ) );
                }

                else {
                    sendInvalidPlayerArg( sender, args[0] );
                }
            }

            else {
                sendInvalidPlayerArg( sender, args[0] );
            }
        }

        return true;
    }
}