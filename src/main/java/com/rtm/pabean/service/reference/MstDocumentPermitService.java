package com.rtm.pabean.service.reference;

import java.util.List;

import com.rtm.pabean.dto.reference.ReferenceDocument;

public interface MstDocumentPermitService {
    
    List<ReferenceDocument> getByName(String name);

    ReferenceDocument getByCode(String code);

    List<ReferenceDocument> getFirst10();

    List<ReferenceDocument> getByNameOrCode(String val);
}
