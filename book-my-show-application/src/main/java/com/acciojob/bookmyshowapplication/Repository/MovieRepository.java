package com.acciojob.bookmyshowapplication.Repository;

import com.acciojob.bookmyshowapplication.Entities.Movie;
import com.acciojob.bookmyshowapplication.Enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //2nd type of fetch where you just define the method
    //no query nothing
    //but naming of the method has to be strict

    Movie findMovieByMovieNameAndAndMovieLanguage(String movieName, Language language);
    Movie findMovieByMovieName(String movieName);

    Movie findByMovieName(String movieName);
    List<Movie> findAllByDurationGreaterThanEqual(double duration);

}
