package com.booking.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.service.ICreditCardService;
import com.booking.service.IPayPalService;

@Transactional
@Service
@Qualifier("PayPalService")
public class PPPaymentService implements IPayPalService{

	@Override
	public boolean processPayment() {
		// TODO Auto-generated method stub
		return true;
	}

}