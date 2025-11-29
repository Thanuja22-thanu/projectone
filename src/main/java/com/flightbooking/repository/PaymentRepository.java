package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Payment;
import com.flightbooking.entity.Paymentmode;
import com.flightbooking.entity.Paymentstatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	    List<Payment> findByStatus(Paymentstatus status);

	    List<Payment> findByAmountGreaterThan(Double amount);

	    List<Payment> findByPaymentmode(Paymentmode mode);
	}

