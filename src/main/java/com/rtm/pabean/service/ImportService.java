package com.rtm.pabean.service;

import java.io.File;

import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.model.tracking.DocumentTracking;

public interface ImportService {
    
    TblPibHdr store(String orderId, String companyId, File file) throws Exception;

    TblPibHdr update(DocumentTracking documentTracking, File file) throws Exception;
}
