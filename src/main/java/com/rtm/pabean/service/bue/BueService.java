package com.rtm.pabean.service.bue;

import java.io.File;

import com.rtm.pabean.dto.ceisa.Queue;
import com.rtm.pabean.dto.ceisa.document.bue.Peb;
import com.rtm.pabean.model.bue.Outbox;

public interface BueService {
    
    String incomingDocument(File file, String orderId, String companyId) throws Exception;

    String updatingDocument(File file, String orderId, String companyId) throws Exception;

    Outbox queueCeisa(Queue queue) throws Exception;

    Peb generatePeb(String car);
}
