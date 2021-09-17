package com.operation3inc.thewheelycoolapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.operation3inc.thewheelycoolapp.model.Option
import com.operation3inc.thewheelycoolapp.repository.OptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OptionViewModel(
    private val optionRepository: OptionRepository
) : ViewModel() {

    val allOptions: LiveData<List<Option>> = optionRepository.allOptions.asLiveData()

    val selectedOptions: LiveData<List<Option>> = optionRepository.selectedOptions.asLiveData()

    /**
     * Function to insert an Option to the database
     * @param option Option that will be inserted
     * Runs on a background thread
     */
    private suspend fun insertOption(option: Option) {
        withContext(Dispatchers.Default) {
            optionRepository.insertOption(option)
        }
    }

    /**
     * Function to update the isSelected value of the option
     * It toggles the value from 0 to 1 or 1 to 0 based on current value
     * Runs on a background thread
     * @param position Position of the option clicked on the list
     */
    fun optionSelectionChanged(position: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val options = allOptions.value
                if (position < options?.size ?: 0) {
                    val option = options?.get(position)
                    option?.let {
                        val isSelected = if (it.isSelected == 0) {
                            1
                        } else {
                            0
                        }
                        optionRepository.updateIsSelected(it.id, isSelected)
                    }
                }
            }
        }
    }

    /**
     * Function to pre-populate the database with options
     */
    fun prePopulateOptions() = viewModelScope.launch {
        insertOption(Option(name = "Option 1", isSelected = 0))
        insertOption(Option(name = "Option 2", isSelected = 0))
        insertOption(Option(name = "Option 3", isSelected = 0))
        insertOption(Option(name = "Option 4", isSelected = 0))
        insertOption(Option(name = "Option 5", isSelected = 0))
    }
}