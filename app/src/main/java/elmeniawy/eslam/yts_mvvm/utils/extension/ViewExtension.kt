package elmeniawy.eslam.yts_mvvm.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * ViewExtension
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context

    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }

        context = context.baseContext
    }

    return null
}

fun View.getParentFragment(): Fragment? {
    var context = this.context

    while (context is ContextWrapper) {
        if (context is Fragment) {
            return context
        }

        context = context.baseContext
    }

    return null
}