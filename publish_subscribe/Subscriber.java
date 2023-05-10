//package cs.tcd.ie;

import java.net.DatagramSocket;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Scanner;

import tcdIO.*;

// Client class
// An instance accepts user input

public class Subscriber extends Node implements Runnable{
    static final int DEFAULT_SUB_PORT = 50002;
    static final int BROKER_DST_PORT = 50001;
    static final String DEFAULT_DST_NODE = "localhost";

    public static int subPort = 50002;
    InetSocketAddress dstAddress;

    // Constructor
    // Attempts to create socket at given port and create an InetSocketAddress for the destinations

    Subscriber(String dstHost, int dstPort, int srcPort){
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
        System.out.println("starting thread");
    }


    // Test method
    // Sends a packet to a given address
    public static void main(String[] args) throws BindException {
        try {
        	++subPort;
            Thread sub = new Thread(new Subscriber(DEFAULT_DST_NODE, BROKER_DST_PORT, subPort));
            //Subscriber sub = new Subscriber(DEFAULT_DST_NODE, BROKER_DST_PORT, ++subPort);
            sub.start();
        }catch(java.lang.Exception e) {e.printStackTrace();}
    }

    public void run() {
        try{
            Scanner input = new Scanner(System.in);
            while(true) {
                System.out.println("Type sub or unsub.");
                String subType = input.next();
                byte[]dataTmp = null;
                DatagramPacket packet = null;
                if(subType.equals("sub"))
                {
                    System.out.println(("Subscribe to new topic: "));
                    dataTmp = (input.next().getBytes());
                    // array with header 1 byte overhead
                    byte[]data = new byte[dataTmp.length + HEADER_OVERHEAD];
                    // 2 = subscribe
                    data[0] = 2;
                    //copy over the data bytes
                    for(int i=0; i<dataTmp.length; i++) {
                        data[HEADER_OVERHEAD + i] = dataTmp[i];
                    }
                    packet = new DatagramPacket(data, data.length, dstAddress);
                    socket.send(packet);
                    System.out.println("Subscribed!");
                    //this.wait();
                }
                else if(subType.equals("unsub")) {
                    byte[]data = new byte[10];
                    // 3 = unsubscribe
                    data[0] = 3;
                    packet = new DatagramPacket(data, data.length, dstAddress);
                    socket.send(packet);
                   // this.wait();
                }
            }
        }catch (Exception e){System.out.println("Error");}
    }
}
