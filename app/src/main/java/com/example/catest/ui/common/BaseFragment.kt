package com.example.catest.ui.common

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.catest.ui.SharedViewModel
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment(): ScopeFragment() {

    internal val sharedViewModel: SharedViewModel by sharedViewModel()
    private val navController: NavController by lazy { Navigation.findNavController(requireView()) }

    fun navigate(navDirections: NavDirections) {
        navController.navigate(navDirections)
    }

    fun navigate(resId: Int, args: Bundle? = null) {
        navController.navigate(resId, args)
    }

    fun goBack() {
        if (!navController.popBackStack()) {
            requireActivity().finish()
        }
    }

    fun enableOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, true) {
            // Handle the back button event
            if (!onBackPress()) {
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }
    }

    open fun onBackPress() = false

    fun initViewModelObservers(viewModel: BaseViewModel) {
        viewModel.feedbackUser.observe(viewLifecycleOwner, EventObserver(this::onFeedback))
        viewModel.loading.observe(viewLifecycleOwner, EventObserver(this::onLoading))
    }

    open fun onFeedback(feedbackUser: FeedbackUser) =
        sharedViewModel.handleFeedbackUser(feedbackUser)

    open fun onLoading(loading: Boolean) {
        sharedViewModel.handleLoading(loading)
    }
}