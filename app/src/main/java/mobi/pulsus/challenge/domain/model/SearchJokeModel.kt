package mobi.pulsus.challenge.domain.model

import com.google.gson.annotations.SerializedName

data class SearchJokeModel(
    @SerializedName("total") val total: Int,
    @SerializedName("result") val result: List<JokeModel>
)