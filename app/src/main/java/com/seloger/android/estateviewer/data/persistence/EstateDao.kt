package com.seloger.android.estateviewer.data.persistence

import androidx.room.*
import com.seloger.android.estateviewer.data.entity.Estate
import kotlinx.coroutines.flow.Flow

@Dao
interface EstateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(estate: Estate)

    @Update
    fun update(estate: Estate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(estates: List<Estate>)

    @Query("SELECT * FROM estates")
    fun load(): Flow<List<Estate>>

    @Query("DELETE FROM estates")
    fun deleteAll()
}