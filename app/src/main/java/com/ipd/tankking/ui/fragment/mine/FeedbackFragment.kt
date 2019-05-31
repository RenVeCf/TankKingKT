package com.ipd.tankking.ui.fragment.mine

import android.os.Bundle
import com.ipd.tankking.R
import com.ipd.tankking.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_feedback.view.*

class FeedbackFragment:BaseFragment(){
    override fun getBaseLayout(): Int = R.layout.fragment_feedback

    override fun initView(bundle: Bundle?) {
    }

    override fun loadData() {
    }

    override fun initListener() {
        mRootView?.iv_commit?.setOnClickListener {
            toastShow(true,"感谢您的反馈!")
        }
    }

}