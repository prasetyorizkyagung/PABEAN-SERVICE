package com.rtm.pabean.service.reference;

import java.util.List;

import com.rtm.pabean.dto.reference.DetailCode;

public interface MstTpsService {
    
    List<DetailCode> getByCustomsOffice(String customsOffice);

    List<DetailCode> getByCustomsOfficeAndName(String customsOffice, String name);

    DetailCode getByCustomsOfficeAndCode(String customsOffice, String code);
}
