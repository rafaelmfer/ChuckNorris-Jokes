package mobi.pulsus.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("categories") val categories: List<String>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("id") val id: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String
)