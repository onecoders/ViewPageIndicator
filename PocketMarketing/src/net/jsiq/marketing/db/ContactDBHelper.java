package net.jsiq.marketing.db;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.model.ContactItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper {

	static final String KEY_ROWID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_NUMBER = "number";
	static final String KEY_EMAIL = "email";
	static final String TAG = "ContactDBAdapter";

	static final String DATABASE_NAME = "PocketMarketing";
	static final String DATABASE_TABLE = "contact";
	static final int DATABASE_VERSION = 1;

	static final String DATABASE_CREATE = "CREATE TABLE contact (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " name VARCHAR," + " number VARCHAR," + " email VARCHAR)";

	final Context context;

	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public ContactDBHelper(Context context) {
		this.context = context;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public ContactDBHelper open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long insert(ContactItem item) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, item.getName());
		cv.put(KEY_NUMBER, item.getNumber());
		cv.put(KEY_EMAIL, item.getEmail());
		return db.insert(DATABASE_TABLE, null, cv);
	}

	public boolean delete(long id) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}

	public boolean update(ContactItem item) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, item.getName());
		cv.put(KEY_NUMBER, item.getNumber());
		cv.put(KEY_EMAIL, item.getEmail());
		return db.update(DATABASE_TABLE, cv, KEY_ROWID + "=" + item.get_id(),
				null) > 0;
	}

	public ContactItem queryById(long id) {
		ContactItem item = null;
		Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_NUMBER, KEY_EMAIL }, KEY_ROWID + "=" + id, null, null,
				null, null, null);
		if (c != null && c.moveToFirst()) {
			item = new ContactItem();
			item.set_id(c.getInt(c.getColumnIndex(KEY_ROWID)));
			item.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			item.setNumber(c.getString(c.getColumnIndex(KEY_NUMBER)));
			item.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
		}
		c.close();
		return item;
	}

	public List<ContactItem> queryAll() {
		List<ContactItem> items = new ArrayList<ContactItem>();
		Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_NUMBER, KEY_EMAIL }, null, null, null, null, KEY_NAME);
		if (c != null) {
			while (c.moveToNext()) {
				ContactItem item = new ContactItem();
				item.set_id(c.getInt(c.getColumnIndex(KEY_ROWID)));
				item.setName(c.getString(c.getColumnIndex(KEY_NAME)));
				item.setNumber(c.getString(c.getColumnIndex(KEY_NUMBER)));
				item.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
				items.add(item);
			}
		}
		c.close();
		return items;
	}

}
