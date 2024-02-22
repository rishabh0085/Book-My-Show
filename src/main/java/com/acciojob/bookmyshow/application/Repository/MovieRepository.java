package com.acciojob.bookmyshow.application.Repository;

import com.acciojob.bookmyshow.application.Entities.Movie;
import com.acciojob.bookmyshow.application.Enums.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    //This is 2nd type of fetch
    //you need to define Method Definition
    //No Query
    Movie findMovieByMovieNameAndAndMovieLanguage(String movieName, Languages language);
    Optional<Movie> findByMovieName(String movieName);
    List<Movie> findAllByMovieDurationGreaterThanEqual(double movieDuration);

    @Query(value = "SELECT movie_name FROM movie ORDER BY rating DESC LIMIT 1", nativeQuery = true)
    String findMostLikedMovieTitle();

}
