package com.rtm.pabean.dto.ceisa.document.bup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BarangSpekKhusus {

    private int kodeSpekKhusus;
    private String uraianBarangSpekKhusus;
}
