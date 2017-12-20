package com.apps65.common.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Vitaly on 20.12.2017.
 */
class Employee {
    @SerializedName("f_name") var firstName: String? = null
    @SerializedName("l_name") var lastName: String? = null
    @SerializedName("birthday") var birthday: String? = null
    @SerializedName("avatr_url") var avatrUrl: String? = null
    @SerializedName("specialty") var specialtyList: List<Specialty>? = null
}