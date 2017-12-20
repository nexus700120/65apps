package com.apps65.common.data

import com.apps65.api.Apps65Api
import com.apps65.api.Apps65Service
import io.reactivex.Single
import retrofit2.Response
import java.io.IOException


/**
 * Created by Vitaly on 20.12.2017.
 */
class InternetException: Exception()
class UnknownException: Exception()

open class NetworkInteractor {

    val service: Apps65Service
    get() = Apps65Api.service

    fun <T> wrap(single: Single<Response<T>>): Single<T> {
        return single.flatMap({ tResponse ->
            val body = tResponse.body()
            if (body != null && tResponse.isSuccessful) {
                return@flatMap Single.just(body)
            }
            Single.error<T>(UnknownException())
        }).onErrorResumeNext({ throwable -> Single.error(clarifyError(throwable)) })
    }

    private fun clarifyError(t: Throwable?): Throwable {
        t ?: return UnknownException()
        return if (t is IOException) {
            InternetException()
        } else UnknownException()
    }
}