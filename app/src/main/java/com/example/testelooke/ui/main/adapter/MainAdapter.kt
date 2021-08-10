package com.example.testelooke.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testelooke.app.load
import com.example.testelooke.data.model.Topping
import com.example.testelooke.databinding.CardToppingBinding

class MainAdapter :
    RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {

    private var listToppingTypes = mutableListOf<Topping>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapterViewHolder {
        return MainAdapterViewHolder(
            CardToppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MainAdapterViewHolder,
        position: Int
    ) {
        holder.bind(listToppingTypes[position])
    }

    override fun getItemCount(): Int {
        return listToppingTypes.size
    }

    fun updateList(list: MutableList<Topping>) {
        listToppingTypes = list
        notifyDataSetChanged()
    }

    inner class MainAdapterViewHolder(
        private val binding: CardToppingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Topping) {
            binding.tvTypeTopping.text = item.type
            binding.ivTopping.load(getUriImage(item))
        }

        fun getUriImage(item: Topping): Int {
            val uri = "@drawable/topping${item.id}"
            val imageResource =
                binding.root.context.resources.getIdentifier(
                    uri,
                    null,
                    binding.root.context.packageName
                )
            return imageResource
        }
    }
}
