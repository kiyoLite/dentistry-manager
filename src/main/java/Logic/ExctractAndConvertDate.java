/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author soyky
 */
//format YYYY-MM-DD 
public class ExctractAndConvertDate {

//    YYYY-MM-DD-HH-MM
    public static List<Integer> extractDateTime(String unformattedDateTime, String separator) {
        // 9 is the last index from date according to next format YYYY-MM-DD-HH-MM 
        int endIndexDate = 9;
        String unformattedDate = unformattedDateTime.substring(0, endIndexDate + 1);
        List<Integer> date = ExctractAndConvertDate.extractDate(unformattedDate, separator);
        //unformattedTime start from index endIndexDate + 2 beacuse have to sum the separator and real "startIndexTime"
        String unformattedTime = unformattedDateTime.substring(endIndexDate + 2);
        List<Integer> time = ExctractAndConvertDate.extractTime(unformattedTime, separator);
        return Stream.concat(date.stream(), time.stream()).toList();

    }

    public static List<Integer> extractDate(String unformattedDate, String separator) {
        List<String> datePartsAsStrings = Arrays.asList(unformattedDate.split(separator));
        Stream<Integer> dateParts = datePartsAsStrings.stream().map((curString) -> Integer.parseInt(curString));

        return dateParts.toList();

    }
//    HH-MM

    public static List<Integer> extractTime(String unformattedTime, String separator) {
        List<String> timePartsAsStrings = Arrays.asList(unformattedTime.split(separator));
        Stream<Integer> timeParts = timePartsAsStrings.stream().map((curTimePart) -> Integer.parseInt(curTimePart));
        return timeParts.toList();

    }

    public static Calendar convertToDate(List<Integer> dateParts) {
        return new GregorianCalendar(
                dateParts.get(0),
                // is less 1 beacause Calendar represent the months in range 0 -11
                dateParts.get(1) - 1,
                dateParts.get(2)
        );
    }

    public static Calendar convertToDateTime(List<Integer> datetimeParts) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                datetimeParts.get(0),
                // is less 1 beacause Calendar represent the months in range 0 -11
                datetimeParts.get(1) - 1,
                datetimeParts.get(2),
                datetimeParts.get(3),
                datetimeParts.get(4)
        );
        return calendar;
    }

}
