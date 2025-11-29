package com.flightbooking.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity


public class Booking {
	@Id
	@GeneratedValue
	private Integer id;
	@CreationTimestamp
	private LocalDate bookingdate;
	@Enumerated(EnumType.STRING)
	private Bookingstatus bookingstatus;
	
	
	@ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight 	flight;

    

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Passenger> passengers;

    
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JsonManagedReference
    private Payment payment;

}
