package com.rtm.pabean.dto.reference;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DetailCode {
    
    private String code, description, countryCode;

    public DetailCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
