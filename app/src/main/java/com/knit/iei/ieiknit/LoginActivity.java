package com.knit.iei.ieiknit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.PermissionCallback;

public class LoginActivity extends AppCompatActivity implements PermissionCallback {

    private SignInButton mGoogleBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int RC_SIGN_IN=1;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG ="LoginActivity";
    private Button button;
    CardView login;
    private EditText email,pass;
    private TextView register;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        register=(TextView) findViewById(R.id.register);
        email=(EditText) findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        login=(CardView) findViewById(R.id.login);
        dialog=new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mGoogleBtn=(SignInButton)findViewById(R.id.googlebtn);


        authStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){
                    dialog.dismiss();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartSignin();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Signing in");
                dialog.show();
                signIn();
            }
        });

    }

    public void forgotpassword(View view){
        Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
    }

    protected void onStart() {

        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public  void rgstr(View view){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                dialog.dismiss();
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            // FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this,"Make sure you Have Internet Connection",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private void StartSignin() {
        dialog.setMessage("Signing in");
        dialog.show();
        String email1 = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if(TextUtils.isEmpty(email1)||TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"enter all the fields",Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

        else
        {

            firebaseAuth.signInWithEmailAndPassword(email1, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void onPermissionsGranted(int requestCode) {
        if (requestCode == PointerIconCompat.TYPE_COPY ) {
//            button_take_a_photo1.setEnabled(true);
//            button_take_a_photo2.setEnabled(true);
        }
    }

    public void onPermissionsDenied(int requestCode) {
        if (requestCode == PointerIconCompat.TYPE_COPY) {
//            button_take_a_photo1.setEnabled(false);
//            button_take_a_photo2.setEnabled(false);
        }
    }
    public void onResume() {
        super.onResume();

        new AskPermission.Builder(this).setPermissions("android.permission.GET_ACCOUNTS",
                "android.permission.READ_PROFILE","android.permission.READ_CONTACTS","android.permission.CALL_PHONE","android.permission.INTERNET").setCallback( this).request(PointerIconCompat.TYPE_COPY);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}
