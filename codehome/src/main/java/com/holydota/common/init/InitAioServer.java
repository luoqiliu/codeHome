package com.holydota.common.init;

import com.holydota.common.log.ILog;
import com.holydota.common.log.LogFactory;
import com.holydota.common.tool.aio.server.AioTcpServer;

public class InitAioServer {
    private ILog logger = LogFactory.getLog(InitAioServer.class);

    public void init() {
        try {
            AioTcpServer server = new AioTcpServer();
            new Thread(server).start();
        } catch (Exception e) {
            logger.error("AIO server start faild, cause:" + e.toString());
        }
    }

}
