package com.github.cyberryan1.dripcore.features.discord;

import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DiscordCommand extends BaseCommand {

    public DiscordCommand() {
        super( "discord", null, null, null );
    }


    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        String link = YMLUtils.getConfig().getStr( "commands.discord.link" );
        sender.spigot().sendMessage( new ComponentBuilder()
                .append( getColorizedStr( "\n&9&lDISCORD &7» &bClick Here\n" ) )
                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) )
                .create()
        );

        return true;
    }
}