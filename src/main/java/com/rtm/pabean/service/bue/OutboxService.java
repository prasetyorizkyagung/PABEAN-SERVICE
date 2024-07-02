package com.rtm.pabean.service.bue;

import java.util.List;

import com.rtm.pabean.model.bue.Outbox;

public interface OutboxService {
    
    void saveOrUpdate(Outbox outbox) throws Exception;

    void updateStatus(String outboxId, String status, String message) throws Exception;

    List<Outbox> getQueue();
}
