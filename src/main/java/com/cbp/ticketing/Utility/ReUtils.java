package com.cbp.ticketing.Utility;

import com.cbp.rulesengine.crypt.ext.SecFactory;

public class ReUtils {
	public static String encryptPassword(String password) {
		String encryptedPassword = null;
		if (password != null) {
			encryptedPassword = SecFactory.getManager().encryptData(password);
		}
		return encryptedPassword;
	}

	public static String decryptPassword(String password) {
		String decryptedPassword = null;
		if (password != null) {
			decryptedPassword = SecFactory.getManager().decryptData(password);
		}
		return decryptedPassword;
	}


}
