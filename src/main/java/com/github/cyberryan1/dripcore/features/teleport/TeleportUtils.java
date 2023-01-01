package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class TeleportUtils {

    public static boolean NOTIFS_ENABLED = true;

    private static String NOTIF_PERM = "";
    public static String TP_NOTIF = "";
    public static String TPHERE_NOTIF = "";
    public static String TPO_NOTIF = "";
    public static String TPOHERE_NOTIF = "";
    public static String TP_DISABLED = "";

    public static void initialize() {
        NOTIFS_ENABLED = YMLUtils.getConfig().getBool( "commands.teleport.notifs.enabled" );
        NOTIF_PERM = YMLUtils.getConfigUtils().getStr( "commands.teleport.notifs.permission" );
        TP_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tp-message" );
        TPHERE_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tphere-message" );
        TPO_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tpo-message" );
        TPOHERE_NOTIF = YMLUtils.getConfig().getColoredStr( "commands.teleport.notifs.tpohere-message" );
        TP_DISABLED = YMLUtils.getConfig().getColoredStr( "commands.teleport.tptoggle.teleport-disabled" );
    }

    public static boolean hasTeleportEnabled( Player player ) {
        return YMLUtils.getData().get( "teleport.toggle." + player.getUniqueId().toString() + ".disabled" ) == null;
    }

    public static void sendTeleportNotif( Player staff, Player target, String notif ) {
        if ( NOTIFS_ENABLED ) {
            notif = notif.replace( "[STAFF]", staff.getName() ).replace( "[TARGET]", target.getName() );

            for ( Player p : Bukkit.getOnlinePlayers() ) {
                if ( CyberVaultUtils.hasPerms( p, NOTIF_PERM ) ) {
                    CyberMsgUtils.sendMsg( p, notif );
                }
            }
        }
    }

    public static void sendTargetTeleportDisabled( Player staff, Player target ) {
        String msg = TP_DISABLED.replace( "[PLAYER]", target.getName() );
        CyberMsgUtils.sendMsg( staff, msg );
    }
}