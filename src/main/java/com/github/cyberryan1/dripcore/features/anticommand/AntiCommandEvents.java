package com.github.cyberryan1.dripcore.features.anticommand;

import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.List;

public class AntiCommandEvents implements Listener {

    private static List<String> preventedCommands = new ArrayList<>( List.of(
            "/pl", "/plugin", "/plugins", "/say", "/me", "/?", "/w", "/whisper", "/help", "/ver", "/?", "/version", "/teammsg", "/tell"
    ) );

    private static String JUNIOR_MOD_PERM = "";

    public AntiCommandEvents() {
        JUNIOR_MOD_PERM = YMLUtils.getConfig().getStr( "global.junior-mod-perm" );
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent( PlayerCommandPreprocessEvent event ) {
        final String LABEL = event.getMessage().split( " " )[0];
        boolean contains = false;
        for ( String str : preventedCommands ) {
            if ( LABEL.equalsIgnoreCase( str ) ) {
                contains = true;
                break;
            }
        }
        if ( contains || LABEL.contains( ":" ) ) {
            if ( CyberVaultUtils.hasPerms( event.getPlayer(), JUNIOR_MOD_PERM ) == false ) {
                event.setCancelled( true );

                if ( event.getMessage().split( " " )[0].contains( "plugin" ) || event.getMessage().split( " " )[0].equalsIgnoreCase( "pl" ) ) {
                    CyberMsgUtils.sendMsg( event.getPlayer(), "&fPlugins (7): &aDripCore&f, &aDripMain&f, &aDripDonors&f, &aDripAPI&f, &aNow&f, &aFuck&f, &aOff" );
                }
                else {
                    CyberMsgUtils.sendMsg( event.getPlayer(), "&cYou cannot run this command" );
                }
            }
        }
    }

    @EventHandler
    public void onTabCompleteEvent( TabCompleteEvent event ) {
        final String MSG = event.getBuffer();
        if ( CyberVaultUtils.hasPerms( event.getSender(), JUNIOR_MOD_PERM ) ) { return; }
        if ( MSG.length() <= 2 ) { event.setCancelled( true ); return; }

        for ( String str : preventedCommands ) {
            if ( MSG.startsWith( str + " " ) ) {
                event.setCancelled( true );
                return;
            }
        }
    }
}