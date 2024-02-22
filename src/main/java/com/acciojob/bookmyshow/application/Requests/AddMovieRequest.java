package com.acciojob.bookmyshow.application.Requests;

import com.acciojob.bookmyshow.application.Enums.Genre;
import com.acciojob.bookmyshow.application.Enums.Languages;
import lombok.Data;
import java.time.LocalDate;
@Data    //This is MovieDTO so i need to use @Data Annotation

public class AddMovieRequest {

    private String movieName;
    private Languages movieLanguage;
    private Genre movieGenre;
    private LocalDate movieReleaseDate;//if u want to keep only date we use LocalDate...but if use Date u get both Date and Time
    private double movieDuration;
    private double rating;

}
