package entity;

import entity.Constants.CURRENCY;

public class Account {
	private int number;
	private String name;
	private String password;
	private double balance;
	private CURRENCY currency;
	
	
	/**
	 * Constructor
	 * @param number
	 * @param name
	 * @param password
	 * @param balance
	 * @param currency
	 */
	public Account(int number, String name, String password, double balance, CURRENCY currency) {
		super();
		this.number = number;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.currency = currency;
	}
	
	/**
	 * Default constructor
	 */
	public Account() {
		super();
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void getNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return the currency
	 */
	public CURRENCY getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CURRENCY currency) {
		this.currency = currency;
	}

}
