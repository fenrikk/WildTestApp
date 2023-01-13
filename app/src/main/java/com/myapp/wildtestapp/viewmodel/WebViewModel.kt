package com.myapp.wildtestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference

class WebViewModel(
    private val database: DatabaseReference
) : ViewModel() {
    fun setLink(link: String) {
        database.child("LINK").setValue(link)
    }
}