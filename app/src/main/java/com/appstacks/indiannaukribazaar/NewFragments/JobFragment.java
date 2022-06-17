package com.appstacks.indiannaukribazaar.NewFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.FirebaseAdapters.JobAdapter;
import com.appstacks.indiannaukribazaar.FirebaseModels.JobModel;
import com.appstacks.indiannaukribazaar.NewActivities.RegistrationActivity;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.FragmentJobBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobFragment extends Fragment {

    public JobFragment() {
        // Required empty public constructor
    }

    FragmentJobBinding binding;

    DatabaseReference jobRef;
    ArrayList<JobModel> jobList;
    JobAdapter jobAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        binding = FragmentJobBinding.inflate(inflater,container,false);

        jobRef = FirebaseDatabase.getInstance().getReference("AdminPanel").child("job");

        jobList = new ArrayList<>();

        jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot s : snapshot.getChildren()) {
                        JobModel data = s.getValue(JobModel.class);
                        binding.loadingid.setVisibility(View.GONE);
                        jobList.add(data);
                        jobAdapter = new JobAdapter(jobList, getContext());

                        binding.jobRecyclerView.setAdapter(jobAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        binding.jobRecyclerView.setLayoutManager(linearLayoutManager);
//                        binding.loadingFragment.setVisibility(View.GONE);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//
//        if (snapshot.exists()) {
//            listPoet.clear();
//            for (DataSnapshot s : snapshot.getChildren()) {
//                PoetModel data = s.getValue(PoetModel.class);
//
//                listPoet.add(data);
////                        Arrays.sort(new ArrayList[]{listPoet});
//
//                poetFragmentAdapter = new PoetAdapter(listPoet, getContext());
//
//                binding.poetRecyclerView.setAdapter(poetFragmentAdapter);
//
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//                binding.poetRecyclerView.setLayoutManager(gridLayoutManager);
//                binding.loadingFragment.setVisibility(View.GONE);
//
//            }







        return binding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }




}