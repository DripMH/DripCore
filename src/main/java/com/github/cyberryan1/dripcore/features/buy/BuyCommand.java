package com.github.cyberryan1.dripcore.features.buy;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.utils.CyberColorUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.util.List;

public class BuyCommand extends CyberCommand {

    public BuyCommand() {
        super(
                "buy",
                "&8/&7buy"
        );
        setDemandPlayer( true );

        register( false );
    }


    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {

        String link = YMLUtils.getConfig().getStr( "commands.buy.store-link" );
        command.getSender().spigot().sendMessage( new ComponentBuilder()
                .append( CyberColorUtils.getColored( "\n&9&lSTORE &7» &bClick Here\n" ) )
                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) )
                .create()
        );

        return true;
    }
}