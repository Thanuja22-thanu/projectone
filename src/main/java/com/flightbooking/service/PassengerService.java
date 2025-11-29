package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.PassengerDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;

@Service
public class PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger) {
		ResponseStructure<Passenger> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Passenger added successfully ✈️😊");
		structure.setData(passengerDao.savePassenger(passenger));

		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {
		ResponseStructure<List<Passenger>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("All passengers retrieved 📋👥");
		structure.setData(passengerDao.getAllPassengers());

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(Integer id) {
		Optional<Passenger> opt = passengerDao.getPassengerById(id);

		if (opt.isEmpty()) {
			throw new RuntimeException("Passenger not found with ID: " + id + " ❌");
		}

		ResponseStructure<Passenger> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Passenger found 🔍✔️");
		structure.setData(opt.get());

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger updated) {
        Optional<Passenger> opt = passengerDao.getPassengerById(updated.getId());

        if (opt.isEmpty()) {
            throw new RuntimeException("Passenger not found with ID ❌");
        }

        Passenger existing = opt.get();

        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setGender(updated.getGender());
        existing.setSeatNumber(updated.getSeatNumber());
        existing.setContactNumber(updated.getContactNumber());

        Passenger saved = passengerDao.updatePassenger(existing);

        ResponseStructure<Passenger> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Passenger updated successfully 🔄✔️");
        structure.setData(saved);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContact(String conactNumber) {
		Optional<Passenger> opt = passengerDao.getPassengerByContactNumber(conactNumber);

		if (opt.isEmpty()) {
			throw new RuntimeException("Passenger not found with contact number: " + conactNumber + " ❌");
		}

		ResponseStructure<Passenger> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Passenger found by contact 🔍📞");
		structure.setData(opt.get());

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Page<Passenger>>> getpassengerbypageandsort(int pagenumber, int pagesize,
			String field) {
		ResponseStructure<Page<Passenger>> response = new ResponseStructure<Page<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger records with paging and sorting 📄📊");
		response.setData(passengerDao.getpassengertByPageAndSort(pagenumber, pagesize, field));
		return new ResponseEntity<ResponseStructure<Page<Passenger>>>(response, HttpStatus.OK);
	}
}
