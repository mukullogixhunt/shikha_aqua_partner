package com.logixhunt.shikhaaquapartner.ui.adapters.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.databinding.CustomSpinnerAdapterBinding;
import com.logixhunt.shikhaaquapartner.model.StateModel;

import java.util.List;

public class StateSpinnerAdapter extends ArrayAdapter<StateModel> {

    private final LayoutInflater inflater;

    public StateSpinnerAdapter(@NonNull Context context, @NonNull List<StateModel> items) {
        super(context, 0, items);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, android.R.layout.simple_spinner_item);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
    }

    private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource) {
        final CustomSpinnerAdapterBinding binding;
        if (convertView == null) {
            binding = CustomSpinnerAdapterBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (CustomSpinnerAdapterBinding) convertView.getTag();
        }

        // Get the custom object from the list
        StateModel customItem = getItem(position);

        // Populate the views using the custom object
        if (customItem != null) {
            binding.tvSpinner.setText(customItem.getmStateName());
            // You can also set other views in the layout with data from the custom object if needed.
        }

        if (position == getCount() - 1) {
//            binding.viewBorder.setVisibility(View.GONE);
            binding.spinnerLayout.setBackground(getContext().getDrawable(R.drawable.spinner_list_bg_last));
        } else {
//            binding.viewBorder.setVisibility(View.VISIBLE);
            binding.spinnerLayout.setBackground(getContext().getDrawable(R.drawable.spinner_list_bg_middle));
        }

        return convertView;
    }

}
