package net.edcubed.TextModeCommons;

import java.io.*;

public class PlayerMP implements Serializable{
    private Status connectionStatus;
    private Player connectionPlayer;
    private String versionCode;
    public PlayerMP(Status status, Player player, String version){
        this.connectionStatus = status;
        this.connectionPlayer = player;
        this.versionCode = version;
    }
    public Status getConnectionStatus() {
        return this.connectionStatus;
    }
    public Player getConnectionPlayer() {return this.connectionPlayer;}
    public String getVersionCode() {return this.versionCode;}
    public enum Status {
        LOGIN, PLAYER, DISCONNECT
    }
}