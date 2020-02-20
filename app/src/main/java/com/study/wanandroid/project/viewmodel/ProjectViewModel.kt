package com.study.wanandroid.project.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseViewModel
import com.study.common.https.BaseResponse
import com.study.wanandroid.project.data.ProjectRepository
import com.study.wanandroid.project.data.ProjectRsp
import com.study.wanandroid.project.data.ProjectTabRsp

class ProjectViewModel(application: Application) : BaseViewModel<ProjectRepository>(application) {
    val mProjectTapData: MutableLiveData<BaseResponse<List<ProjectTabRsp>>> = MutableLiveData()
    val mProjectListData: MutableLiveData<BaseResponse<ProjectRsp>> = MutableLiveData()

    fun getProjectTab() {
        mRespository.getProjectTab(mProjectTapData)
    }

    fun getProjectList(uid: Int, page: Int) {
        mRespository.getProjectList(uid, page, mProjectListData)
    }

}
