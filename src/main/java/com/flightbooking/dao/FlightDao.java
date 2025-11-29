package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.service.FlightService;

@Repository
public class FlightDao {
	@Autowired
	 private FlightRepository flightrepository;
	public Flight Addflight(Flight flight) {
		return flightrepository.save(flight);
	}
	public List<Flight> getallflights() {
		return flightrepository.findAll();
	}
	 
	 public Optional<Flight> getflightbyid(Integer id)
	 {
		 return flightrepository.findById(id);
	 }
		
		public List<Flight> getflightbysourceanddestination(String source, String destination) {
			
			return flightrepository.findBySourceAndDestination(source,destination);
		}

public List<Flight> getflightbyairline(String airline) {
	
	return flightrepository.findFlightByAirline(airline);
}
public Page<Flight> getFlightByPageAndSort(int pagenumber, int pagesize, String field) {
	
	return flightrepository.findAll(PageRequest.of(pagenumber, pagesize,Sort.by(field).descending()));
}

public Optional<Flight> deleteflight(Integer id) {
    Optional<Flight> opt = flightrepository.findById(id);
    if (opt.isPresent()) {
        flightrepository.deleteById(id);
    }
    return opt; 
}

}


	