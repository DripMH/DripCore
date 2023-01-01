package com.github.cyberryan1.dripcore.features.invsee;

import com.github.cyberryan1.cybercore.spigot.gui.Gui;
import com.github.cyberryan1.cybercore.spigot.gui.GuiItem;
import com.github.cyberryan1.cybercore.spigot.gui.events.GuiClickEvent;
import com.github.cyberryan1.cybercore.spigot.utils.CyberGuiUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberItemUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InvseeGui {

    //                      Staff
    //                              Open GUI
    public static final Map<Player, InvseeGui> openGuis = new HashMap<>();
    //                      Staff
    //                              Target
    public static final Map<Player, Player> staffTargets = new HashMap<>();
    private static String INVSEE_EDIT_PERM = null;
    //                Target Inventory Slot
    //                          GUI Inventory Slot
    private static final Map<Integer, Integer> slotMap = new HashMap<>();

    static {
        // Main inventory slots
        int guiSlot = 0;
        int invSlot = 9;
        for ( int i = 0; i < 27; i++ ) {
            slotMap.put( invSlot, guiSlot );
            invSlot++;
            guiSlot++;
        }

        // Hotbar slots
        guiSlot = 45;
        invSlot = 0;
        for ( int i = 0; i < 9; i++ ) {
            slotMap.put( invSlot, guiSlot );
            invSlot++;
            guiSlot++;
        }

        // Armor slots
        guiSlot = 41;
        invSlot = 36;
        for ( int i = 0; i < 4; i++ ) {
            slotMap.put( invSlot, guiSlot );
            invSlot++;
            guiSlot++;
        }
    }

    private final Gui gui;
    private final Player staff;
    private final Player target;
    private boolean open = false;

    public InvseeGui( Player staff, Player target ) {
        this.staff = staff;
        this.target = target;

        if ( INVSEE_EDIT_PERM == null ) { INVSEE_EDIT_PERM = YMLUtils.getConfigUtils().getStr( "commands.invsee.edit-permission" ); }
        final ItemStack backgroundGlass = CyberGuiUtils.getBackgroundGlass();
        backgroundGlass.setType( Material.WHITE_STAINED_GLASS_PANE );

        this.gui = new Gui( "&b" + target.getName() + "&f's Inventory", 6, backgroundGlass );
        this.gui.setCloseEvent( () -> {
            openGuis.remove( staff );
            staffTargets.remove( staff );
        } );
        this.gui.setAllowPlayerInvClicks( true );
    }

    public void open() {
        update();
        this.gui.openInventory( this.staff );
        openGuis.put( this.staff, this );
        staffTargets.put( this.staff, this.target );
        open = true;
    }

    public void update() {
        final Inventory targetInv = target.getInventory();
        final ItemStack EMPTY_ITEM = CyberItemUtils.createItem( Material.BLACK_STAINED_GLASS_PANE, "&7" );

        final GuiClickEvent clickEvent = ( guiItem ) -> {
            if ( CyberVaultUtils.hasPerms( this.staff, INVSEE_EDIT_PERM ) == false ) { return; }
            if ( guiItem.getSlot() >= 27 && guiItem.getSlot() <= 40 ) { return; }
            final int targetInvSlot = slotMap.keySet().stream()
                    .filter( key -> slotMap.get( key ) == guiItem.getSlot() )
                    .findFirst()
                    .orElse( -1 );
            if ( targetInvSlot == -1 ) { return; }

            ItemStack targetItem = targetInv.getItem( targetInvSlot );
            if ( targetItem != null ) {
                if ( targetItem.equals( EMPTY_ITEM ) ) { targetItem = new ItemStack( Material.AIR ); }
                else { targetItem = targetItem.clone(); }
            }
            ItemStack staffCursor = guiItem.getEvent().getCursor();
            if ( staffCursor != null ) { staffCursor = staffCursor.clone(); }

            // Getting the item from the target's inventory
            if ( staffCursor == null || staffCursor.getType().isAir() ) {
                targetInv.setItem( targetInvSlot, new ItemStack( Material.AIR ) );
                guiItem.getEvent().setCursor( targetItem );
            }

            // Swapping the the item in the target's inventory with the item in the staff's cursor
            else {
                targetInv.setItem( targetInvSlot, staffCursor );
                guiItem.getEvent().setCursor( targetItem );
            }

            update();
        };

        for ( int targetInvIndex : slotMap.keySet() ) {
            final int guiIndex = slotMap.get( targetInvIndex );
            ItemStack item = targetInv.getItem( targetInvIndex );
            if ( item == null || item.getType().isAir() ) { item = EMPTY_ITEM.clone(); }

            if ( open ) { gui.updateItem( new GuiItem( item, guiIndex, clickEvent ) ); }
            else { gui.addItem( new GuiItem( item, guiIndex, clickEvent ) ); }
        }
    }

    public Player getStaff() {
        return staff;
    }

    public Player getTarget() {
        return target;
    }

    public Gui getGui() {
        return gui;
    }
}