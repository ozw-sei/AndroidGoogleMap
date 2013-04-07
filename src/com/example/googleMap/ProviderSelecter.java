package com.example.googleMap;

import android.location.Criteria;
import android.location.LocationManager;

public class ProviderSelecter
{
    //取得する位置情報の精度
    public static final int ACCURACY = Criteria.ACCURACY_FINE;

    //方位制度
    public static final int BEARING_ACCURACY = Criteria.ACCURACY_LOW;

    //緯度経度取得
    public static final int HORIZONITAL_ACCURACY = Criteria.ACCURACY_HIGH;

    //高度の取得
    public static final int VERTICAL_ACCURACY = Criteria.NO_REQUIREMENT;

    //電源消費レベルの設定
    public static final int POWER_LEVEL = Criteria.NO_REQUIREMENT;

    //速度の制度
    public static final int SPEED_ACCURACY = Criteria.NO_REQUIREMENT;

    //速度の設定
    public static final boolean ALTITUDE = false;

    //方位の取得の有無
    public static final boolean BEARING = false;

    //位置情報取得に関して金銭的なコストの許可
    public static final boolean COST_ALLOWED = false;

    //速度を検出するかどうか
    public static final boolean SPEED = false;

    Criteria mCriteria = new Criteria();

    public ProviderSelecter() {
        mCriteria.setAccuracy( ACCURACY );
        mCriteria.setBearingAccuracy( BEARING_ACCURACY );
        mCriteria.setAltitudeRequired( ALTITUDE );
        mCriteria.setBearingRequired( BEARING );
        mCriteria.setCostAllowed( COST_ALLOWED );
        mCriteria.setHorizontalAccuracy( HORIZONITAL_ACCURACY );
        mCriteria.setVerticalAccuracy( VERTICAL_ACCURACY );
        mCriteria.setPowerRequirement( POWER_LEVEL );
        mCriteria.setSpeedAccuracy( SPEED_ACCURACY );
        mCriteria.setSpeedRequired( SPEED );
        mCriteria.setVerticalAccuracy( VERTICAL_ACCURACY );
    }

    public String getBestProvider( boolean enableOnly, LocationManager locationManager ) {
        return locationManager.getBestProvider( mCriteria, enableOnly );
    }

}