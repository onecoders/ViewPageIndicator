package net.jsip.market.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

import net.jsip.market.db.Column;

import android.os.Environment;


public class Data2File {

	private static String path = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/intro/test.file";

	public static List<Column> queryAll() {
		List<Column> list = new ArrayList<Column>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Column column = null;
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			while ((column = (Column) ois.readObject()) != null) {
				list.add(column);
			}
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static void insert(Column newColumn) {
		List<Column> list = queryAll();
		list.add(newColumn);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			for (Column column : list) {
				oos.writeObject(column);
				oos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
