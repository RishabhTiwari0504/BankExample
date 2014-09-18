package com.avalia.bank.persist;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.avalia.bank.account.Account;
import com.avalia.bank.cutomer.Address;
import com.avalia.bank.cutomer.Customer;

public class DataBase {
	public static DataBase Instance = new DataBase(); // initially assign
														// because object
														// required.(INSTANCE).
	private static String filePath = "C:/temp/bank.csv";

	private DataBase() {

	}

	public List<Account> getAccounts() {

		List<Account> accounts = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath)))) {

			String line = br.readLine();
			while (line != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					String[] split = line.split(",");
					int accNum = (int) Double.parseDouble(split[0]);
					String name = split[2];
					int bal = (int) Double.parseDouble(split[1]);
					String houseNo = "";
					String pin = "";
					if (split.length > 3) {
						houseNo = split[3];
						pin = split[4];
					}

					Customer c = new Customer(name, houseNo, pin);
					Account acc = new Account(bal, accNum, c);
					accounts.add(acc);
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accounts;
	}

	public void saveAccount(Account acc) {
		try (FileWriter fw = new FileWriter(filePath, true);) {
			fw.append("\n" + acc.getStringToSave());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateAccount(Account acc) {

	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		DataBase.filePath = filePath;
	}

	public void saveAddress(Address address) {

	}

}
