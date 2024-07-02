package com.rtm.pabean.model.tracking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "tracking")
public class MstResponseDocument {

    @Id
    private String responseId;
    private String responseName;
}
