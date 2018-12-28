package de.easterapps.sotclock;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.google.android.gms.common.internal.Preconditions.checkArgument;

public class PirateDate {
    private long day = 0;
    private long hour = 0;
    private long minute = 0;

    public PirateDate updateCurrentTime() {

        Date date = new Date();
        Timestamp unixTime = new Timestamp(date.getTime());
        this.setDay(((((unixTime.getTime() / 60000) / 24)) % 30) + 1);
        this.setHour((((unixTime.getTime() / 60000) % 1440 % 24)));
        this.setMinute((((unixTime.getTime() / 1000) % 60)));
        return this;
    }


    public void setDay(long day) {
        this.day = day;
    }

    public String getDayText() {
        return String.format("%02d", day) + getDayOfMonthSuffix((int) day);
    }

    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public String getAmPmTime() {
        DateFormat dateFormat = new SimpleDateFormat("hh.mm");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, (int) hour);
        cal.set(Calendar.MINUTE, (int) minute);
        cal.set(Calendar.DAY_OF_MONTH, (int) day);
        Date date = cal.getTime();

        String dateString = dateFormat.format(date).toString();
        return dateString;
    }

    public String getAmPm() {
        DateFormat dateFormat = new SimpleDateFormat("aa");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, (int) hour);
        cal.set(Calendar.MINUTE, (int) minute);
        cal.set(Calendar.DAY_OF_MONTH, (int) day);
        Date date = cal.getTime();

        String dateString = dateFormat.format(date).toString();
        return dateString;

    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }
}
