package com.mayikt.tcp.bio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * @author 周宇
 * @create 2021-09-19 8:28
 */
public class ClientTcpSocket {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080);
            socket.connect(address);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                socket.getOutputStream().write(scanner.next().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
