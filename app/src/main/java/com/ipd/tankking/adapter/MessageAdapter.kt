package com.ipd.tankking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ipd.tankking.R
import com.ipd.tankking.bean.MessageBean

/**
 * Created by jumpbox on 2017/8/31.
 */
class MessageAdapter(
    val context: Context,
    private val list: List<MessageBean>?,
    private val itemClick: (info: MessageBean) -> Unit
) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list?.size ?: 0


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = list!![position]

        holder.itemView.setOnClickListener {
            itemClick.invoke(info)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}