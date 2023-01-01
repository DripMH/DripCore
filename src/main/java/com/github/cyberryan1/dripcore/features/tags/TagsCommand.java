package com.github.cyberryan1.dripcore.features.tags;

import com.github.cyberryan1.cybercore.spigot.CyberCore;
import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberColorUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TagsCommand extends CyberCommand {

    private static ArrayList<UUID> cooldown = new ArrayList<>();

    public TagsCommand() {
        super(
                "tags",
                YMLUtils.getConfigUtils().getStr( "commands.tags.permission" ),
                "&8/&7tags &b(apply/appeal/report) (player)"
        );
        setDemandPlayer( true );
        setDemandPermission( true );
        setMinArgLength( 2 );
        setArgType( 0, ArgType.STRING );
        setStringArgOptions( 0, List.of( "apply", "appeal", "report" ) );
        setArgType( 1, ArgType.ONLINE_PLAYER );

        register( true );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();
        final String tagType = command.getArg( 0 );
        final Player target = command.getPlayerAtArg( 1 );


        if ( cooldown.contains( target.getUniqueId() ) ) {
            CyberMsgUtils.sendMsg( player, "&b" + target.getName() + " &7has already been sent a tag within the last 15 seconds!" );
            return true;
        }

        String discordLink = YMLUtils.getConfig().getStr( "commands.discord.link" );

        if ( tagType.equalsIgnoreCase( "apply" ) ) {
            CyberMsgUtils.sendMsg( player, "&7You have sent the &bapply &7tag to &b" + target.getName() );
            target.spigot().sendMessage( new ComponentBuilder()
                    .append( CyberColorUtils.getColored( "\n  &7To apply for staff, join our &9&ndiscord&7.\n" ) )
                    .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                    .append( CyberColorUtils.getColored( "  &7Look in the &9#applications&7 channel, and look at the pinned message.\n" ) )
                    .append( CyberColorUtils.getColored( "  &7Make sure to &9read the message&7 in it's entirety, then &9click the link&7 for the forum to apply. Good luck!\n" ) )
                    .append( CyberColorUtils.getColored( "  &7&o(This message was shown to you by a staff member)\n" ) )
                    .create()
            );
            target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
            registerCooldown( target );
        }

        else if ( tagType.equalsIgnoreCase( "appeal" ) ) {
            CyberMsgUtils.sendMsg( player, "&7You have sent the &bappeal &7tag to &b" + target.getName() );
            target.spigot().sendMessage( new ComponentBuilder()
                    .append( CyberColorUtils.getColored( "\n  &7To make an appeal, join our &9&ndiscord&7.\n" ) )
                    .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                    .append( CyberColorUtils.getColored( "  &7Then create an appeal ticket in &b#tickets&7.\n" ) )
                    .append( CyberColorUtils.getColored( "  &7&o(This message was shown to you by a staff member)\n" ) )
                    .create()
            );
            target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
            registerCooldown( target );
        }

        else if ( tagType.equalsIgnoreCase( "report" ) ) {
            CyberMsgUtils.sendMsg( player, "&7You have sent the report tag to &b" + target.getName() );
            target.spigot().sendMessage( new ComponentBuilder()
                    .append( CyberColorUtils.getColored( "\n  &7To report someone, please do &b/report (player)&7.\n" ) )
                    .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                    .append( CyberColorUtils.getColored( "  &7Then click what you want to report the player for.\n" ) )
                    .append( CyberColorUtils.getColored( "  &7If you wish to &bsubmit evidence&7,\n" ) )
                    .append( CyberColorUtils.getColored( "  &7Join our &9&ndiscord&7 and make a ticket in &b#tickets&7.\n" ) )
                    .append( CyberColorUtils.getColored( "  &7&o(This message was shown to you by a staff member)\n" ) )
                    .create()
            );
            target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
            registerCooldown( target );
        }

        return true;
    }

    private static void registerCooldown( Player player ) {
        cooldown.add( player.getUniqueId() );
        Bukkit.getScheduler().runTaskLater( CyberCore.getPlugin(), () -> {
            cooldown.remove( player.getUniqueId() );
        }, 100L );
    }
}