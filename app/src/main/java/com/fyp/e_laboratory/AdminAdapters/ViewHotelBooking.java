package com.fyp.e_laboratory.AdminAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.UserPanel.OrdersModels;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewHotelBooking extends RecyclerView.Adapter<ViewHotelBooking.myHoder>{
    Context context;
    List<OrdersModels> apointmentModelList;

    public ViewHotelBooking(Context context, List<OrdersModels> apointmentModelList) {
        this.context = context;
        this.apointmentModelList = apointmentModelList;
    }

    @NonNull
    @Override
    public ViewHotelBooking.myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHotelBooking.myHoder holder, int position) {
        OrdersModels apointmentModel=apointmentModelList.get(position);
        holder.tvname.setText(apointmentModel.getHoldername());
        holder.tvaddress.setText(apointmentModel.getHolderaddress());
        holder.tvphone.setText(apointmentModel.getHolderphone());
        holder.tvdj.setText(apointmentModel.getHolderdj());
        holder.tvqawal.setText(apointmentModel.getHolderqawal());
        holder.tvphotographaer.setText(apointmentModel.getHolderphotographar());
        holder.tvtailor.setText(apointmentModel.getHoldertailor());
        holder.tvnikah.setText(apointmentModel.getHoldernikah());
        holder.tbsloon.setText(apointmentModel.getHoldersloon());
        Picasso.get().load(apointmentModel.getHotelUrl()).into(holder.img_hotel);
    }

    @Override
    public int getItemCount() {
        return apointmentModelList.size();
    }

    public class myHoder extends RecyclerView.ViewHolder {
        TextView tvname,tvphone,tvaddress,tvnikah,tvdj,tvphotographaer,tvqawal,tvtailor,tbsloon;
        ImageView cardshowsss, img_hotel;
        Button btndelivry;
        public myHoder(@NonNull View itemView) {
            super(itemView);
            img_hotel = itemView.findViewById(R.id.img_hotel);
            tvname=itemView.findViewById(R.id.usernamerequired);
            tvaddress=itemView.findViewById(R.id.address_idrequired);
            tvphone=itemView.findViewById(R.id.phonenumberrequired);
            tvnikah=itemView.findViewById(R.id.nikahrequired);
            tvdj=itemView.findViewById(R.id.djrequired);

            cardshowsss=itemView.findViewById(R.id.cardshowwws);
            tvphotographaer=itemView.findViewById(R.id.photographatbookrequired);
            tvqawal=itemView.findViewById(R.id.qawalrequired);
            tvtailor=itemView.findViewById(R.id.tailorrequired);
            tbsloon=itemView.findViewById(R.id.sloonrequired);

            btndelivry=itemView.findViewById(R.id.medi_delivry_items);
        }
    }
}
