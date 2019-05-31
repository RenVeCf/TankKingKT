package com.ipd.tankking.widget

import android.content.Context
import android.view.View
import android.view.animation.Animation
import com.ipd.tankking.R
import com.ipd.tankking.ui.activity.account.LoginActivity
import razerdp.basepopup.BasePopupWindow

class ExitPopup : BasePopupWindow {

    constructor(context: Context?) : super(context)

    init {
        findViewById(R.id.iv_confirm).setOnClickListener {
            dismiss()
            LoginActivity.launch(context)
        }
    }

    override fun onCreatePopupView(): View {
        return createPopupById(R.layout.popup_exit)
    }

    override fun getClickToDismissView(): View {
        return this.findViewById(R.id.iv_close)
    }

    override fun initShowAnimation(): Animation? = null


    override fun initAnimaView(): View? = null


}