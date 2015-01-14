package com.ruanko.contactbook;

import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.bussiness.UserBussiness;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class CBApplication extends Application {
	private static CBApplication instance;

	@Override
	public void onCreate() {
		Log.v("xionglu", "application created");
		instance = this;
		ContactBussiness ub = new ContactBussiness();
		ub.fetchContactInformationV2(getApplicationContext());
		super.onCreate();
	}

	public static CBApplication getInstance() {
		if (instance == null) {
			instance = new CBApplication();
		}
		return instance;
	}

	public String getVersionName() {
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1.0";
	}

}
