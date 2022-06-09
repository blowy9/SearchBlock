package com.example.searchblock

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.PopupMenu
import com.google.android.material.navigation.NavigationBarItemView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web_browser.loadUrl("https://www.google.com")

        web_browser.settings.javaScriptEnabled = true // we need to enable javascript
        web_browser.canGoBack()
        web_browser.webViewClient = WebClient(this)

        // Now we need to load an url everytime we search something
        search_btn.setOnClickListener {
            val searchURL = "https://www.google.com/search?q=" + url.text.toString()
            web_browser.loadUrl(searchURL)
        }

        settings_btn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }


//Unfinished
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_1 -> {
                if (event.isShiftPressed) {
                    val searchURL = "https://www.google.com/search?q=" + url.text.toString()
                    web_browser.loadUrl(searchURL)
                }
                true

            }else -> super.onKeyUp(keyCode, event)

        }
    }



    override fun onBackPressed() {
        web_browser.goBack()
    }


    class WebClient internal constructor(private val activity: Activity):WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

    }
}