package com.authtoken.authtoken.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserDTO {

    private Long id;

    private MultipartFile resume;

    private MultipartFile  avatar;

    private LocalDate createdDate;
}