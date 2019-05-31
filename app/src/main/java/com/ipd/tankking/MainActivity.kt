package com.ipd.tankking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ipd.tankking.adapter.GameAdapter
import com.ipd.tankking.bean.GameInfo
import com.ipd.tankking.platform.http.HttpUrl
import com.ipd.tankking.ui.BaseActivity
import com.ipd.tankking.ui.WebActivity
import com.ipd.tankking.ui.activity.mine.MineActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun launch(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_main

    override fun initView(bundle: Bundle?) {
    }

    override fun loadData() {
        game_recycler_view.layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false)
        game_recycler_view.adapter = GameAdapter(
            mActivity, arrayListOf(
                GameInfo(R.mipmap.game_1, 0, 10),
                GameInfo(R.mipmap.game_2, 1, 20),
                GameInfo(R.mipmap.game_3, 2, 50),
                GameInfo(R.mipmap.game_4, 3, 100)
            )
        ) {
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.GAME_MATCH)
        }
    }

    override fun initListener() {
        civ_avatar.setOnClickListener {
            //个人中心
            MineActivity.launch(mActivity)
        }
        iv_recharge_vip.setOnClickListener {
            //开通会员
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.RECHARGE_VIP)
        }
        cl_money_store.setOnClickListener {
            //金币商城
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.MONEY_STORE)
        }
        cl_gold_store.setOnClickListener {
            //钻石商城
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.GOLD_STORE)
        }
        iv_lottery.setOnClickListener {
            //抽奖
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.LOTTERY)
        }
        iv_rank.setOnClickListener {
            //排行
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.RANK)
        }
        iv_team.setOnClickListener {
            //战队
            WebActivity.launch(mActivity, WebActivity.URL, HttpUrl.TEAM)
        }
    }

}
