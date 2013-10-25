package learn2crack.jsonparsing.bean;

import java.util.List;

public class Person {

	private String name;
	private String surname;
	private Address address;
	private List<PhoneNumber> phoneList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PhoneNumber> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneNumber> phoneList) {
		this.phoneList = phoneList;
	}

}