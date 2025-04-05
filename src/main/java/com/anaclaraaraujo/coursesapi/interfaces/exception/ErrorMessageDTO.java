package com.anaclaraaraujo.coursesapi.interfaces.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String message;
    public String field;
}
