package com.apps65.common.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Vitaly on 20.12.2017.
 */
class Specialty {
    @SerializedName("specialty_id") var specialtyId: Int? = null
    @SerializedName("name") var name: String? = null
}