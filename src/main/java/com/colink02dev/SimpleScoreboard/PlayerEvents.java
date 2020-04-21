package com.colink02dev.SimpleScoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        HubScoreboard.initScoreboard(e.getPlayer());
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.getScoreboard().getTeam("players").setPrefix(String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
        }
    }
    @EventHandler
    public void playerLeave(PlayerQuitEvent e) {
        HubScoreboard.removePlayerRecord(e.getPlayer().getUniqueId());
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.getScoreboard().getTeam("players").setPrefix(String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
        }
    }
}
