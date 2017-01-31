package com.account.management.services;

import com.account.management.domain.Student;
import com.account.management.exceptions.InvalidCardException;
import com.account.management.exceptions.UnauthorizedPaymentException;

public interface PaymentService {

	public void makePayment(Student student, String cardNumber, double fees) 
			throws InvalidCardException, UnauthorizedPaymentException;
	
}
