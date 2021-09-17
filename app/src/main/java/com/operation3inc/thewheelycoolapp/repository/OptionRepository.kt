package com.operation3inc.thewheelycoolapp.repository

import androidx.annotation.WorkerThread
import com.operation3inc.thewheelycoolapp.dao.OptionDao
import com.operation3inc.thewheelycoolapp.model.Option
import kotlinx.coroutines.flow.Flow

class OptionRepository(
    private val optionDao: OptionDao
) {
    /**
     * Get all the options from the database
     */
    val allOptions: Flow<List<Option>> = optionDao.getAllOptions()

    /**
     * Get only the selected options where isSelected = 1
     */
    val selectedOptions: Flow<List<Option>> = optionDao.getSelectedOptions()

    /**
     * Function to insert a new option to the database
     * @param option Option object to insert
     */
    @WorkerThread
    suspend fun insertOption(option: Option) {
        optionDao.insertOption(option)
    }

    /**
     * Function to update an option's isSelected value in the database
     * @param optionId ID of the option that will be updated
     * @param isSelected The isSelected value that will be set
     */
    @WorkerThread
    suspend fun updateIsSelected(optionId: Long, isSelected: Int) {
        optionDao.updateIsSelected(optionId, isSelected)
    }
}