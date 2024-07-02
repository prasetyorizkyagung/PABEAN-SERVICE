package com.rtm.pabean.model.bup.module;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

@Data
public class TblPibFas {
    
    private String car, kdFasBM, kdFasCuk, kdFasPPN, kdFasPPH, kdFasPBM, kdFasBMAD, BMADS, kdFasBMTP, BMTPS, kdFasBMIM, BMIMS, kdFasBMPB, BMPBS;
    private BigInteger serial;
    private BigDecimal fasBM, fasCuk, fasPPN, fasPPH, fasPBM, fasBMAD, fasBMTP, fasBMIM, fasBMPB;
}
