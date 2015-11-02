package com.holydota.common.tool.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.xml.crypto.Data;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;

public class Client {
    private static ILog logger = LogFactory.getLog(Client.class);

    public static void main(String[] args) {
        try {
            logger.info("client start");
            DatagramSocket ds = new DatagramSocket(9001);
            byte[] result = new byte[1024];
            DatagramPacket dp = new DatagramPacket(result, result.length);
            ds.receive(dp);
            System.out.println(dp.getPort());
            System.out.println(dp.getAddress());
            System.out.println(new String(dp.getData(), 0, dp.getData().length, "utf-8"));
            ds.close();
        } catch (SocketException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
