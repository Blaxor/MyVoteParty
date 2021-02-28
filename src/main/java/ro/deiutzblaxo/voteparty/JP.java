package ro.deiutzblaxo.voteparty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class JP {
    BukkitTask taskID;
    int vote = 10;

    public JP(){
        Main.cm.loadConfig();
         Cache.PARTY_VOTE_COMMAND = (ArrayList<String>) Main.cm.getConfig().getStringList("commands");//TODO
    }

    public void startParty(){
        Cache.VOTE_NOW = 0;

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',Main.cm.getConfig().getString("vp-start")));//TODO

        taskID = Bukkit.getScheduler().runTaskTimer(Main.instance, () -> {

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    Main.cm.getConfig().getString("vp-time-ramain").replace("{time}", String.valueOf(vote))));//TODO
            if(vote <= 0) {

                Cache.PARTY_VOTE_COMMAND.forEach(command ->{Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
                System.out.println(command);

                });
                Bukkit.getScheduler().cancelTask(taskID.getTaskId());
                vote = 10;
            }
            vote--;
        }, 0L, 20L);





    }



}
