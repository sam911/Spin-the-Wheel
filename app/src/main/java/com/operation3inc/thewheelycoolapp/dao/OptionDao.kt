package com.operation3inc.thewheelycoolapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.operation3inc.thewheelycoolapp.model.Option
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {

    /**
     * Function to insert a new option to the database. If already exists then replace
     * @param option Option object that will be inserted into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: Option)

    /**
     * Function to update an option's is_selected field
     * @param optionId The ID of the option in the database
     * @param isSelected The new isSelected value that will be updated
     */
    @Query("UPDATE options SET is_selected = :isSelected WHERE id = :optionId")
    suspend fun updateIsSelected(optionId: Long, isSelected: Int)

    /**
     * Function to get all the options in the database
     * @return A list of options
     */
    @Query("SELECT * FROM options ORDER BY name")
    fun getAllOptions(): Flow<List<Option>>

    /**
     * Function to get all the options that are selected
     * @return A list options where isSelected == 1
     */
    @Query("SELECT * FROM options WHERE is_selected = 1 ORDER BY name")
    fun getSelectedOptions(): Flow<List<Option>>
}