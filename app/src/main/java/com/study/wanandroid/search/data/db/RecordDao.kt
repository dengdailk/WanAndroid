package com.study.wanandroid.search.data.db

import androidx.room.*

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM search_history")
    fun getAll():List<Record>

    @Insert
    fun insert(vararg record:Record)

    @Delete
    fun delete(record:Record?)
    @Update
    fun update(vararg record: Record)
}