package net.edcubed.TextModeServer;

import java.net.InetAddress;
import net.edcubed.TextModeCommons.*;

public class PlayerData {
    InetAddress ip;
    Player player;
    int port;
    public PlayerData(Player gamePlayer,InetAddress playerIP, int playerPort){
        ip = playerIP;
        player = gamePlayer;
        port = playerPort;
    }
    public InetAddress getPlayerIP() {return ip;}
    public Player getPlayer() {return player;}
    public int getPort() {return port;}
}
