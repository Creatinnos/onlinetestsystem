package com.creatinnos.onlinetestsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String FORMATE = "ddMMyyyHHmmssSSS";
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	public  static String getDate(Date date) {
		return getDate(FORMATE, date);
	}

	public static String getDate(String formate, Date date) {
		simpleDateFormat = new SimpleDateFormat(formate);
		return simpleDateFormat.format(date);
	}

	public static void main(String[] args) {

		System.out.println(getDate(new Date()));
	}
}
