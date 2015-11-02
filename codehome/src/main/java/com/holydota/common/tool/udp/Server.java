package com.holydota.common.tool.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;

public class Server {
    private static ILog logger = LogFactory.getLog(Server.class);

    public static void main(String[] args) {
        try {
            logger.info("server start");
            DatagramSocket ds = new DatagramSocket(9002);
            byte[] data = "wo shi shu ju".getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 9001);
            ds.send(dp);
            ds.close();
        } catch (SocketException e) {
            logger.error("", e);
        } catch (UnknownHostException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }

    }

}
