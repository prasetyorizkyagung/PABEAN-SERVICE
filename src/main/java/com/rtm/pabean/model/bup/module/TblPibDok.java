package com.rtm.pabean.model.bup.module;

import java.util.Date;

import lombok.Data;

@Data
public class TblPibDok {

    private String car, dokKd, dokNo, dokinst, kdGroupDok;
    private Date dokTg;
    private int noUrut;

}
