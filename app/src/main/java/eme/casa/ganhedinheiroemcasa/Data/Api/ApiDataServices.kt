package eme.casa.ganhedinheiroemcasa.Data.Api

import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDataServices {


    private val ganheMain: ApiDataInstance = Retrofit.Builder()
        .baseUrl("http://45.66.164.9:7569/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiDataInstance::class.java)

    suspend fun mainGanhe(): Response<List<ApiModel>> {
        return ganheMain.getGanhe()
    }


    private val ganheBackup: ApiDataInstance = Retrofit.Builder()
        .baseUrl("http://148.72.209.46:7569/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiDataInstance::class.java)

    suspend fun backupGanhe(): Response<List<ApiModel>> {
        return ganheBackup.getGanhe()
    }
}