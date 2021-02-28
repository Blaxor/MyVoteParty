package ro.deiutzblaxo.voteparty;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Cache  {

    public static HashMap<String, Integer> VOTES = new HashMap<>();
    public static List<String> VOTE_COMMAND = new ArrayList<>();
    public static ArrayList<String> PARTY_VOTE_COMMAND = new ArrayList<>();

    public static int VOTE_NOW;
    public static int MAX_VOTE = 100;
}
