package com.acciojob.bookmyshowapplication.Entities;

import com.acciojob.bookmyshowapplication.Enums.Genre;
import com.acciojob.bookmyshowapplication.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true, nullable = false)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //So, putting it all together, the code is instructing the system to store the values of the Language enum as strings in the database
    // when saving or retrieving information about the movieLanguage property. This can be useful when working with databases that expect enum values to be stored in a certain way,
    // such as using strings instead of integers.

    @Enumerated(value = EnumType.STRING)
    private Language movieLanguage;

    private LocalDate releaseDate;

    private double duration;

    private double rating;

    //Movie entity works as a Parent for showEntity
    //Bi-Directional mapping
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}
