package com.acciojob.bookmyshow.application.Transformers;

import com.acciojob.bookmyshow.application.Entities.User;
import com.acciojob.bookmyshow.application.Requests.AddUserRequest;

public class UserTransformers {

    public static User convertRequestToEntity(AddUserRequest userRequest)
    {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .emailId(userRequest.getEmailId())
                .phoneNo(userRequest.getPhoneNo()).build();

        return user;
    }
}
