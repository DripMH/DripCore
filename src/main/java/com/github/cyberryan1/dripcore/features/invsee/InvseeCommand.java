package com.github.cyberryan1.dripcore.features.invsee;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class InvseeCommand extends CyberCommand {

    public InvseeCommand() {
        super(
                "invsee",
                YMLUtils.getConfigUtils().getStr( "commands.invsee.permission" ),
                "&8/&7invsee &b(player)"
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
        InvseeGui gui = new InvseeGui( player, target );
        gui.open();
        return true;
    }
}