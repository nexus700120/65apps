package com.apps65.modules.stafflist.view

import com.apps65.common.domain.entity.Employee
import com.apps65.modules.stafflist.presenter.StaffListPresenter
import com.apps65.modules.stafflist.presenter.StaffListPresenterImpl
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Vitaly on 20.12.2017.
 */
interface StaffListView: MvpView {
    fun showProgress()
    fun hideProgress()
    fun showError(message: String?)
    fun bind(employeeList: List<Employee>)
}

class StaffListFragment: MvpFragment<StaffListView, StaffListPresenter>() {

    override fun createPresenter(): StaffListPresenter = StaffListPresenterImpl()

}