package com.example.agendasync1;

import android.annotation.SuppressLint;
import android.content.Context;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;


import com.google.android.gms.location.LocationCallback;

import com.google.android.gms.location.LocationResult;

public class Localizador extends LocationCallback implements LocationListener {

    private FusedLocationProviderClient client;
    private GoogleMap mapa;
    private Context context;

    public Localizador(Context context, GoogleMap mapa){
        this.client = LocationServices.getFusedLocationProviderClient(context);
        this.mapa = mapa;
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public void fazBusca(){
        LocationRequest request = new LocationRequest();

        request.setSmallestDisplacement(50);

        request.setInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        client.requestLocationUpdates(request, this, null);
    }

    public void cancelarBusca(){
        client.removeLocationUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(coordenada);
        mapa.moveCamera(cameraUpdate);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        LatLng coordenada = new LatLng(locationResult.getLastLocation().getLongitude(),1);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(coordenada);
        mapa.moveCamera(cameraUpdate);
    }
}