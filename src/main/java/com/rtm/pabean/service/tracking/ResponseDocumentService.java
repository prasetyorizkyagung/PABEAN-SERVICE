package com.rtm.pabean.service.tracking;

import java.util.List;

import com.rtm.pabean.model.tracking.MstResponseDocument;

public interface ResponseDocumentService {
    
    List<MstResponseDocument> getByName(String name);

    List<MstResponseDocument> getFirst10();

    List<MstResponseDocument> getAll();

    MstResponseDocument getByCode(String code);
}
