package by.godevelopment.fifthtask.domain

data class GeographicPointModel(
    val id: Int,
    val tittle_type: String? = null,
    val snippet_address: String? = null,
    val distance: Float? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
