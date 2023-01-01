package com.github.cyberryan1.dripcore.features.gamemode;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.List;

public class CreativeCommand extends CyberCommand {

    public CreativeCommand() {
        super(
                "gmc",
                YMLUtils.getConfigUtils().getStr( "commands.gamemode.creative.permission" ),
                "&8/&7gmc &b[player]"
        );
        setDemandPlayer( true );
        setDemandPermission( true );
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
            player.setGameMode( GameMode.CREATIVE );
            CyberMsgUtils.sendMsg( player, "&7Your gamemode has been set to &bCREATIVE" );
        }

        else {
            final Player target = command.getPlayerAtArg( 0 );
            target.setGameMode( GameMode.CREATIVE );
            CyberMsgUtils.sendMsg( player, "&7Set &b" + target.getName() + "&7's gamemode to &bCREATIVE" );
        }

        return true;
    }
}