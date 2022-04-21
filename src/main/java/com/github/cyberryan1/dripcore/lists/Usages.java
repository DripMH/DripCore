package com.github.cyberryan1.dripcore.lists;

import com.github.cyberryan1.dripcore.utils.yml.ConfigUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;

public class Usages {

    private static ConfigUtils getConfigUtils() {
        return YMLUtils.getConfigUtils();
    }

    // global
    public static final String PLAYER_REQUIRED = getConfigUtils().getColoredStr( "global.player-required" );
    public static final String CONSOLE_REQUIRED = getConfigUtils().getColoredStr( "global.console-required" );

    // gamemode
    public static final String GAMEMODE_ADVENTURE = getConfigUtils().getColoredStr( "commands.gamemode.adventure.usage" );
    public static final String GAMEMODE_CREATIVE = getConfigUtils().getColoredStr( "commands.gamemode.creative.usage" );
    public static final String GAMEMODE_SPECTATOR = getConfigUtils().getColoredStr( "commands.gamemode.spectator.usage" );
    public static final String GAMEMODE_SURVIVAL = getConfigUtils().getColoredStr( "commands.gamemode.survival.usage" );

    // flight
    public static final String FLIGHT = getConfigUtils().getColoredStr( "commands.flight.usage" );

    // teleport
    public static final String TELEPORT = getConfigUtils().getColoredStr( "commands.teleport.tp.usage" );
    public static final String TELEPORT_HERE = getConfigUtils().getColoredStr( "commands.teleport.tphere.usage" );
    public static final String TELEPORT_OVERRIDE = getConfigUtils().getColoredStr( "commands.teleport.tpo.usage" );
    public static final String TELEPORT_OVERRIDE_HERE = getConfigUtils().getColoredStr( "commands.teleport.tpohere.usage" );
    public static final String TELEPORT_TOGGLE = getConfigUtils().getColoredStr( "commands.teleport.tptoggle.usage" );

    // enderchest
    public static final String ENDERCHEST = getConfigUtils().getColoredStr( "commands.enderchest.usage" );

    // invsee
    public static final String INVSEE = getConfigUtils().getColoredStr( "commands.invsee.usage" );

    // tags
    public static final String TAGS = getConfigUtils().getColoredStr( "commands.tags.usage" );

    // broadcast
    public static final String BROADCAST = getConfigUtils().getColoredStr( "commands.broadcast.usage" );
}