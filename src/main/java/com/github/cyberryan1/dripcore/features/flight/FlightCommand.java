package com.github.cyberryan1.dripcore.features.flight;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
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

public class FlightCommand extends BaseCommand {

    public FlightCommand() {
        super( "flight", Permissions.FLIGHT, PermissionMessages.FLIGHT, Usages.FLIGHT );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( VaultUtils.hasPerms( sender, Permissions.FLIGHT_OTHERS ) ) {
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

        else if ( args.length == 0 ) { // /flight
            if ( demandPlayer( sender ) ) {
                Player player = ( Player ) sender;
                if ( player.getAllowFlight() == false ) { // enabling flight
                    player.setAllowFlight( true );
                    sender.sendMessage( getColorizedStr( "&uYour flight has been &aenabled" ) );
                }

                else { // disabling flight
                    player.setAllowFlight( false );
                    sender.sendMessage( getColorizedStr( "&uYour flight has been &cdisabled" ) );
                }
            }
        }

        else { // /flight [player]
            if ( VaultUtils.hasPerms( sender, Permissions.FLIGHT_OTHERS ) ) {
                if ( CoreUtils.isValidUsername( args[0] ) ) {
                    Player target = Bukkit.getPlayer( args[0] );
                    if ( target != null ) {
                        if ( target.getAllowFlight() == false ) { // enabling flight
                            target.setAllowFlight( true );
                            sender.sendMessage( getColorizedStr( "&aEnabled &y" + target.getName() + "&u's flight " ) );
                        }

                        else { // disabling flight
                            target.setAllowFlight( false );
                            sender.sendMessage( getColorizedStr( "&cDisabled &y" + target.getName() + "&u's flight " ) );
                        }
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
                sender.sendMessage( PermissionMessages.FLIGHT_OTHERS );
            }
        }

        return true;
    }
}