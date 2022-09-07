package com.example.a3_rajbeer_sokhi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZeldaCharacters {
    private int attack;
    @SerializedName("common_locations")
    private List<String> location;
    private int defense;
    private String description;
    @SerializedName("image")
    private String imageURL;

    private String name;

    public int getAttack() {
        return attack;
    }

    public List<String> getLocation() {
        return location;
    }

    public int getDefense() {
        return defense;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ZeldaCharacters{" +
                "attack=" + attack +
                ", location=" + location +
                ", defense=" + defense +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isFromLocation(String location){
        return this.location.contains(location);
    }
}
