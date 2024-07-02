package com.rtm.pabean.model.bup.module;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TblPibDtlVD {

    private String car, jenis;
    private BigInteger serial;
    private BigDecimal nilai;
    private Date tgJatuhTempo;

}
