package com.acciojob.bookmyshow.application.Repository;

import com.acciojob.bookmyshow.application.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmailId(String emailId);
}
