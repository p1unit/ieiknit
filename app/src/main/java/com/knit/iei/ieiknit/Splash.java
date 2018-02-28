package com.knit.iei.ieiknit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PointerIconCompat;
import android.view.Window;
import android.view.WindowManager;
import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.PermissionCallback;

public class Splash extends Activity{

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

//    public void onPermissionsGranted(int requestCode) {
//        if (requestCode == PointerIconCompat.TYPE_COPY ) {
////            button_take_a_photo1.setEnabled(true);
////            button_take_a_photo2.setEnabled(true);
//        }
//    }
//
//    public void onPermissionsDenied(int requestCode) {
//        if (requestCode == PointerIconCompat.TYPE_COPY) {
////            button_take_a_photo1.setEnabled(false);
////            button_take_a_photo2.setEnabled(false);
//        }
//    }
//    public void onResume() {
//        super.onResume();
//
//        new AskPermission.Builder(this).setPermissions("android.permission.GET_ACCOUNTS",
//                "android.permission.READ_PROFILE","android.permission.READ_CONTACTS","android.permission.CALL_PHONE","android.permission.INTERNET").setCallback( this).request(PointerIconCompat.TYPE_COPY);
//    }
    }

