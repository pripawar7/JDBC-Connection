package com.account.management.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.account.management.dao.AccountDAO;
import com.account.management.domain.Account;
import com.account.management.domain.Student;
import com.account.management.exceptions.InvalidCardException;
import com.account.management.exceptions.UnauthorizedPaymentException;
import com.account.management.services.PaymentForPayPal;

@ContextConfiguration("classpath:accountdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class AccountDAOTest {
	@Autowired
	@Qualifier("accountDaoJdbc")
	private AccountDAO accountDAO;
	
	@Autowired
	@Qualifier("paymentForPayPal")
	private PaymentForPayPal payPal;

	private Account setupAccount; /* Initialized in the setup() method */
	Student	student1 = new Student("Tim", "101", true, "PG", null);	


	@Before
	public void setup() {
		/*
		 * We'll use this student in various tests. The student should already
		 * be in the database
		 */
		String s1 = "09/22/2016";

		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = null;
		try {
			date1 = sd.parse(s1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupAccount = new Account("101", 0.0, date1);

		//return setupAccount;
	}
	
	@Test
	public void testCreateAccount(){
	
		int acctCount;
	//	System.out.println(student1.getStudentId());
		acctCount = accountDAO.createAccount(student1);
	//	System.out.println("acct"+ acctFromDb.getId());	
		assertNotNull(acctCount);
		assertEquals(1, acctCount);
	}
	
	@Test
	public void testRetrieveBalance() {
		System.out.println("ID" + setupAccount.getId());
		int balance = accountDAO.retrieveBalance(setupAccount.getId());
		System.out.println("bal" + balance);
		assertEquals(0, balance);
	}
	
	@Test
	public void testAddAmount() {
		
	accountDAO.addAmount(setupAccount.getId(), 100.0);
	//System.out.println("accnt bal" + setupAccount.getBalanceDue());
	double bal=accountDAO.retrieveBalance(setupAccount.getId());
	assertEquals(100.0,bal , 0.0);

	}
	
	@Test
	public void testRetrieveOverDueAccounts() {
		
		List<Account> dueAccntList;
		dueAccntList=accountDAO.retrieveOverDueAccounts();
		System.out.println("size:"+dueAccntList.size());
		assertEquals(0,dueAccntList.size());
	
	}
	
	@Test(expected=InvalidCardException.class)
	public void makePaymentTest1() throws Exception {
		payPal.makePayment(student1, "123", 1000);
	}
	
	@Test(expected=UnauthorizedPaymentException.class)
	public void makePaymentTest2() throws Exception {
		payPal.makePayment(student1, "555550000011111", 1000);

	}
	
	@Test
	public void testSetBalance() throws Exception {
		accountDAO.setNewBalance(setupAccount.getId(), 0);
		assertEquals(0,setupAccount.getBalanceDue(),0.0);	
	}
	
}

	