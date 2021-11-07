package com.example.anotherweatherapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> cities;
    Context ct;

    public MyAdapter(Context context, ArrayList<String> n) {
        ct = context;
        cities = n;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textCityLine;
        TextView textTemperatureLine;
        Button invisibleButton;

        public MyViewHolder(View v) {
            super(v);
            textCityLine = v.findViewById(R.id.city_name_line);
            textTemperatureLine = v.findViewById(R.id.temperature_line);
            invisibleButton = v.findViewById(R.id.button_invisible);
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View v = inflater.inflate(R.layout.fila_layout, parent, false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textCityLine.setText(cities.get(position));
        holder.textTemperatureLine.setText( 26 + "ºC");

        //setup for the invisible button
        holder.invisibleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , CitySelectedActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public int getItemCount() {
        return cities.size();
    }
}
