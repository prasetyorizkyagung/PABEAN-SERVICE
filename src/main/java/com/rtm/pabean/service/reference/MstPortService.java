package com.rtm.pabean.service.reference;

import java.util.List;

import com.rtm.pabean.dto.reference.DetailCode;

public interface MstPortService {
    
    List<DetailCode> getAll();

    DetailCode getByCode(String code);

    List<DetailCode> getByCountryCode(String countryCode);

    List<DetailCode> getByName(String name);
}
