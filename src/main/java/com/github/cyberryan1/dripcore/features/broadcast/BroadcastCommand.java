package com.github.cyberryan1.dripcore.features.broadcast;

import com.github.cyberryan1.cybercore.spigot.command.CyberCommand;
import com.github.cyberryan1.cybercore.spigot.command.sent.SentCommand;
import com.github.cyberryan1.cybercore.spigot.utils.CyberColorUtils;
import com.github.cyberryan1.cybercore.spigot.utils.CyberMsgUtils;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class BroadcastCommand extends CyberCommand {

    private final String BROADCAST_FORMAT;

    public BroadcastCommand() {
        super(
                "broadcast",
                YMLUtils.getConfigUtils().getStr( "commands.broadcast.permission" ),
                "&8/&7broadcast &b(message)"
        );
        setDemandPermission( true );
        setMinArgLength( 1 );

        register( false );

        BROADCAST_FORMAT = YMLUtils.getConfig().getColoredStr( "commands.broadcast.broadcast-message" );
    }


    @Override
    public List<String> tabComplete( SentCommand command ) {
        return List.of();
    }

    @Override
    public boolean execute( SentCommand command ) {
        String msg = CyberColorUtils.getColored( BROADCAST_FORMAT.replace( "[MESSAGE]", command.getCombinedArgs( 0 ) ) );
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            CyberMsgUtils.sendMsg( p, "\n", msg, "\n" );
            p.playSound( p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1 );
        }
        return true;
    }
}