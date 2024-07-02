package com.rtm.pabean.model.bup.module;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TblPibDtlDok {

    private String car, kdFasDtl, dokKd, dokNo, kdGroupDok, noSeriBrgSkep;
    private BigInteger serial, noUrut;
    private Date dokTg;

}
