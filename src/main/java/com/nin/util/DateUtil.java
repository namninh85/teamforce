package com.nin.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
	static DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("dd/MM/yyyy")
		                     .withLocale( Locale.UK )
		                     .withZone( ZoneId.systemDefault() );
	
	public static String instantToString(Instant instant) {
		String output = formatter.format( instant );
		return output;
	}
	
	public static String longDateToString(long longDate) {
		if(longDate < 10000000000L) {
			longDate = longDate * 1000;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(longDate);
		return instantToString(calendar.toInstant());
	}

}
