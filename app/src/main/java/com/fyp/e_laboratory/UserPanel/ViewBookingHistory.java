package com.fyp.e_laboratory.UserPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.fyp.e_laboratory.AdminAdapters.ManageHotelAdpt;
import com.fyp.e_laboratory.AdminAdapters.ViewHotelBooking;
import com.fyp.e_laboratory.Admin_panel.ViewApointMents;
import com.fyp.e_laboratory.Model.ApointmentModel;
import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.SharedPrefrence.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingHistory extends AppCompatActivity {
    RecyclerView recyclerView;

    List<OrdersModels> apointmentModelList=new ArrayList<>();
    DatabaseReference databaseReference;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_reports);
        recyclerView=findViewById(R.id.myreports);
        prefManager=new PrefManager(getApplicationContext());
        String uid=prefManager.getUserID();

        databaseReference = FirebaseDatabase.getInstance().getReference("BookOrder").child(prefManager.getUserID());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    OrdersModels apointmentModel=dataSnapshot.getValue(OrdersModels.class);

                    apointmentModelList.add(apointmentModel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new ViewHotelBooking(getApplicationContext(),apointmentModelList));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewBookingHistory.this, "Something Wrong :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
