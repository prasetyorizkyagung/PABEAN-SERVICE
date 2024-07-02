package com.rtm.pabean.dto.ceisa.document.bue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class KesiapanBarang {

    private String kodeJenisBarang, kodeJenisGudang, namaPic, alamat, nomorTelpPic, lokasiSiapPeriksa, kodeCaraStuffing,
            tanggalPkb, waktuSiapPeriksa, kodeJenisPartOf;
    private int jumlahContainer20, jumlahContainer40;

}
