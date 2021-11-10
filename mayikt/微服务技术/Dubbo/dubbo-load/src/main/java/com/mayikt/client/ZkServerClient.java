package com.mayikt.client;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-09-12 18:43
 */
public class ZkServerClient {
    // 存放服务列表信息
    public static List<String> listServer = new ArrayList<>();

    // 客户端：读取service节点，获取下面的子节点value值 本地实现远程调用。
    private static ZkClient zkClient = new ZkClient("127.0.0.1:18080");
    private static String parentService = "/service";

    public static void main(String[] args) {
        //注册所有server
        initServer();
        ZkServerClient client = new ZkServerClient();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name;
            try {
                name = console.readLine();
                if ("exit".equals(name)) {
                    System.exit(0);
                }
                client.send(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 注册所有server
    public static void initServer() {
        // listServer.clear();
        // listServer.add("127.0.0.1:18080");
        // 从Zookeeper上获取服务列表信息
        List<String> children = zkClient.getChildren(parentService);
        getChildData(zkClient, children);
        // 使用Zk事件通知获取最新服务列表信息
        zkClient.subscribeChildChanges(parentService, (parentPath, currentChilds) -> {
            System.out.println("注册中心服务里列表信息发生变化..");
            getChildData(zkClient, currentChilds);
        });
    }

    public static void getChildData(ZkClient zkClient, List<String> children) {
        listServer.clear();
        for (String p : children) {
            String serverAddres = zkClient.readData(parentService + "/" + p);
            listServer.add(serverAddres);
        }
        System.out.println("服务接口地址:" + listServer.toString());

    }

    // 请求总数
    private static int reqCount = 1;

    // 获取当前server信息
    public static String getServer() {
        int index = reqCount % listServer.size();
        String addres = listServer.get(index);
        System.out.println("客户端请求服务端:" + addres);
        reqCount++;
        return addres;
    }

    public void send(String name) {
        String server = ZkServerClient.getServer();
        String[] cfg = server.split(":");

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(name);
            while (true) {
                String resp = in.readLine();
                if (resp == null){
                    break;
                }else if (resp.length() > 0) {
                    System.out.println("Receive : " + resp);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
