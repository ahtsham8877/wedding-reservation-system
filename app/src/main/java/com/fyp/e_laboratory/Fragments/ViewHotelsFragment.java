package com.fyp.e_laboratory.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fyp.e_laboratory.AdminAdapters.ViewHotelAdapter;
import com.fyp.e_laboratory.Model.ApointmentModel;
import com.fyp.e_laboratory.R;
import com.fyp.e_laboratory.SharedPrefrence.PrefManager;
import com.fyp.e_laboratory.UserPanel.ViewHotels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewHotelsFragment extends Fragment {


    List<ApointmentModel> medicineDelivryList=new ArrayList<>();
    PrefManager prefManager;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_view_hotels, container, false);

        recyclerView=view.findViewById(R.id.rvview);
        prefManager=new PrefManager(getContext());
        String uid=prefManager.getUserID();
        getData(uid);
        return view;
    }

    public void getData(String uid){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("HotelPost");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    ApointmentModel pdfModel=snapshot1.getValue(ApointmentModel.class);
//                    if (pdfModel.getUid().equals(uid)) {
                    medicineDelivryList.add(pdfModel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(new ViewHotelAdapter(getContext(), medicineDelivryList));
                    //     }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "error "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}