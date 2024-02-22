package com.acciojob.bookmyshowapplication.Controllers;

import com.acciojob.bookmyshowapplication.Entities.Movie;
import com.acciojob.bookmyshowapplication.Requests.AddMovieRequest;
import com.acciojob.bookmyshowapplication.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @PostMapping("/addMovie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest addMovieRequest)
    {
        String result = movieService.addMovie(addMovieRequest);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping("/getMovieInfo")
    public Movie getMovie(@RequestParam("movieId")Integer movieId){
        return movieService.getMovie(movieId);
    }
}
