package com.appstacks.indiannaukribazaar.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.FirebaseAdapters.JobAdapter;
import com.appstacks.indiannaukribazaar.FirebaseModels.JobModel;
import com.appstacks.indiannaukribazaar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SpinWheelActivity extends AppCompatActivity {

    DatabaseReference jobRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_wheel);


//        jobRef = FirebaseDatabase.getInstance().getReference("AdminPanel").child("job");
//
//
//        jobRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//
//                    for (DataSnapshot s : snapshot.getChildren()) {
//                        JobModel data = s.getValue(JobModel.class);
//                        jobList.add(data);
//                        jobAdapter = new JobAdapter(jobList, getContext());
//
//                        binding.jobRecyclerView.setAdapter(jobAdapter);
//
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                        binding.jobRecyclerView.setLayoutManager(linearLayoutManager);
////                        binding.loadingFragment.setVisibility(View.GONE);
//
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });




    }
}