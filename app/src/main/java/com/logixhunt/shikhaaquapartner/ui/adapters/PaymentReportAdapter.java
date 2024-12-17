package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logixhunt.shikhaaquapartner.databinding.CollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.PaymentReportAdapterBinding;
import com.logixhunt.shikhaaquapartner.listeners.RequestPaymentCollectionListener;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.PaymentReportModel;
import com.logixhunt.shikhaaquapartner.utils.IndianCurrencyFormat;

import java.util.List;

public class PaymentReportAdapter extends RecyclerView.Adapter<PaymentReportAdapter.MyViewHolder> {


    private Context context;
    private List<PaymentReportModel> items;


    public PaymentReportAdapter(Context context, List<PaymentReportModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PaymentReportAdapterBinding binding = PaymentReportAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PaymentReportModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvCustomerName.setText(item.getmUserName());
        holder.binding.tvCollected.setText(new IndianCurrencyFormat().inCuFormatText(item.getmTransPaidAmount()));
        holder.binding.tvPendingPayment.setText(new IndianCurrencyFormat().inCuFormatText(item.getmTransAmount()));

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        PaymentReportAdapterBinding binding;

        MyViewHolder(PaymentReportAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
