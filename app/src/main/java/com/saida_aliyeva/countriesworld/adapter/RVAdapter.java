package com.saida_aliyeva.countriesworld.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.countriesworld.model_class.Countries;
import com.saida_aliyeva.countriesworld.Listener;
import com.saida_aliyeva.countriesworld.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVViewHolder>  implements Filterable {
    Context context;
   private List<Countries> countriesList;
   private List<Countries> countriesListFiltered;
   private Listener listener;

    public RVAdapter(Context context, List<Countries> countriesList,Listener listener) {
        this.context = context;
        this.countriesList = countriesList;
        this.countriesListFiltered = countriesList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_countries, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.bind(countriesList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countriesList = countriesListFiltered;
                } else {
                    List<Countries> filteredList = new ArrayList<>();
                    for (Countries row : countriesListFiltered) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getCapital().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    countriesList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countriesList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countriesList = (ArrayList<Countries>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

