package com.acciojob.bookmyshow.application.Controllers;

import com.acciojob.bookmyshow.application.Requests.BookTicketRequest;
import com.acciojob.bookmyshow.application.Response.ShowTicketResponse;
import com.acciojob.bookmyshow.application.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest)
    {
        try {
            String result = ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("viewTicket")
    public ResponseEntity viewTicket(@RequestParam("ticketId") Integer ticketId)
    {
        try {
            ShowTicketResponse showTicketResponse = ticketService.viewTicket(ticketId);
            return  new ResponseEntity(showTicketResponse,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("cancelBookedTicket")
    public ResponseEntity cancelBookedTicket(@RequestParam("ticketId") Integer ticketId) {
        try {
            String result = ticketService.cancelBookedTicket(ticketId);
            return new  ResponseEntity(result,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity (e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
