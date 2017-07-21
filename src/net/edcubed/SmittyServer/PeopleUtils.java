package net.edcubed.SmittyServer;

import java.net.InetAddress;
import java.util.ArrayList;
import net.edcubed.SmittyCommons.*;

public class PeopleUtils {
    ArrayList<PlayerData> playerStuff = new ArrayList<PlayerData>();
    public PeopleUtils(){}
    public ArrayList<PlayerData> getGamePlayers() {return playerStuff;}
    public void setGamePlayers(ArrayList<PlayerData> players) {playerStuff = players;}
    public void addGamePlayer(Player player, InetAddress ip, int port) {
        PlayerData gamePlayer = new PlayerData(player,ip,port);
        playerStuff.add(gamePlayer);
    }
    public void deleteGamePlayer(PlayerData player) {
        for (int i = 0; i < playerStuff.size(); i++) {
            if (playerStuff.get(i).getPlayer().getPlayerName().equalsIgnoreCase(player.getPlayer().getPlayerName())) {
                playerStuff.remove(playerStuff.get(i));
            }
        }
    }
    public Player getPlayerFromPlayerData(PlayerData player) {
        Player person = null;
        for (int i = 0; i < playerStuff.size(); i++) {
            if (playerStuff.get(i) == player) {
                person = playerStuff.get(i).getPlayer();
            }
        }
        return person;
    }
}
