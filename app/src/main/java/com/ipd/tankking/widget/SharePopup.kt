package com.ipd.tankking.widget

import android.content.Context
import android.view.View
import android.view.animation.Animation
import com.ipd.jumpbox.jumpboxlibrary.utils.ToastCommom
import com.ipd.tankking.R
import com.ipd.tankking.platform.global.GlobalApplication
import razerdp.basepopup.BasePopupWindow

class SharePopup : BasePopupWindow {

    constructor(context: Context?) : super(context)

    init {
        findViewById(R.id.ll_share_friend).setOnClickListener {
            dismiss()
            ToastCommom.getInstance().show(GlobalApplication.mContext, true, "分享成功")
        }
        findViewById(R.id.ll_share_moment).setOnClickListener {
            dismiss()
            ToastCommom.getInstance().show(GlobalApplication.mContext, true, "分享成功")
        }
    }

    override fun onCreatePopupView(): View {
        return createPopupById(R.layout.popup_share)
    }

    override fun getClickToDismissView(): View {
        return this.findViewById(R.id.iv_close)
    }

    override fun initShowAnimation(): Animation? = null


    override fun initAnimaView(): View? = null


}