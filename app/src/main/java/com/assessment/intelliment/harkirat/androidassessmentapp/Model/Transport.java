package com.assessment.intelliment.harkirat.androidassessmentapp.Model;

import com.google.gson.annotations.SerializedName;

public class Transport {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;

    @SerializedName("fromcentral")
    private TravelDuration travelDuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public TravelDuration getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(TravelDuration travelDuration) {
        this.travelDuration = travelDuration;
    }

    @Override
    public String toString() {
        return name;
    }
}
