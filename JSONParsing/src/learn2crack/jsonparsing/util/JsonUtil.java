package learn2crack.jsonparsing.util;

import java.util.ArrayList;
import java.util.List;

import learn2crack.jsonparsing.bean.Person;
import learn2crack.jsonparsing.bean.Person.Address;
import learn2crack.jsonparsing.bean.Person.PhoneNumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	// parse to JSON
	public static String toJSON(Person person) {
		try {
			JSONObject jsonObj = new JSONObject();
			// name attribute
			jsonObj.put("name", person.getName());
			// surname attribute
			jsonObj.put("surname", person.getSurname());
			// address attribute
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("address", person.getAddress().getAddress());
			jsonAdd.put("city", person.getAddress().getCity());
			jsonAdd.put("state", person.getAddress().getState());
			jsonObj.put("address", jsonAdd);
			// phone number list attribute
			JSONArray jsonArr = new JSONArray();
			for (PhoneNumber pn : person.getPhoneList()) {
				JSONObject pnObj = new JSONObject();
				pnObj.put("num", pn.getNumber());
				pnObj.put("type", pn.getType());
				jsonArr.put(pnObj);
			}
			jsonObj.put("phoneNumber", jsonArr);
			return jsonObj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	// parse JSON data
	public static Person fromJSON(String jsonStr) {
		Person person = null;
		try {
			person = new Person();
			JSONObject jObj = new JSONObject(jsonStr);
			person.setSurname(jObj.getString("surname"));
			person.setName(jObj.getString("name"));
			JSONObject jsonAdd = jObj.getJSONObject("address");
			Address address = person.new Address();
			address.setAddress(jsonAdd.getString("address"));
			address.setCity(jsonAdd.getString("city"));
			address.setState(jsonAdd.getString("state"));
			person.setAddress(address);
			JSONArray jsonArr = jObj.getJSONArray("phoneNumber");
			List<PhoneNumber> phoneList = new ArrayList<PhoneNumber>();
			for (int i = 0; i < jsonArr.length(); i++) {
				PhoneNumber pn = person.new PhoneNumber();
				JSONObject jsonPhone = jsonArr.getJSONObject(i);
				pn.setNumber(jsonPhone.getString("num"));
				pn.setType(jsonPhone.getString("type"));
				phoneList.add(pn);
			}
			person.setPhoneList(phoneList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return person;
	}

}
