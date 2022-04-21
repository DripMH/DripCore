package com.github.cyberryan1.dripcore.features.tags;

import com.github.cyberryan1.cybercore.CyberCore;
import com.github.cyberryan1.cybercore.utils.CoreUtils;
import com.github.cyberryan1.dripcore.features.BaseCommand;
import com.github.cyberryan1.dripcore.lists.PermissionMessages;
import com.github.cyberryan1.dripcore.lists.Permissions;
import com.github.cyberryan1.dripcore.lists.Usages;
import com.github.cyberryan1.dripcore.utils.CommandUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TagsCommand extends BaseCommand {

    private static ArrayList<UUID> cooldown = new ArrayList<>();

    public TagsCommand() {
        super( "tags", Permissions.TAGS, PermissionMessages.TAGS, Usages.TAGS );
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String label, String[] args ) {

        if ( permissionsAllowed( sender ) ) {
            if ( args.length == 0 ) {
                ArrayList<String> toReturn = new ArrayList<>();
                Collections.addAll( toReturn, "apply", "appeal", "report" );
                return toReturn;
            }
            else if ( args.length == 2 ) {
                return CommandUtils.matchOnlinePlayers( args[1] );
            }
        }

        return null;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        if ( permissionsAllowed( sender ) == false ) {
            sendPermissionMsg( sender );
            return true;
        }

        if ( args.length >= 2 ) {
            if ( CoreUtils.isValidUsername( args[1] ) ) {
                Player target = Bukkit.getPlayer( args[1] );
                if ( target != null ) {
                    if ( cooldown.contains( target.getUniqueId() ) ) {
                        sender.sendMessage( getColorizedStr( "&y" + target.getName() + "&u has already been sent a tag within the last 15 seconds!" ) );
                        return true;
                    }

                    String discordLink = YMLUtils.getConfig().getStr( "commands.discord.link" );

                    if ( args[0].equalsIgnoreCase( "apply" ) ) {
                        sender.sendMessage( getColorizedStr( "&uSent the &yapply&u tag to &y" + target.getName() ) );
                        target.spigot().sendMessage( new ComponentBuilder()
                                .append( getColorizedStr( "\n  &7To apply for staff, join our &9&ndiscord&7.\n" ) )
                                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                                .append( getColorizedStr( "  &7Look in the &9#applications&7 channel, and look at the pinned message.\n" ) )
                                .append( getColorizedStr( "  &7Make sure to &9read the message&7 in it's entirety, then &9click the link&7 for the forum to apply. Good luck!\n" ) )
                                .append( getColorizedStr( "  &7&o(This message was shown to you by a staff member)\n" ) )
                                .create()
                        );
                        target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
                        registerCooldown( target );
                    }

                    else if ( args[0].equalsIgnoreCase( "appeal" ) ) {
                        sender.sendMessage( getColorizedStr( "&uSent the &yappeal&u tag to &y" + target.getName() ) );
                        target.spigot().sendMessage( new ComponentBuilder()
                                .append( getColorizedStr( "\n  &7To make an appeal, join our &9&ndiscord&7.\n" ) )
                                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                                .append( getColorizedStr( "  &7Then create an appeal ticket in &b#tickets&7.\n" ) )
                                .append( getColorizedStr( "  &7&o(This message was shown to you by a staff member)\n" ) )
                                .create()
                        );
                        target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
                        registerCooldown( target );
                    }

                    else if ( args[0].equalsIgnoreCase( "report" ) ) {
                        sender.sendMessage( getColorizedStr( "&uSent the &yreport&u tag to &y" + target.getName() ) );
                        target.spigot().sendMessage( new ComponentBuilder()
                                .append( getColorizedStr( "\n  &7To report someone, please do &b/report (player)&7.\n" ) )
                                .event( new ClickEvent( ClickEvent.Action.OPEN_URL, discordLink ) )
                                .append( getColorizedStr( "  &7Then click what you want to report the player for.\n" ) )
                                .append( getColorizedStr( "  &7If you wish to &bsubmit evidence&7,\n" ) )
                                .append( getColorizedStr( "  &7Join our &9&ndiscord&7 and make a ticket in &b#tickets&7.\n" ) )
                                .append( getColorizedStr( "  &7&o(This message was shown to you by a staff member)\n" ) )
                                .create()
                        );
                        target.playSound( target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
                        registerCooldown( target );
                    }

                    else {
                        sendUsage( sender );
                    }
                }

                else {
                    sendInvalidPlayerArg( sender, args[1] );
                }
            }

            else {
                sendInvalidPlayerArg( sender, args[1] );
            }
        }

        else {
            sendUsage( sender );
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