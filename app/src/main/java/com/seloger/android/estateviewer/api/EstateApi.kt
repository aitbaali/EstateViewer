package com.seloger.android.estateviewer.api

import retrofit2.http.GET

interface EstateApi {

    companion object {
        const val BASE_URL = "https://gsl-apps-technical-test.dignp.com"
    }

    @GET("listings.json")
    suspend fun getEstates(): EstatesResponse
}