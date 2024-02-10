package com.aaronbujatin.bebrzjwt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
