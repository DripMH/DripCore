package com.github.cyberryan1.dripcore.features.workbench;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class WorkbenchCommand extends CyberCommand {

    public WorkbenchCommand() {
        super(
                "workbench",
                YMLUtils.getConfigUtils().getStr( "commands.workbench.permission" ),
                "&8/&7workbench"
        );
        setDemandPlayer( true );
        setDemandPermission( true );

        register( false );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();
        player.openWorkbench( null, true );
        return true;
    }
}