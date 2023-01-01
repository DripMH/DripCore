package com.github.cyberryan1.dripcore.features.ping;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class PingCommand extends CyberCommand {

    public PingCommand() {
        super(
                "ping",
                "&8/&7ping &b[player]"
        );
        setDemandPlayer( true );
        setMinArgLength( 0 );
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

        if ( command.getArgs().length == 0 ) {
            CyberMsgUtils.sendMsg( player, "&7Your ping is &b" + player.getPing() );
        }

        else {
            Player target = command.getPlayerAtArg( 0 );
            CyberMsgUtils.sendMsg( player, "&b" + target.getName() + "&7's ping is &b" + target.getPing() );
        }

        return true;
    }
}