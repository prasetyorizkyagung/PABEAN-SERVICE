package com.rtm.pabean.service.bup;

import java.util.List;

import com.rtm.pabean.model.bup.Inbox;

public interface InboxService {

    void saveOrUpdate(Inbox inbox) throws Exception;

    void updateStatus(String inboxId, String status, String message) throws Exception;

    List<Inbox> getQueue();
}
