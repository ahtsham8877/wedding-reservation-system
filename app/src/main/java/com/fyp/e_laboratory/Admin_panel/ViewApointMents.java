package com.fyp.e_laboratory.Admin_panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.fyp.e_laboratory.AdminAdapters.ViewHotelAdapter;
import com.fyp.e_laboratory.AdminAdapters.ViewHotelBooking;
import com.fyp.e_laboratory.Model.AdminBookOrderModel;
import com.fyp.e_laboratory.Model.ApointmentModel;
import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.SharedPrefrence.PrefManager;
import com.fyp.e_laboratory.UserPanel.OrdersModels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewApointMents extends AppCompatActivity {

    RecyclerView recyclerView;
    Intent intent;
    String value;
    List<AdminBookOrderModel> apointmentModelList=new ArrayList<>();
    DatabaseReference databaseReference;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_apoint_ments);

        prefManager=new PrefManager(this);
        intent=getIntent();
        value=intent.getStringExtra("v");
        System.out.println("v_____________________"+prefManager.getUserID());
        recyclerView=findViewById(R.id.aplistrecy);
        databaseReference = FirebaseDatabase.getInstance().getReference("BookOrderAdmin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AdminBookOrderModel model = dataSnapshot.getValue(AdminBookOrderModel.class);

                    apointmentModelList.add(model);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new AdminBookOrderAdapter(getApplicationContext(),apointmentModelList));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewApointMents.this, "Something Wrong :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}