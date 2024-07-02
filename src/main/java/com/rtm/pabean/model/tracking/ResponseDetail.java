package com.rtm.pabean.model.tracking;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "tracking")
public class ResponseDetail {

    @EmbeddedId
    private ResponseDetailPK responseDetailPK;
    private String message;
}