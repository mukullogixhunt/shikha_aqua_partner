package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.databinding.BottleCollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.CollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.listeners.RequestBottleCollectionListener;
import com.logixhunt.shikhaaquapartner.listeners.RequestPaymentCollectionListener;
import com.logixhunt.shikhaaquapartner.model.BottleCollectionModel;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.ui.activities.OrderDetailsActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.IndianCurrencyFormat;

import java.util.List;

public class BottleCollectionAdapter extends RecyclerView.Adapter<BottleCollectionAdapter.MyViewHolder> {


    private Context context;
    private List<BottleCollectionModel> items;
    private RequestBottleCollectionListener requestBottleCollectionListener;


    public BottleCollectionAdapter(Context context, List<BottleCollectionModel> items, RequestBottleCollectionListener requestBottleCollectionListener) {
        this.context = context;
        this.items = items;
        this.requestBottleCollectionListener = requestBottleCollectionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BottleCollectionAdapterBinding binding = BottleCollectionAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BottleCollectionModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvCustomerName.setText(item.getmUserName());
        holder.binding.tvAddress.setText(item.getmUserAddress());
        holder.binding.tvBottles.setText(String.format("%s Bottles", item.getmTotalBottle()));


        holder.binding.btnAddCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBottleCollectionListener.onBottleCollect(item);
            }
        });


        holder.binding.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+item.getmUserMobile()));
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BottleCollectionAdapterBinding binding;

        MyViewHolder(BottleCollectionAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
