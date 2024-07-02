package com.rtm.pabean.service;

import java.io.File;

import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.tracking.DocumentTracking;

public interface ExportService {
    
    TblPebHdr store(String orderId, String companyId, File file) throws Exception;

    TblPebHdr update(DocumentTracking documentTracking, File file) throws Exception;
}
