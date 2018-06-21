package merryweather.com.adorable.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by S on 21.06.2018.
 */

public class DateConverter {

    public static String getDateStringWithUnixTimestamp(long timestamp) {
        java.util.Date time = new java.util.Date((long)timestamp);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }
}
