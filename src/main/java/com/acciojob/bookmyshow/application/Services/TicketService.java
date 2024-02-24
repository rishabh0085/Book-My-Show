package com.acciojob.bookmyshow.application.Services;

import com.acciojob.bookmyshow.application.Entities.*;
import com.acciojob.bookmyshow.application.Enums.SeatType;
import com.acciojob.bookmyshow.application.Repository.ShowRepository;
import com.acciojob.bookmyshow.application.Repository.ShowSeatRepository;
import com.acciojob.bookmyshow.application.Repository.TicketRepository;
import com.acciojob.bookmyshow.application.Repository.UserRepository;
import com.acciojob.bookmyshow.application.Requests.BookTicketRequest;
import com.acciojob.bookmyshow.application.Response.ShowTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest) throws Exception {
        //1.First get the Show Entity
        Show show = showRepository.findById(bookTicketRequest.getShowId()).get();

        //2.We check weather seats are available or not!!!
        List<ShowSeat> showSeatList = show.getShowSeatList();

        int totalBookingAmount = 0; //initially amount is set as 0

        for (String BookedSeatNo : bookTicketRequest.getSeatList()) {
            //we need to check weather seat is available or not!!!
            for (ShowSeat showSeat : showSeatList) {
                if (showSeat.getSeatNo().equals(BookedSeatNo)
                        && (bookTicketRequest.getSeatType().equals(showSeat.getSeatType()))) {
                    //if both condition becomes true than you can book ticket
                    if (showSeat.isAvailable()) {
                        showSeat.setAvailable(Boolean.FALSE);
                        showSeat.setFoodAttached(bookTicketRequest.isFoodAttached());
                        totalBookingAmount = totalBookingAmount + showSeat.getSeatPrice();

                        if (bookTicketRequest.isFoodAttached()) {
                            totalBookingAmount += 400;
                        }

                    } else {
                        throw new Exception("Seat No. " + showSeat.getSeatNo() + " is already booked!!!");
                    }

                }
            }
        }

        //get user
        User user = userRepository.findByEmailId(bookTicketRequest.getEmailId());

        //if you reached here means all seats are available and all chosen seats are booked SuccessFully!!!
        //Save the Ticket Entity
        Ticket ticket = Ticket.builder()
                .NoOfBookedSeats(bookTicketRequest.getSeatList().toString())
                .seatType(bookTicketRequest.getSeatType())
                .totalAmountPaid(totalBookingAmount)
                .movieName(show.getMovie().getMovieName())
                .theaterNameAndAddress(show.getTheater().getTheaterName() + " " + show.getTheater().getTheaterAddress())
                .movieTiming(show.getShowTime())
                .movieDate(show.getShowDate())
                .user(user)
                .show(show).build(); //F.K being set

        show.getTicketList().add(ticket); //Bi-Directional Mapping
        user.getTicketList().add(ticket); //Bi-Directional Mapping
        ticket = ticketRepository.save(ticket);


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmailId());
        msg.setFrom("springacciojob@gmail.com");
        msg.setSubject("Movie Ticket Conformation!!!");


        msg.setText("Hey " + user.getUserName() + " Your Tickets for " + ticket.getMovieName() + " at " +
                ticket.getTheaterNameAndAddress() + " has been booked Successfully!!! . Your Booked seats are " + ticket.getNoOfBookedSeats() + " [" + ticket.getSeatType() + "]" + ". Your show Time " + ticket.getMovieTiming() + " and Show Date is" + " "
                + ticket.getMovieDate() + ". Total Amount of Rs " + ticket.getTotalAmountPaid() + " is already Paid. Enjoy the Movie!!!");

        javaMailSender.send(msg);

        return "Ticket has been booked Successfully!!! with TicketID " + ticket.getTicketId();

    }

    //Logic to ViewBooked Ticket...
    public ShowTicketResponse viewTicket(Integer ticketId) throws Exception {
        //1.Get Ticket Entity
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isEmpty()) {
            throw new Exception("Invalid ticketId!!!");
        }

        Ticket ticket = optionalTicket.get();

        //2.from ticket entity get show entity
        Show show = ticket.getShow();
        //3.from show entity get movie entity
        Movie movie = show.getMovie();

        String movieName = show.getMovie().getMovieName();
        String theaterInformation = show.getTheater().getTheaterName() + " " + show.getTheater().getTheaterAddress();
        String bookedSeats = ticket.getNoOfBookedSeats();
        SeatType seatType = ticket.getSeatType();

        ShowTicketResponse showTicketResponse = ShowTicketResponse.builder()
                .movieName(movieName)
                .theaterInfo(theaterInformation)
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .totalAmountPaid(ticket.getTotalAmountPaid())
                .seatNo(bookedSeats)
                .seatType(seatType).build();

        String emailId = ticket.getUser().getEmailId();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springacciojob@gmail.com");
        simpleMailMessage.setTo(emailId);
        simpleMailMessage.setSubject("Movie Ticket Conformation!!!");
        simpleMailMessage.setText(showTicketResponse.toString());

        javaMailSender.send(simpleMailMessage);

        return showTicketResponse;

    }

    public String cancelBookedTicket(Integer ticketId) throws Exception {

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isEmpty()) {
            throw new Exception("Invalid TicketId!!!");
        }

        Ticket ticket = optionalTicket.get();

        User user = ticket.getUser();

        // Mark associated show seats as available and update database
        for (ShowSeat bookedSeat : ticket.getShow().getShowSeatList()) {
            if (ticket.getNoOfBookedSeats().contains(bookedSeat.getSeatNo())) {
                bookedSeat.setAvailable(true);
                bookedSeat.setFoodAttached(false);
                showSeatRepository.save(bookedSeat);
            }
        }

        // Remove the ticket from the user's ticket list
        user.getTicketList().remove(ticket);
        userRepository.save(user);

        // Delete the ticket
        ticketRepository.delete(ticket);

        return "Ticket has been successfully canceled!";
    }


}


