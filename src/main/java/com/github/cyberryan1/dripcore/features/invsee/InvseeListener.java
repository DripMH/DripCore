package com.github.cyberryan1.dripcore.features.invsee;

import com.github.cyberryan1.cybercore.spigot.CyberCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class InvseeListener implements Listener {

    @EventHandler
    public void onInventoryClick( InventoryClickEvent event ) {
        if ( event.getWhoClicked() instanceof Player == false ) { return; }
        checkUpdate( ( Player ) event.getWhoClicked() );
    }

    @EventHandler
    public void onInventoryDrag( InventoryDragEvent event ) {
        if ( event.getWhoClicked() instanceof Player == false ) { return; }
        checkUpdate( ( Player ) event.getWhoClicked() );
    }

    @EventHandler
    public void onPlayerDrop( PlayerDropItemEvent event ) {
        checkUpdate( event.getPlayer() );
    }

    @EventHandler
    public void onEntityPickup( EntityPickupItemEvent event ) {
        if ( event.getEntity() instanceof Player == false ) { return; }
        checkUpdate( ( Player ) event.getEntity() );
    }

    @EventHandler
    public void onBlockBreak( BlockBreakEvent event ) {
        checkUpdate( event.getPlayer() );
    }

    @EventHandler
    public void onBlockPlace( BlockPlaceEvent event ) {
        checkUpdate( event.getPlayer() );
    }

    @EventHandler
    public void onPlayerConsume( PlayerItemConsumeEvent event ) {
        checkUpdate( event.getPlayer() );
    }

    @EventHandler
    public void onDamage( EntityDamageEvent event ) {
        if ( event.getEntity() instanceof Player == false ) { return; }
        checkUpdate( ( Player ) event.getEntity() );
    }

    @EventHandler
    public void onDeath( PlayerDeathEvent event ) {
        checkUpdate( event.getEntity() );
    }

    private void checkUpdate( Player target ) {
        Bukkit.getScheduler().runTaskLater( CyberCore.getPlugin(), () -> {
            for ( Player staff : InvseeGui.staffTargets.keySet() ) {
                if ( InvseeGui.staffTargets.get( staff ).getUniqueId().equals( target.getUniqueId() ) ) {
                    InvseeGui.openGuis.get( staff ).update();
                }
            }
        }, 2L );
    }
}