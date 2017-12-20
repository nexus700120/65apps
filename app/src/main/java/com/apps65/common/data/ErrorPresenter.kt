package com.apps65.common.data

import android.content.Context
import com.apps65.R
import java.lang.ref.WeakReference


/**
 * Created by Vitaly on 20.12.2017.
 */
interface ErrorPresenter {
    fun present(t: Throwable?) : String
}

class ErrorPresenterImpl(context: Context?) : ErrorPresenter {

    private val refContext: WeakReference<Context?> = WeakReference(context)

    override fun present(t: Throwable?): String {
        val context = refContext.get() ?: return ""

        if (t == null || t.javaClass == UnknownException::class.java) {
            return context.getString(R.string.common_error_unknown)
        }
        return if (t.javaClass == InternetException::class.java) {
            context.getString(R.string.common_error_internet)
        } else context.getString(R.string.common_error_unknown)
    }
}