package br.com.oxxynet.chatoxxy.util

import android.content.Context
import android.widget.Toast
import android.support.annotation.StringRes



class AppUtils {

    companion object {

        fun showToast(context: Context, @StringRes text: Int, isLong: Boolean) {
            showToast(context, context.getString(text), isLong)
        }

        fun showToast(context: Context, text: String, isLong: Boolean) {
            Toast.makeText(context, text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
        }
    }

}