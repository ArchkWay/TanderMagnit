package com.example.archek.tandermagnit.model;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("countryTitle")
    private String countryTitle;
    @SerializedName("point")
    private Point point;
    @SerializedName("districtTitle")
    private String districtTitle;
    @SerializedName("cityId")
    private Integer cityId;
    @SerializedName("cityTitle")
    private String cityTitle;
    @SerializedName("regionTitle")
    private String regionTitle;
    @SerializedName("stationId")
    private Integer stationId;
    @SerializedName("stationTitle")
    private String stationTitle;

    private boolean picked = false;

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

}
