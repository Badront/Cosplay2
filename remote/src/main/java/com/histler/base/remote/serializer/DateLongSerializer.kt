package com.histler.base.remote.serializer

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class DateLongSerializer : JsonDeserializer<Date>, JsonSerializer<Date> {
    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, jsonDeserializationContext: JsonDeserializationContext): Date {
        return Date(jsonElement.asLong)
    }

    override fun serialize(date: Date?, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
        return JsonPrimitive(date?.time ?: 0L)
    }
}