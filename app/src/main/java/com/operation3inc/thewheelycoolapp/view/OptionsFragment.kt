package com.operation3inc.thewheelycoolapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.operation3inc.thewheelycoolapp.databinding.FragmentOptionsBinding
import com.operation3inc.thewheelycoolapp.viewmodel.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OptionsFragment : Fragment(), OptionItemClickListener {

    private var binding: FragmentOptionsBinding? = null

    /**
     * Shared viewModel that was initiated in the MainActivity
     */
    private val optionViewModel by sharedViewModel<OptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            optionsFragment = this@OptionsFragment
            viewModel = optionViewModel
            optionListAdapter = OptionListAdapter(this@OptionsFragment)
        }
        return binding?.root
    }

    /**
     * Function to listen to click on the DONE button
     * Navigates to the WheelFragment
     */
    fun onBtnDoneClick() {
        val action = OptionsFragmentDirections.actionOptionsFragmentToWheelFragment()
        view?.findNavController()?.navigate(action)
    }

    /**
     * Function to listen to option item click on the recyclerview
     * Toggle isSelected value
     * @param position Position of the item on the list that was clicked
     */
    override fun onOptionItemClick(position: Int) {
        optionViewModel.optionSelectionChanged(position)
    }
}