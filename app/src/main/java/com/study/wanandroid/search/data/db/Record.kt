package com.study.wanandroid.search.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Entity(tableName = "search_history")
data class Record (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "name")
    var name:String
)