package com.flightbooking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Bookingstatus;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.Norecordavailableexception;
import com.flightbooking.repository.BookingRepository;
import com.flightbooking.repository.FlightRepository;
@Repository
public class BookingDao {
	@Autowired 
	private FlightRepository flightrepository;
	
@Autowired
private BookingRepository bookingrepository;

	public  Booking createbooking(Booking booking) {
		return bookingrepository.save(booking);
	}
	
	public List<Booking> getallbooking() {
		return bookingrepository.findAll();
		
	}
	public Optional<Booking> getbookingbyid(Integer id) {
		return bookingrepository.findById(id);
	}
	
	public List<Booking> getbookingbydate(LocalDate bookingdate) {
		return bookingrepository.findBookingByBookingdate(bookingdate);
	
	}
	 public List<Booking> getBookingsByFlightId(Integer flightId) {
	        return bookingrepository.findBookingByFlightId(flightId);
	    }
	 public List<Booking> getbookingbystatus(Bookingstatus bookingstatus) {
		 return bookingrepository.findBookingByBookingstatus(bookingstatus);
			
		
	 }
	 public Booking updateBooking(Integer bookingId, Bookingstatus newStatus) {
	        Optional<Booking> optionalBooking = bookingrepository.findById(bookingId);

	        if (!optionalBooking.isPresent()) {
	            throw new Norecordavailableexception("Booking not found with ID: " + bookingId);
	        }

	        Booking booking = optionalBooking.get(); // fetch existing record
	        booking.setBookingstatus(newStatus);     // update only status
	        return bookingrepository.save(booking);  // save and return full object
	    }

	
	 public Optional<Booking> deletebooking(Integer id) {
		 Optional<Booking> opt = bookingrepository.findById(id);
		    if (opt.isPresent()) {
		        bookingrepository.deleteById(id);
		    }
		    return opt; 
	 }
	 public Page<Booking> getBookByPagingAndSorting (int pagenumber,int pagesize,String field){
		 Page<Booking> booking=bookingrepository.findAll(PageRequest.of(pagenumber, pagesize,Sort.by(field).descending()));
		 if(!booking.isEmpty()) {
			 return booking;
		 }
		 else {
			 throw new Norecordavailableexception ("no record in db");
		 }

	 
	
	
	
}
}
