package cs1302.models.weather_api;

import com.google.gson.annotations.SerializedName;

public class DarkSkyResponse {

    @SerializedName("currently")
    private Currently currently;

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public static class Currently {

        @SerializedName("icon")
        private String icon;

        @SerializedName("temperature")
        private String temperature;

        @SerializedName("windSpeed")
        private String windSpeed;

        @SerializedName("precipProbability")
        private String precipProbability;

        public String getIcon() {
            return icon;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public String getPrecipProbability() {
            return precipProbability;
        }
    }


}
