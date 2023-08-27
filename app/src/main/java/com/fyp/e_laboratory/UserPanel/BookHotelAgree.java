package com.fyp.e_laboratory.UserPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fyp.e_laboratory.Admin_panel.AddHotels;
import com.fyp.e_laboratory.Model.AdminBookOrderModel;
import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.SharedPrefrence.PrefManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

public class BookHotelAgree extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String uid,hurl,billurl, hotelName;
    ImageView imageView;
    EditText edname,edadd,edphone;
    Button btnconfirmorder;
    int totalbill;
    ProgressDialog progressDialog;
    CheckBox nikahcheck,djcheck,qawalcheck,tailorcheck,salooncheck,photgraphercheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel_agree);
        imageView=findViewById(R.id.urlagree);
        edname=findViewById(R.id.weddbook_name);
        edadd=findViewById(R.id.weddbook_address);
        edphone=findViewById(R.id.weddingbook_phone);
        btnconfirmorder=findViewById(R.id.confirm_button);
        nikahcheck=findViewById(R.id.nikahbook1);
        djcheck=findViewById(R.id.djbook);
        qawalcheck=findViewById(R.id.qawalbook);
        tailorcheck=findViewById(R.id.talorbook);
        salooncheck=findViewById(R.id.sloonbook);
        photgraphercheck=findViewById(R.id.photographatbook);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Order Delivring");
        progressDialog.setMessage("Wait........");



        intent=getIntent();
        uid=intent.getStringExtra("uid");
        hurl=intent.getStringExtra("hurl");
        billurl=intent.getStringExtra("geturl");
        hotelName=intent.getStringExtra("name");
        Picasso.get().load(hurl).into(imageView);
        PrefManager prefManager=new PrefManager(this);

        btnconfirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nikah = "no", dj = "no", qawaali = "no", tailor = "no", salon = "no", photog = "no";
                if(nikahcheck.isChecked())
                    nikah = "yes";
                if(djcheck.isChecked())
                    dj = "yes";
                if(qawalcheck.isChecked())
                    qawaali = "yes";
                if(tailorcheck.isChecked())
                    tailor = "yes";
                if(salooncheck.isChecked())
                    salon = "yes";
                if(photgraphercheck.isChecked())
                    photog = "yes";

                uploadorder(prefManager.getUserID(),uid,edname.getText().toString(),edadd.getText().toString(),edphone.getText().toString(),nikah,dj,qawaali,tailor,salon, photog);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                    }
    }
    public void uploadorder(String userid,String posterid, String holdername, String holderaddress, String holderphone, String holdernikah, String holderdj, String holderqawal, String holdertailor, String holdersloon, String holderphotographar){
        progressDialog.show();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookOrder").child(userid);
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("BookOrder").child(posterid);
        DatabaseReference adminReference = FirebaseDatabase.getInstance().getReference().child("BookOrderAdmin");

        OrdersModels ordersModels=new OrdersModels(userid,posterid,holdername,holderaddress,holderphone,holdernikah,holderdj,holderqawal,holdertailor,holdersloon,holderphotographar);
        ordersModels.setHotelUrl(hurl);

        databaseReference1.push().setValue(ordersModels);
        databaseReference.push().setValue(ordersModels).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Date currentTime = Calendar.getInstance().getTime();

                AdminBookOrderModel model = new AdminBookOrderModel();
                model.setUserName(holdername);
                model.setHotelName(hotelName);
                model.setHotelPic(hurl);
                model.setTime(currentTime.toString());
                adminReference.push().setValue(model);
                progressDialog.dismiss();
                Toast.makeText(BookHotelAgree.this, "Service Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(BookHotelAgree.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
