package mobi.pulsus.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchJokeResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("result") val result: List<JokeResponse>
)