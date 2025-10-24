package io.nekohasekai.sagernet.ui

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.takisoft.preferencex.SimpleMenuPreference
import io.nekohasekai.sagernet.R
import io.nekohasekai.sagernet.utils.Theme
import io.nekohasekai.sagernet.utils.blurBackground
import io.nekohasekai.sagernet.utils.clearBlur

abstract class ThemedActivity(
    private val contentLayoutId: Int = 0,
    open val isDialog: Boolean = false
) : AppCompatActivity(if (contentLayoutId != 0) contentLayoutId else 0) {

    var themeResId = 0
    var uiMode = 0

    private val handler = Handler(Looper.getMainLooper())
    private var isBlurred = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!isDialog) Theme.apply(this) else Theme.applyDialog(this)
        Theme.applyNightTheme()
        super.onCreate(savedInstanceState)
        uiMode = resources.configuration.uiMode

        if (Build.VERSION.SDK_INT >= 35) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { _, insets ->
                val top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
                findViewById<AppBarLayout>(R.id.appbar)?.updatePadding(top = top)
                insets
            }
        }

        setupDialogBlurListener()
    }

    private fun setupDialogBlurListener() {
        window.decorView.viewTreeObserver.addOnWindowFocusChangeListener { hasFocus ->
            if (!hasFocus && !isBlurred) {

                // Cek apakah yang muncul adalah popup / SimpleMenuPreference
                val skipBlur = window.decorView.rootView?.findViewById<View>(
                    com.takisoft.preferencex.R.id.select_dialog_listview
                ) != null

                if (skipBlur) return@addOnWindowFocusChangeListener // skip blur untuk popup

                handler.postDelayed({
                    blurBackground()
                    isBlurred = true
                }, 50) // blur muncul cepat tapi halus
            } else if (hasFocus && isBlurred) {
                handler.postDelayed({
                    clearBlur()
                    isBlurred = false
                }, 100) // blur hilang halus
            }
        }
    }

    override fun setTheme(resId: Int) {
        super.setTheme(resId)
        themeResId = resId
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.uiMode != uiMode) {
            uiMode = newConfig.uiMode
            ActivityCompat.recreate(this)
        }
    }

    fun snackbar(@StringRes resId: Int): Snackbar = snackbar("").setText(resId)
    fun snackbar(text: CharSequence): Snackbar = snackbarInternal(text).apply {
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 10
    }

    internal open fun snackbarInternal(text: CharSequence): Snackbar =
        throw NotImplementedError()
}
