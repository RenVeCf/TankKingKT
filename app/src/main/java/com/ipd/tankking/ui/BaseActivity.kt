package com.ipd.tankking.ui

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.ipd.jumpbox.jumpboxlibrary.utils.ToastCommom
import com.ipd.tankking.platform.global.GlobalApplication
import kotlin.properties.Delegates


/**
 * Created by jumpbox on 2017/5/19.
 */
abstract class BaseActivity : FragmentActivity() {
    protected var mActivity: Activity by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityScreenOrientation()
        setContentView(getBaseLayout())
        mActivity = this
        //设置屏幕显示方向
        onViewAttach()

        initBaseLayout()
        initView(bundle = savedInstanceState)
        loadData()
        initListener()
    }


    override fun onDestroy() {
        super.onDestroy()
        onViewDetach()
    }

    /**
     * 屏幕显示方向
     */
    open protected fun setActivityScreenOrientation() {
//        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }


    protected abstract fun getBaseLayout(): Int
    open protected fun initBaseLayout() {}
    protected open fun onViewAttach() {}
    protected open fun onViewDetach() {}
    protected abstract fun initView(bundle: Bundle?)
    protected abstract fun loadData()
    protected abstract fun initListener()


    fun toastShow(errmsg: String) = toastShow(false, errmsg)

    fun toastShow(success: Boolean, errmsg: String) {
        ToastCommom.getInstance().show(GlobalApplication.mContext, success, errmsg)
    }


}
