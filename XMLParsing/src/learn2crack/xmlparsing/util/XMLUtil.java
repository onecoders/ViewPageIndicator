package learn2crack.xmlparsing.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import learn2crack.xmlparsing.model.Person;
import learn2crack.xmlparsing.model.Person.Address;
import learn2crack.xmlparsing.model.Person.PhoneNumber;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class XMLUtil {

	public static String toXML(Person... persons) {
		StringWriter stringWriter = new StringWriter();

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlSerializer xmlSerializer = factory.newSerializer();
			xmlSerializer.setOutput(stringWriter);
			xmlSerializer.startDocument("utf-8", true);
			xmlSerializer.startTag(null, "persons");
			for (Person person : persons) {
				xmlSerializer.startTag(null, "person");
				// name attribute
				xmlSerializer.startTag(null, "name");
				xmlSerializer.text(person.getName());
				xmlSerializer.endTag(null, "name");
				// surname attribute
				xmlSerializer.startTag(null, "surname");
				xmlSerializer.text(person.getSurname());
				xmlSerializer.endTag(null, "surname");
				// address attribute
				xmlSerializer.startTag(null, "addresse");

				xmlSerializer.startTag(null, "address");
				xmlSerializer.text(person.getAddress().getAddress());
				xmlSerializer.endTag(null, "address");

				xmlSerializer.startTag(null, "city");
				xmlSerializer.text(person.getAddress().getCity());
				xmlSerializer.endTag(null, "city");

				xmlSerializer.startTag(null, "state");
				xmlSerializer.text(person.getAddress().getState());
				xmlSerializer.endTag(null, "state");

				xmlSerializer.endTag(null, "addresse");
				// PhoneNumbers attribute
				xmlSerializer.startTag(null, "phoneNumbers");

				for (PhoneNumber pn : person.getPhoneList()) {
					xmlSerializer.startTag(null, "phoneNumber");

					xmlSerializer.startTag(null, "type");
					xmlSerializer.text(pn.getType());
					xmlSerializer.endTag(null, "type");

					xmlSerializer.startTag(null, "number");
					xmlSerializer.text(pn.getNumber());
					xmlSerializer.endTag(null, "number");

					xmlSerializer.endTag(null, "phoneNumber");
				}

				xmlSerializer.endTag(null, "phoneNumbers");

				xmlSerializer.endTag(null, "person");
			}
			xmlSerializer.endTag(null, "persons");
			xmlSerializer.endDocument();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}

	public static List<Person> fromXML(InputStream is) {
		List<Person> persons = new ArrayList<Person>();
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = xmlPullParserFactory.newPullParser();
			parser.setInput(is, "utf-8");

			int eventType = parser.getEventType();
			Person person = null;
			Address address = null;
			List<PhoneNumber> phoneNumbers = null;
			PhoneNumber phoneNumber = null;
			while (XmlPullParser.END_DOCUMENT != eventType) {
				String nodeName = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (nodeName.equals("person")) {
						person = new Person();
					}
					if (nodeName.equals("name")) {
						person.setName(parser.nextText());
					}
					if (nodeName.equals("surname")) {
						person.setSurname(parser.nextText());
					}
					if (nodeName.equals("addresse")) {
						address = person.new Address();
					}
					if (nodeName.equals("address")) {
						address.setAddress(parser.nextText());
					}
					if (nodeName.equals("city")) {
						address.setCity(parser.nextText());
					}
					if (nodeName.equals("state")) {
						address.setState(parser.nextText());
					}
					if (nodeName.equals("phoneNumbers")) {
						phoneNumbers = new ArrayList<Person.PhoneNumber>();
					}
					if (nodeName.equals("phoneNumber")) {
						phoneNumber = person.new PhoneNumber();
					}
					if (nodeName.equals("number")) {
						phoneNumber.setNumber(parser.nextText());
					}
					if (nodeName.equals("type")) {
						phoneNumber.setType(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (nodeName.equals("person") && person != null) {
						persons.add(person);
					}
					if (nodeName.equals("addresse") && address != null) {
						person.setAddress(address);
					}
					if (nodeName.equals("phoneNumbers") && phoneNumbers != null) {
						person.setPhoneList(phoneNumbers);
					}
					if (nodeName.equals("phoneNumber") && phoneNumber != null) {
						phoneNumbers.add(phoneNumber);
					}
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return persons;
	}

}
