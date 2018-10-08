package com.example.archek.tandermagnit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archek.tandermagnit.model.ObjectResponse;
import com.example.archek.tandermagnit.model.Station;
import com.example.archek.tandermagnit.net.RestApi;
import com.example.archek.tandermagnit.net.TanderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TanAdapter.Callback {

    DatePicker datePicker; //Устанавливаем переменные/Instal variables
    TextView dateVision;
    String today;
    TanAdapter adapterFrom = new TanAdapter( this );
    TanAdapter adapterTo = new TanAdapter(  this );
    @Nullable
    Call<ObjectResponse> call;
    private final TanderService service = RestApi.createService( TanderService.class );
    RecyclerView rvFrom;
    RecyclerView rvTo;
    TextView tvFromHead;
    TextView tvToHead;
    Handler handler = new Handler(  );
    ArrayList<Station> tempStationsList = new ArrayList <>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        dateVision = findViewById( R.id.tvDate );//Инициализируем переменные/initiate variables
        setToday();
        dateVision.setText( today );
        datePicker = findViewById( R.id.dpDatePicker );
        tvFromHead = findViewById( R.id.tvFromHead );
        tvToHead = findViewById( R.id.tvToHead );
        rvFrom = findViewById(R.id.rvFrom);
        rvTo = findViewById(R.id.rvTo);
        RecyclerView.LayoutManager layoutManagerFrom = new LinearLayoutManager(this);
        rvFrom.setLayoutManager(layoutManagerFrom);
        rvFrom.setAdapter(adapterFrom);
        RecyclerView.LayoutManager layoutManagerTo = new LinearLayoutManager(this);
        rvTo.setLayoutManager(layoutManagerTo);
        rvTo.setAdapter(adapterTo);

        call = service.getData();//Загружаем оба списка(отправка, прибиытие)/Load both lists(from,to)
        call.enqueue( new Callback <ObjectResponse>() {
            @Override
            public void onResponse(Call <ObjectResponse> call, Response<ObjectResponse> response) {
                ObjectResponse objectResponse = response.body();
                adapterFrom.replaceAllFrom(objectResponse.getCitiesFrom() );
                adapterTo.replaceAllTo( objectResponse.getCitiesTo() );
            }
            @Override
            public void onFailure(Call <ObjectResponse> call, Throwable t) {
                if(call.isCanceled()){
                    Toast.makeText( MainActivity.this, R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        tvFromHead.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {//Кнопка поиска по списку станций отправления/
                        EditText etSearchFrom;  //Button search for list of station "from"
                        etSearchFrom = findViewById( R.id.etSearchFrom );
                        etSearchFrom.setVisibility( View.VISIBLE );
                        editTextSearch( etSearchFrom,"from" );
                    }
        } );

        tvToHead.setOnClickListener( new View.OnClickListener() {//Кнопка поиска по списку станций прибытия
                @Override                                       //Button search for list of station "to"
                public void onClick(View v) {
                    EditText etSearchTo = findViewById( R.id.etSearchTo );
                    etSearchTo.setVisibility( View.VISIBLE );
                    editTextSearch( etSearchTo,"to" );
                }
        } );

        dateVision.setOnClickListener( new View.OnClickListener() {//Кнопка выбора даты(по умолчанию сегодня)
            @Override                                               // Button choosing date(default - today)
            public void onClick(View v) {
                datePicker.setVisibility( View.VISIBLE );
                rvFrom.setVisibility( View.INVISIBLE );
                rvTo.setVisibility( View.INVISIBLE );
            }
        } );
    }

    public void buttonClickDateChoose(View view) {  //Обработка выбора даты/Working on date choose
        String date;
        int dayOfMonth = datePicker.getDayOfMonth();
        int monthOfYear = datePicker.getMonth();
        int year = datePicker.getYear();
        String day;
        if (dayOfMonth < 10) day = "0" + dayOfMonth;
        else day = String.valueOf(dayOfMonth);
        monthOfYear++;
        String month;
        if (monthOfYear < 10) month = "0" + monthOfYear;
        else month = String.valueOf(monthOfYear);
        date = day + "." + month + "." + year;
        dateVision.setText( date );
        datePicker.setVisibility( View.GONE );
        rvFrom.setVisibility( View.VISIBLE );
        rvTo.setVisibility( View.VISIBLE );
    }

    @Override
    public void onStationClick(Station station){//Выбор станций/ Choose stations

        tempStationsList.add( station );
        station.setPicked( true );
        if(tempStationsList.size() == 2) {
            Intent intent = StationInfo.makeIntent( this, tempStationsList );//Отправляем интент во 2е активити
            startActivity( intent );                                               //Send intent to 2nd activity
            tempStationsList.clear();


        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {//Устанавливаем меню/instal menu
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){//Варианты выбора меню / There are variants for choosing menu
        int id = item.getItemId();//Обычные всплывающие подсказки, без функционала
        switch (id) {             //Simple floating toasts, without functions
            case R.id.about:
                Toast.makeText( MainActivity.this, R.string.about, Toast.LENGTH_SHORT ).show();
                return true;
            case R.id.shedule:
                Toast.makeText( MainActivity.this, R.string.shedule, Toast.LENGTH_SHORT ).show();
                return true;
        default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void editTextSearch(EditText editText, final String direction){//Метод для анализа вводимых данных, в поиск
                                                                          //There is method for parsing input data to searching
        editText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(final Editable s) {
                handler.removeCallbacksAndMessages( null );
                handler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        searchData( s.toString(), direction );
                    }

                },400 );
            }
        } );
    }
    private void searchData(final String searchBody, final String direction){//Метод для поиска станций
        if(searchBody.isEmpty()){                                            //Method for searching station
            return;
        }
        if (call != null){
            call.cancel();
        }
        call = service.searchData(searchBody);
        //noinspection ConstantConditions
        call.enqueue( new Callback <ObjectResponse>() {
            @Override
            public void onResponse(Call <ObjectResponse> call, Response<ObjectResponse> response) {
                if(response.body() != null) {
                    //noinspection ConstantConditions
                    if(direction.equals( "from" )) {
                        adapterFrom.replaceSearchFrom( searchBody, response.body().getCitiesFrom() );
                    }
                    else if(direction.equals( "to" )){
                        adapterTo.replaceSearchTo( searchBody, response.body().getCitiesTo() );
                    }
                }
            }
            @Override
            public void onFailure(Call <ObjectResponse> call, Throwable t) {
                if(!call.isCanceled()){
                    Toast.makeText( MainActivity.this,R.string.error, Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
        public void setToday() { //Ставим сегодняшнюю дату
            @SuppressLint("SimpleDateFormat") // Instal todat date
            SimpleDateFormat dateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
            Date dater = new Date();
            today = dateFormat.format( dater );
        }

}

