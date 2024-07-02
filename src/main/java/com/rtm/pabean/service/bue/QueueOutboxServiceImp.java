package com.rtm.pabean.service.bue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rtm.pabean.model.bue.Outbox;
import com.rtm.pabean.service.QueueService;

@Service("bueQueueOutboxServiceImp")
public class QueueOutboxServiceImp implements QueueService<Outbox> {

    private Queue<Outbox> queue = new LinkedList<>();

    private OutboxService outboxService;

    @Autowired
    public QueueOutboxServiceImp(@Qualifier("bueOutboxServiceImp") OutboxService outboxService) {
        this.outboxService = outboxService;
        List<Outbox> listOfOutbox = this.outboxService.getQueue();
        for (Outbox outbox : listOfOutbox) {
            addQueue(outbox);
        }
    }

    @Override
    public void addQueue(Outbox value) {
        queue.add(value);
    }

    @Override
    public Outbox consumeQueue() {
        return queue.poll();
    }

    @Override
    public Queue<Outbox> getQueue() {
        return queue;
    }
    
}
