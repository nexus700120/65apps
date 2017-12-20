package com.apps65.modules.speclist.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps65.R
import com.apps65.common.data.ErrorPresenterImpl
import com.apps65.common.domain.entity.Employee
import com.apps65.common.domain.entity.Specialty
import com.apps65.modules.speclist.domain.interactor.SpecListInteractorImpl
import com.apps65.modules.speclist.presenter.SpecListPresenter
import com.apps65.modules.speclist.presenter.SpecListPresenterImpl
import com.apps65.widget.ProgressView
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Vitaly on 20.12.2017.
 */
interface SpecListView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun showError(message: String?)
    fun bind(specList: List<Specialty>)
}

class SpecListFragment : MvpFragment<SpecListView, SpecListPresenter>(), SpecListView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressView: ProgressView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_spec_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressView = view.findViewById(R.id.progress_view)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(view.context,
                DividerItemDecoration.VERTICAL))
        recyclerView.adapter = SpecListAdapter()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewRestored()
    }

    override fun createPresenter(): SpecListPresenter = SpecListPresenterImpl(
            interactor = SpecListInteractorImpl(),
            errorPresenter = ErrorPresenterImpl(activity)
    )

    override fun hideProgress() {
        progressView.hideProgress()
    }

    override fun showError(message: String?) {
        progressView.error(message) { presenter.retry() }
    }

    override fun bind(specList: List<Specialty>) {
        val adapter = recyclerView.adapter as SpecListAdapter
        adapter.specList = specList
        adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressView.showProgress()
    }
}

