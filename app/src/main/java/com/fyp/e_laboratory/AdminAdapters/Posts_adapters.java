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

import com.fyp.e_laboratory.Model.ApointmentModel;
import com.fyp.e_laboratory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Posts_adapters extends RecyclerView.Adapter<Posts_adapters.myHoder> {
    Context context;
    List<ApointmentModel> apointmentModelList;

    public Posts_adapters(Context context, List<ApointmentModel> apointmentModelList) {
        this.context = context;
        this.apointmentModelList = apointmentModelList;
    }


    @NonNull
    @Override
    public Posts_adapters.myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminpost_items,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Posts_adapters.myHoder holder, int position) {
        ApointmentModel apointmentModel=apointmentModelList.get(position);
        holder.tvname.setText(apointmentModel.getName());
        holder.tvaddress.setText(apointmentModel.getAddress());
        holder.tvtime.setText(apointmentModel.getPhone());
        holder.tvphone.setText(apointmentModel.getTime());
        holder.city.setText(apointmentModel.getCity());
        holder.billamount.setText(apointmentModel.getAmount());
        System.out.println("url is_____________________"+apointmentModel.getUrl());
        Picasso.get().load(apointmentModel.getUrl()).into(holder.cardshowsss);
    }

    @Override
    public int getItemCount() {
        return apointmentModelList.size();
    }

    public class myHoder extends RecyclerView.ViewHolder {
        TextView tvname,tvphone,tvaddress,tvtime,city,billamount;
        ImageView cardshowsss;
        Button btndelivry;
        public myHoder(@NonNull View itemView) {
            super(itemView);

            tvname=itemView.findViewById(R.id.patients_name);
            tvaddress=itemView.findViewById(R.id.patiensAddress);
            tvphone=itemView.findViewById(R.id.patientsphone);
            tvtime=itemView.findViewById(R.id.patientstime);
            city=itemView.findViewById(R.id.city);

            cardshowsss=itemView.findViewById(R.id.cardshowwws);
            billamount=itemView.findViewById(R.id.billamount);
            btndelivry=itemView.findViewById(R.id.medi_delivry_items);
        }
    }
}
