package com.example.googlemapsuteq;

import com.google.gson.annotations.SerializedName;

public class Constructor {
    @SerializedName("id")
    private String id;

    @SerializedName("logo")
    private String logo;

    @SerializedName("facultad")
    private String facultad;

    @SerializedName("decano")
    private String decano;

    @SerializedName("ubicacion")
    private String ubicacion;

    @SerializedName("latitud")
    private String latitud;

    @SerializedName("longitud")
    private String longitud;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
