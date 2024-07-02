package com.rtm.pabean.model.bup;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupVdItem")
@Data
@Table(schema = "import", name = "vd_item")
public class VdItem {

    @EmbeddedId
    private BupVdItemPK vdItemPK;
    private Date dueDate;
    private BigDecimal vdValue;
}
