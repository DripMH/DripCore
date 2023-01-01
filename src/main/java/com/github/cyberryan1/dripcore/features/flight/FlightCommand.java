package com.github.cyberryan1.dripcore.features.flight;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class FlightCommand extends CyberCommand {

    private final String FLIGHT_OTHERS_PERM;

    public FlightCommand() {
        super(
                "flight",
                YMLUtils.getConfigUtils().getStr( "commands.flight.permission" ),
                "&8/&7fly &b[player]"
        );
        setDemandPlayer( true );
        setDemandPermission( true );
        setMinArgLength( 0 );
        setArgType( 0, ArgType.ONLINE_PLAYER );

        register( true );

        FLIGHT_OTHERS_PERM = YMLUtils.getConfigUtils().getStr( "commands.flight.permission-others" );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();

        if ( command.getArgs().length == 0 ) { // /flight
            if ( player.getAllowFlight() == false ) { // enabling flight
                player.setAllowFlight( true );
                CyberMsgUtils.sendMsg( player, "&7Your flight has been &aenabled" );
            }

            else { // disabling flight
                player.setAllowFlight( false );
                CyberMsgUtils.sendMsg( player, "&7Your flight has been &cdisabled" );
            }
        }

        else { // /flight [player]
            if ( CyberVaultUtils.hasPerms( player, FLIGHT_OTHERS_PERM ) ) {
                final Player target = command.getPlayerAtArg( 0 );

                if ( target.getAllowFlight() == false ) { // enabling flight
                    target.setAllowFlight( true );
                    CyberMsgUtils.sendMsg( player, "&aEnabled &b" + target.getName() + "&7's flight" );
                }
                else { // disabling flight
                    target.setAllowFlight( false );
                    CyberMsgUtils.sendMsg( player, "&cDisabled &b" + target.getName() + "&7's flight" );
                }
            }

            else {
                sendPermissionMsg( player );
            }
        }

        return true;
    }
}