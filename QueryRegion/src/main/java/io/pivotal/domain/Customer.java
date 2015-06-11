package io.pivotal.domain;

public class Customer {

	private Integer customerNumber;
	private String firstName;
	private String lastName;
	
	public Customer() {
	}

	public Customer(int customerNumber, String firstName, String lastName) {
		super();
		this.customerNumber = new Integer(customerNumber);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
	
}
