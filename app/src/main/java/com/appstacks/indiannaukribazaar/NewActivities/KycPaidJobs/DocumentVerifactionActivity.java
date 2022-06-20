package com.appstacks.indiannaukribazaar.NewActivities.KycPaidJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityDocumentVerifactionBinding;

public class DocumentVerifactionActivity extends AppCompatActivity {
    
    ActivityDocumentVerifactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentVerifactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        
        binding.nationalIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocumentVerifactionActivity.this,ScanFrontActivity.class));
            }
        });


        binding.passportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocumentVerifactionActivity.this,ScanFrontActivity.class));
            }
        });

        binding.driverLicenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocumentVerifactionActivity.this,ScanFrontActivity.class));
            }
        });
    }

}