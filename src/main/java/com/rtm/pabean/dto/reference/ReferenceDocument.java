package com.rtm.pabean.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceDocument {

    private String code, name;
    private boolean isFacility;
}
