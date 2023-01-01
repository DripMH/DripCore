package com.github.cyberryan1.dripcore.features.teleport;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportToggleCommand extends CyberCommand {

    public TeleportToggleCommand() {
        super(
                "tptoggle",
                YMLUtils.getConfigUtils().getStr( "commands.teleport.tptoggle.permission" ),
                "&8/&7tptoggle"
        );
        setDemandPlayer( true );
        setDemandPermission( true );

        register( false );
    }
    
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();

        if ( TeleportUtils.hasTeleportEnabled( player ) == false ) {
            // enabling teleports
            YMLUtils.getData().set( "teleport.toggle." + player.getUniqueId().toString(), null );
            YMLUtils.getData().save();

            CyberMsgUtils.sendMsg( player, "&aEnabled&7 other players to teleport to you" );
        }

        else {
            // disabling teleports
            YMLUtils.getData().set( "teleport.toggle." + player.getUniqueId().toString() + ".disabled", true );
            YMLUtils.getData().save();

            CyberMsgUtils.sendMsg( player, "&acDisabled&7 other players from teleporting to you" );
        }

        return true;
    }
}
