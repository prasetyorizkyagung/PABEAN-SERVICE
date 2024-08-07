package com.rtm.pabean.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public abstract class ParserEngine<T> {
    
    private boolean stoped, paused;
    
    private QueueService<T> queueService;

    public ParserEngine(QueueService<T> queueService) {
        this.queueService = queueService;
    }

    public abstract void parseFile(T data);

    @Async
    public void runEngine() throws InterruptedException {
        while (!isStoped()) {
            Thread.sleep(3000);
            if (!isPaused()) {
                T data = queueService.consumeQueue();
                if (data == null) {
                    setPaused(true);
                } else {
                    parseFile(data);
                }
            }
        }
    }

    public boolean isStoped() {
        return this.stoped;
    }

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
