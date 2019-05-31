package com.ipd.tankking.ui.fragment.mine

import android.support.v7.widget.LinearLayoutManager
import com.ipd.tankking.adapter.MessageAdapter
import com.ipd.tankking.bean.BaseResult
import com.ipd.tankking.bean.MessageBean
import com.ipd.tankking.ui.ListFragment
import rx.Observable

class MessageFragment : ListFragment<BaseResult<List<MessageBean>>, MessageBean>() {


    override fun loadListData(): Observable<BaseResult<List<MessageBean>>> {
        return Observable.create {
            val list = arrayListOf<MessageBean>()
            for (index in 0 until 10) {
                list.add(MessageBean())
            }
            it.onNext(BaseResult(0, list))
            it.onCompleted()
        }
    }


    override fun isNoMoreData(result: BaseResult<List<MessageBean>>): Int {
        if (page == INIT_PAGE && (result.data == null || result.data.isEmpty())) {
            return EMPTY_DATA
        } else if (result.data == null || result.data.isEmpty()) {
            return NO_MORE_DATA
        }
        return NORMAL
    }

    private var mAdapter: MessageAdapter? = null
    override fun setOrNotifyAdapter() {
        if (mAdapter == null) {
            mAdapter = MessageAdapter(context, data) {

            }
            recycler_view.layoutManager = LinearLayoutManager(context)
            recycler_view.adapter = mAdapter
        } else {
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun addData(isRefresh: Boolean, result: BaseResult<List<MessageBean>>) {
        data?.addAll(result?.data ?: arrayListOf())
    }
}
