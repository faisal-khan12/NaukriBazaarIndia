package com.appstacks.indiannaukribazaar.NewActivities.KycPaidJobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appstacks.indiannaukribazaar.Activities.ActivityMain;
import com.appstacks.indiannaukribazaar.NewFragments.FindJobsFragments;
import com.appstacks.indiannaukribazaar.R;

public class WelldoneActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welldone);

        Button goBackBTn = findViewById(R.id.goBackBtn);




       goBackBTn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(WelldoneActivity.this, ActivityMain.class));





    }
        });
    }




}