package com.example.catest.ui.webview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catest.ui.common.Event
import com.example.catest.ui.common.postValueEvent

class WebViewViewModel : ViewModel() {

    private val _loader = MutableLiveData<Event<Boolean>>()
    val loader: LiveData<Event<Boolean>> = _loader

    fun setLoaderState(state: Boolean) {
        _loader.postValueEvent(state)
    }
}