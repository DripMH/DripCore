package com.github.cyberryan1.dripcore.features.rules;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;

import java.util.List;

public class RulesCommand extends CyberCommand {

    final String rules[];

    public RulesCommand() {
        super(
                "rules",
                "&8/&7rules"
        );

        register( false );

        rules = YMLUtils.getConfig().getColoredStrList( "commands.rules.rules-message" );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        CyberMsgUtils.sendMsg( command.getSender(), rules );
        return true;
    }
}