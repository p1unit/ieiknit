package com.knit.iei.ieiknit;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDetail extends AppCompatActivity {

    private EditText name,rollno;
    private Spinner year,branch;
   // private Button submit;
    private CardView submit;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        name=(EditText)findViewById(R.id.name);
        rollno=(EditText)findViewById(R.id.rollno);
        year=(Spinner)findViewById(R.id.year);
        branch=(Spinner)findViewById(R.id.branch);
        database=FirebaseDatabase.getInstance();
        submit=(CardView) findViewById(R.id.submit);
        reference=database.getReference("users");
        mAuth=FirebaseAuth.getInstance();

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

    }


    public void fnlsubmit(View view){
        submitt(view);
    }

    private void submitt(View view) {
        String name1=name.getText().toString();
        String rollno1=rollno.getText().toString();
        String year1 = year.getSelectedItem().toString();
        String branch1 = branch.getSelectedItem().toString();

        if(TextUtils.isEmpty(name1)||TextUtils.isEmpty(rollno1)||branch1.equals("Select Course Type")||year1.equals("Select Current Year")){
            Toast.makeText(UserDetail.this,"Please fill all the Details",Toast.LENGTH_LONG).show();
        }
        else {


//                    HashMap<String, String> datamap = new HashMap<>();
//                    datamap.put("Name", name1);
//                    datamap.put("Roll No", rollno1);
//                    datamap.put("Year", year1);
//                    datamap.put("Branch", branch1);
            String user_id=mAuth.getCurrentUser().getUid();
            DatabaseReference current_user_db=reference.child(user_id);
            current_user_db.child("Name").setValue(name1);
            current_user_db.child("Roll No").setValue(rollno1);
            current_user_db.child("Year").setValue(year1);
            current_user_db.child("Branch").setValue(branch1);

            //  reference.push().setValue(datamap);

            Toast.makeText(UserDetail.this,"Now we have your Details",Toast.LENGTH_LONG).show();
        }
    }
}
