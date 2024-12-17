package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.databinding.CollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.OrderAdapterBinding;
import com.logixhunt.shikhaaquapartner.gps.GpsTracker;
import com.logixhunt.shikhaaquapartner.listeners.RequestOnStatusChangeListener;
import com.logixhunt.shikhaaquapartner.listeners.RequestPaymentCollectionListener;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.OrderModel;
import com.logixhunt.shikhaaquapartner.ui.activities.MainActivity;
import com.logixhunt.shikhaaquapartner.ui.activities.OrderDetailsActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.IndianCurrencyFormat;
import com.logixhunt.shikhaaquapartner.utils.Utils;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {


    private Context context;
    private List<CollectionModel> items;

    private RequestPaymentCollectionListener requestPaymentCollectionListener;


    public CollectionAdapter(Context context, List<CollectionModel> items, RequestPaymentCollectionListener requestPaymentCollectionListener) {
        this.context = context;
        this.items = items;
        this.requestPaymentCollectionListener = requestPaymentCollectionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CollectionAdapterBinding binding = CollectionAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CollectionModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvCustomerName.setText(item.getmUserName());
        holder.binding.tvAddress.setText(item.getmUserAddress());
        holder.binding.tvAmount.setText(new IndianCurrencyFormat().inCuFormatText(item.getTotalBalanceAmt()));


        holder.binding.btnAddCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPaymentCollectionListener.onPaymentCollect(item);
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
        CollectionAdapterBinding binding;

        MyViewHolder(CollectionAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
