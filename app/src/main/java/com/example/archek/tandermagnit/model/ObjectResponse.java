package com.example.archek.tandermagnit.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectResponse {

    @SerializedName("citiesFrom")
    private List<CitiesFrom> citiesFrom;

    @SerializedName("citiesTo")
    private List<CitiesTo> citiesTo;

    public List <CitiesTo> getCitiesTo() {
        return citiesTo;
    }

    public List <CitiesFrom> getCitiesFrom() {
        return citiesFrom;
    }

    public void setCitiesTo(List <CitiesTo> citiesTo) {
        this.citiesTo = citiesTo;
    }

    public void setCitiesFrom(List <CitiesFrom> citiesFrom) {
        this.citiesFrom = citiesFrom;
    }
}

