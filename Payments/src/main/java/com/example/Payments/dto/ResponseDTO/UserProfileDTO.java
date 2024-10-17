package com.example.Payments.dto.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class UserProfileDTO {
    private long userId;
    private String fullName;
    private String email;
    private String phoneNo;
    private String nationalId;
    private String gender;
    private Date dateOfBirth;
    private String nationality;
    private String userType;
}
