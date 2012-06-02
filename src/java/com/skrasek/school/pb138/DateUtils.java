package com.skrasek.school.pb138;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Date list generator
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class DateUtils {

    public static List<Date> getList(Date start, Date end)
    {
        long s = start.getTime();
        long e = end.getTime();

        List<Date> list = new ArrayList<Date>();
        long skip = 60 * 60 * 24 * 1000; // 1 days

        do {
            list.add(new Date(s));
            s += skip;
        } while (s < e);

        return list;
    }

    public static String fromJsonToString(String twDate)
    {
        return twDate.replace("-", "").replace(" ", "").replace(":", "");
    }

    public static String fromDateToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.format(date);
    }

    public static Date fromStringToDate(String date)
    {
        Calendar s = Calendar.getInstance();
        s.set(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)) - 1,
                Integer.parseInt(date.substring(8))
        );
        return s.getTime();
    }

    public static Calendar fromDbStringToCalendar(String date) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        return c;
    }

    public static String fromCalendarToJsonString(Calendar date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date.getTime());
    }

}
