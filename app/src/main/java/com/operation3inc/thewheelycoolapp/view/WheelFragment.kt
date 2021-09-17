package com.operation3inc.thewheelycoolapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.operation3inc.thewheelycoolapp.databinding.FragmentWheelBinding
import com.operation3inc.thewheelycoolapp.viewmodel.OptionViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WheelFragment : Fragment() {

    private var binding: FragmentWheelBinding? = null

    private val optionViewModel by sharedViewModel<OptionViewModel>()

    private var numberOfOptions: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            wheelFragment = this@WheelFragment
            viewModel = optionViewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopAppBarNavigationClickListener()

        context?.let { setUpWheel() }
    }

    /**
     * Function to set Wheel values
     */
    private fun setUpWheel() {
        optionViewModel.selectedOptions.observe(viewLifecycleOwner, { options ->
            if (!options.isNullOrEmpty()) {
                val wheelItems: MutableList<String> = mutableListOf()

                numberOfOptions = options.size

                for (option in options) {
                    wheelItems.add(option.name)
                }

                binding?.wheel?.apply {
                    addWheelItems(wheelItems)
                }
            }
        })
    }

    /**
     * Function to respond to close button click on the toolbar
     */
    private fun setTopAppBarNavigationClickListener() {
        binding?.topAppBar?.setNavigationOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }

    /**
     * Function to listen to the spin button click
     * Set a random value for the wheel to rotate to
     */
    fun onBtnSpinClick() {
        if (numberOfOptions > 0) {
            binding?.wheel?.apply {
                rotateWheelTo((0..numberOfOptions).random())
            }
        }
    }
}