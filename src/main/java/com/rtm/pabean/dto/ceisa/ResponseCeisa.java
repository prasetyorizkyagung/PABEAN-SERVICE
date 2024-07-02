package com.rtm.pabean.dto.ceisa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseCeisa {
    
    private String status, message, idHeader, nomorAju, idUser, email, flagBlokir, flagAktivasi;
    private ResponseLogin item;
    private List<String> data;
    private List<ResponseStatus> dataStatus;
    private List<ResponseDetail> dataRespon;
}
