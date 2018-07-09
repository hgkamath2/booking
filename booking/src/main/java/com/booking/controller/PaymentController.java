package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.service.ICreditCardService;
import com.booking.service.IPaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	@Qualifier("CreditCardService")
	private IPaymentService ccService;
	
	@Autowired
	@Qualifier("PayPalService")
	private IPaymentService ppService;
	
	//validate payment info, auth cc, security code, expiry etc
	//and process payment for amount
}
