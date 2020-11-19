package com.elinext.bids.reader.service;

public interface QueueExecutorService {

    void addTask(Runnable task, String groupIdentifier);

}
