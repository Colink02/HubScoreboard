package com.colink02dev.SimpleScoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardUpdater extends BukkitRunnable {
    //String[] animationText = new String[] {"&6&lE&3&lnfinium","&6&lEn&3&lfinium","&6&lEnf&3&linium","&6&lEnfi&3&lnium","&6&lEnfin&3&lium","&6&lEnfini&3&lum","&6&lEnfiniu&3&lm","&6&lEnfinium","&3&lEnfinium","&6&lEnfinium","&3&lEnfinium"};
    List<String> animationText = ScoreboardPlugin.getInstance().getConfig().getStringList("scoreboard-animation.text");
    int animationLoop = 0;
    @Override
    public void run() {
        for(Map.Entry<UUID, Scoreboard> data: HubScoreboard.scoreboards.entrySet()) {
            Objects.requireNonNull(Objects.requireNonNull(Bukkit.getPlayer(data.getKey())).getScoreboard().getObjective(DisplaySlot.SIDEBAR)).setDisplayName(ChatColor.translateAlternateColorCodes('&',animationText.get(animationLoop)));
        }
        if(animationLoop == animationText.size() - 1) {
            animationLoop = 0;
        } else {
            animationLoop+=1;
        }
    }
}
