package com.example.college_map.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "-Tutorial Center\n" +
                "-ITS help desk"
    }
    val text: LiveData<String> = _text
}