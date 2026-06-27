package com.qoqokoi.myapp.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.util.Locale

@BindingAdapter("formattedRupiah")
fun TextView.setFormattedRupiah(price: Int) {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    this.text = numberFormat.format(price.toLong())
}
