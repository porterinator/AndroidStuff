package merryweather.com.adorable.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by S on 20.06.2018.
 */

public class WeatherResponse {

    public Coord coord;
    public Sys sys;
    public ArrayList<Weather> weather;
    public Main main;
    public Wind wind;
    @SerializedName("3h")
    public boolean rain;
    public Clouds clouds;
    public int dt;
    public int id;
    public String name;
    public int cod;
}
