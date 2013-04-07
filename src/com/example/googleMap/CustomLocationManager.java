package com.example.googleMap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class CustomLocationManager implements LocationListener
{
    //Field
    private LocationManager mLocationManager;


    public CustomLocationManager( Context context ) {
        mLocationManager = ( LocationManager ) context.getSystemService( Activity.LOCATION_SERVICE );
    }

    /**
     * 位置を取得する
     *
     * @param provider
     *         　プロバイダ
     */
    public void startLocation( String provider ) {
        mLocationManager.requestLocationUpdates( provider, 0, 0, this );
    }

    public void removeUpdate() {
        mLocationManager.removeUpdates( this );
    }

    @Override
    public void onLocationChanged( Location location ) {
        //位置情報を取得した際に呼ばれる
    }

    @Override
    public void onStatusChanged( String provider, int status, Bundle extras ) {
        //GPSプロバイダの状態が変化した時
    }

    @Override
    public void onProviderEnabled( String provider ) {
        //GPSプロバイダの状態が有効になった時
    }

    @Override
    public void onProviderDisabled( String provider ) {
        //GPSプロバイダの状態が無効になった時
    }

    public static interface LocationCallback
    {
        public void onComplete( Location loc );

        public void onTimeout();
    }
}
