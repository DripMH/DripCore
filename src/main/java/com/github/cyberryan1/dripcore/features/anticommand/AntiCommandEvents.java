package com.github.cyberryan1.dripcore.features.anticommand;

import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

public class AntiCommandEvents implements Listener {

    private static List<String> preventedCommands = new ArrayList<>( List.of(
            "/pl", "/plugin", "/plugins", "/say", "/me", "/?", "/w", "/whisper", "/help"
    ) );

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
            if ( VaultUtils.hasPerms( event.getPlayer(), YMLUtils.getConfig().getStr( "global.junior-mod-perm" ) ) == false ) {
                event.setCancelled( true );

                if ( event.getMessage().split( " " )[0].contains( "plugin" ) || event.getMessage().split( " " )[0].equalsIgnoreCase( "pl" ) ) {
                    event.getPlayer().sendMessage( CoreUtils.getColored( "&fPlugins (7): &aDripCore&f, &aDripMain&f, &aDripDonors&f, &aDripAPI&f, &aNow&f, &aFuck&f, &aOff") );
                }
                event.getPlayer().sendMessage( CoreUtils.getColored( "&cYou cannot run this command" ) );
            }
        }
    }
}