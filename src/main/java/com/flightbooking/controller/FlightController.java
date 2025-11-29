package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.service.FlightService;

import jakarta.persistence.OneToMany;





@RequestMapping("/flight")
@RestController
public  class FlightController{
	
	@Autowired 
	 private FlightService flightservice;
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> Addflight(@RequestBody Flight flight){
		return  flightservice.Addflight(flight);}
		

	@GetMapping()
	public ResponseEntity<ResponseStructure<List<Flight>>> getallflights(){
	return flightservice.getallflights();
	
	}	


 @GetMapping("/id/{id}")
 public ResponseEntity<ResponseStructure<Flight>> getflightbyid(@PathVariable Integer id)
 {
	 return flightservice.getflightbyid(id);
 }
	
	@GetMapping("/{source}/{destination}")
    public ResponseEntity<ResponseStructure<List<Flight>>> getflightbysourceanddestination(@PathVariable String source,@PathVariable String destination){
		return flightservice.getflightbysourceanddestination(source,destination);
	}
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightbyairline(@PathVariable String airline){
		return flightservice.getflightbyairline(airline);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> updateflight(@RequestBody Flight flight){
		return flightservice.updateflight(flight);
	}
	@GetMapping("/{pagenumber}/{pagesize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Flight>>> getflightbypageandsort(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field){
		return flightservice.getflightbypageandsort(pagenumber,pagesize,field);
	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteflight(@PathVariable Integer id){
		return flightservice.deleteflight(id);
	
	
	
	
}
}
