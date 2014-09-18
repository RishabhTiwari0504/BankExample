package com.avalia.bank.cutomer;


public class Customer {

	private String name;
	private Address address; // data type Address.

	public Customer(String name) {
		this.name = name;

	}

	public Customer(String name, String houseNo, String pin) {
		this.name = name;
		setAddress(houseNo,pin);

	}

	public void setAddress(String houseNo, String pin){
		this.address = new Address(houseNo, pin);
	}

	public String getName() {
		return name;

	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {

		String s = "Name:" + getName();
		s = s + "\n";
		if (address != null) {
			s = s + getAddress().toString();
		}
		return s;
	}

	public String getStringToSave() {
		String s = name;
		if (address != null) {
			s = s + "," + address.getStringToSave();
		}
		return s;
	}

}