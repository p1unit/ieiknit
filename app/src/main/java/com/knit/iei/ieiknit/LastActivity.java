package com.knit.iei.ieiknit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class LastActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

  //  private Button events,presentTeam;
   private CardView events,About,novel,books;
//   private ViewPager viewPager;

    private FirebaseAuth firebaseAuth;

    SliderLayout sliderLayout1;
    HashMap<String, Integer> Hash_file_maps1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        novel=(CardView)findViewById(R.id.novel);
        events=(CardView) findViewById(R.id.events);
        About=(CardView) findViewById(R.id.about);
        books=(CardView)findViewById(R.id.books);

       // viewPager=(ViewPager)findViewById(R.id.viewPager);

      //  ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
     //   viewPager.setAdapter(viewPagerAdapter);

     //   presentTeam=(Button)findViewById(R.id.presentteam);
        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Hash_file_maps1 = new HashMap<>();
        sliderLayout1 = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps1.put("Team Members", R.drawable.slid1);
        Hash_file_maps1.put("The new ones", R.drawable.slid2);
        Hash_file_maps1.put("One with Flash Lights", R.drawable.slid3);
        Hash_file_maps1.put("Complete Team", R.drawable.slid4);
        Hash_file_maps1.put("Clock is Ticking", R.drawable.slid6);
        Hash_file_maps1.put("", R.drawable.slid9);


        for (String name : Hash_file_maps1.keySet()) {

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps1.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout1.addSlider(textSliderView);
        }

        sliderLayout1.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout1.setCustomAnimation(new DescriptionAnimation());
        sliderLayout1.setDuration(3010);
        sliderLayout1.addOnPageChangeListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LastActivity.this,IEI_Events.class);
                startActivity(intent);
            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LastActivity.this,AboutUS.class);
                startActivity(intent);
            }
        });

        novel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LastActivity.this,Novels.class);
                startActivity(intent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LastActivity.this,Books.class);
                startActivity(intent);
            }
        });
//
//        presentTeam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LastActivity.this,PresentTeam.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rateus) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                Toast.makeText(this, "Thanks for Rating!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        } else if (id == R.id.nav_profile_update) {
            Intent intent=new Intent(LastActivity.this,UserDetail.class);
            startActivity(intent);
           // finish();

        } else if (id == R.id.nav_contact_us) {

            Intent intent=new Intent(LastActivity.this,ContactUS.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            Intent intent=new Intent(LastActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }else if(id==R.id.nav_suggestions_query){
            Intent intent=new Intent(LastActivity.this,SuggQuery.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out this cool IEI KNIT app at: https://play.google.com/store/apps/details?id=com.knit.iei.ieiknit");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
