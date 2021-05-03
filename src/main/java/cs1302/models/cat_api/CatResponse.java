package cs1302.models.cat_api;

import com.google.gson.annotations.SerializedName;

public class CatResponse {

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    @SerializedName("origin")
    private String origin;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Description: ").append(getName()).append("\n");
        sb.append("Origin: ").append(getOrigin()).append("\n");
        return sb.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
