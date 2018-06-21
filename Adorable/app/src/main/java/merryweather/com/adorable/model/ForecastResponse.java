package merryweather.com.adorable.model;

import java.util.ArrayList;

/**
 * Created by S on 21.06.2018.
 */

public class ForecastResponse {

    public ArrayList<ForecastItem> list;

    public class ForecastItem{
        public long dt;
        public Main main;
        public ArrayList<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public Sys sys;
        public String dt_txt;

    }
}
