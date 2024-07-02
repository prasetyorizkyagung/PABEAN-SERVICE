package com.rtm.pabean.service.bup;

import java.io.File;

import com.rtm.pabean.dto.ceisa.Queue;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;
import com.rtm.pabean.model.bup.Outbox;

public interface BupService {

    String incomingDocument(File file, String orderId, String companyId) throws Exception;

    Outbox queueCeisa(Queue queue) throws Exception;

    Pib generatePib(String car);
}
