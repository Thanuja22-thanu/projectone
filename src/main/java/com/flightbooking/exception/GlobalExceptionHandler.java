package com.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flightbooking.dto.ResponseStructure;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Norecordavailableexception.class)
	public ResponseEntity<ResponseStructure<String>>handleNRAE(Norecordavailableexception exception){
		ResponseStructure<String>response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		response.setMessage("failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);

	

}
	@ExceptionHandler(Idnotfoundexception .class)
	public ResponseEntity<ResponseStructure<String>>handleIDNFE(Idnotfoundexception exception){
		ResponseStructure<String>response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		response.setMessage("failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);

	

}
	@ExceptionHandler(Bookingnotfoundexception .class)
	public ResponseEntity<ResponseStructure<String>>handleBNFE(Bookingnotfoundexception exception){
		ResponseStructure<String>response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		response.setMessage("failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);

	

}
	@ExceptionHandler(Paymentnotfoundexception .class)
	public ResponseEntity<ResponseStructure<String>>handlePNFE(Paymentnotfoundexception exception){
		ResponseStructure<String>response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		response.setMessage("failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);

	

}


}
