package com.apps65.modules.speclist.domain.interactor

import com.apps65.common.data.NetworkInteractor
import com.apps65.modules.speclist.domain.entity.StaffResponse
import io.reactivex.Single

/**
 * Created by Vitaly on 20.12.2017.
 */
interface SpecListInteractor {
    fun getStaffInfo(): Single<StaffResponse>
}

class SpecListInteractorImpl: NetworkInteractor(), SpecListInteractor {
    override fun getStaffInfo(): Single<StaffResponse> {
        return wrap(service.getEmployeeList())
    }
}