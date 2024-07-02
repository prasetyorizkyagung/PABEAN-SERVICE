package com.rtm.pabean.dto.ceisa.document.bue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Kemasan {
    
    private int jumlahKemasan, seriKemasan;
    private String kodeJenisKemasan, merkKemasan; 
}
