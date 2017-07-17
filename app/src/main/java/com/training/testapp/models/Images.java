package com.training.testapp.models;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("standard_resolution")
    private StandardResolution standardResolution;

    public StandardResolution getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(StandardResolution standardResolution) {
        this.standardResolution = standardResolution;
    }
}
