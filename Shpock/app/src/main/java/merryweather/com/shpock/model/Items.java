package merryweather.com.shpock.model;

import java.util.ArrayList;

/**
 * Created by S on 19.04.2018.
 */

public class Items {

    private boolean success;

    private ArrayList<Item> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
