package com.operation3inc.thewheelycoolapp.view

interface OptionItemClickListener {

    /**
     * Callback to listen to user click on the option
     * @param position The position of the item in the list that was clicked
     */
    fun onOptionItemClick(position: Int)
}