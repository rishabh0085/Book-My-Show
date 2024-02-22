package com.acciojob.bookmyshow.application.Services;

import com.acciojob.bookmyshow.application.CustomException.InvalidMovieException;
import com.acciojob.bookmyshow.application.Entities.*;
import com.acciojob.bookmyshow.application.Enums.SeatType;
import com.acciojob.bookmyshow.application.Repository.MovieRepository;
import com.acciojob.bookmyshow.application.Repository.ShowRepository;
import com.acciojob.bookmyshow.application.Repository.TheaterRepository;
import com.acciojob.bookmyshow.application.Requests.AddShowRequest;
import com.acciojob.bookmyshow.application.Requests.AddShowSeatRequest;
import com.acciojob.bookmyshow.application.Transformers.ShowTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(AddShowRequest showRequest) throws Exception
    {
        //I need to Save Show Entity for that i have to need to set F.K variables 1 and set bidirectional mapping is 2
        //1.Get the movie Entity
        Optional<Movie> optionalMovie = movieRepository.findByMovieName(showRequest.getMovieName());
        //now you need to check if you ables to get movie entity or not
        if(optionalMovie.isEmpty())
        {
            throw new InvalidMovieException("Movie name Entered does not exist in the DB!!!");
        }

        Movie movie = optionalMovie.get();

        //2.Get the Theater Entity
        Optional<Theater> optionalTheater = theaterRepository.findById(showRequest.getTheaterId());
        //here theater is taken Optional type because findById() is optional type
        //you can take movie also as OptionalType but u need make findMovieByMovieName() method as Optional but we do not make it Optional type thats why i keep it in this way!!!

        if(optionalTheater.isEmpty())
        {
            //you need to throw custom exception
            throw new InvalidMovieException("Theater Id Entered is Incorrect!!!");
        }

        Theater theater = optionalTheater.get();

        //3.Create the Show Entity now!!! using transformers concept
        Show showEntity = ShowTransformers.convertRequestToEntity(showRequest);
        //once i got the showEntity i need to set F.K Attributes(movie and theater)....in showEntity
        showEntity.setMovie(movie);
        showEntity.setTheater(theater);

        //i need to do Bi-Directional mapping in Parent Entity too

        movie.getShowList().add(showEntity);
        theater.getShowList().add(showEntity);

        //Save the ShowEntity
        showEntity = showRepository.save(showEntity);

        return "The Show has been Created with showId "+showEntity.getShowId();

    }


    public String deleteShow(Integer showId) throws Exception
    {
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty())
        {
            throw new Exception("Invalid ShowID!!!");
        }

        Show show = optionalShow.get();

        showRepository.delete(show);

        return "Show with showID "+showId+" has been deleted Successfully!!!";
    }

    public String addShowSeat(AddShowSeatRequest showSeatRequest)
    {
        //1.Get Show Entity first
        Show show = showRepository.findById(showSeatRequest.getShowId()).get();
        //2.Get Theater Entity
        Theater theater = show.getTheater();
        //3.Get TheaterSeat List<>
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        //4.make ShowSeatList
        List<ShowSeat> showSeatList = new ArrayList<>();

        //Every ShowSeat is based on top of TheaterSeat so i will iterate over all the TheaterSeat because every showSeat is dependent
        //over every theater Seat
        for (TheaterSeat theaterSeat: theaterSeatList)
        {
             String seatNo = theaterSeat.getSeatNo();
             SeatType seatType = theaterSeat.getSeatType();

             //Now ShowSeat Entity has to be created!!!
            ShowSeat showSeat = ShowSeat.builder()
                    .isFoodAttached(false)
                    .isAvailable(true)
                    .show(show)
                    .seatNo(seatNo)
                    .seatType(seatType).build();

            //i need to set price
            if(seatType.equals(SeatType.CLASSIC))
            {
                showSeat.setSeatPrice(showSeatRequest.getPriceForClassicSeats());
            }
            else
            {
                showSeat.setSeatPrice(showSeatRequest.getPriceForPremiumSeats());
            }

            showSeatList.add(showSeat);
        }

        show.setShowSeatList(showSeatList);
        showRepository.save(show);

        return "Show Seats have been added to the System Successfully!!! ";
    }


    public List<String> getAllShow(String movieName, LocalDate date) throws Exception
    {
        Optional<Movie> optionalMovie = movieRepository.findByMovieName(movieName);
        if(optionalMovie.isEmpty())
        {
            throw new Exception("Movie Not Yet Release");
        }

        Movie movie=optionalMovie.get();

        List<Show>showList=showRepository.findByMovieAndShowDate(movie,date);

        List<String>showAndTheaterList=new ArrayList<>();

        for(Show s:showList)
        {
            showAndTheaterList.add("showId: "+s.getShowId()+" theater name: "+s.getTheater().getTheaterName()+
                    " Address: "+s.getTheater().getTheaterAddress());
        }
        if(showAndTheaterList.isEmpty())
        {
            throw new Exception("No shows for this movie on given Date");
        }

        return showAndTheaterList;
    }

    public String countOfBookedSeats(Integer showId) throws Exception
    {
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty())
        {
            throw new Exception("Invalid ShowID!!! No Movie assigned to this showID");
        }

        Show show = optionalShow.get();

        List<ShowSeat> showList = show.getShowSeatList();

        int count = 0;

        for (ShowSeat s: showList)
        {
            if (!s.isAvailable()){
                count++;
            }
        }

        if (count == 0)
        {
          throw new Exception("No Tickets Booked for this showID "+showId);
        }

        return "Out of "+showList.size()+" Total No. of Booked Seats are "+count+" for showId "+showId;

    }

}
