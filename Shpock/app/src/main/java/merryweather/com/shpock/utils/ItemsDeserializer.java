package merryweather.com.shpock.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import merryweather.com.shpock.model.Item;
import merryweather.com.shpock.model.Items;


/**
 * Created by S on 19.04.2018.
 */

public class ItemsDeserializer implements JsonDeserializer<Items> {
    private final String SUCCESS = "success";
    private final String ITEMS = "items";

    @Override
    public Items deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonItems = json.getAsJsonObject();
        Items items = new Items();
        items.setSuccess(jsonItems.get(SUCCESS).getAsBoolean());
        JsonArray jsonItemz = jsonItems.getAsJsonArray(ITEMS);
        ArrayList<Item> itemz = new ArrayList<>();
        for (JsonElement  jsonElement : jsonItemz) {
            if (!jsonElement.isJsonNull())
                itemz.add(context.deserialize(jsonElement, Item.class));
        }
        items.setItems(itemz);
        return items;
    }
}
