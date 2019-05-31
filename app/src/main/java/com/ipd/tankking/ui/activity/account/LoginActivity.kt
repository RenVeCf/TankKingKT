package com.ipd.tankking.ui.activity.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.ipd.jumpbox.jumpboxlibrary.utils.LogUtils
import com.ipd.tankking.MainActivity
import com.ipd.tankking.R
import com.ipd.tankking.presenter.AccountPresenter
import com.ipd.tankking.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), AccountPresenter.ILoginView, TextWatcher {

    companion object {
        fun launch(activity: Context) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_login


    private var mPresenter: AccountPresenter<AccountPresenter.ILoginView>? = null
    override fun onViewAttach() {
        super.onViewAttach()
        mPresenter = AccountPresenter()
        mPresenter?.attachView(this, this)
    }

    override fun onViewDetach() {
        super.onViewDetach()
        mPresenter?.detachView()
        mPresenter = null
    }


    override fun initView(bundle: Bundle?) {
    }

    override fun loadData() {
    }

    override fun initListener() {
        et_phone.addTextChangedListener(this)
        et_password.addTextChangedListener(this)

        iv_register.setOnClickListener { RegisterActivity.launch(mActivity) }//注册
        tv_forget.setOnClickListener { ForgetPwdActivity.launch(mActivity) }//忘记密码
        iv_login.setOnClickListener {
            val phone = et_phone.text.toString().trim()
            val password = et_password.text.toString().trim()
            mPresenter?.login(phone, password)
        }

        iv_wechat.setOnClickListener {
            mPresenter?.wechatLogin()
        }
    }

    override fun thirdAuthSuccess(type: Int, token: String, userLogo: String, username: String) {
        LogUtils.e("tag", "$token-$userLogo-$username")
        mPresenter?.thirdLogin(type, token, userLogo, username)
    }

    override fun thirdNeedBinding(type: Int, openId: String, logo: String, nickname: String) {
        BindingPhoneActivity.launch(mActivity)
    }

    override fun thirdAuthCancel() {
        toastShow("取消授权")
    }

    override fun thirdAuthError(errMsg: String) {
        toastShow(errMsg)
    }

    override fun loginSuccess() {
        toastShow(true, "登录成功")
        MainActivity.launch(mActivity)
        finish()
    }

    override fun loginFail(errMsg: String) {
        toastShow(errMsg)
    }


    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        val phone = et_phone.text.toString().trim()
//        val password = et_password.text.toString().trim()
//        iv_login.isEnabled = CommonUtils.isMobileNO(phone) &&
//                CommonUtils.passwordIsLegal(password)
    }

    override fun onDestroy() {
        super.onDestroy()
//        AlipayUtils.getInstance().release()
    }

}