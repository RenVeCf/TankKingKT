package com.ipd.tankking.model

import com.ipd.tankking.platform.http.Response
import com.ipd.tankking.platform.http.RxScheduler
import rx.Observable

/**
 * Created by jumpbox on 2017/5/24.
 */
open class BasicModel : BaseModel() {

    fun <T> getNormalRequestData(observable: Observable<T>, response: Response<T>) {
        observable.compose(RxScheduler.applyScheduler<T>()).subscribe(response)
    }

}