package com.knit.iei.ieiknit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IEI_Events extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef ;
    private List<Post> list;
    private RecyclerView recycle;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iei__events);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        dialog = new ProgressDialog(this);

        dialog.setMessage("Loading Data");
        dialog.show();
        recycle = (RecyclerView) findViewById(R.id.blog_list);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Events");
        myRef.keepSynced(true);
//
        myRef.addValueEventListener(new ValueEventListener() {
         //   int count=0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Post value = dataSnapshot1.getValue(Post.class);
                    if(value==null)
                        continue;
                    Post fire = new Post();
                    String name = value.getTitle();
                    String email = value.getDesc();
                    String image = value.getImage();
                    fire.setTitle(name);
                    fire.setDesc(email);
                    fire.setImage(image);
                    list.add(fire);
                      // Toast.makeText(IEI_Events.this, String.valueOf(count++), Toast.LENGTH_SHORT).show();
                }
                //      Toast.makeText(MainActivity.this, String.valueOf(!list.isEmpty())+" "+String.valueOf(recycle.getAdapter() != null), Toast.LENGTH_SHORT).show();
                if(!list.isEmpty()){
                    dialog.dismiss();
                    //     recycle.getAdapter().notifyItemInserted(1);
                    final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,IEI_Events.this);
                    //  recyclerAdapter.registerAdapterDataObserver(adapterDataObserver);
                    recycle.setLayoutManager(new LinearLayoutManager(IEI_Events.this));
                    recycle.setItemAnimator( new DefaultItemAnimator());
                    recycle.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                dialog.dismiss();
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }
}
