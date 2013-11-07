package net.jsiq.marketing.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Data2DB {

	static final String KEY_ROWID = "_id";
	static final String KEY_COLUMN_1 = "column_1";
	static final String KEY_COLUMN_2 = "column_2";
	static final String KEY_COLUMN_3 = "column_3";
	static final String KEY_COLUMN_4 = "column_4";
	static final String TAG = "DBAdapter";

	static final String DATABASE_NAME = "IntroDB1";
	static final String DATABASE_TABLE = "intro";
	static final int DATABASE_VERSION = 1;

	static final String DATABASE_CREATE = "CREATE TABLE intro (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " column_1 INTEGER,"
			+ " column_2 VARCHAR,"
			+ " column_3 VARCHAR," + " column_4 VARCHAR)";

	final Context context;

	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public Data2DB(Context context) {
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

	public Data2DB open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long insert(Column column) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_COLUMN_1, column.getColumn_1());
		cv.put(KEY_COLUMN_2, column.getColumn_2());
		cv.put(KEY_COLUMN_3, column.getColumn_3());
		cv.put(KEY_COLUMN_4, column.getColumn_4());
		return db.insert(DATABASE_TABLE, null, cv);
	}

	public boolean delete(long id) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}

	public boolean update(Column column) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_COLUMN_1, column.getColumn_1());
		cv.put(KEY_COLUMN_2, column.getColumn_2());
		cv.put(KEY_COLUMN_3, column.getColumn_3());
		cv.put(KEY_COLUMN_4, column.getColumn_4());
		return db.update(DATABASE_TABLE, cv, KEY_ROWID + "=" + column.get_id(),
				null) > 0;
	}

	public Column queryById(long id) {
		Column column = null;
		Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_COLUMN_1, KEY_COLUMN_2, KEY_COLUMN_3, KEY_COLUMN_4 },
				KEY_ROWID + "=" + id, null, null, null, null, null);
		if (c != null && c.moveToFirst()) {
			column = new Column();
			column.set_id(c.getInt(c.getColumnIndex(KEY_ROWID)));
			column.setColumn_1(c.getInt(c.getColumnIndex(KEY_COLUMN_1)));
			column.setColumn_2(c.getString(c.getColumnIndex(KEY_COLUMN_2)));
			column.setColumn_3(c.getString(c.getColumnIndex(KEY_COLUMN_3)));
			column.setColumn_4(c.getString(c.getColumnIndex(KEY_COLUMN_4)));
		}
		return column;
	}

	public List<Column> queryAll() {
		List<Column> columns = new ArrayList<Column>();
		Cursor c = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_COLUMN_1, KEY_COLUMN_2, KEY_COLUMN_3, KEY_COLUMN_4 }, null,
				null, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				Column column = new Column();
				column.set_id(c.getInt(c.getColumnIndex(KEY_ROWID)));
				column.setColumn_1(c.getInt(c.getColumnIndex(KEY_COLUMN_1)));
				column.setColumn_2(c.getString(c.getColumnIndex(KEY_COLUMN_2)));
				column.setColumn_3(c.getString(c.getColumnIndex(KEY_COLUMN_3)));
				column.setColumn_4(c.getString(c.getColumnIndex(KEY_COLUMN_4)));
				columns.add(column);
			}
		}
		c.close();
		return columns;
	}

}
