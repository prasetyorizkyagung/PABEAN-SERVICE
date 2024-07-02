package com.rtm.pabean.service.bup;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rtm.pabean.model.bup.Inbox;
import com.rtm.pabean.service.QueueService;

@Service("bupQueueInboxServiceImp")
public class QueueInboxServiceImp implements QueueService<Inbox> {

    private Queue<Inbox> queue = new LinkedList<>();

    private InboxService inboxService;

    @Autowired
    public QueueInboxServiceImp(@Qualifier("bupInboxServiceImp") InboxService inboxService) {
        this.inboxService = inboxService;
        List<Inbox> listOfInbox = this.inboxService.getQueue();
        for (Inbox inbox : listOfInbox) {
            addQueue(inbox);
        }
    }

    @Override
    public void addQueue(Inbox value) {
        queue.add(value);
    }

    @Override
    public Inbox consumeQueue() {
        return queue.poll();
    }

    @Override
    public Queue<Inbox> getQueue() {
        return queue;
    }
    
}
