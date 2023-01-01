package com.github.cyberryan1.dripcore;

import com.github.cyberryan1.cybercore.spigot.CyberCore;
import com.github.cyberryan1.cybercore.spigot.utils.CyberVaultUtils;
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
import com.github.cyberryan1.dripcore.features.invsee.InvseeListener;
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
        new CyberVaultUtils();

        // Update or reload config/data files
        YMLUtils.getConfig().getYmlLoader().initialize();
        YMLUtils.getData().getYmlLoader().initialize();
        YMLUtils.getDataUtils().sendPathNotFoundWarns( false );

        // Register commands
        registerAllCommands();
        // Register events
        registerAllEvents();

        PlaytimeManager.initializeAllPlayers();
        TeleportUtils.initialize();
    }

    @Override
    public void onDisable() {
        PlaytimeManager.saveAllPlayers();
    }

    private void registerAllCommands() {
        new AdventureCommand();
        new CreativeCommand();
        new SpectatorCommand();
        new SurvivalCommand();
        new FlightCommand();
        new TeleportCommand();
        new TeleportHereCommand();
        new TeleportOverrideCommand();
        new TeleportHereOverrideCommand();
        new TeleportToggleCommand();
        new PingCommand();
        new BuyCommand();
        new WorkbenchCommand();
        new EnderchestCommand();
        new InvseeCommand();
        new DiscordCommand();
        new TagsCommand();
        new BroadcastCommand();
        new RulesCommand();
        new PlaytimeCommand();
    }

    private void registerAllEvents() {
        this.getServer().getPluginManager().registerEvents( new GlobalEvents(), this );
        this.getServer().getPluginManager().registerEvents( new AntiCommandEvents(), this );
        this.getServer().getPluginManager().registerEvents( new InvseeListener(), this );
    }
}