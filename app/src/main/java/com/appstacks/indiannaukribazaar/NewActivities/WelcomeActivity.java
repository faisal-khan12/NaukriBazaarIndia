package com.appstacks.indiannaukribazaar.NewActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appstacks.indiannaukribazaar.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding binding;
//    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        auth = FirebaseAuth.getInstance();

//        binding.getStartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this, BoardingActivity.class);
//                startActivity(intent);
//            }
//        });

//        if (auth.getCurrentUser() != null) {
//            binding.getStartBtn.setVisibility(View.INVISIBLE);
//            new Handler().postDelayed(new Runnable(){
//                @Override
//                public void run() {
//                    /* Create an Intent that will start the Menu-Activity. */
//                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                    finish();
//
//                }
//            }, 2000);
//
//
//        }



    }
}