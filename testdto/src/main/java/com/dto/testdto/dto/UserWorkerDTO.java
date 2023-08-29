package com.dto.testdto.dto;

import lombok.Data;

//combine the model you want to in this file
@Data
public class UserWorkerDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String gender;
    private String icNumber;
    private String password;
    private String role;
}
