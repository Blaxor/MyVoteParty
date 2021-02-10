package ro.deiutzblaxo.voteparty;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Main plugin = Main.instance;

    public FileConfiguration db;
    public File file;
    public File configFile;
    public FileConfiguration config;
    public ConfigManager(){
        if(!plugin.getDataFolder().exists()){

                plugin.getDataFolder().mkdir();

        }
        configFile = new File(plugin.getDataFolder(),"config.yml");
        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            config = YamlConfiguration.loadConfiguration(configFile);
            config.set("vp-time-ramain", "&e Time remain {time}");
            config.set("vp-start", "VoteParty will start in 10 seconds!");
            config.set("vp-started","VOTEPARTY STARTED!");
            config.set("player-vote","{player_name} voted our server on {site}");
            config.set("commands", new String[]{"bcast vote1", "bcast vote2"});
            config.set("commands-vote",new String[]{"bcast vote1", "bcast {player_name}"});
            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        file = new File(plugin.getDataFolder(), "players.yml");
        if(!file.exists()){
            try {
                plugin.getDataFolder().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig(){
        return config;
    }
    public void saveConfig(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfig(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public int getPlayer(String name) {
        db = YamlConfiguration.loadConfiguration(file);
        return db.getString(name) == null ? 0 : db.getInt(name);
    }
    public boolean containsPlayer(String name) {
        db = YamlConfiguration.loadConfiguration(file);
        return db.getString(name) != null;
    }

    public void setPlayer(String name){
        db = YamlConfiguration.loadConfiguration(file);
        db.set(name,Cache.VOTES.get(name));
        try {
            db.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
