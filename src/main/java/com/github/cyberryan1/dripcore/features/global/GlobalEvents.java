package com.github.cyberryan1.dripcore.features.global;

import com.github.cyberryan1.dripcore.features.playtime.PlaytimeManager;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GlobalEvents implements Listener {

    private static boolean HUNGER_ENABLED = YMLUtils.getConfig().getBool( "features.no-hunger" );
    private static boolean NIGHTVISION_ENABLED = YMLUtils.getConfig().getBool( "features.night-vision" );

    @EventHandler
    public void onHungerChange( FoodLevelChangeEvent event ) {
        if ( HUNGER_ENABLED ) {
            event.setFoodLevel( 20 );
        }
    }

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event ) {
        if ( NIGHTVISION_ENABLED ) {
            event.getPlayer().addPotionEffect( new PotionEffect( PotionEffectType.NIGHT_VISION, 1000000, 10, false, false ) );
        }

        PlaytimeManager.initializePlayer( event.getPlayer() );
    }

    @EventHandler
    public void onPlayerQuit( PlayerQuitEvent event ) {
        PlaytimeManager.savePlayer( event.getPlayer() );
    }
}