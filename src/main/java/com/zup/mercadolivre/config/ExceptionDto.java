package com.zup.mercadolivre.config;

public class ExceptionDto {
    private String field;
    private String statusCode;
    private String message;

    public ExceptionDto(String field, String statusCode, String message) {
        this.field = field;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
