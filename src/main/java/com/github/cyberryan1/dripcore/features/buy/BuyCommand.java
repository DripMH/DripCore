package com.github.cyberryan1.dripcore.features.buy;

import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BuyCommand extends BaseCommand {

    public BuyCommand() {
        super( "buy", null, null, null );
    }


    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {
        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        String link = YMLUtils.getConfig().getStr( "commands.buy.store-link" );
        sender.spigot().sendMessage( new ComponentBuilder()
                .append( getColorizedStr( "\n&9&lSTORE &7» &bClick Here\n" ) )
                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) )
                .create()
        );

        return true;
    }
}