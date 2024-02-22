package com.acciojob.bookmyshow.application.Controllers;

import com.acciojob.bookmyshow.application.Requests.AddShowRequest;
import com.acciojob.bookmyshow.application.Requests.AddShowSeatRequest;
import com.acciojob.bookmyshow.application.Services.ShowService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("addShow")
    public ResponseEntity addShow(@RequestBody AddShowRequest showRequest)
    {
        try {

            String result = showService.addShow(showRequest);
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteShow")
    public ResponseEntity deleteShow(@RequestParam("showId") Integer showId)
    {
        try {
            String deleteShow = showService.deleteShow(showId);
            return new ResponseEntity(deleteShow,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("addShowSeats")
    public ResponseEntity addShowSeat(@RequestBody AddShowSeatRequest showSeatRequest)
    {
         try {

             String result = showService.addShowSeat(showSeatRequest);
             return new ResponseEntity(result,HttpStatus.OK);

         }catch (Exception e)
         {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }


    @GetMapping("/getShowsOnGivenDate")
    public ResponseEntity getShowsOnGivenDate(@RequestParam("movieName")String movieName,
                                              @RequestParam("date") LocalDate date)
    {
        try
        {
            List<String> showAndTheaterList=showService.getAllShow(movieName,date);
            return new ResponseEntity(showAndTheaterList,HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("countOfBookedSeats")
    public ResponseEntity countOfBookedSeats(@RequestParam("showId") Integer showId)
    {
        try {
            String count = showService.countOfBookedSeats(showId);
            return new ResponseEntity(count,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
