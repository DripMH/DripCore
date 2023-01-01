package com.github.cyberryan1.dripcore.features.playtime;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.command.settings.ArgType;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaytimeCommand extends CyberCommand {

    public PlaytimeCommand() {
        super(
                "playtime",
                "&8/&7playtime &b[player]"
        );
        setDemandPlayer( true );
        setMinArgLength( 0 );
        setArgType( 0, ArgType.OFFLINE_PLAYER );

        register( true );
    }

    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        final Player player = command.getPlayer();
        OfflinePlayer target = player;

        if ( command.getArgs().length >= 1 ) {
            target = command.getOfflinePlayerAtArg( 0 );
        }

        long playtime = PlaytimeManager.getPlaytimeSeconds( target );

        final long DAYS = playtime / 86400;
        playtime -= DAYS * 86400;
        final long HOURS = playtime / 3600;
        playtime -= HOURS * 3600;
        final long MINUTES = playtime / 60;
        playtime -= MINUTES * 60;
        final long SECONDS = playtime;

        CyberMsgUtils.sendMessage( player,
                "&8",
                "&b" + target.getName() + "&7's Playtime &8- ",
                "&8",
                " &7Days: &b" + DAYS,
                " &7Hours: &b" + HOURS,
                " &7Minutes: &b" + MINUTES,
                " &7Seconds: &b" + SECONDS,
                "&8"
        );

        return true;
    }
}