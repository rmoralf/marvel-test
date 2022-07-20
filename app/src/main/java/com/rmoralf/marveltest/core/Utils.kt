package com.rmoralf.marveltest.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.platform.UriHandler
import androidx.paging.LoadState
import com.rmoralf.marveltest.core.Constants.TAG

class Utils {
    companion object {
        fun printError(message: String?) {
            Log.d(TAG, message ?: "")
        }

        fun printError(errorState: LoadState.Error) {
            val error = errorState.error
            Log.d(TAG, error.message ?: error.toString())
        }


        fun createSubroute(screen: String, arg: String): String {
            return "$screen/{$arg}"
        }

        fun openUrl(uriHandler: UriHandler, url: String) {
            uriHandler.openUri(url)
        }

        fun showErrorToast(
            context: Context,
            @StringRes stringRes: Int,
            formatArgs: Int? = null
        ) {
            Toast.makeText(
                context,
                context.getString(stringRes, formatArgs),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}