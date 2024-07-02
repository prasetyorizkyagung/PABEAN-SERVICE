package com.rtm.pabean.service.reference;

import java.util.List;

import com.rtm.pabean.dto.reference.DetailCode;

public interface MstDetailService {
    
    List<DetailCode> getByHeaderId(String headerId);

    List<DetailCode> getByName(String headerId, String name);

    DetailCode getByHeaderIdCode(String headerId, String code);

    List<DetailCode> getByCodeOrName(String headerId, String parameter);
}
