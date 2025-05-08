package setor.surah.tif.model

data class MahasiswaResponse(
    val response: Boolean,
    val message: String,
    val data: MahasiswaData
)

data class MahasiswaData(
    val info: MahasiswaInfo,
    val setoran: SetoranDetails
)

data class MahasiswaInfo(
    val nama: String,
    val nim: String,
    val email: String,
    val angkatan: String,
    val semester: Int,
    val dosen_pa: Dosen
)
