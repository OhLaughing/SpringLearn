package com.demo;

import com.demo.entity.Server;
import com.demo.mapper.ServerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerInitListener implements ServletContextListener {
    private static Map<Integer, ThreadPoolExecutor> threadPoolExecutorMap = new ConcurrentHashMap<>();
    private ServerMapper serverMapper;
    private WebApplicationContext springContext;

    public static ThreadPoolExecutor getThreadPoolExecutor(int serverId) {
        return threadPoolExecutorMap.get(serverId);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("begin to init server application");
        springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        serverMapper = springContext.getBean(ServerMapper.class);
        List<Server> list = serverMapper.findAll();
        log.info("server num: " + list.size());
        System.out.println(list);
        for (Server server : list) {
            threadPoolExecutorMap.put(server.getId(), new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10)));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
