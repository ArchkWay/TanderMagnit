package com.example.archek.tandermagnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.archek.tandermagnit.model.Station;

import java.util.ArrayList;

public class StationInfo extends AppCompatActivity{

    private static final String EXTRA_FROM_COUNTRY_TITLE = "EXTRA_FROM_COUNTRY_TITLE";//Выставляем все строки для значений из Интента
    private static final String EXTRA_FROM_CITY_TITLE = "EXTRA_FROM_CITY_TITLE";     //Instal all strings for all values from Intent
    private static final String EXTRA_FROM_CITY_ID = "EXTRA_FROM_CITY_ID";
    private static final String EXTRA_FROM_DISTRICT_TITLE = "EXTRA_FROM_DISTRICT_TITLE";
    private static final String EXTRA_FROM_REGION_TITLE = "EXTRA_FROM_REGION_TITLE";
    private static final String EXTRA_FROM_STATION_TITLE = "EXTRA_FROM_STATION_TITLE";
    private static final String EXTRA_FROM_STATION_ID = "EXTRA_FROM_STATION_ID";
    private static final String EXTRA_FROM_POINT_LATITUDE = "EXTRA_FROM_POINT_LATITUDE";
    private static final String EXTRA_FROM_POINT_LONGITUDE = "EXTRA_FROM_POINT_LONGITUDE";

    private static final String EXTRA_TO_COUNTRY_TITLE = "EXTRA_TO_COUNTRY_TITLE";
    private static final String EXTRA_TO_CITY_TITLE = "EXTRA_TO_CITY_TITLE";
    private static final String EXTRA_TO_CITY_ID = "EXTRA_TO_CITY_ID";
    private static final String EXTRA_TO_DISTRICT_TITLE = "EXTRA_TO_DISTRICT_TITLE";
    private static final String EXTRA_TO_REGION_TITLE = "EXTRA_TO_REGION_TITLE";
    private static final String EXTRA_TO_STATION_TITLE = "EXTRA_TO_STATION_TITLE";
    private static final String EXTRA_TO_STATION_ID = "EXTRA_TO_STATION_ID";
    private static final String EXTRA_TO_POINT_LATITUDE = "EXTRA_TO_POINT_LATITUDE";
    private static final String EXTRA_TO_POINT_LONGITUDE = "EXTRA_TO_POINT_LONGITUDE";

    public static Intent makeIntent(Context context, ArrayList<Station> list) { //Раставляем данные из интента, в заведённые ячейки
        return new Intent( context, StationInfo.class ) // выставляю все(на перспективу дальнейшей разработки)
                .putExtra( StationInfo.EXTRA_FROM_COUNTRY_TITLE, list.get( 0 ).getCountryTitle() )//There are instaling all data from intent, in prepared stocks
                .putExtra( StationInfo.EXTRA_FROM_CITY_TITLE, list.get( 0 ).getCityTitle() )
                .putExtra( StationInfo.EXTRA_FROM_CITY_ID, list.get( 0 ).getCityId() )
                .putExtra( StationInfo.EXTRA_FROM_DISTRICT_TITLE, list.get( 0 ).getDistrictTitle() )
                .putExtra( StationInfo.EXTRA_FROM_REGION_TITLE, list.get( 0 ).getRegionTitle() )
                .putExtra( StationInfo.EXTRA_FROM_STATION_TITLE, list.get( 0 ).getStationTitle() )
                .putExtra( StationInfo.EXTRA_FROM_STATION_ID, list.get( 0 ).getStationId() )
                .putExtra( StationInfo.EXTRA_FROM_POINT_LATITUDE, list.get( 0 ).getPoint().getLatitude() )
                .putExtra( StationInfo.EXTRA_FROM_POINT_LONGITUDE, list.get( 0 ).getPoint().getLongitude() )

                .putExtra( StationInfo.EXTRA_TO_COUNTRY_TITLE, list.get( 1 ).getCountryTitle() )
                .putExtra( StationInfo.EXTRA_TO_CITY_TITLE, list.get( 1 ).getCityTitle() )
                .putExtra( StationInfo.EXTRA_TO_CITY_ID, list.get( 1 ).getCityId() )
                .putExtra( StationInfo.EXTRA_TO_DISTRICT_TITLE, list.get( 1 ).getDistrictTitle() )
                .putExtra( StationInfo.EXTRA_TO_REGION_TITLE, list.get( 1 ).getRegionTitle() )
                .putExtra( StationInfo.EXTRA_TO_STATION_TITLE, list.get( 1 ).getStationTitle() )
                .putExtra( StationInfo.EXTRA_TO_STATION_ID, list.get( 1 ).getStationId() )
                .putExtra( StationInfo.EXTRA_TO_POINT_LATITUDE, list.get( 1 ).getPoint().getLatitude() )
                .putExtra( StationInfo.EXTRA_TO_POINT_LONGITUDE, list.get( 1 ).getPoint().getLongitude() );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.info_stations );

        Intent intent = getIntent();
        String countryFrom = intent.getStringExtra( EXTRA_FROM_COUNTRY_TITLE );//Перекладываем из экстра строк в локальные
        String cityFrom = intent.getStringExtra( EXTRA_FROM_CITY_TITLE );     //Replace from EXTRA to locals variables
        String stationFrom = intent.getStringExtra( EXTRA_FROM_STATION_TITLE );
        String regionFrom = intent.getStringExtra(EXTRA_FROM_REGION_TITLE);
        String districtFrom = intent.getStringExtra(EXTRA_FROM_DISTRICT_TITLE);

        String countryTo = intent.getStringExtra( EXTRA_TO_COUNTRY_TITLE );
        String cityTo = intent.getStringExtra( EXTRA_TO_CITY_TITLE );
        String stationTo = intent.getStringExtra( EXTRA_TO_STATION_TITLE );
        String regionTo = intent.getStringExtra(EXTRA_TO_REGION_TITLE);
        String districtTo = intent.getStringExtra(EXTRA_TO_DISTRICT_TITLE);


        TextView tvCountryFrom = findViewById( R.id.tvCountryFrom );//Инициализируем вьюшки
        TextView tvCityFrom = findViewById( R.id.tvCityFrom );      //Initialize views
        TextView tvStationFrom = findViewById( R.id.tvStationFrom );
        TextView tvRegionFrom = findViewById( R.id.tvRegionFrom );
        TextView tvDistrictFrom = findViewById( R.id.tvDistrictFrom );
        TextView tvCountryTo = findViewById( R.id.tvCountryTo );
        TextView tvCityTo = findViewById( R.id.tvCityTo );
        TextView tvStationTo = findViewById( R.id.tvStationTo );
        TextView tvRegionTo = findViewById( R.id.tvRegionTo );
        TextView tvDistrictTo = findViewById( R.id.tvDistrictTo );
        Button btnBack = findViewById( R.id.btnBack );

        tvCountryFrom.setText( countryFrom );//Закладываем строки в подготовленные вьюшки
        tvCityFrom.setText(cityFrom); //Put the strings in prepared views
        tvStationFrom.setText(  stationFrom );
        tvRegionFrom.setText( regionFrom );
        tvDistrictFrom.setText(districtFrom);

        tvCountryTo.setText( countryTo );
        tvCityTo.setText(cityTo);
        tvStationTo.setText( stationTo );
        tvRegionTo.setText( regionTo );
        tvDistrictTo.setText(districtTo);

        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBtn = new Intent(getApplicationContext(),//Возврат в Основное активити
                        MainActivity.class);                          //Comeback to MainActivity
                startActivity(returnBtn);
            }
        } );
    }

}

