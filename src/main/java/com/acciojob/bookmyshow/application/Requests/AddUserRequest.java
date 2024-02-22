package com.acciojob.bookmyshow.application.Requests;

import lombok.Data;

@Data
public class AddUserRequest {

    private String userName;
    private String emailId;
    private String phoneNo;

}
