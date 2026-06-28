package com.qoqokoi.myapp.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(
    imgView: ImageView,
    imgUrl: String?,
) {
    imgUrl?.let {
        val imgUri =
            imgUrl
                .toUri()
                .buildUpon()
                .scheme("https")
                .build()

        Glide
            .with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(android.R.drawable.progress_horizontal)
                    .error(android.R.drawable.stat_notify_error),
            ).into(imgView)
    }
}
