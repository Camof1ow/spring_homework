package com.example.homework;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class ResEntity {

    private StatusEnum status;
    private Object data;

    public ResEntity(Object data,StatusEnum status) {
        this.status = status;
        this.data = data;
    }

    public enum StatusEnum {

        OK(200, "OK"),
        BAD_REQUEST(400, "BAD_REQUEST"),
        NOT_FOUND(404, "NOT_FOUND"),
        INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

        final int statusCode;
        final String code;

        StatusEnum(int statusCode, String code) {
            this.statusCode = statusCode;
            this.code = code;
        }
    }
}