package com.manual.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String userCode;
    private String password;
}
