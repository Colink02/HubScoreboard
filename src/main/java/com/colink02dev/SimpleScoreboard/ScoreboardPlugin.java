package com.colink02dev.SimpleScoreboard;

import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardPlugin extends JavaPlugin {
    private static LuckPerms luckPermsInst;
    static Plugin instance;
    public static Economy econ;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        if (!setupEconomy()) {
            this.getLogger().warning("Missing Vault!");
            //getServer().getPluginManager().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPermsInst = provider.getProvider();
        } else {
            this.getLogger().warning("Failed to Load due to missing LuckPerms!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if(this.getConfig().getBoolean("scoreboard-animation.enabled")) {
            new ScoreboardUpdater().runTaskTimer(this, 1, 5);
        }
        new ScoreboardScheduler().runTaskTimer(this, 1, 300);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        for(Player p : Bukkit.getOnlinePlayers()) {
            HubScoreboard.initScoreboard(p);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        instance = null;
    }
    public static Plugin getInstance() {
        return instance;
    }
    public static LuckPerms luckPermsInstance() {
        return luckPermsInst;
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }
}
