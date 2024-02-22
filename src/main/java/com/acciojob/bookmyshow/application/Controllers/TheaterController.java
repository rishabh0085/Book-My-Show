package com.acciojob.bookmyshow.application.Controllers;

import com.acciojob.bookmyshow.application.Requests.AddTheaterRequest;
import com.acciojob.bookmyshow.application.Requests.AddTheaterSeatRequest;
import com.acciojob.bookmyshow.application.Requests.GrossRevenueRequest;
import com.acciojob.bookmyshow.application.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("addTheater")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest addTheaterRequest)
    {
        String result = theaterService.addTheater(addTheaterRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("deleteTheater")
    public ResponseEntity deleteTheater(@RequestParam("theaterId") Integer theaterId)
    {
        try {
            String result = theaterService.deleteTheater(theaterId);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatRequest theaterSeatRequest)
    {
        String result = theaterService.addTheaterSeats(theaterSeatRequest);
        return result;
    }

    @GetMapping("grossRevenueOfMovie")
    public ResponseEntity grossRevenueOfMovie(@RequestBody GrossRevenueRequest grossRevenueRequest)
    {
        try {
            double grossRevenue = theaterService.grossRevenue(grossRevenueRequest);
            return new ResponseEntity(grossRevenue,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
