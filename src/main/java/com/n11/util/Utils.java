package com.n11.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String minutesToDisplayTime(int minutes) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		Date dt = null;
		dt = sdf.parse(Integer.toString(minutes));
		sdf = new SimpleDateFormat("HH:mm a");
		return sdf.format(dt);
	}

}
