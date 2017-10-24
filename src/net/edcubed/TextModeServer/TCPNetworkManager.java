package net.edcubed.TextModeServer;

import net.edcubed.TextModeCommons.Packets.LoginPacket;
import net.edcubed.TextModeCommons.PlayerMP;

import java.io.*;
import java.net.*;

public class TCPNetworkManager extends Thread{
    private ServerSocket tcpSocket;
    public int bindPort = 26655;

    public TCPNetworkManager(){
        try {
            System.out.println("Starting TCP network utils");
            System.out.println("Binding TCP to port " + bindPort);
            this.tcpSocket = new ServerSocket(bindPort);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                //Socket connectionSocket = tcpSocket.accept();
                addThread(tcpSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addThread(Socket socket) {
        new Thread(new Runnable() {
            public void run() {
                ObjectOutputStream objectOutput;
                ObjectInputStream objectInput;
                try {
                    objectInput = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    objectOutput = new ObjectOutputStream(socket.getOutputStream());
                }catch (IOException e){
                    e.printStackTrace();
                    closeSocket(socket);
                    return;
                }
                Object message = "uninitialized";
                while (true) {
                    try {
                        //DataInputStream objectInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                        message = objectInput.readObject();
                        if (message instanceof LoginPacket) {
                            playerLogin(socket, objectOutput);
                        }
                        if (message instanceof PlayerMP) {
                            PlayerMP playerMP = (PlayerMP)message;
                            if (playerMP.getConnectionStatus() == PlayerMP.Status.LOGIN) {
                                playerLogin(socket, objectOutput);
                            }
                        }
                        if (message instanceof String) {
                            System.out.println((String)message);
                            if (((String) message).equalsIgnoreCase("i'm still here please don't leave me")) {
                                sendTCPData("i'll always be here for you :)", socket, objectOutput);
                            }
                        }
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                        closeSocket(socket);
                        return;
                    }catch(IOException e){
                        e.printStackTrace();
                        closeSocket(socket);
                        return;
                    }
                }
            }
        }).start();
    }

    public void closeSocket(Socket skt) {
        try {
            skt.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendTCPData(Object message, Socket skt, ObjectOutputStream objectOutput) {
        try {
            objectOutput.writeObject(message);
            objectOutput.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void playerLogin(Socket skt, ObjectOutputStream objectOutput){
        sendTCPData(Main.generator.getWorld(), skt, objectOutput);
        sendTCPData(Main.worldSize, skt, objectOutput);
    }
}
