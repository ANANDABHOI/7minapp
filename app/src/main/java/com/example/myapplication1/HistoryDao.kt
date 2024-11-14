package com.example.myapplication1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Dao
interface HistoryDao {
    @Insert
    fun insert(historyEntity: HistoryEntity):Long


}

annotation class Inject

class HistoryRepository @Inject constructor(
    private val historyDao: HistoryDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun insert(historyEntity: HistoryEntity)= withContext(ioDispatcher){
        historyDao.insert(historyEntity)
    }
}

