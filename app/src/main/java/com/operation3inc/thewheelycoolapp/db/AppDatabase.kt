package com.operation3inc.thewheelycoolapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.operation3inc.thewheelycoolapp.dao.OptionDao
import com.operation3inc.thewheelycoolapp.model.Option

@Database(
    version = 1,
    entities = [Option::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun optionDao(): OptionDao
}