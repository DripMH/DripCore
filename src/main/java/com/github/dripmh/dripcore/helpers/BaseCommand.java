package com.github.dripmh.dripcore.helpers;

import com.github.cyberryan1.cybercore.CyberCore;
import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {

    // returns true if the sender is a player, false if not
    public static boolean demandPlayer( CommandSender sender ) {
        if ( sender instanceof Player ) {
            return true;
        }

        sender.sendMessage( Messages.PLAYER_REQUIRED );
        return false;
    }

    // returns true if the sender is a console sender, false if not
    public static boolean demandConsole( CommandSender sender ) {
        if ( sender instanceof ConsoleCommandSender ) {
            return true;
        }

        sender.sendMessage( Messages.CONSOLE_REQUIRED );
        return false;
    }

    // returns all online player names
    public static List<String> getAllOnlinePlayerNames() {
        List<String> toReturn = new ArrayList<>();
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            toReturn.add( p.getName() );
        }
        return toReturn;
    }

    // returns all online player names that starts with the input
    public static List<String> matchOnlinePlayers( String input ) {
        List<String> toReturn = new ArrayList<>();
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            if ( p.getName().toUpperCase().startsWith( input.toUpperCase() ) ) {
                toReturn.add( p.getName() );
            }
        }
        return toReturn.size() == 0 ? null : toReturn;
    }

    // returns the remaining arguments
    public static String combineRemainingArgs( String[] args, int start ) {
        String toReturn = "";
        for ( int i = start; i < args.length; i++ ) {
            toReturn += args[i] + " ";
        }
        return toReturn;
    }

    protected String label;
    protected String permission;
    protected String permissionMsg;
    protected String usage;

    public BaseCommand( String label, String permission, String permissionMsg, String usage ) {
        this.label = label;
        this.permission = permission;
        this.permissionMsg = permissionMsg;
        this.usage = usage;
    }

    // will be done in the individual class, depending on the need
    public abstract List<String> onTabComplete( CommandSender sender, Command command, String label, String args[] );

    @Override
    // will also be done in the individual class as the contents of this depends on the need of the command
    public abstract boolean onCommand( CommandSender sender, Command command, String label, String args[] );

    // can be @Override if needed
    public boolean permissionsAllowed( CommandSender sender ) {
        if ( permission == null ) { return true; }
        return VaultUtils.hasPerms( sender, permission );
    }

    public void sendPermissionMsg( CommandSender sender ) {
        sender.sendMessage( permissionMsg );
    }

    public void sendUsage( CommandSender sender ) {
        sender.sendMessage( usage );
    }

    public void sendInvalidPlayerArg( CommandSender sender, String input ) {
        sender.sendMessage( CoreUtils.getColored( "&7Could not find the player &b\"" + input + "&b\"" ) );
    }

    public void register( boolean includeTabComplete ) {
        CyberCore.getPlugin().getCommand( this.label ).setExecutor( this );
        if ( includeTabComplete ) {
            CyberCore.getPlugin().getCommand( this.label ).setTabCompleter( this );
        }
    }
}