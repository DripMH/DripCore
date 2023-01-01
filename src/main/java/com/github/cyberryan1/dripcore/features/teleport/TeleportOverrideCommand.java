package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportOverrideCommand extends CyberCommand {

    public TeleportOverrideCommand() {
        super(
                "tpo",
                YMLUtils.getConfigUtils().getStr( "commands.teleport.tpo.permission" ),
                "&8/&7tpo &b(player)"
        );
        setDemandPlayer( true );
        setDemandPermission( true );
        setMinArgLength( 1 );
        setArgType( 0, ArgType.ONLINE_PLAYER );

        register( true );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();
        final Player target = command.getPlayerAtArg( 0 );

        player.teleport( target.getLocation() );
        CyberMsgUtils.sendMsg( player, "&7You have been forcefully teleported to &b" + target.getName() );
        TeleportUtils.sendTeleportNotif( player, target, TeleportUtils.TPO_NOTIF );
        return true;
    }
}