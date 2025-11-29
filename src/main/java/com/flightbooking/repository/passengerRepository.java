package com.flightbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Passenger;

public interface passengerRepository extends JpaRepository<Passenger, Integer> {

	    Optional<Passenger> findByContactNumber(String contactNumber);
	}


