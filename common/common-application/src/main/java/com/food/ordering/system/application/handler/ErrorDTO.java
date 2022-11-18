package com.food.ordering.system.application.handler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {
    public final String code;
    public final String message;
}
