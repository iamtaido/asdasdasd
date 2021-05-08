package cs1302.models.mapbox_api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapBoxResponse {

    @SerializedName("features")
    private List<Features> features;


    public static class Features {
        @SerializedName("center")
        private List<Double> center;

        public List<Double> getCenter() {
            return center;
        }

        public void setCenter(List<Double> center) {
            this.center = center;
        }
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}
