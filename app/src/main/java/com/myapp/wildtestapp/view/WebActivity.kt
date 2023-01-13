package com.myapp.wildtestapp.view

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.FileChooserParams
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapp.wildtestapp.databinding.ActivityWebBinding
import com.myapp.wildtestapp.other.EXTRA_NAME
import com.myapp.wildtestapp.other.FILECHOOSER_RESULTCODE
import com.myapp.wildtestapp.other.REQUEST_SELECT_FILE
import com.myapp.wildtestapp.viewmodel.WebViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding
    private val model: WebViewModel by viewModel()

    private var mUploadMessage: ValueCallback<Uri?>? = null
    var uploadMessage: ValueCallback<Array<Uri>>? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val link = intent.getStringExtra(EXTRA_NAME) ?: ""
        model.setLink(link)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(link)
            webViewClient = myWebClient()
            webChromeClient = object : WebChromeClient() {

                override fun onShowFileChooser(
                    mWebView: WebView,
                    filePathCallback: ValueCallback<Array<Uri>>,
                    fileChooserParams: FileChooserParams
                ): Boolean {
                    if (uploadMessage != null) {
                        uploadMessage!!.onReceiveValue(null)
                        uploadMessage = null
                    }
                    uploadMessage = filePathCallback
                    var intent: Intent? = null
                    intent = fileChooserParams.createIntent()
                    try {
                        startActivityForResult(intent, REQUEST_SELECT_FILE)
                    } catch (e: ActivityNotFoundException) {
                        uploadMessage = null
                        return false
                    }
                    return true
                }

                protected fun openFileChooser(
                    uploadMsg: ValueCallback<Uri?>?,
                    acceptType: String?,
                    capture: String?
                ) {
                    mUploadMessage = uploadMsg
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.addCategory(Intent.CATEGORY_OPENABLE)
                    intent.type = "image/*"
                    startActivityForResult(
                        Intent.createChooser(intent, "File Browser"),
                        FILECHOOSER_RESULTCODE
                    )
                }

                protected fun openFileChooser(uploadMsg: ValueCallback<Uri?>?) {
                    mUploadMessage = uploadMsg
                    val i = Intent(Intent.ACTION_GET_CONTENT)
                    i.addCategory(Intent.CATEGORY_OPENABLE)
                    i.type = "image/*"
                    startActivityForResult(
                        Intent.createChooser(i, "File Chooser"),
                        FILECHOOSER_RESULTCODE
                    )
                }
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null) return
                uploadMessage!!.onReceiveValue(FileChooserParams.parseResult(resultCode, intent))
                uploadMessage = null
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage) return
            val result =
                if (intent == null || resultCode != RESULT_OK) null else intent.data
            mUploadMessage!!.onReceiveValue(result)
            mUploadMessage = null
        }
    }

    private inner class myWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "You cant do it", Toast.LENGTH_LONG).show()
    }
}