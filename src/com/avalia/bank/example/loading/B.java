package com.avalia.bank.example.loading;

public class B extends A {
	static {
		System.out.println("Static block Class B");
	}
	{
		System.out.println("Init block Class B");
	}

	B() {
		System.out.println("Cons Class B");
	}

	public static String name() {
		return "Rahul";
	}
}
