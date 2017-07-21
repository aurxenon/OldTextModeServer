package net.edcubed.SmittyServer;

import java.util.ArrayList;

public class Main{
    public static ExtraUtils utils = new ExtraUtils();
    public static PeopleUtils playerUtils = new PeopleUtils();
    public static ArrayList<Integer> worldSize = new ArrayList<Integer>();
    public static int worldSizeX = 1024;
    public static int worldSizeY = 1024;
    public static TerrainGeneration generator;
    public static TCPNetworkManager tcpNetworker;
    public static String versionCode = "alphav1.0";

    public static void main(String[] args){
        try{
            NetworkManager networker = new NetworkManager();
            networker.start();
            tcpNetworker = new TCPNetworkManager();
            tcpNetworker.start();
            worldSize.add(worldSizeX);
            worldSize.add(worldSizeY);
            generator = new TerrainGeneration(worldSizeX, worldSizeY);
            generator.GenerateStyle1();

            /*
            buffer size for udp, port, world size x and y, world generation method
             */

            System.out.println("Hello!");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
