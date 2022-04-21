package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class TeleportUtils {

    public static final boolean NOTIFS_ENABLED = YMLUtils.getConfig().getBool( "commands.teleport.notifs.enabled" );

    public static final String TP_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tp-message" );
    public static final String TPHERE_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tphere-message" );
    public static final String TPO_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tpo-message" );
    public static final String TPOHERE_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tpohere-message" );

    public static boolean hasTeleportEnabled( Player player ) {
        if ( YMLUtils.getData().get( "teleport.toggle." + player.getUniqueId().toString() + ".disabled" ) != null ) {
            return false;
        }
        return true;
    }

    public static void sendTeleportNotif( Player staff, Player target, String notif ) {
        if ( NOTIFS_ENABLED ) {
            notif = notif.replace( "[STAFF]", staff.getName() ).replace( "[TARGET]", target.getName() );

            for ( Player p : Bukkit.getOnlinePlayers() ) {
                if ( VaultUtils.hasPerms( p, Permissions.TELEPORT_NOTIF ) ) {
                    p.sendMessage( notif );
                }
            }
        }
    }

    public static void sendTargetTeleportDisabled( Player staff, Player target ) {
        String msg = YMLUtils.getConfig().getColoredStr( "commands.teleport.tptoggle.teleport-disabled" );
        msg = msg.replace( "[PLAYER]", target.getName() );
        staff.sendMessage( msg );
    }
}