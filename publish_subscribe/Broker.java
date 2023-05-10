//package cs.tcd.ie;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.ArrayList;

import tcdIO.Terminal;

public class Broker extends Node{
    static final int BROKER_PORT = 50001;

    InetSocketAddress dstAddress;
    private ArrayList<InetSocketAddress> subscribers;

    Broker(int port){
        try {
            subscribers = new ArrayList<InetSocketAddress>();
            socket = new DatagramSocket(port);
            listener.go();
        }catch(java.lang.Exception e) {e.printStackTrace();}
    }

    // adds a subscriber to the list
    public void subscribe(InetSocketAddress newSub) {
        subscribers.add(newSub);
    }

    //removes a subscriber from the list
    public void unsubscribe(InetSocketAddress deleteSub) {
        int index = subscribers.indexOf(deleteSub);
        System.out.println("Removed subscriber: "+(index+1));
        subscribers.remove(index);
    }

    // forwards the publishers message to the subscribers
    public synchronized void forward2Subs(DatagramPacket packet) throws Exception{
        for(InetSocketAddress sub : subscribers) {
            packet.setSocketAddress(sub);
            socket.send(packet);
        }
    }

    // Assume that incoming packets contain a string and print the string
    public synchronized void onReceipt(DatagramPacket packet) {
        try {
            byte[] data = packet.getData();
            if(data[0] == 1) {
                // Publisher, send ok back
                DatagramPacket response = (new StringContent("ok!\n")).toDatagramPacket();
                response.setSocketAddress(packet.getSocketAddress());
                socket.send(response);

                // if publisher publishes and sublist is not empty
                if(!subscribers.isEmpty()) {
                    forward2Subs(packet);
                }
            }
            // new subscription
            else if(data[0] == 2) {
                subscribe(new InetSocketAddress(packet.getAddress(),packet.getPort()));
                System.out.println("New sub!");
            }
            // unsubscribe
            else if(data[0] == 3){
                unsubscribe(new InetSocketAddress(packet.getAddress(),packet.getPort()));
                System.out.println("Unsubscribed!");
            }
        }catch(Exception e) {e.printStackTrace();}
    }

    public synchronized void start() throws Exception{
        System.out.println("Waiting for contact:");
        while(true) {
            this.wait();
        }
    }

    public static void main(String[]args) {
        try {
            (new Broker(BROKER_PORT)).start();
        }catch(java.lang.Exception e) {e.printStackTrace();}
    }
}
