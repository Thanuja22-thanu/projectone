package com.flightbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Bookingstatus;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	
	List<Booking> findBookingByBookingdate(LocalDate bookingdate);
	List<Booking> findBookingByFlightId(Integer flightId);
	List<Booking> findBookingByBookingstatus(Bookingstatus bookingstatus);
	


}
