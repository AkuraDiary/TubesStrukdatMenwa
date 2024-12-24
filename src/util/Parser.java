package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Date parseStringToDate(String date, String format) {
        if (date == null || format == null) {
            throw new IllegalArgumentException("Date and format cannot be null");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            System.err.println("Failed to parse date: " + e.getMessage());
            return null;
        }
    }
}
