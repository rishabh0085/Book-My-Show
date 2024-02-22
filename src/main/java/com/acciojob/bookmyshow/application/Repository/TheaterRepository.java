package com.acciojob.bookmyshow.application.Repository;

import com.acciojob.bookmyshow.application.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Optional<Theater> findByTheaterNameAndTheaterAddress(String theaterName, String theaterAddress);

}
