package com.acciojob.bookmyshow.application.Services;

import com.acciojob.bookmyshow.application.Entities.Show;
import com.acciojob.bookmyshow.application.Entities.ShowSeat;
import com.acciojob.bookmyshow.application.Entities.Theater;
import com.acciojob.bookmyshow.application.Entities.TheaterSeat;
import com.acciojob.bookmyshow.application.Enums.SeatType;
import com.acciojob.bookmyshow.application.Repository.TheaterRepository;
import com.acciojob.bookmyshow.application.Requests.AddTheaterRequest;
import com.acciojob.bookmyshow.application.Requests.AddTheaterSeatRequest;
import com.acciojob.bookmyshow.application.Requests.GrossRevenueRequest;
import com.acciojob.bookmyshow.application.Transformers.TheaterTransformers;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(AddTheaterRequest theaterRequest)
    {

        Theater theater = TheaterTransformers.convertRequestToEntity(theaterRequest);

        theater = theaterRepository.save(theater);

        return "New Theater has been added to DB with TheaterID "+theater.getTheaterId();
    }

    public String deleteTheater(Integer theaterId) throws Exception
    {
        Optional<Theater> optionalTheater = theaterRepository.findById(theaterId);

        if (optionalTheater.isEmpty())
        {
            throw new Exception("Invalid TheaterID!!!");
        }

        Theater theater = optionalTheater.get();

        theaterRepository.delete(theater);

        return "Theater with theaterID "+theaterId+" has been deleted successfully!!!";

    }

    public String addTheaterSeats(AddTheaterSeatRequest theaterSeatRequest)
    {
        int noOfClassicSeats = theaterSeatRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatRequest.getNoOfPremiumSeats();

        //Generate the TheaterSeat Entity
        //Save the Relevant attribute

        Theater theater = theaterRepository.findById(theaterSeatRequest.getTheaterId()).get();

        int quoClassic = noOfClassicSeats/5;

        int remClassic = noOfClassicSeats%5;

        //So that you dont need to store the seatType

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        for(int row = 1;row<=quoClassic;row++){

            for(int col = 1;col<=5;col++) {
                char ch = (char)('A'+(col-1));
                String seatNo = row+""+ch;

                TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                        .seatNo(seatNo)
                        .seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeatEntity);
            }
        }

        int rowNoForRemainder = quoClassic+1;

        for(int col=1;col<=remClassic;col++){

            char ch = (char)('A'+(col-1));
            String seatNo = rowNoForRemainder+""+ch;
            TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeatEntity);
        }

        int quoPremium = noOfPremiumSeats/5;
        int remPremium = noOfPremiumSeats%5;
        for(int row = 1;row<=quoPremium;row++){
            for(int col = 1;col<=5;col++) {

                char ch = (char)('A'+(col-1));
                String seatNo = row+""+ch;
                TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                        .seatNo(seatNo)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeatEntity);
            }
        }

        rowNoForRemainder = quoPremium+1;

        for(int col=1;col<=remPremium;col++){

            char ch = (char)('A'+(col-1));
            String seatNo = rowNoForRemainder+""+ch;
            TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeatEntity);
        }
        //Created a TheaterSeat Entity : set the FK in the Theater Seat Entity
        //Setting the variable of the Bidirectional mapping in the parent class
        theater.setTheaterSeatList(theaterSeatList);

        //Save both parent and child : Not required , only save the parent :
        theaterRepository.save(theater);

        return "Theater seats have been added to DB Successfully!!!";

    }

    public double grossRevenue(GrossRevenueRequest grossRevenueRequest) throws Exception
    {
        Optional<Theater> optionalTheater = theaterRepository.findByTheaterNameAndTheaterAddress(grossRevenueRequest.getTheaterName(),grossRevenueRequest.getTheaterAddress());
        if (optionalTheater.isEmpty())
        {
            throw new Exception("Theater Not Found!!!");
        }
        Theater theater = optionalTheater.get();

        List<Show> showList = theater.getShowList();

        double grossRevenue = 0;

        for (Show show: showList)
        {
            //i need to check that for the selected movie i need to check from showList
            if (show.getMovie().getMovieName().equals(grossRevenueRequest.getMovieName()))
            {
                //this theater having shows for my selected movie
                for (ShowSeat seat: show.getShowSeatList())
                {
                    if(!seat.isAvailable())
                    {
                        grossRevenue += seat.getSeatPrice();
                    }
                }
            }
        }
        if (grossRevenue == 0) {
            throw new Exception("Movie is not Present in this Theater!!!");
        }

        return grossRevenue;
    }
}
