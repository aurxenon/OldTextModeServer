package net.edcubed.TextModeCommons;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Entity implements Serializable{
    private String playerName;
    private String firstJoin = null;
    private int hoursPlayed = 0;
    private ArrayList<Player> playerFriends = new ArrayList<Player>();

    public Player(String name, int[] xy) {
        super(xy);
        playerName = name;
        this.setControllable(true);
    }
    public Player(String name, int[] xy, int attack) {
        super(xy);
        playerName = name;
        this.setControllable(true);
        this.setAttackDamage(attack);
    }
    //stats
    public String getPlayerName() {return this.playerName;}
    public String getFirstJoin() {return this.firstJoin;}
    public int getHoursPlayed() {return this.hoursPlayed;}

    //friends
    public void addFriend(Player friend) {this.playerFriends.add(friend);}
    public void removeFriend(Player friend) {this.playerFriends.remove(friend);}
    public ArrayList<Player> getFriends() {return this.playerFriends;}
}