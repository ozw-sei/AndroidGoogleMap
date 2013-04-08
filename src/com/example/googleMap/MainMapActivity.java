package com.example.googleMap;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import java.util.ArrayList;

public class MainMapActivity extends MapActivity
{
    //MapView
    private MapView mMapView;

    //Satellite statement
    private boolean mIsSatellite = false;

    private MapController mMapController;

    private CustomLocationManager mCustomLocationManager;

    private GeoPoint mCurrentPoint;

    private static final int LOCATION_TIME_OUT = 10000;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        mCustomLocationManager = new CustomLocationManager( this );

        //initialize MapView
        mMapView = ( MapView ) findViewById( R.id.mapView );
        mMapView.setClickable( true );
        mMapView.setBuiltInZoomControls( true );

        // get Map Controller
        mMapController = mMapView.getController();
        mMapController.setZoom( 16 );

        //衛星写真かどうかの確認
        mIsSatellite = mMapView.isSatellite();
        mMapView.setSatellite( mIsSatellite );
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    //現在地
    public void onClickCurrent( View v ) {
        mCustomLocationManager.getNowLocationData( LOCATION_TIME_OUT,
                new CustomLocationManager.LocationCallback()
                {

                    @Override
                    public void onComplete( Location loc ) {
                        mCurrentPoint = new GeoPoint( ( int ) ( loc.getLatitude() * 1e6 ),
                                ( int ) ( loc.getLongitude() * 1e6 ) );
                        if ( mCurrentPoint != null ) {
                            Log.d( "onClickCurrent", "mCurrentPoint != null" );
                            mMapController.animateTo( mCurrentPoint );
                        }
                    }

                    @Override
                    public void onTimeout() {
                        Toast.makeText( getApplicationContext(), R.string.toast_time_out, Toast.LENGTH_SHORT ).show();
                    }
                } );
    }


    //衛生写真
    public void onClickSatellite( View v ) {
        mIsSatellite = !mIsSatellite;
        mMapView.setSatellite( mIsSatellite );
    }

    //周辺情報の取得と表示
    public void onClickInfo( View v ) {
    }

    // private
    private ArrayList<PointInfo> createTargetList( Location loc ) {
        ArrayList<PointInfo> targetList = new ArrayList<PointInfo>();

        targetList.add( createPointInfo( loc, 35.710058, 139.8107128, "東京スカイツリー" ) );

        return targetList;
    }

    /**
     * 位置情報の設定
     *
     * @param current
     *         　Locationインスタンス
     * @param lat
     *         　緯度
     * @param lon
     *         　経度
     * @param name
     *         　名称
     * @return　Point情報
     */
    private PointInfo createPointInfo( Location current, double lat, double lon, String name ) {
        Location loc = new Location( "tmp" );
        loc.setLatitude( lat );
        loc.setLongitude( lon );

        PointInfo info = new PointInfo( loc, name );
        info.setDistance( current.distanceTo( loc ) );

        return info;
    }
}
