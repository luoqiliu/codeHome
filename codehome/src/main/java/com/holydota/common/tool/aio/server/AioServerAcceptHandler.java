package com.holydota.common.tool.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;

public class AioServerAcceptHandler implements
                                   CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    private ILog logger = LogFactory.getLog("aioLogger");

    public void cancelled(AsynchronousServerSocketChannel attachment) {
        try {
            logger.warn(attachment.getLocalAddress().toString() + " cancelled");
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public void completed(AsynchronousSocketChannel socket, AsynchronousServerSocketChannel attachment) {
        try {
            logger.info("AioAcceptHandler.completed called");
            attachment.accept(attachment, this);
            logger.info("有客户端连接:" + socket.getRemoteAddress().toString());
            startRead(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        logger.error(exc.toString());
    }

    public void startRead(AsynchronousSocketChannel socket) {
        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);
        socket.read(clientBuffer, clientBuffer, new AioServerReadHandler(socket));
    }

}
