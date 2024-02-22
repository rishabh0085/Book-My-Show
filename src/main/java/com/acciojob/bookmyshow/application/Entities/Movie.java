package com.acciojob.bookmyshow.application.Entities;

import com.acciojob.bookmyshow.application.Enums.Genre;
import com.acciojob.bookmyshow.application.Enums.Languages;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true,nullable = false)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private Languages movieLanguage;

    @Enumerated(value = EnumType.STRING)
    private Genre movieGenre;

    private LocalDate movieReleaseDate;//if u want to keep only date we use LocalDate...but if use Date u get both Date and Time

    private double movieDuration;

    private double rating;

    //Between Movie and Show Entity Movie is Parent and Show is Child
    //Bi-Directional mapping
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}
