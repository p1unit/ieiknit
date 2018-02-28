package com.knit.iei.ieiknit;

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

public class Novels extends AppCompatActivity {


    private String[]  mblarry ={"Database Management System-Korth",
            "Data Structures- Lipseherz",
            "Communication System-Forouzan",
            "Engineering Mathematics 3-H.K.Dass",
            "Digital Logic Design-Morris Mano",
            "Data Mining And Warehousing-Jiawei Han",
            "Automata-Ullman,D.Goswami",
            "Microprocessor -Gaonker",
            "Operating System-Galvin",
            "Software Engineering-Pressman",
            "Computer Graphics And Multimedia-Donald Hearn",
            "Compiler Design-Ullman",
            "Web Technology-Jeffrey c. Jackson","And many more......"};

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_novels);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  mblarry);

        listView = (ListView) findViewById(R.id.list2);
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
