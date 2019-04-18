package elmeniawy.eslam.yts_mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.CancellationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * NetworkUtils
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */

/**
 * Check if connected to Internet or not.
 *
 * @param context: Context instance to use in getting connectivity service.
 * @return A Boolean representing if connected or not or false if error.
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)

    return if (connectivityManager is ConnectivityManager) {
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected
    } else {
        false
    }
}

/**
 * Check if the provided throwable is caused by network issue.
 *
 * @param throwable: Throwable instance to check.
 * @return A Boolean representing if throwable caused by network issue.
 */
fun isNetworkThrowable(throwable: Throwable): Boolean = (throwable is ConnectException
        || throwable is SocketTimeoutException
        || throwable is UnknownHostException
        || throwable is TimeoutException)

/**
 * Check if the provided exception is caused by cancelling job.
 *
 * @param exception: Exception instance to check.
 * @return A Boolean representing if exception caused by cancelling job.
 */
fun isCancellationException(exception: Exception): Boolean = exception is CancellationException