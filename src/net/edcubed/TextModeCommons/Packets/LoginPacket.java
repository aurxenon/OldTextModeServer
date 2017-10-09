package net.edcubed.TextModeCommons.Packets;

import java.io.Serializable;

public class LoginPacket implements Serializable{
    String versionCode;
    String username;
    String password;
    public LoginPacket(String versionCode, String username, String password){
        this.versionCode = versionCode;
        this.username = username;
        this.password = password;
    }
    public String getVersionCode(){return versionCode;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}