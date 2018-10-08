package com.example.archek.tandermagnit;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.archek.tandermagnit.model.CitiesFrom;
import com.example.archek.tandermagnit.model.CitiesTo;
import com.example.archek.tandermagnit.model.Station;

import java.util.ArrayList;
import java.util.List;

public class TanAdapter extends RecyclerView.Adapter<TanAdapter.ViewHolder> {

    private ArrayList<Station> stations = new ArrayList <>(  ); //Устанавливаются переменные, списки
    private ArrayList<CitiesFrom> citiesFrom = new ArrayList <>(  );//Instal variables, lists
    private ArrayList<CitiesTo> citiesTo = new ArrayList <>(  );
    private final Callback callback;
    

    public TanAdapter(Callback callback) { //Конструктор с колбэком
        this.callback = callback; // Constructor with callback
    }

    public void replaceAllFrom(List <CitiesFrom> citiesFromList ) { //Метод для загрузки станций "отправки" в адаптер
        stations.clear();                                           //Method for loading stations "from" in adapter
        citiesFrom.clear();
        citiesFrom.addAll( citiesFromList );
        for(int i = 0; i < citiesFrom.size(); i++){
            stations.addAll( citiesFrom.get( i ).getStations() );
        }

        notifyDataSetChanged();
    }

    public void replaceAllTo(List <CitiesTo> citiesToList ) {//Метод для загрузки станций "прибытия" в адаптер
        stations.clear();                                     //Method for loading stations "to" in adapter
        citiesTo.clear();
        citiesTo.addAll( citiesToList );
        for(int i = 0; i < citiesTo.size(); i++){
            stations.addAll( citiesTo.get( i ).getStations() );
        }

        notifyDataSetChanged();
    }

    public void replaceSearchFrom(String searchBody, List<CitiesFrom> searchResponse){
        stations.clear();                                           //Метод для загрузки результа поиска станций "отправки" в адаптер
        ArrayList <Station> stationsList = new ArrayList <>();      //Method for loading search results stations "from" in adapter
        for (int i = 0; i < searchResponse.size(); i++) {
                stationsList.addAll( searchResponse.get( i ).getStations() );
            }
        for (int i = 0; i < stationsList.size(); i++) {
            if (stationsList.get( i ).getStationTitle().toLowerCase().contains( searchBody ) || stationsList.get( i ).getCountryTitle().toLowerCase().contains( searchBody ) || stationsList.get( i ).getCityTitle().toLowerCase().contains( searchBody )) {
                stations.add( stationsList.get( i ) );
            }
            notifyDataSetChanged();
        }
    }
    public void replaceSearchTo(String searchBody, List<CitiesTo> searchResponse){
        stations.clear();                                           //Метод для загрузки результа поиска станций "прибытия" в адаптер
        ArrayList <Station> stationsList = new ArrayList <>();      //Method for loading search results stations "to" in adapter
        for (int i = 0; i < searchResponse.size(); i++) {
            stationsList.addAll( searchResponse.get( i ).getStations() );
        }
        for (int i = 0; i < stationsList.size(); i++) {
            if (stationsList.get( i ).getStationTitle().toLowerCase().contains( searchBody ) || stationsList.get( i ).getCountryTitle().toLowerCase().contains( searchBody ) || stationsList.get( i ).getCityTitle().toLowerCase().contains( searchBody )) {
                stations.add( stationsList.get( i ) );
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Station station = stations.get(holder.getAdapterPosition());                    //Листенер на элементы списка из ресайклвью, при нажатии выделяется цветом
                    holder.cv.setCardBackgroundColor( Color.parseColor( "#ffcc11" ) );//Listener on element, on click item featuring color

                callback.onStationClick( station );


            }
        } );
        return holder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Station station = stations.get(position);                   //Выставляем базовые данные(загруженные) для каждого элемента списка
        holder.tvCountryIt.setText( station.getCountryTitle() );    //Initiate base data(loaded) for each element in list
        holder.tvCityIt.setText( station.getCityTitle() );
        holder.tvStationIt.setText( station.getStationTitle() );

    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{//Выставляем и далее инициируем вью для списка
        TextView tvCountryIt;                                 //Initiate views for list(recycler view)
        TextView tvCityIt;
        TextView tvStationIt;
        CardView cv;


        ViewHolder(View itemView) {
            super(itemView);
            tvCountryIt = itemView.findViewById( R.id.tvCountryIt );
            tvCityIt = itemView.findViewById( R.id.tvCityIt );
            tvStationIt = itemView.findViewById( R.id.tvStationIt );
            cv = itemView.findViewById( R.id.cv );

            

        }
    }


    public interface Callback{
        void onStationClick(Station station);

    }
}
