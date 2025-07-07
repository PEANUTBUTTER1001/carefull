package com.openstudy.data.api

import com.openstudy.data.model.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MedicineApiService {
    @GET("getDrbEasyDrugList")
    suspend fun getDrugInfo(
        @Query("serviceKey") serviceKey: String,
        @Query("itemName") itemName: String,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("_type") type: String = "json"
    ): Response<MedicineResponse>
}