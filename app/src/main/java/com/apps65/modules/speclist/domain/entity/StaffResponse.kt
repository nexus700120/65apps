package com.apps65.modules.speclist.domain.entity

import com.apps65.common.domain.entity.Employee
import com.google.gson.annotations.SerializedName

/**
 * Created by Vitaly on 20.12.2017.
 */
class StaffResponse {
    @SerializedName("response") var employeeList: List<Employee>? = null
}