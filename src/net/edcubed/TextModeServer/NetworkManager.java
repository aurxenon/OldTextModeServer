package net.edcubed.TextModeServer;

import java.io.*;
import java.net.*;

import net.edcubed.TextModeCommons.*;

public class NetworkManager extends Thread{
    private DatagramSocket socket;
    private ExtraUtils utils = new ExtraUtils();
    private PeopleUtils playerUtils = new PeopleUtils();

    public NetworkManager(){
        try {
            System.out.println("Starting UDP network utils");
            this.socket = new DatagramSocket(26656);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while (true) {
            byte[] data = new byte[2048];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            Object message = "uninitialized";
            try {
                socket.receive(packet);
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream inputStream = new ObjectInputStream(in);
                message = inputStream.readObject();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

            if (message instanceof PlayerMP) {
                PlayerMP playerMP = (PlayerMP) message;
                if (!playerMP.getVersionCode().equalsIgnoreCase(Main.versionCode)) {
                    sendData("This server is running on " + Main.versionCode + " and you are using " + playerMP.getVersionCode(), packet.getAddress(), packet.getPort());
                    continue;
                }
                Player player = playerMP.getConnectionPlayer();
                if (playerMP.getConnectionStatus() == PlayerMP.Status.LOGIN) {
                    System.out.println(packet.getAddress().toString() + " joined the game with name " + player.getPlayerName() + " from port " + packet.getPort());
                    playerLogin(player, packet);
                }
                if (playerMP.getConnectionStatus() == PlayerMP.Status.DISCONNECT) {
                    System.out.println(player.getPlayerName() + " left the game");
                    playerDisconnect(player, packet, packet.getPort());
                }
                utils.updatePlayer(player);
                sendData(utils.getGamePlayers(), packet.getAddress(), packet.getPort());
            }
            if (message instanceof Place) {
                sendDataToAll(Main.generator.getWorld());
            }
        }
    }

    public void sendData(Object inputData, InetAddress ipAddress, int port){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(inputData);
            byte[] data = outputStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
            os.flush();
            socket.send(packet);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendDataToAll(Object inputData){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(inputData);
            byte[] data = outputStream.toByteArray();
            for (int i = 0; i < playerUtils.getGamePlayers().size(); i++) {
                InetAddress ipAddress = playerUtils.getGamePlayers().get(i).getPlayerIP();
                int port = playerUtils.getGamePlayers().get(i).getPort();
                DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
                socket.send(packet);
            }
            os.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void playerLogin(Player player, DatagramPacket packet){
        utils.addGamePlayer(player);
        playerUtils.addGamePlayer(player, packet.getAddress(), packet.getPort());
    }
    public void playerDisconnect(Player player, DatagramPacket packet, int port){
        utils.deleteGamePlayer(player);
        playerUtils.deleteGamePlayer(new PlayerData(player, packet.getAddress(), port));
    }

}
