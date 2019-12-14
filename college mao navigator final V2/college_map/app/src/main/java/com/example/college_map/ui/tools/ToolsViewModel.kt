package com.example.college_map.ui.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Engineering, Computer Science and Technology\n" +
                "-Computer Science Department\n" +
                "-Electrical and Computer Engineering Department\n" +
                "-Civil Engineering Department\n" +
                "- Mechanical Engineering Department\n"
    }
    val text: LiveData<String> = _text
}