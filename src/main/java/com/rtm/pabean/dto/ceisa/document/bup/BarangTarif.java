package com.rtm.pabean.dto.ceisa.document.bup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BarangTarif {
    
    private String kodeJenisTarif, kodeFasilitasTarif, kodeJenisPungutan, kodeSatuanBarang, flagBmtSementara;
    private BigDecimal jumlahSatuan, tarif, tarifFasilitas, nilaiBayar, nilaiFasilitas;
}
