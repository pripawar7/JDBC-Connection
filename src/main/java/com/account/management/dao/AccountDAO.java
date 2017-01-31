package com.account.management.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import com.account.management.domain.Account;
import com.account.management.domain.Student;


public interface AccountDAO {

		public int createAccount(Student student);
		public int retrieveBalance(String accountId);
		public void addAmount(String account,double newbalance);
		public List<Account> retrieveOverDueAccounts();
		public void setNewBalance(String accountId, double value) ;


}
