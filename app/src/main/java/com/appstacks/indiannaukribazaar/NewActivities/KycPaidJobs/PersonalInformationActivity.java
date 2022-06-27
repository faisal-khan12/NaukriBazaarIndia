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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PersonalInformationActivity extends AppCompatActivity {

    ActivityPersonalInformationBinding binding;
    String firstName, lastName, userAddress, city, zipCode, gst;
    String uniqueKey;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            user = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
        firstName = binding.firstnameBox.getText().toString();
        lastName = binding.lastnamebox.getText().toString();
        userAddress = binding.addressBox.getText().toString();
        city = binding.cityBox.getText().toString();
        zipCode = binding.zipbox.getText().toString();
        gst = binding.gstBox.getText().toString();
        uniqueKey = UUID.randomUUID().toString();


        PersonalInformationModel model = new PersonalInformationModel(
                firstName, lastName, userAddress, city, zipCode, gst, uniqueKey,
                user,"","","");

        databaseReference.child(user).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
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

        binding.firstnameBox.setText("");
        binding.lastnamebox.setText("");
        binding.addressBox.setText("");
        binding.cityBox.setText("");
        binding.zipbox.setText("");
        binding.gstBox.setText("");


    }

    private void validation() {


        if (binding.firstnameBox.getText().toString().isEmpty()) {
            binding.firstnameBox.setError("Please Type your first name");
        } else if (binding.lastnamebox.getText().toString().isEmpty()) {
            binding.lastnamebox.setError("Please Type your last name");
        } else if (binding.addressBox.getText().toString().isEmpty()) {
            binding.addressBox.setError("Please Type your Address");
        } else if (binding.cityBox.getText().toString().isEmpty()) {
            binding.cityBox.setError("Please Type your City");
        } else if (binding.zipbox.getText().toString().isEmpty()) {
            binding.zipbox.setError("Please Type Country Zip Code");
        } else if (binding.gstBox.getText().toString().isEmpty()) {
            binding.gstBox.setError("Please Type GST");
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