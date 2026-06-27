package com.qoqokoi.myapp.model

sealed class DataItem {
    abstract val id: Int

    data class BarangItem(
        val barang: Barang,
    ) : DataItem() {
        override val id = barang.id
    }

    object Header : DataItem() {
        override val id = Int.MIN_VALUE
    }
}
