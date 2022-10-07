package mobi.pulsus.challenge.domain.model

data class JokeModel(
    val categories: List<String>,
    val createdAt: String,
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)