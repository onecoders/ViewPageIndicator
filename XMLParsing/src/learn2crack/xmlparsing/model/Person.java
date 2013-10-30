package learn2crack.xmlparsing.model;

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

	@Override
	public String toString() {
		String info = "---Person---\n";
		info += "name:" + name + " ,surname:" + surname + " ,address:"
				+ address.address + " ,city:" + address.city + " ,state:"
				+ address.state;
		for (PhoneNumber pn : phoneList) {
			info += " ,number:" + pn.number + " ,type:" + pn.type;
		}
		return info;
	}

	public class Address {

		private String address;
		private String city;
		private String state;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	}

	public class PhoneNumber {

		private String type;
		private String number;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

	}

}