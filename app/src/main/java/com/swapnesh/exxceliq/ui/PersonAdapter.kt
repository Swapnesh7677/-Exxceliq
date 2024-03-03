package com.swapnesh.exxceliq.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swapnesh.exxceliq.databinding.RvListItemsBinding
import com.swapnesh.exxceliq.domain.model.Person
import com.swapnesh.exxceliq.domain.model.PersonData

class PersonAdapter : PagedListAdapter<PersonData, PersonAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem = getItem(position)
        holder.apply {
            bind(userItem)
            itemView.tag = userItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }



    class ViewHolder(
        private val binding: RvListItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind( item: PersonData?) {
            binding.apply {

                textViewName.text = buildString {
                    append(item?.first_name)
                    append(" ")
                    append(item?.first_name)
                }
                textViewEmail.text = item?.email
                Glide.with(itemView).load(item?.avatar).into(binding.ivuser)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<PersonData>() {

    override fun areItemsTheSame(oldItem: PersonData, newItem: PersonData): Boolean {
        return oldItem.first_name == newItem.first_name
    }

    override fun areContentsTheSame(oldItem: PersonData, newItem: PersonData): Boolean {
        return oldItem == newItem
    }
}