package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;
import com.flightbooking.service.PassengerService;


	@RestController
	@RequestMapping("/passenger")
	public class PassengerController {

	    @Autowired
	    private PassengerService passengerService;

	    @PostMapping
	    public ResponseEntity<ResponseStructure<Passenger>> addPassenger(@RequestBody Passenger passenger) {
	        return passengerService.addPassenger(passenger);
	    }

	    @GetMapping
	    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {
	        return passengerService.getAllPassengers();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id) {
	        return passengerService.getPassengerById(id);
	    }

	    @PutMapping
	    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(
	             @RequestBody Passenger passenger) {
	        return passengerService.updatePassenger( passenger);
	    }

	    @GetMapping("/contact/{number}")
	    public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContact(
	            @PathVariable String number) {
	        return passengerService.getPassengerByContact(number);
	    }

	    @GetMapping("/{pagenumber}/{pagesize}/{field}")
		public ResponseEntity<ResponseStructure<Page<Passenger>>> getpassengerbypageandsort(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field){
			return passengerService.getpassengerbypageandsort(pagenumber,pagesize,field);
		
		}
	

}
