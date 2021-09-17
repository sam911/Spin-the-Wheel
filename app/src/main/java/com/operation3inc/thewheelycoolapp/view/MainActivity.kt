package com.operation3inc.thewheelycoolapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.operation3inc.thewheelycoolapp.R
import com.operation3inc.thewheelycoolapp.viewmodel.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    /**
     * Initiate OptionViewModel that can be shared among the activity and fragments
     */
    private val optionViewModel by viewModel<OptionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prePopulateOptions()
    }

    /**
     * Function to pre-populate the database with options if not already filled
     */
    private fun prePopulateOptions() {
        optionViewModel.allOptions.observe(this, { options ->
            if (options.isNullOrEmpty()) {
                optionViewModel.prePopulateOptions()
            }
        })
    }
}