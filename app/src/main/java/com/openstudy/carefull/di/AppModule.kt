package com.openstudy.carefull.di

import com.openstudy.data.api.MedicineApiService
import com.openstudy.data.repository.MedicineRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.openstudy.carefull.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideMedicineApiService(retrofit: Retrofit): MedicineApiService =
        retrofit.create(MedicineApiService::class.java)

    @Provides
    @Singleton
    fun provideMedicineRepository(apiService: MedicineApiService): MedicineRepository =
        MedicineRepository(apiService, BuildConfig.MEDICINE_API_KEY)
}