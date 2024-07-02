package com.rtm.pabean.service.bup;

import java.util.List;

import com.rtm.pabean.model.bup.Outbox;

public interface OutboxService {
    
    void saveOrUpdate(Outbox outbox) throws Exception;

    void updateStatus(String outboxId, String status, String message) throws Exception;

    List<Outbox> getQueue();
}
