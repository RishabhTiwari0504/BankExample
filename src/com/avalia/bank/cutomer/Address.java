package com.avalia.bank.cutomer;

public class Address {

	private final String houseNo;
	private final String pin;

	public Address(String houseNo, String pin) {
		this.houseNo = houseNo;
		this.pin = pin;
	}

	public void address() {

	}

	public String getHouseNo() {
		return houseNo;
	}

	public String getPin() {
		return pin;
	}

	@Override
	public String toString() {
		return "Address: " + houseNo + "-" + pin;
	}

	public String getStringToSave() {

		return houseNo + "," + pin;
	}

}
