package com.manual.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDTO {
    private Boolean status;
    private String jwt;
    private String message;
}
