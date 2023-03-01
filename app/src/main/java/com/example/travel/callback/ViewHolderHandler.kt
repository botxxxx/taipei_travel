package com.example.travel.callback

interface ViewHolderHandler<T> {
    fun bind(item: T?)
}