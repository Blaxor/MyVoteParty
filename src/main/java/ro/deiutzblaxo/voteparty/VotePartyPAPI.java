package ro.deiutzblaxo.voteparty;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Locale;


public class VotePartyPAPI extends PlaceholderExpansion {



    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if(params.equalsIgnoreCase("VOTE_MAX"))
            return String.valueOf(Cache.MAX_VOTE);
        else if(params.equalsIgnoreCase("VOTE_DONE")){
            return String.valueOf(Cache.VOTE_NOW);
        }else if(params.equalsIgnoreCase("VOTE_REMAIN")){
            return String.valueOf(Cache.MAX_VOTE - Cache.VOTE_NOW);
        }else if (params.equalsIgnoreCase("VOTE_BY_PLAYER")) {
            return Cache.VOTES.containsKey(p.getName().toLowerCase(Locale.ROOT)) ? String.valueOf(Cache.VOTES.get(p.getName().toLowerCase(Locale.ROOT))) : 0+"";
        }


        return null;
    }
    @Override
    public String getAuthor() {
        return "Deiutz";
    }

    @Override
    public String getIdentifier() {
        return "vp";
    }

    @Override
    public String getVersion() {

        return "1.0";
    }
}
