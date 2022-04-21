package com.github.cyberryan1.dripcore.features.gamemode;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpectatorCommand extends BaseCommand {

    public SpectatorCommand() {
        super( "gmsp", Permissions.GAMEMODE_SPECTATOR, PermissionMessages.GAMEMODE_SPECTATOR, Usages.GAMEMODE_SPECTATOR );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        if ( args.length <= 1 ) {
            if ( args.length == 0 ) { return CommandUtils.getAllOnlinePlayerNames(); }
            return CommandUtils.matchOnlinePlayers( args[0] );
        }
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        else if ( args.length == 0 ) { // /gmc
            if ( demandPlayer( sender ) == false ) {
                return true;
            }

            Player player = ( Player ) sender;
            player.setGameMode( GameMode.SPECTATOR );
            sender.sendMessage( getColorizedStr( "&uYour gamemode has been set to &ySPECTATOR" ) );
        }

        else { // /gmc [player]
            if ( CoreUtils.isValidUsername( args[0] ) ) {
                Player target = Bukkit.getPlayer( args[0] );
                if ( target != null ) {
                    target.setGameMode( GameMode.SPECTATOR );
                    sender.sendMessage( getColorizedStr( "&uSet &y" + target.getName() + "&u's gamemode to &ySPECTATOR" ) );
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