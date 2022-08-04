import com.google.gson.annotations.SerializedName

data class WeatherForecast (
	@SerializedName("data") val data : List<Data>,
	@SerializedName("city_name") val city_name : String,
	@SerializedName("lon") val lon : Double,
	@SerializedName("timezone") val timezone : String,
	@SerializedName("lat") val lat : Double,
	@SerializedName("country_code") val country_code : String,
	@SerializedName("state_code") val state_code : String
)