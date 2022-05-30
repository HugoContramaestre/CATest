package com.example.catest.ui.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.catest.databinding.FragmentWebviewBinding
import com.example.catest.ui.common.BaseFragment
import com.example.catest.ui.common.EventObserver
import com.example.catest.ui.common.WEB_BASE_URL
import com.example.catest.ui.common.extensions.hide
import com.example.catest.ui.common.extensions.show
import com.example.catest.ui.common.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewFragment : BaseFragment() {

    private lateinit var binding: FragmentWebviewBinding
    private val viewModel: WebViewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        with(binding) {
            webview.webViewClient = object: WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.loaderLayout.visible(true)
                    binding.progressBar.show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.loaderLayout.visible(false)
                    binding.progressBar.hide()
                }
            }
            webview.loadUrl(WEB_BASE_URL)
            webview.settings.setSupportZoom(true)
            webview.settings.javaScriptEnabled = true
        }
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        viewModel.loader.observe(viewLifecycleOwner, EventObserver{
            binding.loaderLayout.visible(it)
        })
    }
}