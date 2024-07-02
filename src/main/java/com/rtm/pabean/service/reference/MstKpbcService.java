package com.rtm.pabean.service.reference;

import java.util.List;

import com.rtm.pabean.dto.reference.DetailCode;

public interface MstKpbcService {
    
    List<DetailCode> getAll();

    DetailCode getByCode(String code);

    DetailCode getByPortCode(String portCode);

    List<DetailCode> getByName(String name);
}
