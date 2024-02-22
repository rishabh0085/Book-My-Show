package com.acciojob.bookmyshowapplication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theaters")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;

    private String theaterName;

    private String theaterAddress;

    private int numOfScreens;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();

    /* @OneToMany(mappedBy = "Theater", cascade = CascadeType.ALL)
       private List<TheaterSeat> theaterSeatList = new ArrayList<>();
    This annotation establishes a one-to-many relationship between the Theater entity and the TheaterSeat entity. It means that one theater can have multiple seats.
    mappedBy = "Theater" specifies that the TheaterSeat class has a field named Theater that is the owning side of this relationship.
    cascade = CascadeType.ALL: Specifies that any changes (insert, update, delete) made to a Theater instance should be applied to its associated TheaterSeat instances as well.
    */

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}
