package com.ipd.tankking.ui.activity.mine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.widget.LinearLayout
import com.ipd.tankking.R
import com.ipd.tankking.ui.BaseActivity
import com.ipd.tankking.ui.fragment.mine.*
import com.ipd.tankking.widget.ExitPopup
import kotlinx.android.synthetic.main.activity_mine.*

class MineActivity : BaseActivity() {
    companion object {
        fun launch(activity: Activity) {
            val intent = Intent(activity, MineActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getBaseLayout(): Int = R.layout.activity_mine

    override fun initView(bundle: Bundle?) {
        changePage(0)
    }

    override fun loadData() {
    }


    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
        tabs.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                changePage(index)
            }
        }
        ll_exit.setOnClickListener {
            ExitPopup(mActivity).showPopupWindow()
        }
    }


    private val tabs: Array<LinearLayout> by lazy {
        arrayOf(
            ll_user_info,
            ll_wallet,
            ll_msg,
            ll_share,
            ll_about,
            ll_feedback
        )
    }


    private fun changePage(pos: Int) {
        setTabChecked(pos)
        setTabSelection(pos)
    }

    private fun setTabChecked(pos: Int) {
        tabs.forEachIndexed { index, layout ->
            layout.isSelected = pos == index
            layout.getChildAt(0).isSelected = pos == index
        }
    }


    private var userInfoFragment: UserInfoFragment? = null
    private var walletFragment: WalletFragment? = null
    private var messageFragment: MessageFragment? = null
    private var shareFragment: ShareFragment? = null
    private var aboutFragment: AboutFragment? = null
    private var feedbackFragment: FeedbackFragment? = null

    /**
     * 选中的页面
     *
     * @param position
     */
    private fun setTabSelection(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> if (userInfoFragment == null) {
                userInfoFragment = UserInfoFragment()
                transaction.add(R.id.fl_container, userInfoFragment)
            } else {
                transaction.show(userInfoFragment)
            }
            1 -> if (walletFragment == null) {
                walletFragment = WalletFragment()
                transaction.add(R.id.fl_container, walletFragment)
            } else {
                transaction.show(walletFragment)
            }
            2 -> if (messageFragment == null) {
                messageFragment = MessageFragment()
                transaction.add(R.id.fl_container, messageFragment)
            } else {
                transaction.show(messageFragment)
            }
            3 -> if (shareFragment == null) {
                shareFragment = ShareFragment()
                transaction.add(R.id.fl_container, shareFragment)
            } else {
                transaction.show(shareFragment)
            }
            4 -> if (aboutFragment == null) {
                aboutFragment = AboutFragment()
                transaction.add(R.id.fl_container, aboutFragment)
            } else {
                transaction.show(aboutFragment)

            }
            5 -> if (feedbackFragment == null) {
                feedbackFragment = FeedbackFragment()
                transaction.add(R.id.fl_container, feedbackFragment)
            } else {
                transaction.show(feedbackFragment)

            }
        }
        // 提交
        transaction.commit()
    }

    /**
     * 隐藏所有的页面
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        if (userInfoFragment != null) {
            transaction.hide(userInfoFragment)
        }
        if (walletFragment != null) {
            transaction.hide(walletFragment)
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment)
        }
        if (shareFragment != null) {
            transaction.hide(shareFragment)
        }
        if (aboutFragment != null) {
            transaction.hide(aboutFragment)
        }
        if (feedbackFragment != null) {
            transaction.hide(feedbackFragment)
        }
    }


    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment == null) return
        when (fragment) {
            is UserInfoFragment -> userInfoFragment = fragment
            is WalletFragment -> walletFragment = fragment
            is MessageFragment -> messageFragment = fragment
            is ShareFragment -> shareFragment = fragment
            is AboutFragment -> aboutFragment = fragment
            is FeedbackFragment -> feedbackFragment = fragment
        }
    }

}