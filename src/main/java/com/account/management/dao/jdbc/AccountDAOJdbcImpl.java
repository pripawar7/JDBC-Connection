package com.account.management.dao.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.account.management.dao.AccountDAO;
import com.account.management.domain.Account;
import com.account.management.domain.Student;


@Repository("accountDaoJdbc")
public class AccountDAOJdbcImpl implements AccountDAO{
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private ArrayList<Account> acctList;

	@PostConstruct
	public void init() {
		Account acct;

		acctList = new ArrayList<Account>();

		String s1 = "09/22/2016", s2 = "06/11/2015";
		;
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = null, date2 = null;
		try {
			date1 = sd.parse(s1);
			date2 = sd.parse(s2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		acct = new Account("101", 0.0, date1);
		acctList.add(acct);

		acct = new Account("102", 0.0, date2);
		acctList.add(acct);
	}

	public int createAccount(Student student) {
		// TODO Auto-generated method stub

		String s1 = "09/22/2016";
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = null;
		
		try {
			date1 = sd.parse(s1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	String sql = "INSERT INTO account(id,balanceDue,date)  values (?,?,?)";
	int rowsInserted = jdbcTemplate.update(sql,student.getStudentId(),0.0,date1);
		
	return rowsInserted;
	}

	public int  retrieveBalance(String accountId) {
		// TODO Auto-generated method stub

		String sql="SELECT balanceDue from account where id=?";
		
		
		 List<Integer> balanceList = jdbcTemplate.queryForList(sql, Integer.class,accountId); 
		    if (balanceList.isEmpty()) {
		    	System.out.println("am here");
		        return 0;
		    } else {
		        return balanceList.get(0);
		    }

	}

	public void addAmount(String accountId, double fees) {
		// TODO Auto-generated method stub
		double curBalance = retrieveBalance(accountId);

		double newBalance = curBalance + fees;
		
		String sql= "UPDATE account SET balanceDue=? where id=?";
		
		jdbcTemplate.update(sql,newBalance,accountId);
		
		
	}

	public void setNewBalance(String accountId, double value) {
		// TODO Auto-generated method stub
		
		String sql= "UPDATE account SET balanceDue=? where id=?";
		
		jdbcTemplate.update(sql,value,accountId);
		
	}

	public List<Account> retrieveOverDueAccounts() {
	
		String s3 = "06/12/2015";
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Date dueDate=null;
		try {
			 dueDate=sd.parse(s3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Account> dueAccntList;
		String sql = "SELECT id FROM account where balanceDue > 0 and date>?";
		
		dueAccntList=jdbcTemplate.queryForList(sql,Account.class,dueDate);
		
		return dueAccntList;
	}
	
}
