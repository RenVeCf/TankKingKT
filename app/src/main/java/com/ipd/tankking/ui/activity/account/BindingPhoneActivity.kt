package com.ipd.tankking.ui.activity.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.ipd.tankking.MainActivity
import com.ipd.tankking.R
import com.ipd.tankking.presenter.AccountPresenter
import com.ipd.tankking.ui.BaseActivity
import com.ipd.tankking.utils.TimeCountHelper
import kotlinx.android.synthetic.main.activity_binding_phone.*

class BindingPhoneActivity : BaseActivity(), AccountPresenter.IBindingPhoneView, TextWatcher {

    companion object {
        fun launch(activity: Activity, type: Int, openId: String, logo: String, nickname: String) {
            val intent = Intent(activity, BindingPhoneActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("openId", openId)
            intent.putExtra("logo", logo)
            intent.putExtra("nickname", nickname)
            activity.startActivity(intent)
        }

        fun launch(activity: Activity) {
            val intent = Intent(activity, BindingPhoneActivity::class.java)
            intent.putExtra("needSkip", false)
            activity.startActivity(intent)
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_binding_phone


    private val mType by lazy { intent.getIntExtra("type", 0) }
    private val mOpenId by lazy { intent.getStringExtra("openId") }
    private val mLogo by lazy { intent.getStringExtra("logo") }
    private val mNickname by lazy { intent.getStringExtra("nickname") }
    private var mPresenter: AccountPresenter<AccountPresenter.IBindingPhoneView>? = null
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
        et_sms.addTextChangedListener(this)

        tv_get_sms.setOnClickListener {
            val phone = et_phone.text.toString().trim()
            mPresenter?.getSmsCode(phone, "1")
        }
        iv_binding.setOnClickListener {
            val phone = et_phone.text.toString().trim()
            val code = et_sms.text.toString().trim()
            mPresenter?.bindingPhone(mType, phone, code, mOpenId, mLogo, mNickname)
        }

    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        val phone = et_phone.text.toString().trim()
//        val sms = et_sms.text.toString().trim()
//        iv_binding.isEnabled = CommonUtils.isMobileNO(phone) && sms.length >= 4
    }

    private fun initCodeBtn() {
        tv_get_sms.isEnabled = true
        tv_get_sms.text = "获取验证码"
    }

    private var mTimeCountHelper: TimeCountHelper? = null
    override fun getSmsCodeSuccess() {
        toastShow(true, "已发送验证码到您的手机")
        tv_get_sms.isEnabled = false

        if (mTimeCountHelper == null) {
            mTimeCountHelper = TimeCountHelper.newInstance().setTimeCountListener(
                object : TimeCountHelper.TimeCountListener {
                    override fun onChange(aLong: Long) {
                        tv_get_sms.text = "${aLong}秒"
                    }

                    override fun onFinish() {
                        initCodeBtn()
                    }
                }
            )
        }

        mTimeCountHelper?.start()
    }

    override fun getSmsCodeFail(errMsg: String) {
        initCodeBtn()
        toastShow(errMsg)
    }

    override fun bindingSuccess() {
        toastShow(true, "登录成功")
        val intent = Intent(mActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun bindingFail(errMsg: String) {
        toastShow(errMsg)
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimeCountHelper?.release()
    }

}