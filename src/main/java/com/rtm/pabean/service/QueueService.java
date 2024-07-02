package com.rtm.pabean.service;

import java.util.Queue;

public interface QueueService<T> {
    
    void addQueue(T value);

    T consumeQueue();

    Queue<T> getQueue();
}
