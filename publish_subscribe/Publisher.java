//package cs.tcd.ie;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Scanner;

import tcdIO.*;

// Client class
// An instance accepts user input

public class Publisher extends Node{
    static final int DEFAULT_SRC_PORT = 50000;
    static final int BROKER_DST_PORT = 50001;
    static final String DEFAULT_DST_NODE = "localhost";

    InetSocketAddress dstAddress;

    // Constructor
    // Attempts to create socket at given port and create an InetSocketAddress for the destinations

    Publisher(String dstHost, int dstPort, int srcPort){
        try {
            dstAddress = new InetSocketAddress(dstHost, dstPort);
            socket = new DatagramSocket(srcPort);
            listener.go();
        } catch(java.lang.Exception e) {e.printStackTrace();}
    }

    // Assume that incoming packets contains a String and print the string
    public synchronized void onReceipt(DatagramPacket packet) {
        StringContent content = new StringContent(packet);
        this.notify();
        System.out.println(content.toString());
    }

    // Sender Method
    public synchronized void start() throws Exception{
        Scanner input = new Scanner(System.in);
        while(true) {
            DatagramPacket packet = null;
            System.out.print("Publish: ");
            byte[] dataTmp = (input.nextLine().getBytes());
            // array with header 1 byte overhead
            byte[]data = new byte[dataTmp.length + HEADER_OVERHEAD];
            // 1 = publish message
            data[0] = 1;
            //copy over the data bytes
            for(int i=0; i<dataTmp.length; i++) {
                data[HEADER_OVERHEAD + i] = dataTmp[i];
            }
            packet = new DatagramPacket(data, data.length, dstAddress);
            socket.send(packet);
            System.out.println("Published!");
            this.wait();
        }
    }


    // Test method
    // Sends a packet to a given address
    public static void main(String[]args) {
        try {
            (new Publisher(DEFAULT_DST_NODE, BROKER_DST_PORT, DEFAULT_SRC_PORT)).start();
        }catch(java.lang.Exception e) {e.printStackTrace();}
    }

}
