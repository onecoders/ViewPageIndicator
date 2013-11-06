package net.jsip.market.test;

import net.jsip.market.db.Column;
import net.jsip.market.db.Data2DB;
import android.test.AndroidTestCase;


public class Data2DBTest extends AndroidTestCase {

	Data2DB adapter;

	@Override
	protected void setUp() throws Exception {
		adapter = new Data2DB(getContext());
		adapter.open();
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		adapter.close();
		super.tearDown();
	}

	public void testQuery() {
		Column column = new Column(1, "TEST" + 1, "Rest" + 1, "Github" + 1);
		long id = adapter.insert(column);
		assertEquals(column, adapter.queryById(id));
	}

	public void testUpdate() {
		Column column = new Column(2, "TEST" + 2, "Rest" + 2, "Github" + 2);
		long id = adapter.insert(column);
		column.set_id(id);
		column.setColumn_1(13);
		column.setColumn_2("TSET");
		column.setColumn_3("tseR");
		column.setColumn_4("buhtiG");
		adapter.update(column);
		assertEquals(column, adapter.queryById(id));
	}

	public void testDelete() {
		Column column = new Column(3, "TEST" + 3, "Rest" + 3, "Github" + 3);
		long id = adapter.insert(column);
		assertEquals(true, adapter.delete(id));
		assertNull(adapter.queryById(id));
	}

	private void assertEquals(Column expected, Column actual) {
		assertEquals(expected.getColumn_1(), actual.getColumn_1());
		assertEquals(expected.getColumn_2(), actual.getColumn_2());
		assertEquals(expected.getColumn_3(), actual.getColumn_3());
		assertEquals(expected.getColumn_4(), actual.getColumn_4());
	}

}
