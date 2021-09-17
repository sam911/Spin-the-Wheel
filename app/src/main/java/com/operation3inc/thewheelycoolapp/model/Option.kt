package com.operation3inc.thewheelycoolapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "options")
data class Option(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "is_selected") var isSelected: Int,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
