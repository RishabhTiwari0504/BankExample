package com.avalia.bank.account;

import com.avalia.bank.Bank;
import com.avalia.bank.cutomer.Customer;

public class Account {

	private final int accountNumber; // account no is in account class.
	private double balance; // balance no is in account class.
	private boolean firstTime = true; // for transaction.
	public final Customer customer;

	public Account(double balance, int accountNumber, Customer customer) {
		this.balance = balance;
		this.customer = customer;
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void withdraw(double howMuch) {
		if (howMuch > 0) {
			if (firstTime == true) {
				double tempBalance = balance - howMuch;
				if (tempBalance > Bank.MIN_BALANCE) {
					balance = balance - howMuch;
					firstTime = false;
				} else {
					System.err.println("insufficient Balance.");
				}

			} else {
				Bank bank = Bank.INSTANCE;
				double tempBalance = balance - howMuch
						- bank.getTransactionfees();
				if (tempBalance > Bank.MIN_BALANCE) {
					balance = balance - howMuch - bank.getTransactionfees();
				} else {
					System.err.println("insufficient Balance.");
				}

			}

		}

	}

	public void deposite(double howMuch) {
		if (howMuch > 0) {
			balance = balance + howMuch;
		}
	}

	public int getAccountNo() {
		return accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getStringToSave() {
		String s = accountNumber + "," + balance;
		if (customer != null)
			s = s + "," + customer.getStringToSave();

		return s;
	}

	@Override
	public String toString() {

		String s = "*******************ACCOUNT INFO*********************\n";
		s = s + "Balance : " + getBalance() + "\nAccount Number : "
				+ getAccountNo();
		s = s + "\n";
		s = s + customer.toString();
		s = s+"\n****************************************************\n";
		return s;
	}

}