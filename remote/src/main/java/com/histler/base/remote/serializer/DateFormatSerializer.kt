package com.histler.base.remote.serializer

import com.google.gson.*
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.util.*

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class DateFormatSerializer constructor(private val dateFormat: DateFormat) : JsonSerializer<Date>, JsonDeserializer<Date?> {
    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(dateFormat.format(src))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        return try {
            dateFormat.parse(json?.asString)
        } catch (e: ParseException) {
            null
        }
    }
}