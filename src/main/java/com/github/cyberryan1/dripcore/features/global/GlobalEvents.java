package com.github.cyberryan1.dripcore.features.global;

import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class GlobalEvents implements Listener {

    private static boolean HUNGER_ENABLED = YMLUtils.getConfig().getBool( "features.no-hunger" );

    @EventHandler
    public void onHungerChange( FoodLevelChangeEvent event ) {
        if ( HUNGER_ENABLED ) {
            event.setFoodLevel( 20 );
        }
    }
}