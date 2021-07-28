package eme.casa.ganhedinheiroemcasa.Data.Api

import retrofit2.Response
import retrofit2.http.GET
import eme.casa.ganhedinheiroemcasa.Data.Model.ApiModel

interface ApiDataInstance {

    @GET("monkeymakingadvantageandisadvantages")
    suspend fun getGanhe(): Response<List<ApiModel>>
}