package setor.surah.tif.model

data class SetoranDetails(
    val log: List<SetoranLog>,
    val info_dasar: InfoDasar,
    val ringkasan: List<Ringkasan>,
    val detail: List<SetoranItem>
)