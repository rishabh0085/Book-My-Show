package com.acciojob.bookmyshowapplication.Controllers;

import com.acciojob.bookmyshowapplication.Requests.AddTheaterRequest;
import com.acciojob.bookmyshowapplication.Requests.AddTheaterSeatsRequest;
import com.acciojob.bookmyshowapplication.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;
    @PostMapping("/addTheater")
    public String addTheater(@RequestBody AddTheaterRequest addTheaterRequest)
    {
        String result = theaterService.addTheater(addTheaterRequest);
        return result;
    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatsRequest theaterSeatsRequest)
    {
         String result = theaterService.addTheaterSeats(theaterSeatsRequest);
         return result;
    }
}
