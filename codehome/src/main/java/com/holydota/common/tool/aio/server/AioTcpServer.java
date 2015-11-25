package com.holydota.common.tool.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.math.NumberUtils;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.common.util.LocalSettingKeys;
import com.holydota.common.util.LocalSettings;

public class AioTcpServer implements Runnable {

    private AsynchronousChannelGroup        asyncChannelGroup;
    private AsynchronousServerSocketChannel listener;
    private static final Integer            MAX_THREAD  = NumberUtils.toInt(LocalSettings.getProperty(
                                                            LocalSettingKeys.SERVER_THREAD_POOL_SIZE, "25"));
    private static final Integer            SERVER_PORT = NumberUtils.toInt(LocalSettings.getProperty(
                                                            LocalSettingKeys.SERVER_CHANNEL_LISTEN_PORT, "8888"));
    private ILog                            logger      = LogFactory.getLog("aioLogger");

    public AioTcpServer() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD);
        asyncChannelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        listener = AsynchronousServerSocketChannel.open(asyncChannelGroup).bind(new InetSocketAddress(SERVER_PORT));
    }

    public void run() {
        try {
            System.out.println("server start on port:" + SERVER_PORT);
            listener.accept(listener, new AioServerAcceptHandler());
            //Thread.sleep(millis);
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            logger.info("finished server");
        }
    }

}
