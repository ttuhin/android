package com.tuhin.freefalldetector

import com.tuhin.skfreefall.data.model.FreeFallData

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout

import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.layout_fall_item.view.*

class FreeFallAdapter(private val context: Context, private val data: MutableList<FreeFallData>) :
    RecyclerView.Adapter<FreeFallAdapter.FreeFallHolder>() {

    class FreeFallHolder(val freFallItemContainer: LinearLayout) :
        RecyclerView.ViewHolder(freFallItemContainer)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FreeFallHolder {
        val fallItemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.layout_fall_item,
                parent,
                false
            ) as LinearLayout

        return FreeFallHolder(fallItemView)
    }

    override fun onBindViewHolder(holder: FreeFallHolder, position: Int) {
        if (data.isNotEmpty()) {
            val fallData: String = String.format(context.getString(R.string.event_info),
                data[position].timeStamp,
                data[position].duration
            )
            holder.freFallItemContainer.fallText.text = fallData
        }
    }

    override fun getItemCount() = data.size

    fun updateData(updatedData: List<FreeFallData>) {
        updatedData.run {
            data.clear()
            data.addAll(updatedData)
            notifyDataSetChanged()
        }
    }

}
