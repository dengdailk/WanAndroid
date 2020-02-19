package com.study.wanandroid.wechat.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.api.BaseObserver
import com.study.wanandroid.common.article.data.ArticleRepository


/**
 * @author dengdai
 * @description
 */
class WeChatRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getWeChatData(liveData: MutableLiveData<BaseResponse<List<WeChatNameRsp>>>) {
        apiService.getWeChat().execute(BaseObserver(liveData, loadState, this))
    }

    fun getWeChatListData(
        uid: Int,
        page: Int,
        liveData: MutableLiveData<BaseResponse<WeChatListRsp>>
    ) {
        apiService.getWeChatList(uid, page).execute(BaseObserver(liveData, loadState, this))
    }

}