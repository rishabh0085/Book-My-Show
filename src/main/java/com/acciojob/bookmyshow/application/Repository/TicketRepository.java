package com.acciojob.bookmyshow.application.Repository;

import com.acciojob.bookmyshow.application.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
