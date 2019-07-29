package com.example.androidcourseproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseproject.R;
import com.example.androidcourseproject.fragments.history.WeatherData;

import java.util.List;

public class WeatherHistoryAdapter extends RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder> {
    private List<WeatherData> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView icon;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.weather_data);
            icon = v.findViewById(R.id.weather_data_icon);
        }
    }

    public WeatherHistoryAdapter(List<WeatherData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherData item = data.get(position);
        holder.textView.setText(item.getData());
        holder.icon.setImageResource(item.getIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
