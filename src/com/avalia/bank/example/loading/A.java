package com.avalia.bank.example.loading;

public class A {
	static {
		System.out.println("Static block Class A");
	}
	{
		System.out.println("Init block Class A");
	}

	A() {
		System.out.println("Cons Class A");
	}
}
