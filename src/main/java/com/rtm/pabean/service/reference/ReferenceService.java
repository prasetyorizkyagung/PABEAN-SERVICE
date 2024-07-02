package com.rtm.pabean.service.reference;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dto.reference.DetailCode;
import com.rtm.pabean.dto.reference.ReferenceDocument;

@Service
public class ReferenceService {

    private MstDetailService mstDetailService;
    
    private MstDocumentPermitService mstDocumentPermitService;
    
    private MstKpbcService mstKpbcService;
    
    private MstPortService mstPortService;
    
    private MstTpsService mstTpsService;

    @Autowired
    public ReferenceService(MstDetailService mstDetailService, MstDocumentPermitService mstDocumentPermitService,
            MstKpbcService mstKpbcService, MstPortService mstPortService, MstTpsService mstTpsService) {
        this.mstDetailService = mstDetailService;
        this.mstDocumentPermitService = mstDocumentPermitService;
        this.mstKpbcService = mstKpbcService;
        this.mstPortService = mstPortService;
        this.mstTpsService = mstTpsService;
    }

    public DetailCode kpbcByCode(String code) {
        return mstKpbcService.getByCode(code);
    }

    public DetailCode portByCode(String code) {
        return mstPortService.getByCode(code);
    }

    public ReferenceDocument documentByCode(String code) {
        return mstDocumentPermitService.getByCode(code);
    }

    public DetailCode generalReference(String id, String code) {
        return mstDetailService.getByHeaderIdCode(id, code);
    }

    public List<DetailCode> tpsByCustomsCode(String code) {
        return mstTpsService.getByCustomsOffice(code);
    }

    public DetailCode tpsByCustomsCodeAndCode(String customsCode, String code){
        return mstTpsService.getByCustomsOfficeAndCode(customsCode, code);
    }
}
