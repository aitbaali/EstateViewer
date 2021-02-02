package com.seloger.android.estateviewer.di

import android.content.Context
import com.seloger.android.estateviewer.BuildConfig
import com.seloger.android.estateviewer.api.EstateApi
import com.seloger.android.estateviewer.data.DataSource
import com.seloger.android.estateviewer.data.DefaultEstateRepository
import com.seloger.android.estateviewer.data.persistence.EstateDao
import com.seloger.android.estateviewer.data.persistence.EstatesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024L // 10MB
        val cache = Cache(context.filesDir, cacheSize)

        val okClient = OkHttpClient.Builder().cache(cache)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY
            okClient.addInterceptor(logging)
        }
        return okClient.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(EstateApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): EstateApi =
        retrofit.create(EstateApi::class.java)

    @Provides
    @Singleton
    fun providesEstateDao(@ApplicationContext context: Context): EstateDao =
        EstatesDatabase.getInstance(context).estateDao()

    @Provides
    @Singleton
    fun provideDefaultEstateRepository(api: EstateApi, dao: EstateDao): DataSource =
        DefaultEstateRepository(api, dao)

}