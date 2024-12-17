package com.logixhunt.shikhaaquapartner.ui.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.databinding.OrderAdapterBinding;
import com.logixhunt.shikhaaquapartner.gps.GpsTracker;
import com.logixhunt.shikhaaquapartner.listeners.RequestOnStatusChangeListener;
import com.logixhunt.shikhaaquapartner.model.OrderModel;
import com.logixhunt.shikhaaquapartner.ui.activities.MainActivity;
import com.logixhunt.shikhaaquapartner.ui.activities.OrderDetailsActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.Utils;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {


    private Context context;
    private List<OrderModel> items;
    private RequestOnStatusChangeListener requestOnStatusChangeListener;

    private GpsTracker gpsTracker;


    public OrderAdapter(Context context, List<OrderModel> items, RequestOnStatusChangeListener requestOnStatusChangeListener) {
        this.context = context;
        this.items = items;
        this.requestOnStatusChangeListener = requestOnStatusChangeListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderAdapterBinding binding = OrderAdapterBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderModel item = items.get(holder.getAdapterPosition());

        holder.binding.tvCustomerName.setText(item.getmUserName());
        holder.binding.tvAddress.setText(item.getmOrderAddress());
        holder.binding.tvBookingNo.setText(String.format("#%s", item.getmOrderBillNo()));

        holder.binding.tvDateTime.setText(String.format("%s ,%s", Utils.changeDateFormat(Constant.yyyyMMdd, Constant.ddMMMyyyy, item.getmOrderDelvDate()), item.getmOrderDelvTime()));


        switch (item.getmOrderStatus()) {
            case "1":
                holder.binding.tvStatus.setText(R.string.pending);
                holder.binding.btnAccept.setText(R.string.accept);
                holder.binding.tvStatus.setBackgroundColor(context.getColor(R.color.pending));
                holder.binding.btnAccept.setBackgroundColor(context.getColor(R.color.accepted));
                holder.binding.view.setVisibility(View.VISIBLE);
                holder.binding.btnLayout.setVisibility(View.VISIBLE);
                break;

            case "2":
                holder.binding.tvStatus.setText(R.string.accepted);
                holder.binding.btnAccept.setText(R.string.dispatch_);
                holder.binding.tvStatus.setBackgroundColor(context.getColor(R.color.accepted));
                holder.binding.btnAccept.setBackgroundColor(context.getColor(R.color.running));
                holder.binding.view.setVisibility(View.VISIBLE);
                holder.binding.btnLayout.setVisibility(View.VISIBLE);
                break;

            case "3":
                holder.binding.tvStatus.setText(R.string.dispatched);
                holder.binding.btnAccept.setText(R.string.mark_as_complete);
                holder.binding.tvStatus.setBackgroundColor(context.getColor(R.color.running));
                holder.binding.btnAccept.setBackgroundColor(context.getColor(R.color.completed));
                holder.binding.view.setVisibility(View.VISIBLE);
                holder.binding.btnLayout.setVisibility(View.VISIBLE);
                break;

            case "4":
                holder.binding.tvStatus.setText(R.string.delivered);
                holder.binding.tvStatus.setBackgroundColor(context.getColor(R.color.completed));
                holder.binding.view.setBackgroundColor(context.getColor(R.color.transparent));
                holder.binding.btnLayout.setVisibility(View.GONE);
                break;
        }

        holder.binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOnStatusChangeListener.onStatusChange(item);
            }
        });


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra(Constant.BundleExtras.ORDER_ID, item.getmOrderId());
                context.startActivity(intent);
            }
        });

        holder.binding.tvGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    allowPermissionDialog();
                } else {
                    String[] latLng = getLocation();
                    if (latLng != null) {
                        if (latLng[0].equalsIgnoreCase( context.getResources().getString(R.string._00))
                                || latLng[1].equalsIgnoreCase( context.getResources().getString(R.string._00))) {
                            Toast.makeText(context, "" +  context.getResources().getString(R.string.unable_to_find_location), Toast.LENGTH_SHORT).show();
                        } else {
                            String sourceLatitude = latLng[0];
                            String sourceLongitude = latLng[1];

                            if (!item.getmOrderLat().isEmpty() && !item.getmOrderLong().isEmpty()) {

                                String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + item.getmOrderLat() + "," + item.getmOrderLong();
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                context.startActivity(intent);

                            } else {
                                Toast.makeText(context, "User's location not found! Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }



                }

            }
        });


    }


    private void allowPermissionDialog() {
        ActivityCompat.requestPermissions(MainActivity.mainActivity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public String[] getLocation() {
        gpsTracker = new GpsTracker(context);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            String[] latLng = new String[2];
            latLng[0] = String.valueOf(latitude);
            latLng[1] = String.valueOf(longitude);
            return latLng;
        } else {
            gpsTracker.showSettingsAlert();
            return null;
        }

    }





    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        OrderAdapterBinding binding;

        MyViewHolder(OrderAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
