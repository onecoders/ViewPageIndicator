package net.jsiq.marketing.util;

import java.util.regex.Pattern;

public class ContactUtils {

	public static String formatNumber(String number) {
		String returnStr;
		if (number == null || number.length() == 0) {
			return null;
		}
		returnStr = number.replaceAll("-", "");
		if (returnStr.startsWith("+86")) {
			return returnStr.substring(3, number.length());
		} else if (returnStr.startsWith("17951")) {
			return returnStr.substring(5, number.length());
		} else if (returnStr.startsWith("12593")) {
			return returnStr.substring(5, number.length());
		} else if (returnStr.startsWith("17911")) {
			return returnStr.substring(5, number.length());
		} else if (returnStr.startsWith("17900")) {
			return returnStr.substring(5, number.length());
		} else {
			return returnStr;
		}
	}

	public static String formatAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}

		char c = str.trim().substring(0, 1).charAt(0);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}
}
