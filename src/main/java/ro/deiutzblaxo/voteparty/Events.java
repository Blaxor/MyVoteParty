package ro.deiutzblaxo.voteparty;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Locale;

public class Events implements Listener {
    @EventHandler
    public void JoinEvent(PlayerJoinEvent event){
        String pName = event.getPlayer().getName().toLowerCase(Locale.ROOT);
        if(Main.cm.containsPlayer(pName)){
            Cache.VOTES.put(pName , Main.cm.getPlayer(pName));
        }
    }
    @EventHandler
    public void QuitEvent(PlayerQuitEvent event){
        String pName = event.getPlayer().getName().toLowerCase(Locale.ROOT);
        if(Cache.VOTES.containsKey(pName))
            if(Cache.VOTES.get(pName) > 0){
                Main.cm.setPlayer(pName);
            }

    }

    @EventHandler
    public void onVote(VotifierEvent event){
        if(Bukkit.getOnlinePlayers().stream().filter(player -> event.getVote().getUsername().equalsIgnoreCase(player.getName())).count() == 0) {
            return;
        }

        String pName = event.getVote().getUsername().toLowerCase(Locale.ROOT);
        Cache.VOTES.put(pName , Cache.VOTES.containsKey(pName) ? Cache.VOTES.get(pName) +1 : 1);
        Cache.VOTE_NOW++;
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',Main.cm.getConfig().getString("player-vote")
                .replace("{player_name}",event.getVote().getUsername()).replace("{site}",event.getVote().getServiceName())
                .replace("{VOTE_REMAIN}", String.valueOf(Cache.MAX_VOTE - Cache.VOTE_NOW))));
        Cache.VOTE_COMMAND.forEach(commands ->{
            String command = commands.replace("{player_name}",event.getVote().getUsername());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
        });
        if(Cache.VOTE_NOW >= Cache.MAX_VOTE)
            Main.jp.startParty();
    }


}
