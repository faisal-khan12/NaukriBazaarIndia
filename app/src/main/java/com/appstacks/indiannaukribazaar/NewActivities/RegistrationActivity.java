package com.appstacks.indiannaukribazaar.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.appstacks.indiannaukribazaar.NewActivities.Models.DeviceDataModel;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityRegistrationBinding;
import com.appstacks.indiannaukribazaar.databinding.AlertDialogDeviceBinding;
import com.appstacks.indiannaukribazaar.databinding.HandloadingDialogLayoutBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    DatabaseReference deviceRef;
    AlertDialog loadingDialog;

    String countrycode, mobileNumber;
    //    String registerNumber,registerDevice;\
    String num;

    ProgressDialog dialog;
    String android_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        deviceRef = FirebaseDatabase.getInstance().getReference().child("RegDevices");


        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");


        loadingAlertDialog();


        countrycode = binding.countryCode.getText().toString();

        binding.phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (num != null) {
                    if (num.equals(countrycode + binding.phoneNumber.getText().toString())) {
                        binding.registerBtn.setVisibility(View.VISIBLE);
                        binding.registerBtn2.setVisibility(View.INVISIBLE);
                    } else {
                        binding.registerBtn.setVisibility(View.INVISIBLE);
                        binding.registerBtn2.setVisibility(View.VISIBLE);
                    }
                }


            }
        });


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxValidation();


            }
        });

        binding.registerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(RegisterActivity.this, "Your Device is Registered with your Number Please Enter Registered Number to Continue ", Toast.LENGTH_SHORT).show();
//                View dialogView = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.alert_dialog_device,null);

                AlertDialogDeviceBinding dialogDeviceBinding = AlertDialogDeviceBinding.inflate(getLayoutInflater());
                AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this)
                        .setView(dialogDeviceBinding.getRoot()).create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialogDeviceBinding.canceliMgV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();


            }
        });


//        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RegistrationActivity.this,OTPActivity.class));
//
//
//            }
//        });


    }

    public void checkboxValidation() {
        if (binding.checkBox.isChecked()) {
//            String number = binding.phoneNumber.getText().toString();
            mobileNumber = binding.phoneNumber.getText().toString();
            if (mobileNumber.isEmpty()) {
                binding.phoneNumber.setError("Field Cannot be Empty");
                return;
            }
//            else if (number.length() < 10){
//                binding.phoneNumber.setError("Phone Number length is wrong");
//                return;
//            }
            else {
                loadingDialog.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(countrycode + binding.phoneNumber.getText().toString(),
                        60, TimeUnit.SECONDS, RegistrationActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                loadingDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                super.onCodeSent(s, forceResendingToken);
                                loadingDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                                intent.putExtra("mobileNumber", countrycode + binding.phoneNumber.getText().toString());
                                intent.putExtra("verificationId", verificationID);
                                intent.putExtra("deviceID", android_id);
                                startActivity(intent);
                                finish();
                            }
                        }
                );


//                Intent intent = new Intent(RegisterActivity.this,OtpActivity.class);
//                intent.putExtra("number",countrycode+number);
//                startActivity(intent);
//                Toast.makeText(this, , Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Please Read terms and conditions.", Toast.LENGTH_SHORT).show();

        }


    }

    public void loadingAlertDialog() {

        HandloadingDialogLayoutBinding handloadingBinding = HandloadingDialogLayoutBinding.inflate(getLayoutInflater());
        loadingDialog = new AlertDialog.Builder(RegistrationActivity.this)
                .setView(handloadingBinding.getRoot()).create();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        deviceRef.child(android_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DeviceDataModel dataNo = snapshot.getValue(DeviceDataModel.class);
                    num = dataNo.getUserPhoneNumber();
//                    binding.textViewtv.setText(num);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        deviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(android_id).exists()) {
//                    if (snapshot.child("userPhoneNumber").equals(countrycode+binding.phoneNumber.getText().toString()))

//                    Toast.makeText(MainActivity.this, "Already Registered From This Device", Toast.LENGTH_SHORT).show();
                    binding.registerBtn.setVisibility(View.INVISIBLE);
                    binding.registerBtn2.setVisibility(View.VISIBLE);
                } else {
                    binding.registerBtn.setVisibility(View.VISIBLE);
                    binding.registerBtn2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}