package elmeniawy.eslam.yts_mvvm.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import elmeniawy.eslam.yts_mvvm.R

/**
 * GeneralUtils
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

/**
 * Hide keyboard.
 *
 * @param activity Activity to get system service with.
 */
fun hideSoftKeyboard(@Nullable activity: Activity?) {
    activity?.let {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        if (activity.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!
                    .windowToken, 0
            )
        }
    }
}

/**
 * Set the progress bar color.
 *
 * @param context     Context to get color with.
 * @param progressBar The progress bar to set color.
 */
fun setProgressBarColor(context: Context?, progressBar: ProgressBar) {
    context?.let {
        progressBar.indeterminateDrawable
            .setColorFilter(
                ContextCompat.getColor(context, R.color.green),
                PorterDuff.Mode.SRC_IN
            )
    }
}

/**
 * Show error message in dialog.
 *
 * @param activity Activity instance to create the dialog with.
 * @param errorMessage The error message to display.
 */
@SuppressLint("InflateParams")
fun showErrorMessageDialog(activity: Activity?, errorMessage: String) {
    activity?.let {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_error, null)
        val messageTV = view.findViewById<AppCompatTextView>(R.id.tv_message)
        messageTV.text = errorMessage
        val okButton = view.findViewById<MaterialButton>(R.id.bt_ok)
        val dialog = builder.setView(view).create()
        okButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}