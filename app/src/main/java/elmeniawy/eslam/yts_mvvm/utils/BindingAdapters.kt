package elmeniawy.eslam.yts_mvvm.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import elmeniawy.eslam.yts_mvvm.utils.extension.getParentFragment

/**
 * BindingAdapters
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentFragment: Fragment? = view.getParentFragment()

    if (parentFragment != null && text != null) {
        text.observe(parentFragment, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentFragment: Fragment? = view.getParentFragment()

    if (parentFragment != null && visibility != null) {
        visibility.observe(parentFragment, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}