package com.example.college_map.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "- EveryTable\n" +
                "- SmartStart\n" +
                "- Cafe 47\n" +
                "-Graduate Resource Center"
    }
    val text: LiveData<String> = _text
}