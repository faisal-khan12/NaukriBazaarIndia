package com.appstacks.indiannaukribazaar.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.appstacks.indiannaukribazaar.NewActivities.BoardingActivity;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.Slider.ImageData;
import com.appstacks.indiannaukribazaar.data.SharedPref;
import com.appstacks.indiannaukribazaar.data.ThisApp;
import com.appstacks.indiannaukribazaar.databinding.ActivitySplashBinding;
import com.appstacks.indiannaukribazaar.utils.PermissionUtil;
import com.appstacks.indiannaukribazaar.utils.Tools;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    private SharedPref sharedPref;
    private boolean on_permission_result = false;
    ActivitySplashBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();


        new LongOperation().execute();

        sharedPref = new SharedPref(this);
        Tools.setSmartSystemBar(this);
        Tools.RTLMode(getWindow());
        ThisApp.get().saveClassLogEvent(getClass());
    }

    private class LongOperation extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("SingleImageLink").document("data");
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {


                            ImageData imageData = ImageData.getInstance();
                            imageData.setImglink(document.getString("imageLink"));
                            imageData.setWebsiteLink(document.getString("websiteLink"));
                            imageData.setText(document.getString("text"));
                        }


                    }
                }
            });
            return null;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        ThisApp.get().resetInfo();
        // permission checker for android M or higher
        if (Tools.needRequestPermission() && !on_permission_result) {
            String[] permission = PermissionUtil.getDeniedPermission(this);
            if (permission.length != 0) {
                requestPermissions(permission, 200);
            } else {
                startActivityMainDelay();
            }
        } else {
            startActivityMainDelay();
        }
    }

    private void startActivityMainDelay() {
        // Show splash screen for 2 seconds
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                Intent i = new Intent(ActivitySplash.this, ActivityMain.class);
//                startActivity(i);
//                finish(); // kill current activity
//            }
//        };
//        new Timer().schedule(task, 1500);
        binding.getStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySplash.this, BoardingActivity.class));
                finish();


//                DynamicLink dynamicLink = FirebaseDyna micLinks.getInstance().createDynamicLink()
//                        .setLink(Uri.parse("https://play.google.com/store/apps/details?id=com.app.naukribazaarinc"))
//                        .setDomainUriPrefix("https://appstacks.page.link")
//                        // Open links with this app on Android
//                        .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
//                        // Open links with com.example.ios on iOS
//                        .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
//                        .buildDynamicLink();
//
//                String links = "https://appstacks.page.link/?"+
//                        "link=http://www.jobtanks.com/"+
//                        "&apn="+getPackageName()+
//                        "&st="+"My Refer Link"+
//                        "sd="+"Rewards 20"+
//                        "&si="+"https://storage.googleapis.com/gweb-uniblog-publish-prod/images/HeroHomepage_2880x1200.max-1000x1000.jpg"
//                        ;
//
//
//                Uri dynamicLinkUri = dynamicLink.getUri();
//
//                Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
////                        .setLongLink(dynamicLink.getUri())
//                        .setLongLink(Uri.parse(links))
//                        .buildShortDynamicLink()
//                        .addOnCompleteListener(ActivitySplash.this, task -> {
//                            if (task.isSuccessful()) {
//                                // Short link created
//                                Uri shortLink = task.getResult().getShortLink();
//                                binding.naukriTv.setText(shortLink.toString());
//                                binding.naukriTv.setOnClickListener(view -> {
//
//                                    Intent in = new Intent();
//                                    in.setAction(Intent.ACTION_SEND);
//                                    in.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
//                                    in.setType("text/Plain");
//                                    startActivity(in);
//
//                                });
//
//                                Uri flowchartLink = task.getResult().getPreviewLink();
//                            } else {
//                                // Error
//                                Toast.makeText(ActivitySplash.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                                // ...
//                            }
//                        });
            }
        });
        if (auth.getCurrentUser() != null) {
            binding.getStartBtn.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    startActivity(new Intent(ActivitySplash.this, ActivityMain.class));
                    finish();

                }
            }, 2000);


        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 200) {
            for (String perm : permissions) {
                boolean rationale = shouldShowRequestPermissionRationale(perm);
                sharedPref.setNeverAskAgain(perm, !rationale);
            }
            on_permission_result = true;
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
