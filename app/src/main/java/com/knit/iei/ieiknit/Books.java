package com.knit.iei.ieiknit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Books extends AppCompatActivity {

        // Array of strings...
       private String[]  mblarry ={
                "A tale of two cities",
                "Counterinsurgency Warfare Theory and Practice",
                "Dan Brown - The Da Vinci Code",
                "Five point Someone",
                "Gaban by Munshi Premchand",
                "Godan by Munshi Premchand",
                "Gitanjali by Tagore",
                "Goosebumps-The Werewolf of Fever Swamps",
                "Half Girlfriend",
                "Harry Potter and the chamber of secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "Harry Potter and the Sorcerer's Stone",
                "Paulo Coelho-The Alchemist",
                "The Discovery of India",
                "The story of my experiments with Truth",
                "The Theory of Everything",
                "You Can Win by shiv khera","And 550+ ......"

        };
        private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_books);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  mblarry);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ieiknit.com/services.html"));
                startActivity(browserIntent);
            }
        });
    }


}


