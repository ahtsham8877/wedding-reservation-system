package com.fyp.e_laboratory.Admin_panel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.e_laboratory.AdminAdapters.ViewHotelBooking;
import com.fyp.e_laboratory.Model.AdminBookOrderModel;
import com.fyp.e_laboratory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdminBookOrderAdapter extends RecyclerView.Adapter<AdminBookOrderAdapter.ViewHolder>{

    Context context;
    List<AdminBookOrderModel> list;

    public AdminBookOrderAdapter(Context context, List<AdminBookOrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_iv_admin_view_booked,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdminBookOrderModel model = list.get(position);
        Picasso.get().load(model.getHotelPic()).into(holder.img_hotel_page);
        holder.txt_userName.setText("User Name: "+model.getUserName());
        holder.txt_hotelName.setText("Hotel Name: "+model.getHotelName());
        holder.txt_time.setText("Time: "+model.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_hotel_page;
        TextView txt_userName, txt_hotelName, txt_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hotel_page = itemView.findViewById(R.id.img_hotel_page);
            txt_userName = itemView.findViewById(R.id.txt_userName);
            txt_hotelName = itemView.findViewById(R.id.txt_hotelName);
            txt_time = itemView.findViewById(R.id.txt_time);
        }
    }
}
