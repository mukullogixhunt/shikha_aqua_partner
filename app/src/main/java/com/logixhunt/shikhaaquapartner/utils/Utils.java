package com.logixhunt.shikhaaquapartner.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static String changeDateFormat(String fromFormat, String toFormat, String dateStr) {

        SimpleDateFormat sdfIn = new SimpleDateFormat(fromFormat, Locale.US);
        Date date = null;
        try {
            date = sdfIn.parse(dateStr);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat sdfOut = new SimpleDateFormat(toFormat, Locale.US);
        String formattedTime = sdfOut.format(date);

        return formattedTime;

    }

    public static boolean checkBetweenDates() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.yyyyMMdd, Locale.US);
        SimpleDateFormat sdf2 = new SimpleDateFormat(Constant.yyyyMMddHHmmss, Locale.US);
        Date d1 = new Date();
        Date d2 = new Date();
        String dateFrom = sdf.format(d1);
        String dateTo = sdf.format(d2);


        Date min = null, max = null;   // assume these are set to something
        Date d = new Date();
        try {
            min = sdf2.parse(dateFrom + " 09:00:00");
            max = sdf2.parse(dateTo + " 20:00:00");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        // the date in question

        return d.after(min) && d.before(max);
    }

    public static int getDaysDifference(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null)
            return 0;

        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String getDate(long milliSeconds, String outputFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String indianCurrencyFormatText(String number) {
        try {
            double num = Double.parseDouble(number);
            DecimalFormat dmf = new DecimalFormat("##,##,###.##");
            number = dmf.format(num);
        } catch (Exception e) {
            number = "0";
        }
        return "\u20B9" + number;
    }

    public static List<String> getRemainingDaysOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
// Calculate remaining days in month
        int remainingDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - mCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        ArrayList<String> allDays = new ArrayList<>();
        SimpleDateFormat mFormat = new SimpleDateFormat(Constant.EEddMMM, Locale.US);
        for(int i = 0; i < remainingDay; i++){
            allDays.add(mFormat.format(mCalendar.getTime()));
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return allDays;
    }

    public static List<String> getDaysOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
        int daysInMonth = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        ArrayList<String> allDays = new ArrayList<String>();
        SimpleDateFormat mFormat = new SimpleDateFormat(Constant.EEddMMM, Locale.US);
        for(int i = 0; i < daysInMonth; i++){
            // Add day to list
            allDays.add(mFormat.format(mCalendar.getTime()));
            // Move next day
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return allDays;
    }

}
