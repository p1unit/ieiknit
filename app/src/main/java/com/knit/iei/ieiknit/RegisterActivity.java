package com.knit.iei.ieiknit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,password,password2;
    private CardView submit;
    private FirebaseAuth mAuth;
    private TextView alreadyregisterd;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        dialog=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.emailadd);
        password=(EditText)findViewById(R.id.password);
        password2=(EditText)findViewById(R.id.password2);
        submit=(CardView)findViewById(R.id.submit);
        mAuth=FirebaseAuth.getInstance();
        alreadyregisterd=(TextView)findViewById(R.id.alreg);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

        alreadyregisterd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });



    }

    private void startRegister() {

        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String password22 = password2.getText().toString().trim();

        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }  if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }  if (password1.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        } if(!Objects.equals(password1,password22)){

          //  Log.d(password1,password22);
            Toast.makeText(getApplicationContext(), "Both Passwords not Matched! ", Toast.LENGTH_SHORT).show();
        }


//        progressBar.setVisibility(View.VISIBLE);
        //create user
            dialog.setMessage("Signing Up");
            dialog.show();
            mAuth.createUserWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Successfully Loged In", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });


    }



//    private void startRegister() {
//        dialog.setMessage("Signing Up");
//        dialog.show();
//       // Log.d("ygfiygeyif","lkjwfjrlghvjrehgbl");
//        String emaill=email.getText().toString().trim();
//        String passwordd=password.getText().toString().trim();
//        String passwordd2=password2.getText().toString().trim();
//        if(passwordd.equals(passwordd2)) {
//            if (!TextUtils.isEmpty(emaill) && !TextUtils.isEmpty(passwordd) && !TextUtils.isEmpty(passwordd2)) {
//
//                mAuth.createUserWithEmailAndPassword(emaill, passwordd)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }
//                        });
//            } else {
//                dialog.dismiss();
//                Toast.makeText(RegisterActivity.this, "Some Error Occured Fill all the Details", Toast.LENGTH_LONG).show();
//            }
//        }
//        else{
//            dialog.dismiss();
//            Toast.makeText(RegisterActivity.this,"Retyped Password not matched",Toast.LENGTH_LONG).show();
//        }
//    }
}

