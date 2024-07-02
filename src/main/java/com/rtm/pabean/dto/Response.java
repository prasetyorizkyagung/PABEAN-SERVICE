package com.rtm.pabean.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Response<T> {
    
    @JsonFormat(timezone = "GMT+7")
    private Date timestamp;
    private int status;
    private String error, success;
    private List<T> data;

    public List<T> getData() {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        return this.data;
    }
}
