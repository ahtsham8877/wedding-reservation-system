package com.fyp.e_laboratory.UserAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fyp.e_laboratory.Model.Hotellist;
import com.fyp.e_laboratory.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private static ArrayList<Hotellist> list;

    public RecyclerViewAdapter(Context context, ArrayList<Hotellist> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_hotellistlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotellist model = list.get(position);
        Glide.with(context).load(model.getImage()).into(holder.hotelimageview);
        holder.hotelname.setText(model.getHotelname());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hotelimageview;
        TextView  hotelname;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelimageview = itemView.findViewById(R.id.imageviewrvid);
            hotelname=itemView.findViewById(R.id.tvhotelnameid);
        }
    }
}
