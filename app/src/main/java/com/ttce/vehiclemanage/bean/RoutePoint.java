package com.ttce.vehiclemanage.bean;

import com.baidu.mapapi.model.LatLng;

public class RoutePoint {
    private double lat,lng;

    public RoutePoint(double lat,double lng){
        this.lat=lat;
        this.lng=lng;
    }

    public RoutePoint(LatLng latLng){
        this.lat=latLng.latitude;
        this.lng=latLng.longitude;
    }

    public RoutePoint setLat(double lat){
        this.lat=lat;
        return this;
    }

    public double getLat(){
        return this.lat;
    }

    public RoutePoint setLng(double lng){
        this.lng=lng;
        return this;
    }

    public double getLng(){
        return this.lng;
    }

}
