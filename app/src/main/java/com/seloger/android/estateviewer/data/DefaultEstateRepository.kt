package com.seloger.android.estateviewer.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seloger.android.estateviewer.api.EstateApi
import com.seloger.android.estateviewer.data.entity.Estate
import com.seloger.android.estateviewer.data.persistence.EstateDao
import javax.inject.Inject

class DefaultEstateRepository @Inject constructor(
    private val api: EstateApi,
    private val dao: EstateDao
) : DataSource {

    override suspend fun getEstates(): LiveData<List<Estate>> {
        val result = api.getEstates()
        return MutableLiveData(result.estates)
    }
}

interface DataSource {
    suspend fun getEstates(): LiveData<List<Estate>>
}