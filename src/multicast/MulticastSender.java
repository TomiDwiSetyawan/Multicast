/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;

import java.net.*;
import java.io.*;

public class MulticastSender {

    public static void main(String[] args) {
        InetAddress ia = null;
        int port = 1234;
        byte ttl = (byte) 1;
        // read the address from the command line
        try {
            ia = InetAddress.getByName("234.5.6.7");
            port = Integer.parseInt("1234");
            if (args.length > 2) {
                ttl = (byte) Integer.parseInt(args[2]);
            }
        } catch (Exception ex) {
            System.err.println(ex);
            System.err.println(
                    "Usage: java MulticastSender multicast_address port ttl");
            System.exit(1);
        }
        byte[] data = "Here's some multicast data\r\n".getBytes();
        DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
        try {
            MulticastSocket ms = new MulticastSocket();
            ms.joinGroup(ia);
            for (int i = 1; i <= 10; i++) {
                ms.send(dp, ttl);
            }
            ms.leaveGroup(ia);
            ms.close();
        } catch (SocketException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
