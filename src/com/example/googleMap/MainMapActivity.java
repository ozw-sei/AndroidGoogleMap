package com.example.googleMap;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
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

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

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

    //位置と緯度経度・タイトルを初期化
    private PointInfo createPointInfo( Location current, double lat, double lon, String name ) {
        Location loc = new Location( "tmp" );
        loc.setLatitude( lat );
        loc.setLongitude( lon );

        PointInfo info = new PointInfo( loc, name );
        info.setDistance( current.distanceTo( loc ) );

        return info;
    }
}
