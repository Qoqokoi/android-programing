package com.qoqokoi.myapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qoqokoi.myapp.databinding.ItemHeaderBinding
import com.qoqokoi.myapp.databinding.ItemInventoryBinding
import com.qoqokoi.myapp.model.DataItem
import com.qoqokoi.myapp.model.Post

class AdvancedInventoryAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(InventoryDiffCallback()) {

    // Konstanta untuk membedakan tipe tampilan (Header vs Item Post)
    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.PostItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                // Header biasanya statis, cukup panggil bind()
                holder.bind()
            }
            is ItemViewHolder -> {
                // Casting DataItem menjadi PostItem untuk mengambil objek Post
                val item = getItem(position) as DataItem.PostItem
                holder.bind(item.post)
            }
        }
    }

    // --- VIEW HOLDERS DENGAN FACTORY PATTERN (Clean Architecture) ---

    class HeaderViewHolder private constructor(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind() {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class ItemViewHolder private constructor(private val binding: ItemInventoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) {
            // "post" adalah nama variabel yang lu definisikan di item_inventory.xml
            binding.post = item
            // executePendingBindings() sangat penting agar Glide segera memuat gambar
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemInventoryBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}

// --- CALLBACK UNTUK MENGHITUNG PERBEDAAN DATA DI BACKGROUND THREAD ---

class InventoryDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        // ID unik membantu DiffUtil mengetahui jika posisi item berubah
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        // Cek apakah isi konten berubah (untuk memicu refresh visual)
        return oldItem == newItem
    }
}
