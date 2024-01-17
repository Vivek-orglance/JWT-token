package com.authtoken.authtoken.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class UserResponseDTO {

    private String email;
    private String token;
    private List<String> roles;
}