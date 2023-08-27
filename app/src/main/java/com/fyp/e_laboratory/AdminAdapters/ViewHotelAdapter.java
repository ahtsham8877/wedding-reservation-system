package com.fyp.e_laboratory.AdminAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.e_laboratory.Model.ApointmentModel;
import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.UserPanel.BookHotelAgree;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewHotelAdapter extends RecyclerView.Adapter<ViewHotelAdapter.myHoder> {
    Context context;
    List<ApointmentModel> apointmentModelList;

    public ViewHotelAdapter(Context context, List<ApointmentModel> apointmentModelList) {
        this.context = context;
        this.apointmentModelList = apointmentModelList;
    }

    @NonNull
    @Override
    public ViewHotelAdapter.myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newappartment_item,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHotelAdapter.myHoder holder, int position) {
        ApointmentModel apointmentModel=apointmentModelList.get(position);
        holder.tvname.setText(apointmentModel.getName());
        holder.tvaddress.setText(apointmentModel.getAddress());
        holder.tvrate.setText(apointmentModel.getAmount());
        holder.tvphone.setText(apointmentModel.getPhone());

        System.out.println("url is_____________________"+apointmentModel.getUrl());
        Picasso.get().load(apointmentModel.getHotelurl()).into(holder.cardshowsss);
        holder.btndelivry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, BookHotelAgree.class);
                intent.putExtra("name",apointmentModel.getName());
                intent.putExtra("email",apointmentModel.getEmail());
                intent.putExtra("phone",apointmentModel.getPhone());
                intent.putExtra("address",apointmentModel.getAddress());
                intent.putExtra("time",apointmentModel.getTime());
                intent.putExtra("uid",apointmentModel.getUid());
                intent.putExtra("city",apointmentModel.getCity());
                intent.putExtra("cardnumber",apointmentModel.getCardnumber());
                intent.putExtra("geturl",apointmentModel.getUrl());
                intent.putExtra("amount",apointmentModel.getAmount());
                intent.putExtra("hurl",apointmentModel.getHotelurl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //AddApointment(apointmentModel.getName(),apointmentModel.getEmail(),apointmentModel.getPhone(),apointmentModel.getAddress(),apointmentModel.getTime(),apointmentModel.getUid(),apointmentModel.getCity(),apointmentModel.getCardnumber(),apointmentModel.getUrl(),apointmentModel.getAmount(),apointmentModel.getHotelurl());
//                Intent myactivity = new Intent(context.getApplicationContext(), SendPdfReportd.class);
//                myactivity.putExtra("id",apointmentModel.getUid());
//                myactivity.putExtra("pname",apointmentModel.getName());
//                myactivity.putExtra("number",apointmentModel.getPhone());
//                myactivity.putExtra("address",apointmentModel.getAddress());
//                myactivity.putExtra("url",apointmentModel.getUrl());
//                myactivity.putExtra("amount",apointmentModel.getAmount());
//                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
//                context.getApplicationContext().startActivity(myactivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return apointmentModelList.size();
    }

    public class myHoder extends RecyclerView.ViewHolder {
        TextView tvname,tvphone,tvaddress,tvrate;
        ImageView cardshowsss;
        CardView btndelivry;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.bookedname);
            tvaddress=itemView.findViewById(R.id.bookedaddress);
            tvphone=itemView.findViewById(R.id.bookedphone);
            tvrate=itemView.findViewById(R.id.bookedrate);
        //    city=itemView.findViewById(R.id.city);

            cardshowsss=itemView.findViewById(R.id.bookedimage);
//            billamount=itemView.findViewById(R.id.billamount);
           btndelivry=itemView.findViewById(R.id.btnnextcard);


        }
    }
}
