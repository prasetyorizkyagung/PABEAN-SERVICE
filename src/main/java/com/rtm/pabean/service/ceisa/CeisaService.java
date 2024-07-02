package com.rtm.pabean.service.ceisa;

import java.nio.file.Path;

import com.rtm.pabean.dto.ceisa.ResponseCeisa;
import com.rtm.pabean.dto.ceisa.document.bue.Peb;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;

public interface CeisaService {

    ResponseCeisa login(String username, String password);
    
    String submitPib(String car, String token, boolean isFinal, Pib pib);

    String submitPeb(String car, String token, boolean isFinal, Peb peb);

    ResponseCeisa responseByCar(String car, String token);

    String downloadResponseS3(String token, String filepath);

    Path downloadResponse(String token, String filePath);
}
