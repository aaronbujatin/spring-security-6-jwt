package com.aaronbujatin.bebrzjwt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInDto {

    private String username;
    private String password;

}
