package setor.surah.tif.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import setor.surah.tif.model.Auth
import setor.surah.tif.model.MahasiswaResponse

interface ApiService {

    @FormUrlEncoded
    @POST("/realms/dev/protocol/openid-connect/token")
    suspend fun login(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("scope") scope: String
    ): retrofit2.Response<Auth>

    @FormUrlEncoded
    @POST("/realms/dev/protocol/openid-connect/token")
    suspend fun refreshToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): retrofit2.Response<Auth>

    @GET("mahasiswa/setoran-saya")
    suspend fun getSetoranSaya(
        @Header("Authorization") token: String,
        @Query("apikey") accessToken: String
    ): retrofit2.Response<MahasiswaResponse>

    // Endpoint setoran-saya tanpa parameter apikey
    @GET("mahasiswa/setoran-saya")
    suspend fun getSetoranSaya(
        @Header("Authorization") token: String
    ): retrofit2.Response<MahasiswaResponse>
}