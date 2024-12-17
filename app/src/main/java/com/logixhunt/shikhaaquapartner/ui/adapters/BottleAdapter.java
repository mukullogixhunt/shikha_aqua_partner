package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.databinding.BottleAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.OrderAdapterBinding;
import com.logixhunt.shikhaaquapartner.model.BottleModel;
import com.logixhunt.shikhaaquapartner.utils.MaxValueInputFilter;

import java.util.List;

public class BottleAdapter extends RecyclerView.Adapter<BottleAdapter.MyViewHolder> {

    private Context context;
    private List<BottleModel> items;

    public BottleAdapter(Context context, List<BottleModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BottleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BottleAdapterBinding binding = BottleAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new BottleAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BottleAdapter.MyViewHolder holder, int position) {
        BottleModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvName.setText(item.getmBottleCompany());
        holder.binding.tvTotalBottles.setText(item.getTotalDeliveredBottle());
        holder.binding.tvPendingBottles.setText(item.getTotalPendingBottle());

        if (item.getTotalPendingBottle()!=null && !item.getTotalPendingBottle().isEmpty()){
            int maxAllowedValue = Integer.parseInt(item.getTotalPendingBottle());

            InputFilter[] filters = new InputFilter[] { new MaxValueInputFilter(maxAllowedValue) };
            holder.binding.etCollectedBottle.setFilters(filters);

            holder.binding.etCollectedBottle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Not needed for this implementation
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Not needed for this implementation
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String input = s.toString();
                    if (!input.isEmpty()) {
                        item.setCollectedQty(input);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BottleAdapterBinding binding;

        MyViewHolder(BottleAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
