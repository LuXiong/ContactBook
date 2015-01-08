package com.ruanko.database;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.ruanko.contactbook.CBApplication;

public class DataBaseHelperManager {
	private volatile DataBaseHelper helper;
	private static volatile DataBaseHelperManager instance;
	private static volatile int instanceCount;

	private DataBaseHelperManager() {

	}

	public static DataBaseHelperManager getInstance() {
		if (instance == null) {
			instance = new DataBaseHelperManager();
		}
		return instance;
	}

	public DataBaseHelper getHelper() throws SQLException {
		if (helper == null) {
			helper = OpenHelperManager.getHelper(CBApplication.getInstance(),
					DataBaseHelper.class);
		}
		instanceCount++;
		return helper;
	}

	public void releaseHelper(DataBaseHelper helper) {
		if (helper != null) {
			instanceCount--;
			if (instanceCount <= 0) {
				OpenHelperManager.releaseHelper();
				this.helper = null;
			}
		}
	}
}
