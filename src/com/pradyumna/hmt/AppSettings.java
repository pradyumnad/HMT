package com.pradyumna.hmt;

/**
 * Created with IntelliJ IDEA.
 * User: pradyumnad
 * Date: 27/05/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */

enum UserType {
	ADMIN,
	EXECUTIVE_MANAGER,
	REQUESTING_MANAGER
}

public class AppSettings {
	public static UserType userType;
	public static String userId;
	private static AppSettings ourInstance = new AppSettings();

	public static AppSettings getInstance() {
		return ourInstance;
	}

	private AppSettings() {
	}

	public static void showAlert() {

	}
}
