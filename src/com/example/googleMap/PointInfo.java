package com.example.googleMap;

import android.location.Location;

public class PointInfo
{
    //位置情報
    private final Location mLocation;

    //名称
    private final String mName;

    //距離
    private double mDistance;

    //コンストラクタ
    public PointInfo( Location loc, String name ) {
        mLocation = loc;
        mName = name;
    }

    public Location getLocation() {
        return mLocation;
    }

    public String getName() {
        return mName;
    }

    public void setDistance( double dist ) {
        mDistance = dist;
    }

    public double getDistance() {
        return mDistance;
    }
}
