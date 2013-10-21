package com.intro.compintro.db;

import java.io.Serializable;

public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5399128925674149672L;
	private long _id;
	private int column_1;
	private String column_2;
	private String column_3;
	private String column_4;

	public Column() {

	}

	public Column(int column_1, String column_2, String column_3,
			String column_4) {
		this.column_1 = column_1;
		this.column_2 = column_2;
		this.column_3 = column_3;
		this.column_4 = column_4;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public int getColumn_1() {
		return column_1;
	}

	public void setColumn_1(int column_1) {
		this.column_1 = column_1;
	}

	public String getColumn_2() {
		return column_2;
	}

	public void setColumn_2(String column_2) {
		this.column_2 = column_2;
	}

	public String getColumn_3() {
		return column_3;
	}

	public void setColumn_3(String column_3) {
		this.column_3 = column_3;
	}

	public String getColumn_4() {
		return column_4;
	}

	public void setColumn_4(String column_4) {
		this.column_4 = column_4;
	}

}
