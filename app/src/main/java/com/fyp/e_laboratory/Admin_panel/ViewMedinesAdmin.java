package com.fyp.e_laboratory.Admin_panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.fyp.e_laboratory.AdminAdapters.Posts_adapters;
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

public class ViewMedinesAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ApointmentModel> medicineDelivryList=new ArrayList<>();
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medines_admin);
        recyclerView=findViewById(R.id.medirecyadmin);
        prefManager=new PrefManager(getApplicationContext());
        String uid=prefManager.getUserID();
        getData(uid);
    }
    public void getData(String uid){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("HotelPostdata");
        databaseReference.child(prefManager.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    ApointmentModel pdfModel=snapshot1.getValue(ApointmentModel.class);
//                    if (pdfModel.getUid().equals(uid)) {
                    medicineDelivryList.add(pdfModel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new Posts_adapters(getApplicationContext(), medicineDelivryList));
                    //     }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ViewMedinesAdmin.this, "error "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}