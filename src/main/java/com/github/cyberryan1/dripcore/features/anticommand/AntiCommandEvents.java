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

    private static List<String> preventedCommands = new ArrayList<>( List.of( "/pl", "/plugin", "/plugins", "/say", "/me", "/?" ));

    @EventHandler
    public void onPlayerCommandPreprocessEvent( PlayerCommandPreprocessEvent event ) {
        if ( preventedCommands.contains( event.getMessage() ) || event.getMessage().split( " " )[0].contains( ":" ) ) {
            if ( VaultUtils.hasPerms( event.getPlayer(), YMLUtils.getConfig().getStr( "global.junior-mod-perm" ) ) == false ) {
                event.setCancelled( true );

                if ( event.getMessage().split( " " )[0].contains( "plugin" ) || event.getMessage().split( " " )[0].equalsIgnoreCase( "pl" ) ) {
                    event.getPlayer().sendMessage( CoreUtils.getColored( "&fPlugins (5): &aDripCore&f, &aDripMain&f, &aNow&f, &aFuck&f, &aOff") );
                }
                event.getPlayer().sendMessage( CoreUtils.getColored( "&cYou cannot run this command" ) );
            }
        }
    }
}