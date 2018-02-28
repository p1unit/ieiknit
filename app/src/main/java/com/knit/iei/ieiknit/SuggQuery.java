package com.knit.iei.ieiknit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuggQuery extends AppCompatActivity {
    private EditText name,number,ques;
    private CardView submit;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sugg_query);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        ques=(EditText)findViewById(R.id.ques);
        cardView=(CardView)findViewById(R.id.qsubmitt);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("suggandQuery");
        mAuth=FirebaseAuth.getInstance();
    }

public void qsubmit(View view){

        String name1=name.getText().toString().trim();
        String number1=number.getText().toString().trim();
        String ques1=ques.getText().toString().trim();
        if(!TextUtils.isEmpty(name1)||!TextUtils.isEmpty(number1)||!TextUtils.isEmpty(ques1)){

            String user_id=mAuth.getCurrentUser().getUid();
            DatabaseReference current_user_db=reference.child(user_id);
            current_user_db.child("Name").setValue(name1);
            current_user_db.child("MobileNo").setValue(number1);
            current_user_db.child("Query").setValue(ques1);
            Toast.makeText(SuggQuery.this,"We Will Contact you ",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SuggQuery.this,LastActivity.class);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(SuggQuery.this,"Enter And Select All The Details",Toast.LENGTH_LONG).show();
        }
}
}
