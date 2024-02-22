package com.acciojob.bookmyshow.application.Repository;

import com.acciojob.bookmyshow.application.Entities.Movie;
import com.acciojob.bookmyshow.application.Entities.Show;
import com.acciojob.bookmyshow.application.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
    Optional<Show> findByMovieAndTheaterAndShowDateAndShowTime(Movie movie, Theater theater, LocalDate showDate, LocalTime showTime);

    Optional<Show> findByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate, LocalTime showTime, Movie movie, Theater theater);

    List<Show> findByMovieAndShowDate(Movie movie, LocalDate showDate);

}
