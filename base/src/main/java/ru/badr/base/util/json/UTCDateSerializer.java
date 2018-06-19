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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ABadretdinov
 * 01.07.2015
 * 15:56
 */
public class UTCDateSerializer implements JsonDeserializer<Date>, JsonSerializer<Date> {
    private DateFormat mDateFormat;
    private int mOffset;

    public UTCDateSerializer(String dateFormat) {
        this.mDateFormat = new SimpleDateFormat(dateFormat);
        long ts = System.currentTimeMillis();
        Date localTime = new Date(ts);
        mOffset = TimeZone.getDefault().getOffset(localTime.getTime());
    }


    @Override
    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        try {
            Date utcTime = mDateFormat.parse(json.getAsString());
            return new Date(utcTime.getTime() + mOffset);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        // Convert Local Time to UTC (Works Fine)
        mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return new JsonPrimitive(mDateFormat.format(date));
    }
}
