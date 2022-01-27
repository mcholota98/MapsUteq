package com.example.googlemapsuteq;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = "InfoWindowAdapter";
    private LayoutInflater inflater;

    public InfoWindowAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }


    @Override
    public View getInfoContents(final Marker m) {
        View v = inflater.inflate(R.layout.info_wa, null);
        String[] info = m.getTitle().split("&");
        String url = m.getSnippet();
        return v;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


}
