package com.ksm.wstbackoffice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponseMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
