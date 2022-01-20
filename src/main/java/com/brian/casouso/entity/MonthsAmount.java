package com.brian.casouso.entity;

import java.time.Month;

public class MonthsAmount {

	private String month;
	private double amount;
	private Month monthDate;
	private int yearDate;
	

	

	public MonthsAmount(String month, double amount, Month monthDate, int yearDate) {
		super();
		this.month = month;
		this.amount = amount;
		this.monthDate = monthDate;
		this.yearDate = yearDate;
	}

	public Month getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(Month monthDate) {
		this.monthDate = monthDate;
	}

	public void addAmount(double amount) {
		this.amount += amount;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getYearDate() {
		return yearDate;
	}

	public void setYearDate(int yearDate) {
		this.yearDate = yearDate;
	}
	
	
	
}
