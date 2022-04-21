package com.github.cyberryan1.dripcore.lists;

import com.github.cyberryan1.dripcore.utils.yml.ConfigUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;

public class Permissions {

    private static ConfigUtils getConfigUtils() {
        return YMLUtils.getConfigUtils();
    }

    // gamemode
    public static final String GAMEMODE_ADVENTURE = getConfigUtils().getStr( "commands.gamemode.adventure.permission" );
    public static final String GAMEMODE_CREATIVE = getConfigUtils().getStr( "commands.gamemode.creative.permission" );
    public static final String GAMEMODE_SPECTATOR = getConfigUtils().getStr( "commands.gamemode.spectator.permission" );
    public static final String GAMEMODE_SURVIVAL = getConfigUtils().getStr( "commands.gamemode.survival.permission" );

    // flight
    public static final String FLIGHT = getConfigUtils().getStr( "commands.flight.permission" );
    public static final String FLIGHT_OTHERS = getConfigUtils().getStr( "commands.flight.permission-others" );

    // teleport
    public static final String TELEPORT = getConfigUtils().getStr( "commands.teleport.tp.permission" );
    public static final String TELEPORT_HERE = getConfigUtils().getStr( "commands.teleport.tphere.permission" );
    public static final String TELEPORT_OVERRIDE = getConfigUtils().getStr( "commands.teleport.tpo.permission" );
    public static final String TELEPORT_OVERRIDE_HERE = getConfigUtils().getStr( "commands.teleport.tpohere.permission" );
    public static final String TELEPORT_TOGGLE = getConfigUtils().getStr( "commands.teleport.tptoggle.permission" );
    public static final String TELEPORT_TOGGLE_BYPASS = getConfigUtils().getStr( "commands.teleport.tptoggle.permission-bypass" );
    public static final String TELEPORT_NOTIF = getConfigUtils().getStr( "commands.teleport.notifs.permission" );

    // workbench
    public static final String WORKBENCH = getConfigUtils().getStr( "commands.workbench.permission" );

    // enderchest
    public static final String ENDERCHEST = getConfigUtils().getStr( "commands.enderchest.permission" );
    public static final String ENDERCHEST_OTHERS = getConfigUtils().getStr( "commands.enderchest.permission-others" );

    // invsee
    public static final String INVSEE = getConfigUtils().getStr( "commands.invsee.permission" );

    // tags
    public static final String TAGS = getConfigUtils().getStr( "commands.tags.permission" );

    // broadcast
    public static final String BROADCAST = getConfigUtils().getStr( "commands.broadcast.permission" );
}