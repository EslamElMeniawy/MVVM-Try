package elmeniawy.eslam.yts_mvvm.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import elmeniawy.eslam.yts_mvvm.utils.extension.getParentFragment

/**
 * BindingAdapters
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: Fragment? = view.getParentFragment()

    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}