package com.holydota.common.tool.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;

public class AioServerReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel socket;

    private ILog                      logger = LogFactory.getLog("aioLogger");

    public AioServerReadHandler(AsynchronousSocketChannel socket) {
        this.socket = socket;
    }

    public void cancelled(ByteBuffer attachment) {
        logger.warn(attachment.toString() + " cancelled");

    }

    private CharsetDecoder decoder = Charset.forName("GBK").newDecoder();

    public void completed(Integer i, ByteBuffer buf) {
        if (i > 0) {
            buf.flip();
            try {
                logger.info("收到" + socket.getRemoteAddress().toString() + "的消息:" + decoder.decode(buf));
                buf.compact();
            } catch (CharacterCodingException e) {
                logger.error(e.toString());
            } catch (IOException e) {
                logger.error(e.toString());
            }
            socket.read(buf, buf, this);
        } else if (i == -1) {
            try {
                logger.info("客户端断线:" + socket.getRemoteAddress().toString());
                buf = null;
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
    }

    public void failed(Throwable exc, ByteBuffer buf) {
        System.out.println(exc);
    }
}
