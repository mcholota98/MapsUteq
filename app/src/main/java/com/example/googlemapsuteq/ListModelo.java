package com.example.googlemapsuteq;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListModelo {
    @SerializedName("data")
    private List<Constructor> lmData;

    public List<Constructor> getLmData() {
        return lmData;
    }
}
