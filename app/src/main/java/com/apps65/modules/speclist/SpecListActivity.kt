package com.apps65.modules.speclist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apps65.R
import com.apps65.modules.speclist.view.SpecListFragment

/**
 * Created by Vitaly on 20.12.2017.
 */
class SpecListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        savedInstanceState ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SpecListFragment())
                    .commitNowAllowingStateLoss()
        }
    }
}