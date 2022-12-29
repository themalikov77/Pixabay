package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay.databinding.ItemImageBinding
import com.example.pixabay.model.ImageModel

class PixaAdapter() : Adapter<PixaViewHolder>() {
    private var list: ArrayList<ImageModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addList(newList: List<ImageModel>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class PixaViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root) {
    fun onBind(model: ImageModel) {
        binding.pixaImage.load(model.largeImageURL)
    }

}
