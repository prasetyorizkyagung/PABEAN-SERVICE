package com.rtm.pabean.dto.ceisa.document.bue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Kontainer {
    
    private String kodeTipeKontainer, kodeUkuranKontainer, nomorKontainer, kodeJenisKontainer;
    private int seriKontainer;
}
