package com.knit.iei.ieiknit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ContactUS extends AppCompatActivity {
    private TextView email1;
    private TextView phone1;
    private TextView email2;
    private TextView phone2;
    private TextView email3;
    private TextView phone3;
    private TextView email4;
    private TextView phone4;
    private TextView gnmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact_us);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email1=(TextView)findViewById(R.id.email1);
        phone1=(TextView)findViewById(R.id.phone1);
        email2=(TextView)findViewById(R.id.email2);
        phone2=(TextView)findViewById(R.id.phone2);
        email3=(TextView)findViewById(R.id.email3);
        phone3=(TextView)findViewById(R.id.phone3);
        email4=(TextView)findViewById(R.id.email4);
        phone4=(TextView)findViewById(R.id.phone4);
        gnmail=(TextView)findViewById(R.id.tempmail);
    }

    public void aysphone(View view){
        String s=phone1.getText().toString().trim();
        String ss="tel:"+s.substring(0,s.length());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(ss));
        startActivity(intent);
    }
    public void utkphone(View view){
        String s=phone2.getText().toString().trim();
        String ss="tel:"+s.substring(0,s.length());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(ss));
        startActivity(intent);
    }
    public void aprphone(View view){
        String s=phone3.getText().toString().trim();
        String ss="tel:"+s.substring(0,s.length());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(ss));
        startActivity(intent);
    }
    public void rgvphone(View view){
        String s=phone4.getText().toString().trim();
        String ss="tel:"+s.substring(0,s.length());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(ss));
        startActivity(intent);
    }
    public void aysmail(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s=email1.getText().toString().trim();
        String ss="mailto:"+s.substring(0,s.length())+"?subject=" + "" + "&body=" + "";
        Uri data = Uri.parse(ss);
        intent.setData(data);
        startActivity(intent);

    }
    public void utkmail(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s=email2.getText().toString().trim();
        String ss="mailto:"+s.substring(0,s.length())+"?subject=" + "" + "&body=" + "";
        Uri data = Uri.parse(ss);
        intent.setData(data);
        startActivity(intent);

    }
    public void aprmail(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s=email3.getText().toString().trim();
        String ss="mailto:"+s.substring(0,s.length())+"?subject=" + "" + "&body=" + "";
        Uri data = Uri.parse(ss);
        intent.setData(data);
        startActivity(intent);

    }
    public void rgvmail(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s=email4.getText().toString().trim();
        String ss="mailto:"+s.substring(0,s.length())+"?subject=" + "" + "&body=" + "";
        Uri data = Uri.parse(ss);
        intent.setData(data);
        startActivity(intent);

    }

    public void gnrlmail(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String s=gnmail.getText().toString().trim();
        String ss="mailto:"+s.substring(0,s.length())+"?subject=" + "" + "&body=" + "";
        Uri data = Uri.parse(ss);
        intent.setData(data);
        startActivity(intent);
    }

}
