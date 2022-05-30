package com.example.catest

import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.catest.databinding.ActivityMainBinding
import com.example.catest.ui.SharedViewModel
import com.example.catest.ui.common.BaseActivity
import com.example.catest.ui.common.EventObserver
import com.example.catest.ui.common.extensions.setupWithNavController
import com.example.catest.ui.common.extensions.showSnackbarSimple
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private val sharedView: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        initObservers()
    }

    private fun initObservers(){
        sharedView.feedbackUser.observe(this, EventObserver{
            binding.root.showSnackbarSimple(it.messageResId, it.error)
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNavigation

        val navGraphIds = listOf(
            R.navigation.weather_nav_graph,
            R.navigation.webview_nav_graph,
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_nav_host_fragment,
            intent = intent
        )

        binding.bottomNavigation.menu.forEach {
            val view = bottomNavigationView.findViewById<View>(it.itemId)
            view.setOnLongClickListener {
                true
            }
        }
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}