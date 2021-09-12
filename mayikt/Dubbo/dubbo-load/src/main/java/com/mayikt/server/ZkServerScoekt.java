package com.mayikt.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 周宇
 * @create 2021-09-12 2:33
 * ServerScoekt服务端
 */
public class ZkServerScoekt implements Runnable{

    private static int port = 18080;

    public static void main(String[] args) throws IOException {
        ZkServerScoekt server = new ZkServerScoekt(port);
        Thread thread = new Thread(server);
        thread.start();
    }

    public ZkServerScoekt(int port) {
        ZkServerScoekt.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server start port：" + port);
            Socket socket = null;
            while (true) {
                //accept() 侦听要与此套接字建立的连接并接受它。该方法阻塞，直到建立连接
                socket = serverSocket.accept();
                //线程开始执行
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {

            }
        }
    }

}
