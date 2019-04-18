package elmeniawy.eslam.yts_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.utils.hideSoftKeyboard
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    //region Variables
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    //endregion

    //region Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup navigation.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus

        if ((ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE)
            && v is EditText
            && !v.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrCoords = IntArray(2)
            v.getLocationOnScreen(scrCoords)
            val x = ev.rawX + v.left - scrCoords[0]
            val y = ev.rawY + v.top - scrCoords[1]

            if (x < v.left || x > v.right || y < v.top || y > v.bottom) {
                hideSoftKeyboard(this)
            }
        }

        return super.dispatchTouchEvent(ev)
    }
    //endregion

    //region Injection
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    //endregion
}
