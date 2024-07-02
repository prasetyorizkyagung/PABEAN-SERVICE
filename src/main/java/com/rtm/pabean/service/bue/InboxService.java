package com.rtm.pabean.service.bue;

import java.util.List;

import com.rtm.pabean.model.bue.Inbox;

public interface InboxService {
    
    void saveOrUpdate(Inbox inbox) throws Exception;

    void updateStatus(String inboxId, String status, String message) throws Exception;

    List<Inbox> getQueue();
}
