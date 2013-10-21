package com.intro.compintro.test;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;

import com.intro.compintro.db.Column;
import com.intro.compintro.io.Data2File;

public class Data2FileTest extends AndroidTestCase {

	List<Column> inserts;;

	@Override
	protected void setUp() throws Exception {
		inserts = new ArrayList<Column>();
		for (int i = 0; i < 5; i++) {
			Column column = new Column(i, "Name" + i, "Gender" + i, "Height"
					+ i);
			Data2File.insert(column);
			inserts.add(column);
		}
		super.setUp();
	}

	public void testQueryAndInsert() {
		List<Column> querys = Data2File.queryAll();
		for (int i = 0; i < querys.size(); i++) {
			assertEquals(inserts.get(i), querys.get(i));
		}
	}

	private void assertEquals(Column expected, Column actual) {
		assertEquals(expected.getColumn_1(), actual.getColumn_1());
		assertEquals(expected.getColumn_2(), actual.getColumn_2());
		assertEquals(expected.getColumn_3(), actual.getColumn_3());
		assertEquals(expected.getColumn_4(), actual.getColumn_4());
	}
}
