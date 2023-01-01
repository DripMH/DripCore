package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportCommand extends CyberCommand {

    private final String TELEPORT_TOGGLE_BYPASS_PERM;

    public TeleportCommand() {
        super( 
                "tp",
                YMLUtils.getConfigUtils().getStr( "commands.teleport.tp.permission" ),
                "&8/&7tp &b(player)"
        );
        setDemandPlayer( true );
        setDemandPermission( true );
        setMinArgLength( 1 );
        setArgType( 0, ArgType.ONLINE_PLAYER );

        register( true );

        TELEPORT_TOGGLE_BYPASS_PERM = YMLUtils.getConfigUtils().getStr( "commands.teleport.tptoggle.permission-bypass" );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();
        final Player target = command.getPlayerAtArg( 0 );

        if ( TeleportUtils.hasTeleportEnabled( target ) == false ) {
            if ( CyberVaultUtils.hasPerms( player, TELEPORT_TOGGLE_BYPASS_PERM ) == false ) {
                TeleportUtils.sendTargetTeleportDisabled( player, target );
                return true;
            }
        }

        player.teleport( target.getLocation() );
        CyberMsgUtils.sendMsg( player, "&7You have been teleported to &b" + target.getName() );
        TeleportUtils.sendTeleportNotif( player, target, TeleportUtils.TP_NOTIF );
        return true;
    }
}