package com.anees.pdma.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.anees.pdma.R;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    LinearLayout L_natural_disaster, L_weather_alert, L_warnings, L_incidents, L_event_map, L_contact;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int image[] = {R.drawable.hdr1, R.drawable.hdr2, R.drawable.hdr3};

        viewFlipper = findViewById(R.id.id_viewFlipper);

        for (int i: image){
            flipperImages(i);
        }

        L_natural_disaster = findViewById(R.id.id_btn_natural_disaster);
        L_weather_alert = findViewById(R.id.id_btn_weather_alert);
        L_warnings = findViewById(R.id.id_btn_warnings);
        L_incidents = findViewById(R.id.id_btn_incidents);
        L_event_map = findViewById(R.id.id_btn_event_map);
        L_contact = findViewById(R.id.id_btn_call);

        L_natural_disaster.setOnClickListener(this);
        L_weather_alert.setOnClickListener(this);
        L_warnings.setOnClickListener(this);
        L_incidents.setOnClickListener(this);
        L_event_map.setOnClickListener(this);
        L_contact.setOnClickListener(this);


        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_natural_disaster:
              //  Toast.makeText(this, "Natural Disaster in progress!!!", Toast.LENGTH_SHORT).show();
                new FancyGifDialog.Builder(this)
                        .setTitle("Natural Disasters read in English OR Urdu") // You can also send title like R.string.from_resources
                        .setMessage("A Natural event such as a flood, earthquake, or hurricane that causes great damage or loss of life.") // or pass like R.string.description_from_resources
                        .setNegativeBtnText("اردو") // or pass it like android.R.string.cancel
                        .setPositiveBtnBackground(R.color.colorDialogue_bg) // or pass it like R.color.positiveButton
                        .setPositiveBtnText("English") // or pass it like android.R.string.ok
                        .setNegativeBtnBackground(R.color.colorDialogue_bg1) // or pass it like R.color.negativeButton
                        .setGifResource(R.drawable.gif2)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(Home.this,"Click in English",Toast.LENGTH_SHORT).show();
                                Intent new_intent = new Intent(Home.this, Natural_Disasters_English.class);
                                startActivity(new_intent);
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(Home.this,"Click in Urdu",Toast.LENGTH_SHORT).show();

                                Intent new_intent = new Intent(Home.this, Natural_Disasters_Urdu.class);
                                startActivity(new_intent);
                            }
                        })
                        .build();
                break;

            case R.id.id_btn_weather_alert:
                Toast.makeText(this, "Click Weather Alert", Toast.LENGTH_SHORT).show();
                Intent new_intent = new Intent(Home.this, Weather_Alert_Report.class);
                startActivity(new_intent);
                break;

            case R.id.id_btn_warnings:
                Toast.makeText(this, "Click Warning!!!", Toast.LENGTH_SHORT).show();
                Intent warning_intent = new Intent(Home.this, WarningActivity.class);
                startActivity(warning_intent);
                break;

            case R.id.id_btn_incidents:
                Toast.makeText(this, "Click Incidents", Toast.LENGTH_SHORT).show();

                Intent new_incident = new Intent(Home.this, Incidents.class);
                startActivity(new_incident);

                break;

            case R.id.id_btn_event_map:
                //Toast.makeText(this, "Map in progress!!!", Toast.LENGTH_SHORT).show();
                Intent new_map = new Intent(Home.this, MapsActivity.class);
                startActivity(new_map);

                break;

            case R.id.id_btn_call:
                Toast.makeText(this, "Click Contact Us!!!", Toast.LENGTH_SHORT).show();
                Intent new_contcat = new Intent(Home.this, ContactUs.class);
                startActivity(new_contcat);

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.login_item:
                // do your code
                Intent intnt_login = new Intent(Home.this, Login.class);
                startActivity(intnt_login);

                return true;
            case R.id.refresh_item:
                // do your code
                Toast.makeText(this, "Refresh PDMA!!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about_item:
                // do your code
                Toast.makeText(this, "Click About Us!!!", Toast.LENGTH_SHORT).show();
                Intent intnt_about = new Intent(Home.this, AboutUs.class);
                startActivity(intnt_about);
                return true;
           /* case R.id.setting_item:
                // do your code
                Toast.makeText(this, "Setting in progress!!!", Toast.LENGTH_SHORT).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int ids = item.getItemId();

        if (ids == R.id.nav_home) {

            Intent mIntent = new Intent(Home.this, Home.class);
            startActivity(mIntent);

        } else if (ids == R.id.nav_feedback) {

            Intent intent_feedback = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode("anees@pdma.test.pk, khan@pdma.test.pk") + "?subject=" + Uri.encode("Feedback");
            Uri mail_uri = Uri.parse(uriText);
            intent_feedback.setData(mail_uri);
            startActivity(Intent.createChooser(intent_feedback, "Send Feedback"));

        } else if (ids == R.id.nav_contact) {

            Intent mIntent = new Intent(Home.this, ContactUs.class);
            Home.this.startActivity(mIntent);

        } else if (ids == R.id.nav_about) {

            Intent mIntent = new Intent(Home.this, AboutUs.class);
            startActivity(mIntent);

        } else if (ids == R.id.nav_share) {

            shareLink();

        } else if (ids == R.id.nav_send) {

            sendEmail();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Dha Project Link share
    public void shareLink(){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Install Android Application of PDMA KP. \n https://play.google.com/store/apps/details?id=com.pdma.fasihims.emergencyalertpdmakp&hl=en";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Install Our Android application.");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    //  Send Email Address
    protected void sendEmail() {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"tauseef@pdma.gov.pk, admin@pdma.gov.pk"});
        //  email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        //  email.putExtra(Intent.EXTRA_TEXT, "message");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));

    }

    public void flipperImages(int image){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

}