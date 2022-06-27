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

    String selected = null;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentVerifactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.nationalIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "National ID";
                 intent = new Intent(DocumentVerifactionActivity.this, ScanFrontActivity.class);
                intent.putExtra("selectedDoc", selected);
                startActivity(intent);
            }
        });


        binding.passportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "Passport";
                intent = new Intent(DocumentVerifactionActivity.this, ScanFrontActivity.class);
                intent.putExtra("selectedDoc", selected);
                startActivity(intent);
            }
        });

        binding.driverLicenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "Driver's LicenceImage";
                intent = new Intent(DocumentVerifactionActivity.this, ScanFrontActivity.class);
                intent.putExtra("selectedDoc", selected);
                startActivity(intent);
            }
        });
    }

}