package ru.badr.base.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by ABadretdinov
 * 13.07.2015
 * 14:00
 */
public class SimpleDateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private DateFormat mDateFormat;

    public SimpleDateSerializer(DateFormat dateFormat) {
        this.mDateFormat = dateFormat;
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return mDateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(mDateFormat.format(src));
    }
}
