package setor.surah.tif.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import setor.surah.tif.model.MahasiswaInfo
import setor.surah.tif.repository.SetorRepository

class MahasiswaViewModel(context: Context) : ViewModel() {
    private val repository = SetorRepository(context)

    private val _mahasiswa = MutableStateFlow<MahasiswaInfo?>(null)
    val mahasiswa: StateFlow<MahasiswaInfo?> = _mahasiswa

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchMahasiswa() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = repository.getMahasiswa()
                _mahasiswa.value = response.data.info
                Log.d("MahasiswaViewModel", "Data mahasiswa berhasil diambil: ${response.data.info.nama}")
            } catch (e: Exception) {
                _error.value = "Gagal memuat data mahasiswa: ${e.message}"
                Log.e("MahasiswaViewModel", "Exception: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
