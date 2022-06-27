package com.appstacks.indiannaukribazaar.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appstacks.indiannaukribazaar.FirebaseModels.JobModel;
import com.appstacks.indiannaukribazaar.NewActivities.Models.UserDataModel;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityJobDetailsBinding;
import com.appstacks.indiannaukribazaar.model.User;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobDetailsActivity extends AppCompatActivity {
    ActivityJobDetailsBinding binding;

    String jobImg;
    String jobTitle, companyName, jobType,uniqueKey, jobTime, jobPostDate,jobPostedTime;
    String applyBefore, jobPriceINR, jobDescription, rolesAndRESPONSIBILITIES,salaryRange,jobLocation;
    String key;
    DatabaseReference timeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        uniqueKey = getIntent().getStringExtra("uniqueKey");
        jobTitle = getIntent().getStringExtra("title");
        companyName = getIntent().getStringExtra("companyname");
        jobType = getIntent().getStringExtra("type");
        jobTime = getIntent().getStringExtra("time");

        salaryRange = getIntent().getStringExtra("salary");
        applyBefore = getIntent().getStringExtra("applybefore");
        jobPriceINR = getIntent().getStringExtra("price");
        jobDescription = getIntent().getStringExtra("jobdes");
        rolesAndRESPONSIBILITIES = getIntent().getStringExtra("getRules");
        jobImg = getIntent().getStringExtra("imageurl");
        jobLocation = getIntent().getStringExtra("location");
        jobPostedTime = getIntent().getStringExtra("jobPostedTime");
        jobPostDate = getIntent().getStringExtra("jobPostDate");


        binding.jobtitle.setText(jobTitle);
        binding.companyjob.setText(companyName);
        binding.jobLocationID.setText(jobLocation);
        binding.jobDescriptionID.setText(jobDescription);
        binding.salaryRangeID.setText(salaryRange);
        binding.applyBeforeID.setText(applyBefore);
        binding.jobPriceINrID.setText(jobPriceINR);
        binding.rolesAndRespinsibilitiesID.setText(rolesAndRESPONSIBILITIES);
        binding.postTimeID.setText(jobPostDate);




        try {
            Glide.with(JobDetailsActivity.this).load(jobImg)
                    .placeholder(R.drawable.avatar_placeholder)
                    .into(binding.jobImgView);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}