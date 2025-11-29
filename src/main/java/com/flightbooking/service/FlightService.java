package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.FlightDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.Idnotfoundexception;
import com.flightbooking.exception.Norecordavailableexception;
import com.flightbooking.repository.FlightRepository;

import jakarta.transaction.Transactional;
@Service 
public class FlightService {  
    
    @Autowired 
    private FlightDao flightDao;  

    public ResponseEntity<ResponseStructure<Flight>> Addflight(Flight flight) {  
        ResponseStructure<Flight> response = new ResponseStructure<Flight>();  
        response.setStatusCode(HttpStatus.CREATED.value());  
        response.setMessage("flight record saved ✈️🚀");  
        response.setData(flightDao.Addflight(flight));  
        return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.CREATED);  
    }  

    public ResponseEntity<ResponseStructure<List<Flight>>> getallflights() {  

        ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();  
        if (!flightDao.getallflights().isEmpty()) {  
            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("flight record retrieved 📋✈️");  
            response.setData(flightDao.getallflights());  
            return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);  
        } else {  
            throw new Norecordavailableexception("no flight records are available ⚠️");  
        }  
    }  

    public ResponseEntity<ResponseStructure<Flight>> getflightbyid(Integer id) {  

        ResponseStructure<Flight> response = new ResponseStructure<Flight>();  
        Optional<Flight> opt = flightDao.getflightbyid(id);  
        if (opt.isPresent()) {  

            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("Flight record with id " + id + " is retrieved 🔍✈️");  
            response.setData(opt.get());  

            return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);  
        } else {  
            throw new Norecordavailableexception("flight record with " + id + " is not available ❌");  
        }  
    }  

    public ResponseEntity<ResponseStructure<List<Flight>>> getflightbysourceanddestination(String source, String destination){  
        ResponseStructure<List<Flight>> response = new  ResponseStructure<List<Flight>>();  

        if (!flightDao.getflightbysourceanddestination(source,destination).isEmpty()) {  
            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("Flight record with " + source +"and"+ destination + " is retrieved 🛫🛬");  
            response.setData(flightDao.getflightbysourceanddestination(source,destination));  

            return new ResponseEntity<ResponseStructure<List<Flight>>> (response,HttpStatus.OK);  
        } else {  
            throw new Norecordavailableexception("Flight record with " + source +"and"+ destination + "is not available ❌");  
        }  
    }  

    public ResponseEntity<ResponseStructure<List<Flight>>> getflightbyairline(String airline){  
        ResponseStructure<List<Flight>> response=new  ResponseStructure<List<Flight>>();  

        if (!flightDao.getflightbyairline(airline).isEmpty()) {  
            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("Flight record with " + airline +" is retrieved 🛩️");  
            response.setData(flightDao.getflightbyairline(airline));  

            return new ResponseEntity<ResponseStructure<List<Flight>>> (response,HttpStatus.OK);  
        } else {  
            throw new Norecordavailableexception("Flight record with " + airline + "is not available ❌");  
        }  
    }  

    public ResponseEntity<ResponseStructure<Flight>> updateflight(Flight flight) {  

        ResponseStructure<Flight> response = new ResponseStructure<Flight>();  

        if (flight.getId()==null) {  
            throw new Idnotfoundexception("id must be passed to a update record ⚠️");  
        }  

        Optional <Flight> opt=flightDao.getflightbyid(flight.getId());  

        if(opt.isPresent()) {  
            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("flight record updated 🔄✈️");  
            response.setData(flightDao.Addflight(flight));  

            return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);  
        } else {  
            throw new Idnotfoundexception("no flight records are available ❌");  
        }  
    }  

    public ResponseEntity<ResponseStructure<Page<Flight>>> getflightbypageandsort(int pagenumber,int pagesize,String field) {  

        ResponseStructure<Page<Flight>> response = new ResponseStructure<Page<Flight>>();  
        response.setStatusCode(HttpStatus.OK.value());  
        response.setMessage("flight record have be done by paging and sorting 📄📊");  
        response.setData(flightDao.getFlightByPageAndSort(pagenumber,pagesize,field));  
        return new ResponseEntity<ResponseStructure< Page<Flight>>>(response, HttpStatus.OK);  
    }  


    @Transactional  
    public ResponseEntity<ResponseStructure<String>> deleteflight(Integer id) {  

        ResponseStructure<String> response = new ResponseStructure<>();  
        Optional<Flight> opt = flightDao.deleteflight(id);  

        if (opt.isPresent()) {  
            response.setStatusCode(HttpStatus.OK.value());  
            response.setMessage("flight record with id " + id + " is deleted 🗑️✔️");  
            response.setData("success");  
        } else {  
            response.setStatusCode(HttpStatus.NOT_FOUND.value());  
            response.setMessage("No flight record found with id " + id + " ❌");  
            response.setData("failed");  
        }  

        return new ResponseEntity<>(response, HttpStatus.OK);  
    }  
}
