package com.appstacks.indiannaukribazaar.NewActivities.KycPaidJobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.FirebaseModels.PersonalInformationModel;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityPersonalInformationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PersonalInformationActivity extends AppCompatActivity {

    ActivityPersonalInformationBinding binding;
    String firstName, lastName, userAddress, city, zipCode, gst;
    String uniqueKey;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("UsersInfo");


        binding.nextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();

            }
        });


    }

    private void uploadData() {
        //dialog
        progressDialog.setMessage("Please Wait. . . .");
        progressDialog.setCancelable(true);
        progressDialog.show();


        firstName = binding.etfirstName.getText().toString();
        lastName = binding.etlastName.getText().toString();
        userAddress = binding.etuserAddress.getText().toString();
        city = binding.etCity.getText().toString();
        zipCode = binding.etZipCode.getText().toString();
        gst = binding.etGst.getText().toString();
        uniqueKey = UUID.randomUUID().toString();


        PersonalInformationModel model = new PersonalInformationModel(firstName, lastName, userAddress, city, zipCode, gst, uniqueKey);

        databaseReference.child(uniqueKey).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
                    task.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                emptyBoxes();

                                startActivity(new Intent(PersonalInformationActivity.this, DocumentVerifactionActivity.class));
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(PersonalInformationActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }

    private void emptyBoxes() {

        binding.etfirstName.setText("");
        binding.etlastName.setText("");
        binding.etuserAddress.setText("");
        binding.etCity.setText("");
        binding.etZipCode.setText("");
        binding.etGst.setText("");


    }

    private void validation() {


        if (binding.etfirstName.getText().toString().isEmpty()) {
            binding.etfirstName.setError("Please Type your first name");
        } else if (binding.etlastName.getText().toString().isEmpty()) {
            binding.etlastName.setError("Please Type your last name");
        } else if (binding.etuserAddress.getText().toString().isEmpty()) {
            binding.etuserAddress.setError("Please Type your Address");
        } else if (binding.etCity.getText().toString().isEmpty()) {
            binding.etCity.setError("Please Type your City");
        } else if (binding.etZipCode.getText().toString().isEmpty()) {
            binding.etZipCode.setError("Please Type Country Zip Code");
        } else if (binding.etGst.getText().toString().isEmpty()) {
            binding.etGst.setError("Please Type GST");
        } else {
            uploadData();
        }

    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn) {
            if (binding.etGst.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_visibility);
                //Show Password
                binding.etGst.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_off);
                //Hide Password
                binding.etGst.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

    }


}