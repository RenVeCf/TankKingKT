package com.ipd.tankking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ipd.tankking.R
import com.ipd.tankking.bean.GameInfo
import kotlinx.android.synthetic.main.item_game.view.*

/**
 * Created by jumpbox on 2017/8/31.
 */
class GameAdapter(
    val context: Context,
    private val list: List<GameInfo>?,
    private val itemClick: (info: GameInfo) -> Unit
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list?.size ?: 0


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = list!![position]
        holder.itemView.iv_resourse.setImageResource(info.resId)

        holder.itemView.setOnClickListener {
            itemClick.invoke(info)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}