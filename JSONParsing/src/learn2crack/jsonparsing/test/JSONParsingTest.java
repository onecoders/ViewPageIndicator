package learn2crack.jsonparsing.test;

import java.util.ArrayList;
import java.util.List;

import learn2crack.jsonparsing.model.Person;
import learn2crack.jsonparsing.model.Person.Address;
import learn2crack.jsonparsing.model.Person.PhoneNumber;
import learn2crack.jsonparsing.util.JsonUtil;
import android.test.AndroidTestCase;

public class JSONParsingTest extends AndroidTestCase {

	private Person person;

	@Override
	protected void setUp() throws Exception {
		person = new Person();
		person.setName("Silicon");
		person.setSurname("松花江河畔");
		Person.Address address = person.new Address();
		address.setAddress("Jiangsu常州市武进区");
		address.setCity("常州市");
		address.setState("Jiangsu");
		person.setAddress(address);
		List<PhoneNumber> phoneList = new ArrayList<PhoneNumber>();
		PhoneNumber pn = person.new PhoneNumber();
		pn.setNumber("66668888");
		pn.setType("PHONE");
		phoneList.add(pn);
		pn.setNumber("13866668888");
		pn.setType("电话");
		phoneList.add(pn);
		pn.setNumber("TEST");
		pn.setType("test");
		phoneList.add(pn);
		person.setPhoneList(phoneList);
		super.setUp();
	}

	public void testParseBetweenJSONAndBean() {
		String jsonStr = JsonUtil.toJSON(person);
		Person personFromJSON = JsonUtil.fromJSON(jsonStr);
		assertEquals(person, personFromJSON);
	}

	private void assertEquals(Person expected, Person actual) {
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getSurname(), actual.getSurname());
		Address address1 = expected.getAddress();
		Address address2 = actual.getAddress();
		assertEquals(address1.getAddress(), address2.getAddress());
		assertEquals(address1.getCity(), address2.getCity());
		assertEquals(address1.getState(), address2.getState());
		List<PhoneNumber> phoneList1 = expected.getPhoneList();
		List<PhoneNumber> phoneList2 = actual.getPhoneList();
		assertEquals(phoneList1.size(), phoneList2.size());
		for (int i = 0; i < phoneList1.size(); i++) {
			PhoneNumber pn1 = phoneList1.get(i);
			PhoneNumber pn2 = phoneList2.get(i);
			assertEquals(pn1.getNumber(), pn2.getNumber());
			assertEquals(pn1.getType(), pn2.getType());
		}
	}

}
