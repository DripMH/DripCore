package com.github.cyberryan1.dripcore.features.enderchest;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderchestCommand extends CyberCommand {

    private final String ENDERCHEST_OTHERS_PERM;

    public EnderchestCommand() {
        super(
                "enderchest",
                YMLUtils.getConfigUtils().getStr( "commands.enderchest.permission" ),
                "&8/&7enderchest &b[player]"
        );
        setDemandPermission( true );
        setDemandPlayer( true );
        setMinArgLength( 0 );
        setArgType( 0, ArgType.ONLINE_PLAYER );

        register( true );

        ENDERCHEST_OTHERS_PERM = YMLUtils.getConfigUtils().getStr( "commands.enderchest.permission-others" );
    }


    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();

        if ( command.getArgs().length == 0 ) {
            player.openInventory( player.getEnderChest() );
        }

        else if ( CyberVaultUtils.hasPerms( player, ENDERCHEST_OTHERS_PERM ) ) {
            Player target = command.getPlayerAtArg( 0 );
            player.openInventory( target.getEnderChest() );
        }

        else {
            sendPermissionMsg( player );
        }

        return true;
    }
}