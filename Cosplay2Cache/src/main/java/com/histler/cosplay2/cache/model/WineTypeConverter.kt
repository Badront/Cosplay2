package com.histler.cosplay2.cache.model

import android.arch.persistence.room.TypeConverter
import com.histler.cosplay2.data.model.WinTypeEntity

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
open class WineTypeConverter {

    @TypeConverter
    open fun toEntity(winType: Int): WinTypeEntity {
        return WinTypeEntity.values()[winType]
    }

    @TypeConverter
    open fun fromEntity(winType: WinTypeEntity): Int {
        return winType.ordinal
    }
}