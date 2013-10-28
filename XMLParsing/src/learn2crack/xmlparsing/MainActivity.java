package learn2crack.xmlparsing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import learn2crack.xmlparsing.bean.Person;
import learn2crack.xmlparsing.bean.Person.PhoneNumber;
import learn2crack.xmlparsing.util.XMLUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView xmlStrTextView, personStrTextView;
	private Person person;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		xmlStrTextView = (TextView) findViewById(R.id.xmlStr);
		personStrTextView = (TextView) findViewById(R.id.personStr);

		initPerson();
		String xmlStr = XMLUtil.toXML(person);
		xmlStrTextView.setText(xmlStr);

		InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
		Person personFromXML = XMLUtil.fromXML(is).get(0);
		personStrTextView.setText(personFromXML.toString());

	}

	private void initPerson() {
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.second) {
			startActivity(new Intent(this, ScrollListViewTest.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
