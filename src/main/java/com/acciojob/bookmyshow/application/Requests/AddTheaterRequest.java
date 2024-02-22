package com.acciojob.bookmyshow.application.Requests;

import lombok.Data;

@Data //This is MovieDTO so i need to use @Data Annotation
public class AddTheaterRequest {

    private String theaterName;

    private String theaterAddress;

    private Integer noOfScreens;

}
