package com.account.management.services;

import com.account.management.dao.AccountDAO;
import com.account.management.domain.Student;
import com.account.management.exceptions.InvalidCardException;
import com.account.management.exceptions.UnauthorizedPaymentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("paymentForPayPal")
public class PaymentForPayPal implements PaymentService {
	@Autowired
	@Qualifier("accountDaoJdbc")
	AccountDAO accountDAO;
	
	public void makePayment(Student student, String cardNumber, double fees) throws InvalidCardException, UnauthorizedPaymentException {

		boolean valid = true;

		if (cardNumber.length() != 15) {
			valid = false;
			try {
				throw new InvalidCardException("Invalid Credit Card!! Credit Card length should be 15 characters.");
			} catch (InvalidCardException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw e;
			}
		}

		if (cardNumber.charAt(0) == '5') {
			valid = false;
			try {
				throw new UnauthorizedPaymentException("Unauthorized payment type!!");
			} catch (UnauthorizedPaymentException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				throw e;
			}
		}

		if (valid) {
			String acctId = student.getStudentId();
			accountDAO.addAmount(acctId, fees);
			System.out.println("Using the PayPal credit card processor to process credit card number " + cardNumber
					+ " for: " + student.getStudentName());
			System.out.println("Payment done!!");
			// successful payment update
			//Step 5 : Look up the new new student balance after checkout

			accountDAO.setNewBalance(acctId, 0);
			
		}

		System.out.println();
	}
}
