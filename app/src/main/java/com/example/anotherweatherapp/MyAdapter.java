package com.example.anotherweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public MyViewHolder(View v) {
            super(v);
            textCityLine = v.findViewById(R.id.city_name_line);
            textTemperatureLine = v.findViewById(R.id.temperature_line);
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
    }

    public int getItemCount() {
        return cities.size();
    }
}
