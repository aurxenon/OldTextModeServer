package net.edcubed.SmittyServer;

import java.net.InetAddress;
import net.edcubed.SmittyCommons.*;

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
