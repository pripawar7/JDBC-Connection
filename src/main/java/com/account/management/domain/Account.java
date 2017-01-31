package com.account.management.domain;

import java.util.Date;

public class Account {

	private String id;
	private double balanceDue;
	private Date date;
	
	public Account(String id, double balanceDue, Date date) {
		this.id = id;
		this.balanceDue = balanceDue;
		this.date = date;
	}
	
	public boolean matchesId(String acctId) {
		return (acctId == id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
