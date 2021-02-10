package ro.deiutzblaxo.voteparty;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;


public final class Main extends JavaPlugin {

    public static Main instance;
    public static ConfigManager cm;
    public static JP jp;

    @Override
    public void onEnable() {
        instance = this;

        cm = new ConfigManager();
        jp = new JP();
        cm.loadConfig();
        Cache.VOTE_NOW = cm.getConfig().getInt("votes");
        Bukkit.getOnlinePlayers().forEach(player -> {
            String pName = player.getName().toLowerCase(Locale.ROOT);
            if(Main.cm.containsPlayer(pName)){
                Cache.VOTES.put(pName , Main.cm.getPlayer(pName));
            }
        });

        Cache.VOTE_COMMAND = cm.getConfig().getStringList("commands-vote");

        (new VotePartyPAPI((Plugin)this)).register();
        Bukkit.getPluginManager().registerEvents(new Events() , this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getConsoleSender().sendMessage("Auto-Save PartyVote...");
                        cm.loadConfig();
                        cm.config.set("votes",Cache.VOTE_NOW);
                        cm.saveConfig();
                        Cache.VOTES.forEach((name,key)->{
                            cm.setPlayer(name);
                        });
                        Bukkit.getConsoleSender().sendMessage("Auto-Save PartyVote finished");
                    }
                }
        ,1800*20,1800*20);
    }

    @Override
    public void onDisable() {
        cm.loadConfig();
        cm.config.set("votes",Cache.VOTE_NOW);
        cm.saveConfig();
        Cache.VOTES.forEach((name,key)->{
            cm.setPlayer(name);
        });
    }
}
