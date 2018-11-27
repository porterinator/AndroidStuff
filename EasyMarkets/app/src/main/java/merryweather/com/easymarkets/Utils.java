package merryweather.com.easymarkets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public final static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("mm.dd.yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }
}
