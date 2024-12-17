package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.databinding.BottleAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.ReportBottleAdapterBinding;
import com.logixhunt.shikhaaquapartner.model.BottleModel;
import com.logixhunt.shikhaaquapartner.utils.MaxValueInputFilter;

import java.util.List;

public class ReportBottleAdapter extends RecyclerView.Adapter<ReportBottleAdapter.MyViewHolder> {

    private Context context;
    private List<BottleModel> items;

    public ReportBottleAdapter(Context context, List<BottleModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ReportBottleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReportBottleAdapterBinding binding = ReportBottleAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ReportBottleAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportBottleAdapter.MyViewHolder holder, int position) {
        BottleModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvName.setText(item.getmBottleCompany());
        holder.binding.tvCollectedBottles.setText(item.getTotalReturnBottle());
//        holder.binding.tvPendingBottles.setText(item.getTotalPendingBottle());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ReportBottleAdapterBinding binding;

        MyViewHolder(ReportBottleAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
