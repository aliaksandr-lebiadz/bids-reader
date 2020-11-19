package com.elinext.bids.reader.service.impl;

import com.elinext.bids.reader.service.QueueExecutorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class QueueExecutorServiceImpl implements QueueExecutorService {

    @Value("${threads_queue.core_pool_size}")
    private int corePoolSize;

    @Value("${threads_queue.max_pool_size}")
    private int maximumPoolSize;

    @Value("${threads_queue.alive_time_in_seconds}")
    private int aliveTimeInSeconds;

    private final Map<String, ExecutorService> executorGroups = new HashMap<>();

    @Override
    public void addTask(Runnable runnable, String groupIdentifier) {
        ExecutorService executorByIdentifier = executorGroups.get(groupIdentifier);
        ExecutorService newExecutor = Objects.nonNull(executorByIdentifier) ? executorByIdentifier : createQueueExecutor(groupIdentifier);
        newExecutor.execute(runnable);
    }

    private ExecutorService createQueueExecutor(String groupIdentifier) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor newExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, aliveTimeInSeconds, TimeUnit.SECONDS, queue);
        executorGroups.put(groupIdentifier, newExecutor);
        return newExecutor;
    }
}
