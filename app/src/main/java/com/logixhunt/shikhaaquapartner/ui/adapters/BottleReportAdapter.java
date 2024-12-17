package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.databinding.BottleCollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.BottleReportAdapterBinding;
import com.logixhunt.shikhaaquapartner.listeners.RequestBottleCollectionListener;
import com.logixhunt.shikhaaquapartner.model.BottleCollectionModel;
import com.logixhunt.shikhaaquapartner.model.BottleReportModel;

import java.util.List;

public class BottleReportAdapter extends RecyclerView.Adapter<BottleReportAdapter.MyViewHolder> {


    private Context context;
    private List<BottleReportModel> items;



    public BottleReportAdapter(Context context, List<BottleReportModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BottleReportAdapterBinding binding = BottleReportAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BottleReportModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvCustomerName.setText(item.getmUserName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.binding.rvBottles.setLayoutManager(linearLayoutManager);
        ReportBottleAdapter bottleAdapter = new ReportBottleAdapter(context, item.getBottleList());
        holder.binding.rvBottles.setAdapter(bottleAdapter);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BottleReportAdapterBinding binding;

        MyViewHolder(BottleReportAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
