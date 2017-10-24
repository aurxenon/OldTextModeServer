package net.edcubed.TextModeServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import net.edcubed.TextModeCommons.*;

public class ExtraUtils {
    ArrayList<Player> gamePlayers = new ArrayList<Player>();
    public ExtraUtils(){}
    public ArrayList<Player> getGamePlayers() {return gamePlayers;}
    public void setGamePlayers(ArrayList<Player> players) {gamePlayers = players;}
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public void addGamePlayer(Player player) {gamePlayers.add(player);}
    public void deleteGamePlayer(Player player) {
        for (int i = 0; i < gamePlayers.size(); i++) {
            if (gamePlayers.get(i).getPlayerName().equalsIgnoreCase(player.getPlayerName())) {
                gamePlayers.remove(gamePlayers.get(i));
            }
        }
    }
    public void updatePlayer(Player player) {
        for (int i = 0; i < gamePlayers.size(); i++) {
            if (gamePlayers.get(i).getPlayerName().equalsIgnoreCase(player.getPlayerName())) {
                gamePlayers.set(i, player);
            }
        }
    }
    public void log(Object data) {
        System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " - " + data.toString());
    }
}