package com.brendanjbishop.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * A generic utility class for various DateTime manipulations
 *
 * @author Brendan Bishop | brendan@brendanjbishop.com
 * @version 1.0
 * @since 1.8
 */
public final class DateTimeUtilities {

    /**
     * Converts a LocalDate Object and returns it as a string.
     *
     * @param date This is the date you want converted, not null
     * @return Formatted LocalDate as String
     */
    public static String dateToString(LocalDate date) throws NullPointerException, DateTimeException {
        requireNonNull(date);

        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    /**
     * Converts a LocalDate Object and returns it as a string. Able to add a
     * separator from the com.brendanjbishop.datetime.Separator class.
     *
     * @param date This is the date you want converted, not null
     * @param separator Specify with the Separator class which symbol you would
     * like to use between the parts of your date, not null
     * @see com.brendanjbishop.datetime.Separator
     * @return Formatted LocalDate as String
     */
    public static String dateToString(LocalDate date, Separator separator) throws NullPointerException, DateTimeException {
        requireNonNull(date);

        return date.format(DateTimeFormatter.ofPattern("MM" + separator.getSymbol() + "dd" + separator.getSymbol() + "yyyy"));
    }

    /**
     * Converts a LocalDate Object and returns it as a string. Able to add a
     * custom separator
     *
     * @param date
     * @param separator
     * @return
     */
    public static String dateToString(LocalDate date, String separator) throws NullPointerException, DateTimeException {
        requireNonNull(date);

        return date.format(DateTimeFormatter.ofPattern("MM" + separator + "dd" + separator + "yyyy"));
    }

    /**
     * Converts a String to a LocalDate Object and returns it.
     *
     * @param date The Date you are converting, not null
     * @param format The Format that your String date is in, not null
     * @return A LocalDate object
     */
    public static LocalDate toLocalDate(String date, String format) throws NullPointerException, DateTimeException {
        requireNonNull(date, format);

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Converts java.util.Date into a java.time.LocalDate.
     *
     * @param date A date that you want to convert
     * @return A localDate object
     */
    public static LocalDate toLocalDate(Date date) throws NullPointerException, DateTimeException {
        requireNonNull(date);
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    /**
     * Calculates how many days are left in the year. Also accommodates for leap
     * years.
     *
     * @param date The date you are checking, not null
     * @return The remaining days in the year
     */
    public static int getDaysLeftInYear(LocalDate date) throws NullPointerException, DateTimeException {
        requireNonNull(date);
        return date.lengthOfYear() - date.getDayOfYear();
    }

    /**
     * Takes a String date and a format and calculates how many days are left in
     * the year. Also accommodates for leap years.
     *
     * @param date Date to calculate, not null
     * @param format Format that the Date is in, not null
     * @return An Intger representing the amount of days left in the year
     * @throws DateTimeParseException thrown if LocalDate.parse fails to parse
     * out the date with specified format
     */
    public static int getDaysLeftInYear(String date, DateTimeFormatter format) throws DateTimeParseException, NullPointerException {
        requireNonNull(date, format);
        return LocalDate.parse(date, format).lengthOfYear() - LocalDate.parse(date, format).getDayOfYear();
    }

    /**
     * Calculates the amount of time, in hours, that elapsed between two
     * LocalDateTime objects.
     *
     * @param firstDate The starting date, not null
     * @param lastDate The ending date, not null
     * @return A double that represents the amount of hours that passed between
     * the two LocalDateTime objects
     */
    public static double getElapsedHours(LocalDateTime firstDate, LocalDateTime lastDate) {
        requireNonNull(firstDate, lastDate);
        return firstDate.until(lastDate, ChronoUnit.HOURS);
    }

    /**
     * Calculates the amount of time, in hours, that elapsed between two
     * LocalDateTime objects.
     *
     * @param firstDate The starting date, not null
     * @param lastDate The ending date, not null
     * @return A double that represents the amount of minutes that passed
     * between the two LocalDateTime objects
     */
    public static double getElapsedMinutes(LocalDateTime firstDate, LocalDateTime lastDate) {
        requireNonNull(firstDate, lastDate);
        return firstDate.until(lastDate, ChronoUnit.MINUTES);
    }

    /**
     * Calculates the elapsed hours and minutes as a double with the decimal
     * representing a percentage of an hour, rather than minutes EX: 2.50 =
     * 2hr(s). 30min(s).
     *
     * @param firstDate The starting date, not null
     * @param lastDate The ending date, not null
     * @return a double representing elapsed hours and minutes
     * @throws NullPointerException if parameters are null
     */
    public static double getElapsedHoursAndMinutesAsDouble(LocalDateTime firstDate, LocalDateTime lastDate) throws NullPointerException {
        requireNonNull(firstDate, lastDate);
        int minutes = (int) firstDate.until(lastDate, ChronoUnit.MINUTES) % 60;
        int hours = (int) firstDate.until(lastDate, ChronoUnit.HOURS);

        String decimal = new DecimalFormat(".00").format((double) minutes / 60);
        String combine = hours + "" + decimal;
        return Double.parseDouble(combine);
    }

    /**
     * Calculates the elapsed hours and minutes as a String
     *
     * @param firstDate
     * @param lastDate
     * @return A formatted String representing hours and minutes EX: 3hr(s).
     * 53min(s).
     * @throws NullPointerException if the parameters are null
     */
    public static String getElapsedHoursAndMinutesAsString(LocalDateTime firstDate, LocalDateTime lastDate) throws NullPointerException {
        requireNonNull(firstDate, lastDate);
        int minutes = (int) firstDate.until(lastDate, ChronoUnit.MINUTES) % 60;
        int hours = (int) firstDate.until(lastDate, ChronoUnit.HOURS);
        return String.format("%shr(s). %smin(s)", hours, minutes);
    }

    private static Object[] requireNonNull(Object... obj) {
        for (Object o : obj) {
            if (o == null) {
                throw new NullPointerException("Object was null");
            }
        }
        return obj;
    }
}
