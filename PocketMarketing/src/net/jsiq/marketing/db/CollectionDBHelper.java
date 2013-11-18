package net.jsiq.marketing.db;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.model.ContentCollection;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CollectionDBHelper {

	static final String KEY_ROWID = "_id";
	static final String KEY_CONTENT_ID = "content_id";
	static final String KEY_CONTENT_TITLE = "content_title";
	static final String KEY_CONTENT_SUMMARY = "content_summary";

	static final String DATABASE_NAME = "PocketMarketing";
	static final String DATABASE_TABLE = "collections";
	static final int DATABASE_VERSION = 3;

	static final String DATABASE_CREATE = "CREATE TABLE collections (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " content_id INTEGER,"
			+ " content_title VARCHAR,"
			+ " content_summary VARCHAR)";

	final Context context;

	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public CollectionDBHelper(Context context) {
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

	public CollectionDBHelper open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public boolean insert(ContentCollection colleciton) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_CONTENT_ID, colleciton.getContentId());
		cv.put(KEY_CONTENT_TITLE, colleciton.getContentTitle());
		cv.put(KEY_CONTENT_SUMMARY, colleciton.getContentSummary());
		return db.insert(DATABASE_TABLE, null, cv) > 0;
	}

	public boolean delete(long id) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}

	public List<ContentCollection> queryAll() {
		List<ContentCollection> collections = new ArrayList<ContentCollection>();
		Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_CONTENT_ID, KEY_CONTENT_TITLE, KEY_CONTENT_SUMMARY }, null,
				null, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				ContentCollection collection = new ContentCollection();
				collection.set_id(c.getInt(c.getColumnIndex(KEY_ROWID)));
				collection.setContentId(c.getInt(c
						.getColumnIndex(KEY_CONTENT_ID)));
				collection.setContentTitle(c.getString(c
						.getColumnIndex(KEY_CONTENT_TITLE)));
				collection.setContentSummary(c.getString(c
						.getColumnIndex(KEY_CONTENT_SUMMARY)));
				collections.add(collection);
			}
		}
		c.close();
		return collections;
	}

	public boolean deleteAll() {
		try {
			Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
					KEY_CONTENT_ID, KEY_CONTENT_TITLE, KEY_CONTENT_SUMMARY },
					null, null, null, null, null);
			if (c != null) {
				while (c.moveToNext()) {
					delete(c.getInt(c.getColumnIndex(KEY_ROWID)));
				}
			}
			c.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
