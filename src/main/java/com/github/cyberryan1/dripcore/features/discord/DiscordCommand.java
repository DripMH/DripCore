package com.github.cyberryan1.dripcore.features.discord;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.utils.CyberColorUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.util.List;

public class DiscordCommand extends CyberCommand {

    final String DISCORD_LINK;

    public DiscordCommand() {
        super(
                "discord",
                "&8/&7discord"
        );
        setDemandPlayer( true );

        register( false );
        DISCORD_LINK = YMLUtils.getConfig().getStr( "commands.discord.link" );
    }


    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        command.getSender().spigot().sendMessage( new ComponentBuilder()
                .append( CyberColorUtils.getColored( "\n&9&lDISCORD &7» &bClick Here\n" ) )
                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, DISCORD_LINK ) )
                .create()
        );
        return true;
    }
}