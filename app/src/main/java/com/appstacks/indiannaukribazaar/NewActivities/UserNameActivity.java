package com.appstacks.indiannaukribazaar.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.appstacks.indiannaukribazaar.NewActivities.Models.UserDataModel;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityUserNameBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class UserNameActivity extends AppCompatActivity {

    ActivityUserNameBinding binding;
    DatabaseReference userRef;
    String uiid;
    UserDataModel dataModel;
    String usernameCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userRef = FirebaseDatabase.getInstance().getReference("AllUsers");


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        UserDataModel data = s.getValue(UserDataModel.class);
                        assert data != null;
                        usernameCheck = data.getUserName();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                uiid = UUID.randomUUID().toString();
                String uSeRNameBox = binding.usernameBox.getText().toString();
                if (uSeRNameBox.isEmpty()) {
                    binding.usernameBox.setError("Field is Empty");
                    return;
                } else if (binding.usernameBox.getText().toString().equals(usernameCheck)) {
                    binding.availableTV.setVisibility(View.INVISIBLE);
                    binding.notAvailableTv.setVisibility(View.VISIBLE);
                    binding.notAvailableTv.setText("@" + binding.usernameBox.getText().toString() + " is Already Exists");
                    return;
                } else {
//                    UserDataModel dataModel = new UserDataModel(uiid, binding.usernameBox.getText().toString(), "@gmail", "full", "--", "000");
//                    userRef.child(uiid).setValue(dataModel);

                    Intent intent = new Intent(UserNameActivity.this, User_ProfileActivity.class);
                    intent.putExtra("username", binding.usernameBox.getText().toString());
                    startActivity(intent);
                    finishAffinity();

                }

            }
        });


        binding.usernameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.usernameBox.getText().toString().equals(usernameCheck)) {
                    binding.availableTV.setVisibility(View.INVISIBLE);
                    binding.notAvailableTv.setVisibility(View.VISIBLE);
                    binding.notAvailableTv.setText("@" + binding.usernameBox.getText().toString() + " is Already Exists");
                } else {
                    binding.notAvailableTv.setVisibility(View.INVISIBLE);
                    binding.availableTV.setVisibility(View.VISIBLE);
                    binding.availableTV.setText("@" + binding.usernameBox.getText().toString() + " is Available");
                }


            }
        });


    }
}