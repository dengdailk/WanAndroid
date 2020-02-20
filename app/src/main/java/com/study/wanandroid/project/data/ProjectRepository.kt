package com.study.wanandroid.project.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.api.ApiRepository
import com.study.wanandroid.api.BaseObserver

/**
 * @author Laizexin on 2019/12/19
 * @description
 */
class ProjectRepository(private val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getProjectTab(liveData: MutableLiveData<BaseResponse<List<ProjectTabRsp>>>) {
        apiService.getProjectTab().execute(BaseObserver(liveData, loadState, this))
    }

    fun getProjectList(uid: Int, page: Int, liveData: MutableLiveData<BaseResponse<ProjectRsp>>) {
        apiService.getProjectList(page, uid).execute(BaseObserver(liveData, loadState, this))
    }

}