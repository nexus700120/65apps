package com.apps65.api

import com.apps65.modules.speclist.domain.entity.StaffResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Vitaly on 20.12.2017.
 */
interface Apps65Service {

    companion object {
        val endpoint
            get() = "http://gitlab.65apps.com/"
    }

    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployeeList(): Single<Response<StaffResponse>>
}