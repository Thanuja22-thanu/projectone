package com.flightbooking.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Payment {
	@Id
	@GeneratedValue
	private Integer id;
	@CreationTimestamp
	private LocalDate paymentdate;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private Paymentmode paymentmode;
	@Enumerated(EnumType.STRING)
	private Paymentstatus status;
	@OneToOne
	@JoinColumn(name="booking_id")
	@JsonBackReference
	private Booking booking;
	
	
	

}
