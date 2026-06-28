package com.qoqokoi.myapp.model

sealed class DataItem {
    abstract val id: String

    data class PostItem(
        val post: Post,
    ) : DataItem() {
        override val id = post.url
    }

    object Header : DataItem() {
        override val id = "HEADER_ID"
    }
}
