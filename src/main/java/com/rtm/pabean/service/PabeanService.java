package com.rtm.pabean.service;

import java.io.File;

public interface PabeanService {
    
    String incomingDocument(String documentType, String orderId, String companyId, File file) throws Exception;

    String updatedDocument(String documentType, String orderId, String companyId, File file) throws Exception;
}
