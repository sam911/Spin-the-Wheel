package com.operation3inc.thewheelycoolapp.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.operation3inc.thewheelycoolapp.model.Option
import com.operation3inc.thewheelycoolapp.view.OptionListAdapter

/**
 * Function to set adapter to recyclerview
 * @param recyclerView Recyclerview where the adapter will be set
 * @param adapter Adapter which will be set to the recyclerview
 */
@BindingAdapter("android:setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.adapter = adapter
}

/**
 * Function to update option list on the adapter
 * @param view Recyclerview where the adapter is attached
 * @param options List of options that will be updated to the recyclerview
 */
@BindingAdapter("android:setOptionList")
fun setOptionList(view: RecyclerView, options: MutableList<Option?>?) {
    view.adapter?.let { adapter ->
        (adapter as OptionListAdapter).submitList(options)
    }
}