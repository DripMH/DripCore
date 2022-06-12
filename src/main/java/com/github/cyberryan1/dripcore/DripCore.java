package com.github.cyberryan1.dripcore;

import com.github.cyberryan1.cybercore.CyberCore;
import com.github.cyberryan1.cybercore.utils.VaultUtils;
import com.github.cyberryan1.dripcore.features.anticommand.AntiCommandEvents;
import com.github.cyberryan1.dripcore.features.broadcast.BroadcastCommand;
import com.github.cyberryan1.dripcore.features.buy.BuyCommand;
import com.github.cyberryan1.dripcore.features.discord.DiscordCommand;
import com.github.cyberryan1.dripcore.features.enderchest.EnderchestCommand;
import com.github.cyberryan1.dripcore.features.flight.FlightCommand;
import com.github.cyberryan1.dripcore.features.gamemode.AdventureCommand;
import com.github.cyberryan1.dripcore.features.gamemode.CreativeCommand;
import com.github.cyberryan1.dripcore.features.gamemode.SpectatorCommand;
import com.github.cyberryan1.dripcore.features.gamemode.SurvivalCommand;
import com.github.cyberryan1.dripcore.features.global.GlobalEvents;
import com.github.cyberryan1.dripcore.features.invsee.InvseeCommand;
import com.github.cyberryan1.dripcore.features.ping.PingCommand;
import com.github.cyberryan1.dripcore.features.playtime.PlaytimeCommand;
import com.github.cyberryan1.dripcore.features.playtime.PlaytimeManager;
import com.github.cyberryan1.dripcore.features.rules.RulesCommand;
import com.github.cyberryan1.dripcore.features.tags.TagsCommand;
import com.github.cyberryan1.dripcore.features.teleport.*;
import com.github.cyberryan1.dripcore.features.workbench.WorkbenchCommand;
import com.github.cyberryan1.dripcore.utils.yml.YMLUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class DripCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Initialize Things
        CyberCore.setPlugin( this );
        new VaultUtils();

        // Update or reload config/data files
        YMLUtils.getConfig().getYMLManager().reloadConfig();
        YMLUtils.getConfig().getYMLManager().updateConfig();
        YMLUtils.getData().getYMLManager().reloadConfig();

        YMLUtils.getDataUtils().sendPathNotFoundWarns( false );

        // Register commands
        registerAllCommands();
        // Register events
        registerAllEvents();

        PlaytimeManager.initializeAllPlayers();
    }

    @Override
    public void onDisable() {
        PlaytimeManager.saveAllPlayers();
    }

    private void registerAllCommands() {
        AdventureCommand adventureCommand = new AdventureCommand();
        this.getCommand( "gma" ).setExecutor( adventureCommand );
        this.getCommand( "gma" ).setTabCompleter( adventureCommand );
        CreativeCommand creativeCommand = new CreativeCommand();
        this.getCommand( "gmc" ).setExecutor( creativeCommand );
        this.getCommand( "gmc" ).setTabCompleter( creativeCommand );
        SpectatorCommand spectatorCommand = new SpectatorCommand();
        this.getCommand( "gmsp" ).setExecutor( spectatorCommand );
        this.getCommand( "gmsp" ).setTabCompleter( spectatorCommand );
        SurvivalCommand survivalCommand = new SurvivalCommand();
        this.getCommand( "gms" ).setExecutor( survivalCommand );
        this.getCommand( "gms" ).setTabCompleter( survivalCommand );

        FlightCommand flightCommand = new FlightCommand();
        this.getCommand( "flight" ).setExecutor( flightCommand );
        this.getCommand( "flight" ).setTabCompleter( flightCommand );

        TeleportCommand teleportCommand = new TeleportCommand();
        this.getCommand( "tp" ).setExecutor( teleportCommand );
        this.getCommand( "tp" ).setTabCompleter( teleportCommand );
        TeleportHereCommand teleportHereCommand = new TeleportHereCommand();
        this.getCommand( "tphere" ).setExecutor( teleportHereCommand );
        this.getCommand( "tphere" ).setTabCompleter( teleportHereCommand );
        TeleportOverrideCommand teleportOverrideCommand = new TeleportOverrideCommand();
        this.getCommand( "tpo" ).setExecutor( teleportOverrideCommand );
        this.getCommand( "tpo" ).setTabCompleter( teleportOverrideCommand );
        TeleportHereOverrideCommand teleportHereOverrideCommand = new TeleportHereOverrideCommand();
        this.getCommand( "tpohere" ).setExecutor( teleportHereOverrideCommand );
        this.getCommand( "tpohere" ).setTabCompleter( teleportHereOverrideCommand );
        TeleportToggleCommand teleportToggleCommand = new TeleportToggleCommand();
        this.getCommand( "tptoggle" ).setExecutor( teleportToggleCommand );
        this.getCommand( "tptoggle" ).setTabCompleter( teleportToggleCommand );

        PingCommand pingCommand = new PingCommand();
        this.getCommand( "ping" ).setExecutor( pingCommand );
        this.getCommand( "ping" ).setTabCompleter( pingCommand );

        BuyCommand buyCommand = new BuyCommand();
        this.getCommand( "buy" ).setExecutor( buyCommand );
        this.getCommand( "buy" ).setTabCompleter( buyCommand );

        WorkbenchCommand workbenchCommand = new WorkbenchCommand();
        this.getCommand( "workbench" ).setExecutor( workbenchCommand );
        this.getCommand( "workbench" ).setTabCompleter( workbenchCommand );

        EnderchestCommand enderchestCommand = new EnderchestCommand();
        this.getCommand( "enderchest" ).setExecutor( enderchestCommand );
        this.getCommand( "enderchest" ).setTabCompleter( enderchestCommand );

        InvseeCommand invseeCommand = new InvseeCommand();
        this.getCommand( "invsee" ).setExecutor( invseeCommand );
        this.getCommand( "invsee" ).setTabCompleter( invseeCommand );

        DiscordCommand discordCommand = new DiscordCommand();
        this.getCommand( "discord" ).setExecutor( discordCommand );
        this.getCommand( "discord" ).setTabCompleter( discordCommand );

        TagsCommand tagsCommand = new TagsCommand();
        this.getCommand( "tags" ).setExecutor( tagsCommand );
        this.getCommand( "tags" ).setTabCompleter( tagsCommand );

        BroadcastCommand broadcastCommand = new BroadcastCommand();
        this.getCommand( "broadcast" ).setExecutor( broadcastCommand );
        this.getCommand( "broadcast" ).setTabCompleter( broadcastCommand );

        RulesCommand rulesCommand = new RulesCommand();
        this.getCommand( "rules" ).setExecutor( rulesCommand );
        this.getCommand( "rules" ).setTabCompleter( rulesCommand );

        PlaytimeCommand playtimeCommand = new PlaytimeCommand();
        this.getCommand( "playtime" ).setExecutor( playtimeCommand );
        this.getCommand( "playtime" ).setTabCompleter( playtimeCommand );
    }

    private void registerAllEvents() {
        this.getServer().getPluginManager().registerEvents( new GlobalEvents(), this );
        this.getServer().getPluginManager().registerEvents( new AntiCommandEvents(), this );
    }
}