package com.nin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	static String DATE_FORMAT = "dd/MM/yyyy";
	static DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern(DATE_FORMAT)
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
	
	public static Long strindDateToLong(String strDate) {
		Long longDate = null;
		if(StringUtils.isBlank(strDate)) return null;
		try {
			Date date1= new SimpleDateFormat(DATE_FORMAT).parse(strDate);
			return date1.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return longDate;
	}

}
