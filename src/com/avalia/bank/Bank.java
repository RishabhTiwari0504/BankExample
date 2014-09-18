package com.avalia.bank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.avalia.bank.account.Account;
import com.avalia.bank.cutomer.Customer;
import com.avalia.bank.persist.DataBase;

public class Bank {
	private String name; // bank name.
	private double interestRate;
	private double transactionFees = 10;
	private Account[] accounts = new Account[1000];
	private double sum = 0;
	public int noOfAccount = 0; // Accessible outside the class easily
								// without any object.
	public static int MIN_BALANCE = 100; // Accessible outside the class easily
											// without any object.

	public static Bank INSTANCE = new Bank(); // every same object will alloted
												// to this class.because only
												// one date base is here in our
												// problem.

	private Bank() { // constructor of bank.
		Properties prop = new Properties(); // new object properties. private
											// data.
		String propFileName = "config.properties";

		try {
			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(propFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		interestRate = Integer.parseInt(prop.getProperty("interest"));
		transactionFees = Integer.parseInt(prop.getProperty("transcFee"));
		MIN_BALANCE = Integer.parseInt(prop.getProperty("transcFee"));
		setName(prop.getProperty("bankName"));

		List<Account> accs = DataBase.Instance.getAccounts();

		for (int i = 1; i <= accs.size(); i++) {
			noOfAccount++;
			accounts[i] = accs.get(i - 1);
			sum = sum + accs.get(i - 1).getBalance();
		}

	}

	public Account checkBalance(int accNum) {
		return accounts[accNum];

	}

	public Double calculateInterest(int accNum) {
	
		if (accounts[accNum] != null) {
			double bal = accounts[accNum].getBalance();
			double interest = interestRate * bal / 100;
			return interest;
		} else {
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getInterest() {
		return interestRate;
	}

	public double getTransactionfees() {
		return transactionFees;
	}

	public Account getFullDetail(int accNum) {

		return accounts[accNum];

	}

	public double getTotalBalance() {

		return sum;
	}

	public int getTotalNoOfAccounts() {

		return noOfAccount;
	}

	public Account addNewAccount(double bal, String name, String houseNo,
			String pin) {
		Customer customer = new Customer(name, houseNo, pin);
		noOfAccount++;
		Account account = new Account(bal, noOfAccount, customer);
		accounts[noOfAccount] = account;
		// account object is assign to accounts
		DataBase.Instance.saveAccount(account);
		sum = sum + account.getBalance();
		return account;
	}

	public Account addNewAccount(double bal, String name) {
		return this.addNewAccount(bal, name, "", "");
	}

	public Account depositMoney(int accNum, int amount) {

		if (accounts[accNum] != null) {
			accounts[accNum].deposite(amount);
			return accounts[accNum];
		} else {
			return null;
		}

	}

	public Account withdrawMoney(int accNum, int amount) {

		if (accounts[accNum] != null) {
			accounts[accNum].withdraw(amount);
			return accounts[accNum];
		}

		else {
			return null;
		}

	}

}
