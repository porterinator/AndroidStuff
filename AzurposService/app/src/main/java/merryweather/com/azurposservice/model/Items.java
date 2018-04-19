package merryweather.com.azurposservice.model;

/**
 * Created by S on 19.04.2018.
 */

public class Items {

    private boolean success;

    private Item[] items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
