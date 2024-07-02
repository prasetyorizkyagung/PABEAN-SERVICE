package com.rtm.pabean.dto.ceisa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ResponseDetail {

    private String idRespon, nomorAju, kodeRespon, nomorDaftar, nomorRespon, kodeDokumen, keterangan, pdf;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date tanggalDaftar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date tanggalRespon;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date waktuRespon;
    private List<String> pesan;

    public List<String> getPesan() {
        if (this.pesan == null) {
            this.pesan = new ArrayList<>();
        }
        return this.pesan;
    }
}
