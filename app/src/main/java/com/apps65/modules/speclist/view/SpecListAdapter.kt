package com.apps65.modules.speclist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.apps65.R
import com.apps65.common.domain.entity.Specialty

/**
 * Created by Vitaly on 20.12.2017.
 */
class SpecListAdapter: RecyclerView.Adapter<SpecItemViewHolder>() {

    var specList: List<Specialty> = listOf()

    override fun onBindViewHolder(holder: SpecItemViewHolder, position: Int) {
        holder.bind(specList[position])
    }

    override fun getItemCount(): Int = specList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SpecItemViewHolder(layoutInflater
                .inflate(R.layout.fragment_spec_list_item, parent, false))
    }
}

class SpecItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val specTextView: TextView = itemView.findViewById(R.id.spec_name)

    fun bind(specialty: Specialty) {
        specTextView.text = specialty.name
    }
}