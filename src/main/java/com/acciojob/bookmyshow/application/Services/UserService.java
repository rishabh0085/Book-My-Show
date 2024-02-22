package com.acciojob.bookmyshow.application.Services;

import com.acciojob.bookmyshow.application.Entities.User;
import com.acciojob.bookmyshow.application.Repository.UserRepository;
import com.acciojob.bookmyshow.application.Requests.AddUserRequest;
import com.acciojob.bookmyshow.application.Response.UserInfoResponse;
import com.acciojob.bookmyshow.application.Transformers.UserTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public String addUser(AddUserRequest userRequest)
    {
        User newUser = UserTransformers.convertRequestToEntity(userRequest);

        newUser = userRepository.save(newUser);

        return  "New User has been added Successfully!!! with UserId - "+newUser.getUserId();
    }

    public UserInfoResponse getUser(Integer userId) throws Exception
    {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty())
        {
            throw new Exception("Invalid User!!! User Not registered to Book-My-Application!!!");
        }

        User user = optionalUser.get();

        String userName = user.getUserName();
        String emailId = user.getEmailId();
        String userPhNo = user.getPhoneNo();

        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userName(userName)
                .userEmail(emailId)
                .userPhNo(userPhNo).build();

        return userInfoResponse;
    }

    public User updateUser(String userEmailId, User updatedUser) throws Exception
    {
        User existingUser = userRepository.findByEmailId(userEmailId);
        if (existingUser == null)
        {
            throw new Exception("Invalid User!!! User Not registered to Book-My-Show");
        }

        //here i need to update the existing user
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setEmailId(updatedUser.getEmailId());
        existingUser.setPhoneNo(updatedUser.getPhoneNo());

        userRepository.save(existingUser);

        return existingUser;
    }

    public String deleteUser(String userEmailId) throws Exception
    {
        User existingUser = userRepository.findByEmailId(userEmailId);
        if (existingUser == null)
        {
            throw new Exception("Invalid User!!! User Not registered to Book-My-Show");
        }

        userRepository.delete(existingUser);

        return "User have been deleted Successfully!!!";
    }

}
