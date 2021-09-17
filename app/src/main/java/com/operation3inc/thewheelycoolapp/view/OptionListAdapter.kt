package com.operation3inc.thewheelycoolapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.operation3inc.thewheelycoolapp.R
import com.operation3inc.thewheelycoolapp.databinding.LayoutOptionItemBinding
import com.operation3inc.thewheelycoolapp.model.Option

class OptionListAdapter(
    private val clickListener: OptionItemClickListener
) : ListAdapter<Option, OptionListAdapter.ViewHolder>(
    OptionListDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_option_item,
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: LayoutOptionItemBinding,
        private val clickListener: OptionItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                clickListener.onOptionItemClick(adapterPosition)
            }
        }

        /**
         * Function to bind the item
         * @param option The option item that to bind to the view
         */
        fun bind(option: Option) {
            with(binding) {
                name = option.name
                isSelected = option.isSelected
                executePendingBindings()
            }
        }
    }
}

private class OptionListDiffCallback : DiffUtil.ItemCallback<Option>() {
    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem.isSelected == newItem.isSelected
    }
}