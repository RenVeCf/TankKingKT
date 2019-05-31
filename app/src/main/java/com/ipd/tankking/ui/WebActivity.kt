package com.ipd.tankking.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ipd.tankking.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    companion object {
        val URL = 0
        val HTML = 1

        fun launch(from: Activity, flag: Int, data: String) {
            val intent = Intent(from, WebActivity::class.java)
            intent.putExtra("flag", flag)
            intent.putExtra("data", data)
            from.startActivity(intent)
        }
    }


    override fun getBaseLayout() = R.layout.activity_web

    override fun initView(bundle: Bundle?) {
        progress_bar.isIndeterminate = true
    }

    override fun loadData() {
        web_view.settings.useWideViewPort = true
        web_view.settings.loadWithOverviewMode = true
        web_view.settings.javaScriptEnabled = true

        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress < 100) {
                    progress_bar.visibility = View.VISIBLE
                } else if (newProgress >= 100) {
                    progress_bar.visibility = View.GONE
                }
                progress_bar.progress = newProgress
            }

        }

        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val flag = intent.getIntExtra("flag", 0)
        val data = intent.getStringExtra("data")
        if (flag == URL) {
            if (!TextUtils.isEmpty(data)) {
                web_view.loadUrl(data)
            }
        } else {
            if (!TextUtils.isEmpty(data)) {
                web_view.loadData(data, "text/html; charset=UTF-8", null)
            }
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

    }

    override fun onPause() {
        super.onPause()
        if (web_view != null) {
            web_view.onPause()
            web_view.pauseTimers()
        }
    }

    override fun onResume() {
        super.onResume()
        if (web_view != null) {
            web_view.resumeTimers()
            web_view.onResume()
        }
    }


    override fun onDestroy() {
        if (web_view != null) {
            web_view.destroy()
        }
        super.onDestroy()
    }
}