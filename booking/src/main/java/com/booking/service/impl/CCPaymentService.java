package com.booking.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.service.ICreditCardService;
import com.booking.service.IPaymentService;

@Transactional
@Service
@Qualifier("CreditCardService")
public class CCPaymentService implements ICreditCardService{

	@Override
	public boolean processPayment() {
		return true;
	}

	@Override
	public boolean authorizeCrediCard() {
		// TODO Auto-generated method stub
		return true;
	}
}
