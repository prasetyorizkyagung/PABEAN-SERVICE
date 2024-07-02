package com.rtm.pabean.model.tracking;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ResponseDetailPK implements Serializable {

    private String responseId;
    private int seri;
}
