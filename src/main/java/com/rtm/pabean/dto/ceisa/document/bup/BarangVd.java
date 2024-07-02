package com.rtm.pabean.dto.ceisa.document.bup;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BarangVd {
    
    private String kodeJenisVd, jatuhTempoRoyalti;
    private BigDecimal nilaiBarangVd;
}
