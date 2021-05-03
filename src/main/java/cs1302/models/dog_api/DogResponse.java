package cs1302.models.dog_api;

import com.google.gson.annotations.SerializedName;

public class DogResponse {

    @SerializedName("weight")
    private Measurement weight;

    @SerializedName("height")
    private Measurement height;

    @SerializedName("name")
    private String name;

    @SerializedName("bred_for")
    private String bredFor;

    @SerializedName("breed_group")
    private String breedGroup;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("temperament")
    private String temperament;


    public Measurement getWeight() {
        return weight;
    }

    public void setWeight(Measurement weight) {
        this.weight = weight;
    }

    public Measurement getHeight() {
        return height;
    }

    public void setHeight(Measurement height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    static class Measurement {
        @SerializedName("metric")
        private String metric;

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Bred For: ").append(getBredFor()).append("\n");
        sb.append("Breed Group: ").append(getBreedGroup()).append("\n");
        sb.append("Life Span: ").append(getLifeSpan()).append("\n");
        sb.append("Temperament: ").append(getTemperament()).append("\n");
        sb.append("Weight: ").append(getWeight().getMetric()).append("\n");
        sb.append("Height: ").append(getHeight().getMetric()).append("\n");

        return sb.toString();
    }
}
