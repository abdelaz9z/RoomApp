package com.casecode.roomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casecode.domain.entity.User
import com.casecode.roomapp.databinding.CustomRowBinding

class ListAdapter(private val items: List<User>, private val itemClick: (User) -> Unit) :
    RecyclerView.Adapter<ListAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class UserHolder(private val binding: CustomRowBinding, val itemClick: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        // here comes all the views / variables from view.findViewById()
        // Example: val textView = view.findViewById(R.id.textView1)
        fun bindView(item: User) {
            // here you do all kind of assignments and logics..
            // for Example: textView.text = item.name or just name
            itemView.setOnClickListener { itemClick(item) }

            binding.textViewId.text = item.id.toString()
            binding.textViewFirstName.text = item.firstName
            binding.textViewLastName.text = item.lastName
            binding.textViewAge.text = item.age.toString()
        }
    }

}