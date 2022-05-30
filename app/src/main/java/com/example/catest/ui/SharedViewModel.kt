package com.example.catest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catest.ui.common.Event
import com.example.catest.ui.common.FeedbackUser
import com.example.catest.ui.common.BaseViewModel

class SharedViewModel() : BaseViewModel() {

    private val _homeNavigate = MutableLiveData<Event<Int>>()
    val homeNavigate: LiveData<Event<Int>> get() = _homeNavigate
    //    private val _reloadList = MutableLiveData<Event<Unit>>()
//    val reloadList: LiveData<Event<Unit>> get() = _reloadList

    fun handleFeedbackUser(feedbackUser: FeedbackUser) = sendFeedbackUser(feedbackUser)

    fun handleLoading(loading: Boolean) = toggleLoading(loading)

    fun forceListToReload() {
//        _reloadList.postValueEvent(Unit)
    }
}