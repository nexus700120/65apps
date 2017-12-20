package com.apps65.modules.speclist.presenter

import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import com.apps65.common.data.ErrorPresenter
import com.apps65.common.domain.entity.Employee
import com.apps65.common.domain.entity.Specialty
import com.apps65.modules.speclist.domain.entity.StaffResponse
import com.apps65.modules.speclist.domain.interactor.SpecListInteractor
import com.apps65.modules.speclist.view.SpecListView
import com.apps65.mvp.BaseMvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vitaly on 20.12.2017.
 */
interface SpecListPresenter: MvpPresenter<SpecListView> {
    fun retry()
    fun onViewRestored()
}

class SpecListPresenterImpl(
        private val interactor: SpecListInteractor,
        private val errorPresenter: ErrorPresenter
): BaseMvpPresenter<SpecListView>(), SpecListPresenter {

    override fun onViewRestored() { loadData() }

    override fun retry() { loadData() }

    private fun loadData() {
        ifViewAttached { it.showProgress() }

        subscribe(interactor.getStaffInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { modifyResponse(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onEmployeeListReceived(it) }, { onEmployeeListFailed(it) }))
    }

    @WorkerThread
    private fun modifyResponse(response: StaffResponse): Pair<List<Specialty>, List<Employee>> {
        var employeeList = response.employeeList ?: listOf()
        employeeList.forEach {
            it.firstName = it.firstName?.toLowerCase()?.capitalize()
            it.lastName = it.lastName?.toLowerCase()?.capitalize()
        }
        employeeList = employeeList.sortedBy { it.firstName }

        val specList = mutableListOf<Specialty>()
        response.employeeList?.forEach {
            it.specialtyList?.forEach { spec ->
                if (!spec.name.isNullOrEmpty() && spec.specialtyId ?: -1 > 0
                        && specList.firstOrNull { it.specialtyId == spec.specialtyId} == null) {
                    specList.add(spec)
                }
            }
        }
        specList.sortBy { it.name }
        return Pair(specList, employeeList)
    }

    @UiThread
    private fun onEmployeeListReceived(data: Pair<List<Specialty>, List<Employee>>) {
        ifViewAttached {
            it.hideProgress()
            it.bind(data.first)
        }
    }

    @UiThread
    private fun onEmployeeListFailed(t: Throwable) {
        ifViewAttached { it.showError(errorPresenter.present(t)) }
    }
}