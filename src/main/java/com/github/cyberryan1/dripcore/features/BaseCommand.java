package com.github.cyberryan1.dripcore.features;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {

    private static final String PRIMARY_COLOR = YMLUtils.getConfig().getColoredStr( "global.primary-color" );
    private static final String SECONDARY_COLOR = YMLUtils.getConfig().getColoredStr( "global.secondary-color" );

    // returns true if the sender is a player, false if not
    public static boolean demandPlayer( CommandSender sender ) {
        if ( sender instanceof Player ) {
            return true;
        }

        sender.sendMessage( Usages.PLAYER_REQUIRED );
        return false;
    }

    // returns true if the sender is a console sender, false if not
    public static boolean demandConsole( CommandSender sender ) {
        if ( sender instanceof ConsoleCommandSender ) {
            return true;
        }

        sender.sendMessage( Usages.CONSOLE_REQUIRED );
        return false;
    }

    // formats a string with the primary and secondary colors, as desired
    // use the &y for the primary color and &u for the secondary color
    public static String getColorizedStr( String str ) {
        str = str.replaceAll( "&y", PRIMARY_COLOR ).replaceAll( "&u", SECONDARY_COLOR );
        return CoreUtils.getColored( str );
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
}