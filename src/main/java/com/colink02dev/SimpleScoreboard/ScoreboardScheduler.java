package com.colink02dev.SimpleScoreboard;

import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardScheduler extends BukkitRunnable {
    @Override
    public void run() {
        for(Map.Entry<UUID, Scoreboard> scoreboardEntry : HubScoreboard.scoreboards.entrySet()) {
            Player p = Bukkit.getPlayer(scoreboardEntry.getKey());
            final CachedMetaData data = Objects.requireNonNull(ScoreboardPlugin.luckPermsInstance().getUserManager().getUser(scoreboardEntry.getKey())).getCachedData().getMetaData(ScoreboardPlugin.luckPermsInstance().getContextManager().getStaticQueryOptions());
            String colorizeData = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(data.getPrefix()));
            assert p != null;
            Objects.requireNonNull(p.getScoreboard().getTeam("rank")).setPrefix(colorizeData);
            Objects.requireNonNull(p.getScoreboard().getTeam("coins")).setPrefix(ChatColor.YELLOW + String.valueOf(Math.round(ScoreboardPlugin.econ.getBalance(Bukkit.getPlayer(scoreboardEntry.getKey())))));
        }
    }
}
