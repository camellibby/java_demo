package com.camellibby.seata;

import io.seata.common.XID;
import io.seata.common.thread.NamedThreadFactory;
import io.seata.common.util.NetUtil;
import io.seata.core.constants.ConfigurationKeys;
import io.seata.core.rpc.ShutdownHook;
import io.seata.core.rpc.netty.NettyRemotingServer;
import io.seata.core.rpc.netty.NettyServerConfig;
import io.seata.server.ParameterParser;
import io.seata.server.Server;
import io.seata.server.UUIDGenerator;
import io.seata.server.coordinator.DefaultCoordinator;
import io.seata.server.env.ContainerHelper;
import io.seata.server.env.PortHelper;
import io.seata.server.metrics.MetricsManager;
import io.seata.server.session.SessionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SeataApplication {

    public static void main(String[] args) {
        // get port first, use to logback.xml
        int port = PortHelper.getPort(args);
        System.setProperty(ConfigurationKeys.SERVER_PORT, Integer.toString(port));

        // create logger
        final Logger logger = LoggerFactory.getLogger(Server.class);
        if (ContainerHelper.isRunningInContainer()) {
            logger.info("The server is running in container.");
        }

        //initialize the parameter parser
        //Note that the parameter parser should always be the first line to execute.
        //Because, here we need to parse the parameters needed for startup.
        ParameterParser parameterParser = new ParameterParser(args);

        //initialize the metrics
        MetricsManager.get().init();

        System.setProperty(ConfigurationKeys.STORE_MODE, parameterParser.getStoreMode());

        ThreadPoolExecutor workingThreads = new ThreadPoolExecutor(NettyServerConfig.getMinServerPoolSize(),
                NettyServerConfig.getMaxServerPoolSize(), NettyServerConfig.getKeepAliveTime(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(NettyServerConfig.getMaxTaskQueueSize()),
                new NamedThreadFactory("ServerHandlerThread", NettyServerConfig.getMaxServerPoolSize()), new ThreadPoolExecutor.CallerRunsPolicy());

        NettyRemotingServer nettyRemotingServer = new NettyRemotingServer(workingThreads);
        //server port
        nettyRemotingServer.setListenPort(parameterParser.getPort());
        UUIDGenerator.init(parameterParser.getServerNode());
        //log store mode : file, db, redis
        SessionHolder.init(parameterParser.getStoreMode());

        DefaultCoordinator coordinator = new DefaultCoordinator(nettyRemotingServer);
        coordinator.init();
        nettyRemotingServer.setHandler(coordinator);
        // register ShutdownHook
        ShutdownHook.getInstance().addDisposable(coordinator);
        ShutdownHook.getInstance().addDisposable(nettyRemotingServer);

        //127.0.0.1 and 0.0.0.0 are not valid here.
        if (NetUtil.isValidIp(parameterParser.getHost(), false)) {
            XID.setIpAddress(parameterParser.getHost());
        } else {
            XID.setIpAddress(NetUtil.getLocalIp());
        }
        XID.setPort(nettyRemotingServer.getListenPort());

        try {
            nettyRemotingServer.init();
        } catch (Throwable e) {
            logger.error("nettyServer init error:{}", e.getMessage(), e);
            System.exit(-1);
        }

        System.exit(0);
    }
}

