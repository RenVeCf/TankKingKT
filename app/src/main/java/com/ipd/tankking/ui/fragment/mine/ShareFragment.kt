package com.ipd.tankking.ui.fragment.mine

import android.os.Bundle
import com.ipd.tankking.R
import com.ipd.tankking.ui.BaseFragment
import com.ipd.tankking.widget.SharePopup
import kotlinx.android.synthetic.main.fragment_share.view.*

class ShareFragment : BaseFragment() {
    override fun getBaseLayout(): Int = R.layout.fragment_share

    override fun initView(bundle: Bundle?) {
    }

    override fun loadData() {
    }

    override fun initListener() {
        mRootView?.tv_share_now?.setOnClickListener {
            SharePopup(mActivity).showPopupWindow()
        }
    }

}