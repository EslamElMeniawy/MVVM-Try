package elmeniawy.eslam.yts_mvvm.utils

import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import elmeniawy.eslam.yts_mvvm.utils.extension.getParentActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import elmeniawy.eslam.yts_mvvm.R

/**
 * BindingAdapters
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

/**
 * Set text view text.
 */
@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

/**
 * Set recycler view adapter.
 */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, @Nullable adapter: RecyclerView.Adapter<*>?) {
    adapter?.let {
        view.adapter = adapter
    }
}

/**
 * Set view visibility.
 */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, @Nullable visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

/**
 * Set image view image using picasso.
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, @Nullable imageUrl: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && imageUrl != null) {
        imageUrl.observe(parentActivity, Observer { value ->
            value?.let {
                if (value.isNotEmpty()) {
                    Picasso.get()
                        .load(value)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .fit()
                        .into(view)
                }
            }
        })
    }
}