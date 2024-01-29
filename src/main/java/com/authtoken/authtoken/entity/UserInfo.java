package com.authtoken.authtoken.entity;//package com.authtoken.authtoken.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String fullName;
//
//    @Column(unique = true)
//    private String email;
//
//    private String password;
//
//    @Column(unique = true)
//    private String mobileNumber;
//
//    private String invitationCode;
//
//    private String roles;
//
//    private boolean termsCondition;
//
//    private byte[] resume;
//
//    private byte[] avatar;
//
//    private LocalDate createdDate;
//
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userInfo")
public class UserInfo {

    @Id
    private ObjectId id;

    private String fullName;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Indexed(unique = true)
    private String mobileNumber;

    private String invitationCode;

    private String roles;

    private boolean termsCondition;

    private byte[] resume;

    private byte[] avatar;

    private LocalDate createdDate;


}

