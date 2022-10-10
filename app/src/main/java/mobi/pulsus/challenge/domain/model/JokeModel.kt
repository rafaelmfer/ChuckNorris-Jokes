package mobi.pulsus.challenge.domain.model

import mobi.pulsus.challenge.data.dto.JokeDTO

data class JokeModel(
    val categories: List<String>,
    val createdAt: String,
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String,
    var isFavorite: Boolean = false
) {
    fun modelToDTO(): JokeDTO {
        return JokeDTO(
            createdAt = createdAt,
            iconUrl = iconUrl,
            id = id,
            url = url,
            value = value,
            isFavorite = isFavorite
        )
    }
}