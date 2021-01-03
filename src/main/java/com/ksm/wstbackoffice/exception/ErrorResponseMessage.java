package com.ksm.wstbackoffice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorResponseMessage {
    private LocalDate localDate;
    private String message;
}
