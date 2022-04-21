package com.github.cyberryan1.dripcore.lists;

import com.github.cyberryan1.dripcore.utils.yml.ConfigUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;

public class PermissionMessages {

    private static ConfigUtils getConfigUtils() {
        return YMLUtils.getConfigUtils();
    }

    // gamemode
    public static final String GAMEMODE_ADVENTURE = getConfigUtils().getColoredStr( "commands.gamemode.adventure.permission-message" );
    public static final String GAMEMODE_CREATIVE = getConfigUtils().getColoredStr( "commands.gamemode.creative.permission-message" );
    public static final String GAMEMODE_SPECTATOR = getConfigUtils().getColoredStr( "commands.gamemode.spectator.permission-message" );
    public static final String GAMEMODE_SURVIVAL = getConfigUtils().getColoredStr( "commands.gamemode.survival.permission-message" );

    // flight
    public static final String FLIGHT = getConfigUtils().getColoredStr( "commands.flight.permission-message" );
    public static final String FLIGHT_OTHERS = getConfigUtils().getColoredStr( "commands.flight.permission-others-message" );

    // teleport
    public static final String TELEPORT = getConfigUtils().getColoredStr( "commands.teleport.tp.permission-message" );
    public static final String TELEPORT_HERE = getConfigUtils().getColoredStr( "commands.teleport.tphere.permission-message" );
    public static final String TELEPORT_OVERRIDE = getConfigUtils().getColoredStr( "commands.teleport.tpo.permission-message" );
    public static final String TELEPORT_OVERRIDE_HERE = getConfigUtils().getColoredStr( "commands.teleport.tpohere.permission-message" );
    public static final String TELEPORT_TOGGLE = getConfigUtils().getColoredStr( "commands.teleport.tptoggle.permission-message" );

    // workbench
    public static final String WORKBENCH = getConfigUtils().getColoredStr( "commands.workbench.permission-message" );

    // enderchest
    public static final String ENDERCHEST = getConfigUtils().getColoredStr( "commands.enderchest.permission-message" );
    public static final String ENDERCHEST_OTHERS = getConfigUtils().getColoredStr( "commands.enderchest.permission-others-message" );

    // invsee
    public static final String INVSEE = getConfigUtils().getColoredStr( "commands.invsee.permission-message" );

    // tags
    public static final String TAGS = getConfigUtils().getStr( "commands.tags.permission-message" );

    // broadcast
    public static final String BROADCAST = getConfigUtils().getColoredStr( "commands.broadcast.permission-message" );
}