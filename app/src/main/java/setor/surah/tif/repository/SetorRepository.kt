package setor.surah.tif.repository

import android.content.Context
import setor.surah.tif.model.MahasiswaResponse
import setor.surah.tif.network.RetrofitInstance

class SetorRepository(private val context: Context) {
    private val api = RetrofitInstance.api

    private fun getToken(): String {
        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return sharedPreferences.getString("access_token", "") ?: ""
    }

    suspend fun getMahasiswa(): MahasiswaResponse {
        val token = getToken()
        val response = api.getMahasiswa("Bearer $token")
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Tidak ada data mahasiswa")
        } else {
            throw Exception("Gagal mendapatkan data mahasiswa: ${response.code()} - ${response.message()}")
        }
    }
}
