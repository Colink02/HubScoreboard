package com.colink02dev.SimpleScoreboard;

import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class HubScoreboard {
    public static HashMap<UUID, Scoreboard> scoreboards = new HashMap<>();
    public static void initScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        Scoreboard board = manager.getNewScoreboard();
        Objective mainObjective = board.registerNewObjective("MainHub", "dummy", ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ScoreboardPlugin.getInstance().getConfig().getString("scoreboard-info.static-name"))));
        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team rank = board.registerNewTeam("rank");
        rank.addEntry(ChatColor.RED + "" + ChatColor.AQUA);
        Team coin = board.registerNewTeam("coins");
        coin.addEntry(ChatColor.RED + "" + ChatColor.GRAY);
        Team Count = board.registerNewTeam("players");
        Count.addEntry(ChatColor.RED + "" + ChatColor.YELLOW);
        Score blank2 = mainObjective.getScore(" ");
        blank2.setScore(14);
        Score rankText = mainObjective.getScore(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ScoreboardPlugin.getInstance().getConfig().getString("scoreboard-info.rank-static-text"))));
        rankText.setScore(13);
        Score rankScore = mainObjective.getScore(ChatColor.RED + "" + ChatColor.AQUA);
        rankScore.setScore(12);
        Score blank0 = mainObjective.getScore("  ");
        blank0.setScore(11);
        Score coins = mainObjective.getScore(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ScoreboardPlugin.getInstance().getConfig().getString("scoreboard-info.balance-static-text"))));
        coins.setScore(10);
        Score playerCoins = mainObjective.getScore(ChatColor.RED + "" + ChatColor.GRAY);
        playerCoins.setScore(9);
        Score blank3 = mainObjective.getScore("   ");
        blank3.setScore(8);
        Score players = mainObjective.getScore(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ScoreboardPlugin.getInstance().getConfig().getString("scoreboard-info.players-static-text"))));
        players.setScore(7);
        Score playerCount = mainObjective.getScore(ChatColor.RED + "" + ChatColor.YELLOW);
        playerCount.setScore(6);
        Score blank4 = mainObjective.getScore("    ");
        blank4.setScore(2);
        Score ip = mainObjective.getScore(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ScoreboardPlugin.getInstance().getConfig().getString("scoreboard-info.ip"))));
        ip.setScore(1);

        scoreboards.put(p.getUniqueId(), board);
        addPlayer(p);
    }
    private static void addPlayer(Player p) {
        p.setScoreboard(scoreboards.get(p.getUniqueId()));
        final CachedMetaData data = Objects.requireNonNull(ScoreboardPlugin.luckPermsInstance().getUserManager().getUser(p.getUniqueId())).getCachedData().getMetaData(ScoreboardPlugin.luckPermsInstance().getContextManager().getStaticQueryOptions());
        String colorizeData = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(data.getPrefix()));
        Objects.requireNonNull(p.getScoreboard().getTeam("rank")).setPrefix(colorizeData);
        Objects.requireNonNull(p.getScoreboard().getTeam("coins")).setPrefix(ChatColor.YELLOW + String.valueOf(Math.round(ScoreboardPlugin.econ.getBalance(p))));
        p.getScoreboard().getTeam("players").setPrefix(String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));//BungeeCordHermes.getALLPlayers();
    }
    public static void removePlayerRecord(UUID uuid) {
        scoreboards.remove(uuid);
    }
}
