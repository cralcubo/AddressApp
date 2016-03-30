package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static final String DATE_PATTERN = "dd.MM.YYYY";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	
	public static String format(LocalDate localDate) {
		if(localDate == null) {
			return null;
		}
		
		return DATE_FORMATTER.format(localDate);
	}
	
	public static LocalDate parse(String date) {
		return DATE_FORMATTER.parse(date, LocalDate::from);
	}
	
	public static boolean validDate(String date) {
		return DATE_FORMATTER.parse(date) != null;
	}

}
