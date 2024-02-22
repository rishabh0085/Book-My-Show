package com.acciojob.bookmyshow.application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    @Column(unique = true,nullable = false)
    private String theaterName;

    @Column(unique = true,nullable = false)
    private String theaterAddress;

    //Theater is Parent and TheaterSeat is Child
    //Bi-Directional Mapping
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();

    //Theater is Parent and Show Entity is child
    //Bi-Directional mapping
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();


}
