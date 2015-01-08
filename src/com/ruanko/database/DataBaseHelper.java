package com.ruanko.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ruanko.model.Contact;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

	public static final int DATABASE_VERISION = 1;

	public DataBaseHelper(Context context) {
		super(context, "contact.db", null, DATABASE_VERISION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try{
			TableUtils.createTable(connectionSource, Contact.class);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		
	}
}
