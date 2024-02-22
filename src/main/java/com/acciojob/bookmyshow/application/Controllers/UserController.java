package com.acciojob.bookmyshow.application.Controllers;

import com.acciojob.bookmyshow.application.Entities.User;
import com.acciojob.bookmyshow.application.Requests.AddUserRequest;
import com.acciojob.bookmyshow.application.Response.UserInfoResponse;
import com.acciojob.bookmyshow.application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public String addUser(@RequestBody AddUserRequest userRequest) {
        String result = userService.addUser(userRequest);
        return result;
    }

    @GetMapping("getUserProfile")
    public ResponseEntity getUser(@RequestParam("userId") Integer userId) {
        try {
            UserInfoResponse user = userService.getUser(userId);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity updateUser(@RequestParam("userEmailId") String userEmailId,
                                     @RequestBody User updatedUser) {
        try {
            User updateUser = userService.updateUser(userEmailId, updatedUser);
            return new ResponseEntity(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("deleteUser")
    public ResponseEntity deleteUser(@RequestParam("userEmailId") String userEmailId) {
        try {
            String result = userService.deleteUser(userEmailId);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}