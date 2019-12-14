package com.example.college_map.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "-Bookstore\n" +
                "-Foodcourt\n" +
                "-International Office\n" +
                "- University Club"
    }
    val text: LiveData<String> = _text
}