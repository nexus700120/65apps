package com.apps65.modules.stafflist.presenter

import com.apps65.modules.stafflist.view.StaffListView
import com.apps65.mvp.BaseMvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter

/**
 * Created by Vitaly on 20.12.2017.
 */
interface StaffListPresenter : MvpPresenter<StaffListView>

class StaffListPresenterImpl : BaseMvpPresenter<StaffListView>(), StaffListPresenter