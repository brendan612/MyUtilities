/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendanjbishop.datetime;

import static com.brendanjbishop.datetime.DateTimeUtilities.dateToString;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Bren
 */
public class DateTimeUtilitiesTest {

    private LocalDate date;

    public DateTimeUtilitiesTest() {
    }

    @Before
    public void setUp() {
        // Insead of initializing in each test, just do it here
        // Declaration is above.
        date = LocalDate.now();
    }

    @After
    public void tearDown() {
        // clean up after yourself!
        date = null;
    }

    /**
     * Test of dateToString method, of class DateTimeUtilities.
     */
    @Test
    public void testDateToString_LocalDate() {
        String test = "11/13/2017";
        String form = dateToString(date);
        assertEquals(test,form);
    }

    /**
     * Test of dateToString method, of class DateTimeUtilities.
     */
    @Test
    public void testDateToString_LocalDate_Separator() {
        String test = "11 13 2017";
        Separator s = Separator.SPACE;
        String form = dateToString(date,s);
        assertEquals(test, form);
    }

    /**
     * Test of dateToString method, of class DateTimeUtilities.
     */
    @Test
    public void testDateToString_LocalDate_String() {
        String test = "11!13!2017";
        String s = "!";
        String form = dateToString(date,s);
        assertEquals(test, form);
    }

    /**
     * Test of toLocalDate method, of class DateTimeUtilities.
     */
    @Test
    public void testToLocalDate_String_String() {
        String date = "11-13-2017";
        String format = "MM-dd-yyyy";
        assertEquals(LocalDate.now(), LocalDate.parse(date, DateTimeFormatter.ofPattern(format)));
    }

    /**
     * Test of toLocalDate method, of class DateTimeUtilities.
     */
    @Test
    public void testToLocalDate_Date() {
        java.util.Date d = new java.util.Date();
        assertEquals(date,DateTimeUtilities.toLocalDate(d));
    }
}
