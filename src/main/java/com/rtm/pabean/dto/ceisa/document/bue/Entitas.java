package com.rtm.pabean.dto.ceisa.document.bue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Entitas {

    private String alamatEntitas, kodeEntitas, kodeJenisApi, kodeJenisIdentitas, kodeStatus, namaEntitas, nibEntitas,
            nomorIdentitas, kodeNegara;
    private int seriEntitas;
}
