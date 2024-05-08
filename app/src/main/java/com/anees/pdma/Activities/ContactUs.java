package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anees.pdma.R;

public class ContactUs extends AppCompatActivity implements View.OnClickListener{

    //for vector icons
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    TextView helpline, tv_incharge_number_one, tv_incharge_number_two, tv_incharge_number_three, tv_exchange_number_one, tv_exchange_number_two, tv_exchange_number_three, tv_exchange_number_four,
             tv_feedback, rescue_helpline, police_helpline, irrigation_helpline, wssp_helpline,  tv_ddmo_one, tv_ddmo_two, tv_ddmo_three, tv_ddmo_four, tv_ddmo_five, tv_ddmo_six, tv_ddmo_seven, tv_ddmo_eight, tv_ddmo_nine, tv_ddmo_ten,
             tv_ddmo_eleven, tv_ddmo_twelve, tv_ddmo_thirteen, tv_ddmo_fourteen, tv_ddmo_fifteen, tv_ddmo_sixteen, tv_ddmo_seventeen, tv_ddmo_eighteen,
             tv_ddmo_ninteen, tv_ddmo_twenty, tv_ddmo_twentyone, tv_ddmo_twentytwo, tv_ddmo_twentythree, tv_ddmo_twentyfour, tv_ddmo_twentyfive, tv_ddmo_twentysix;
    ImageView imageView_incharge, imageView_exchange, imageView_ddmo;
    LinearLayout tv_facebook, tv_twitter, tv_website;

    static final Integer CALL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        imageView_incharge = (ImageView) findViewById(R.id.incharge_dropdown);
        imageView_incharge.setOnClickListener(this);
        imageView_exchange = (ImageView) findViewById(R.id.exchange_dropdown);
        imageView_exchange.setOnClickListener(this);
        imageView_ddmo = (ImageView) findViewById(R.id.ddmo_dropdown);
        imageView_ddmo.setOnClickListener(this);

        helpline = (TextView) findViewById(R.id.helpline_text_view);
        helpline.setOnClickListener(this);

        rescue_helpline = (TextView) findViewById(R.id.rescue_text_view);
        rescue_helpline.setOnClickListener(this);

        police_helpline = (TextView) findViewById(R.id.police_text_view);
        police_helpline.setOnClickListener(this);

        irrigation_helpline = (TextView) findViewById(R.id.irrigation_text_view);
        irrigation_helpline.setOnClickListener(this);

        wssp_helpline = (TextView) findViewById(R.id.wssp_text_view);
        wssp_helpline.setOnClickListener(this);

        tv_incharge_number_one = (TextView) findViewById(R.id.incharge_number_one);
        tv_incharge_number_one.setOnClickListener(this);
        tv_incharge_number_one.setVisibility(View.GONE);

        tv_incharge_number_two = (TextView) findViewById(R.id.incharge_number_two);
        tv_incharge_number_two.setOnClickListener(this);
        tv_incharge_number_two.setVisibility(View.GONE);

        tv_incharge_number_three = (TextView) findViewById(R.id.incharge_number_three);
        tv_incharge_number_three.setOnClickListener(this);
        tv_incharge_number_three.setVisibility(View.GONE);

        tv_exchange_number_one = (TextView) findViewById(R.id.exchange_number_one);
        tv_exchange_number_one.setOnClickListener(this);
        tv_exchange_number_one.setVisibility(View.GONE);

        tv_exchange_number_two = (TextView) findViewById(R.id.exchange_number_two);
        tv_exchange_number_two.setOnClickListener(this);
        tv_exchange_number_two.setVisibility(View.GONE);

        tv_exchange_number_three = (TextView) findViewById(R.id.exchange_number_three);
        tv_exchange_number_three.setOnClickListener(this);
        tv_exchange_number_three.setVisibility(View.GONE);

        tv_exchange_number_four = (TextView) findViewById(R.id.exchange_number_four);
        tv_exchange_number_four.setOnClickListener(this);
        tv_exchange_number_four.setVisibility(View.GONE);

        tv_website = (LinearLayout) findViewById(R.id.website_address);
        tv_website.setOnClickListener(this);

        tv_facebook = (LinearLayout) findViewById(R.id.facebook_follow);
        tv_facebook.setOnClickListener(this);

        tv_twitter = (LinearLayout) findViewById(R.id.twitter_follow);
        tv_twitter.setOnClickListener(this);

        tv_feedback = (TextView) findViewById(R.id.feedback);
        tv_feedback.setOnClickListener(this);

        tv_ddmo_one = (TextView) findViewById(R.id.ddmo_one);
        tv_ddmo_one.setOnClickListener(this);
        tv_ddmo_two = (TextView) findViewById(R.id.ddmo_two);
        tv_ddmo_two.setOnClickListener(this);
        tv_ddmo_three = (TextView) findViewById(R.id.ddmo_three);
        tv_ddmo_three.setOnClickListener(this);
        tv_ddmo_four = (TextView) findViewById(R.id.ddmo_four);
        tv_ddmo_four.setOnClickListener(this);
        tv_ddmo_five = (TextView) findViewById(R.id.ddmo_five);
        tv_ddmo_five.setOnClickListener(this);
        tv_ddmo_six = (TextView) findViewById(R.id.ddmo_six);
        tv_ddmo_six.setOnClickListener(this);
        tv_ddmo_seven = (TextView) findViewById(R.id.ddmo_seven);
        tv_ddmo_seven.setOnClickListener(this);
        tv_ddmo_eight = (TextView) findViewById(R.id.ddmo_eight);
        tv_ddmo_eight.setOnClickListener(this);
        tv_ddmo_nine = (TextView) findViewById(R.id.ddmo_nine);
        tv_ddmo_nine.setOnClickListener(this);
        tv_ddmo_ten = (TextView) findViewById(R.id.ddmo_ten);
        tv_ddmo_ten.setOnClickListener(this);
        tv_ddmo_eleven = (TextView) findViewById(R.id.ddmo_eleven);
        tv_ddmo_eleven.setOnClickListener(this);
        tv_ddmo_twelve = (TextView) findViewById(R.id.ddmo_twelve);
        tv_ddmo_twelve.setOnClickListener(this);
        tv_ddmo_thirteen = (TextView) findViewById(R.id.ddmo_thirteen);
        tv_ddmo_thirteen.setOnClickListener(this);
        tv_ddmo_fourteen = (TextView) findViewById(R.id.ddmo_fourteen);
        tv_ddmo_fourteen.setOnClickListener(this);
        tv_ddmo_fifteen = (TextView) findViewById(R.id.ddmo_fifteen);
        tv_ddmo_fifteen.setOnClickListener(this);
        tv_ddmo_sixteen = (TextView) findViewById(R.id.ddmo_sixteen);
        tv_ddmo_sixteen.setOnClickListener(this);
        tv_ddmo_seventeen = (TextView) findViewById(R.id.ddmo_seventeen);
        tv_ddmo_seventeen.setOnClickListener(this);
        tv_ddmo_eighteen = (TextView) findViewById(R.id.ddmo_eighteen);
        tv_ddmo_eighteen.setOnClickListener(this);
        tv_ddmo_ninteen = (TextView) findViewById(R.id.ddmo_nineteen);
        tv_ddmo_ninteen.setOnClickListener(this);
        tv_ddmo_twenty = (TextView) findViewById(R.id.ddmo_twenty);
        tv_ddmo_twenty.setOnClickListener(this);
        tv_ddmo_twentyone = (TextView) findViewById(R.id.ddmo_twentyone);
        tv_ddmo_twentyone.setOnClickListener(this);
        tv_ddmo_twentytwo = (TextView) findViewById(R.id.ddmo_twenttwo);
        tv_ddmo_twentytwo.setOnClickListener(this);
        tv_ddmo_twentythree = (TextView) findViewById(R.id.ddmo_twentythree);
        tv_ddmo_twentythree.setOnClickListener(this);
        tv_ddmo_twentyfour = (TextView) findViewById(R.id.ddmo_twentyfour);
        tv_ddmo_twentyfour.setOnClickListener(this);
        tv_ddmo_twentyfive = (TextView) findViewById(R.id.ddmo_twentyfive);
        tv_ddmo_twentyfive.setOnClickListener(this);
        tv_ddmo_twentysix = (TextView) findViewById(R.id.ddmo_twentysix);
        tv_ddmo_twentysix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpline_text_view:
                Intent intent_helpline = new Intent(Intent.ACTION_CALL);     //implicit intent
                intent_helpline.setData(Uri.parse("tel:1700"));
                //this is a predefined 'if' condition with Intent.ACTION_CALL
                //this is called in Android 6.0 & above versions to get run time permission from user
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //This is called if user has denied the permission before. In this case I am just asking the permission again
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    }
                    return;
                }
                startActivity(intent_helpline);
                break;

            case R.id.rescue_text_view:
                Intent intent_helpline_rescue = new Intent(Intent.ACTION_CALL);     //implicit intent
                intent_helpline_rescue.setData(Uri.parse("tel:1122"));
                //this is a predefined 'if' condition with Intent.ACTION_CALL
                //this is called in Android 6.0 & above versions to get run time permission from user
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //This is called if user has denied the permission before. In this case I am just asking the permission again
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    }
                    return;
                }
                startActivity(intent_helpline_rescue);
                break;

            case R.id.police_text_view:
                Intent intent_helpline_police = new Intent(Intent.ACTION_CALL);     //implicit intent
                intent_helpline_police.setData(Uri.parse("tel:091 9210457"));
                //this is a predefined 'if' condition with Intent.ACTION_CALL
                //this is called in Android 6.0 & above versions to get run time permission from user
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //This is called if user has denied the permission before. In this case I am just asking the permission again
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    }
                    return;
                }
                startActivity(intent_helpline_police);
                break;

            case R.id.irrigation_text_view:
                Intent intent_helpline_irrigation = new Intent(Intent.ACTION_CALL);     //implicit intent
                intent_helpline_irrigation.setData(Uri.parse("tel:091 9212114"));
                //this is a predefined 'if' condition with Intent.ACTION_CALL
                //this is called in Android 6.0 & above versions to get run time permission from user
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //This is called if user has denied the permission before. In this case I am just asking the permission again
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    }
                    return;
                }
                startActivity(intent_helpline_irrigation);
                break;

            case R.id.wssp_text_view:
                Intent intent_helpline_wssp = new Intent(Intent.ACTION_CALL);     //implicit intent
                intent_helpline_wssp.setData(Uri.parse("tel:1334"));
                //this is a predefined 'if' condition with Intent.ACTION_CALL
                //this is called in Android 6.0 & above versions to get run time permission from user
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //This is called if user has denied the permission before. In this case I am just asking the permission again
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                    }
                    return;
                }
                startActivity(intent_helpline_wssp);
                break;

            case R.id.incharge_dropdown:
                if (tv_incharge_number_one.getVisibility() == View.GONE) {
                    tv_incharge_number_one.setVisibility(View.VISIBLE);
                    tv_incharge_number_two.setVisibility(View.VISIBLE);
                    tv_incharge_number_three.setVisibility(View.VISIBLE);
                    imageView_incharge.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

                    tv_incharge_number_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_incharge_one = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_incharge_one.setData(Uri.parse("tel:0345 5893018"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_incharge_one);
                        }
                    });

                    tv_incharge_number_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_incharge_two = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_incharge_two.setData(Uri.parse("tel:0311 9281272"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_incharge_two);
                        }
                    });

                    tv_incharge_number_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_incharge_three = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_incharge_three.setData(Uri.parse("tel:0346 9898816"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_incharge_three);
                        }
                    });
                } else {
                    tv_incharge_number_one.setVisibility(View.GONE);
                    tv_incharge_number_two.setVisibility(View.GONE);
                    tv_incharge_number_three.setVisibility(View.GONE);
                    imageView_incharge.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.exchange_dropdown:
                if (tv_exchange_number_one.getVisibility() == View.GONE) {
                    tv_exchange_number_one.setVisibility(View.VISIBLE);
                    tv_exchange_number_two.setVisibility(View.VISIBLE);
                    tv_exchange_number_three.setVisibility(View.VISIBLE);
                    tv_exchange_number_four.setVisibility(View.VISIBLE);
                    imageView_exchange.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

                    tv_exchange_number_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_call_one = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_call_one.setData(Uri.parse("tel:091 9211854"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_call_one);
                        }
                    });

                    tv_exchange_number_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_call_two = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_call_two.setData(Uri.parse("tel:091 9214095"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_call_two);
                        }
                    });
                    tv_exchange_number_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_call_three = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_call_three.setData(Uri.parse("tel:091 5274340"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_call_three);
                        }
                    });
                    tv_exchange_number_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_call_four = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_call_four.setData(Uri.parse("tel:091 9213959"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_call_four);
                        }
                    });
                } else {
                    tv_exchange_number_one.setVisibility(View.GONE);
                    tv_exchange_number_two.setVisibility(View.GONE);
                    tv_exchange_number_three.setVisibility(View.GONE);
                    tv_exchange_number_four.setVisibility(View.GONE);
                    imageView_exchange.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.website_address:
                Uri pdma_website = Uri.parse("http://www.pdma.gov.pk");      //implicit intent
                Intent intent_website = new Intent(Intent.ACTION_VIEW, pdma_website);
                startActivity(intent_website);
                break;

            case R.id.facebook_follow:
                Intent intent_facebook = new Intent(Intent.ACTION_VIEW);
                String facebook_url = "https://www.facebook.com/PDMAmediacell/?ref=br_rs";
                intent_facebook.setData(Uri.parse(facebook_url));
                startActivity(intent_facebook);
                break;

            case R.id.twitter_follow:
                Intent intent_twitter = new Intent(Intent.ACTION_VIEW);
                String twitter_url = "https://twitter.com/pdmakpk";
                intent_twitter.setData(Uri.parse(twitter_url));
                startActivity(intent_twitter);
                break;

            case R.id.feedback:
                Intent intent_feedback = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("tauseef@pdma.gov.pk, admin@pdma.gov.pk") + "?subject=" + Uri.encode("Feedback");
                Uri mail_uri = Uri.parse(uriText);
                intent_feedback.setData(mail_uri);
                startActivity(Intent.createChooser(intent_feedback, "Send Feedback"));
                break;

            case R.id.ddmo_dropdown:
                if (tv_ddmo_one.getVisibility() == View.GONE) {
                    textViewsVisible();
                    imageView_ddmo.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

                    tv_ddmo_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0992 9310203"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0928 9270039"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0997 312189"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0939 510234"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_five.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:091 9220137"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_six.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0943 413686"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_seven.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0966 9280117"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_eight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0945 9250029"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_nine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0944 880506"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_ten.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0925 622682"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_eleven.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0995 610455"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twelve.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0927 210710"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_thirteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0922 9260046"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_fourteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0998 405091"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_fifteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0998 407029"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_sixteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0969 538332"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_seventeen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0932 412254"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_eighteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0997 300751"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_ninteen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0937 9230701"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twenty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0923 9220104"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentyone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:091 9212304"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentytwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0938 221401"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentythree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0996 850005"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentyfour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0946 9240341"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentyfive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0963 512290"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                    tv_ddmo_twentysix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_ddmo = new Intent(Intent.ACTION_CALL);     //implicit intent
                            intent_ddmo.setData(Uri.parse("tel:0997 214762"));
                            if (ActivityCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ContactUs.this, Manifest.permission.CALL_PHONE)) {
                                    //This is called if user has denied the permission before. In this case I am just asking the permission again
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                } else {
                                    ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                                }
                                return;
                            }
                            startActivity(intent_ddmo);
                        }
                    });

                } else {
                    textViewsHide();
                    imageView_ddmo.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;
        }
    }

    private void textViewsVisible() {
        tv_ddmo_one.setVisibility(View.VISIBLE);
        tv_ddmo_two.setVisibility(View.VISIBLE);
        tv_ddmo_three.setVisibility(View.VISIBLE);
        tv_ddmo_four.setVisibility(View.VISIBLE);
        tv_ddmo_five.setVisibility(View.VISIBLE);
        tv_ddmo_six.setVisibility(View.VISIBLE);
        tv_ddmo_seven.setVisibility(View.VISIBLE);
        tv_ddmo_eight.setVisibility(View.VISIBLE);
        tv_ddmo_nine.setVisibility(View.VISIBLE);
        tv_ddmo_ten.setVisibility(View.VISIBLE);
        tv_ddmo_eleven.setVisibility(View.VISIBLE);
        tv_ddmo_twelve.setVisibility(View.VISIBLE);
        tv_ddmo_thirteen.setVisibility(View.VISIBLE);
        tv_ddmo_fourteen.setVisibility(View.VISIBLE);
        tv_ddmo_fifteen.setVisibility(View.VISIBLE);
        tv_ddmo_sixteen.setVisibility(View.VISIBLE);
        tv_ddmo_seventeen.setVisibility(View.VISIBLE);
        tv_ddmo_eighteen.setVisibility(View.VISIBLE);
        tv_ddmo_ninteen.setVisibility(View.VISIBLE);
        tv_ddmo_twenty.setVisibility(View.VISIBLE);
        tv_ddmo_twentyone.setVisibility(View.VISIBLE);
        tv_ddmo_twentytwo.setVisibility(View.VISIBLE);
        tv_ddmo_twentythree.setVisibility(View.VISIBLE);
        tv_ddmo_twentyfour.setVisibility(View.VISIBLE);
        tv_ddmo_twentyfive.setVisibility(View.VISIBLE);
        tv_ddmo_twentysix.setVisibility(View.VISIBLE);
    }

    private void textViewsHide() {
        tv_ddmo_one.setVisibility(View.GONE);
        tv_ddmo_two.setVisibility(View.GONE);
        tv_ddmo_three.setVisibility(View.GONE);
        tv_ddmo_four.setVisibility(View.GONE);
        tv_ddmo_five.setVisibility(View.GONE);
        tv_ddmo_six.setVisibility(View.GONE);
        tv_ddmo_seven.setVisibility(View.GONE);
        tv_ddmo_eight.setVisibility(View.GONE);
        tv_ddmo_nine.setVisibility(View.GONE);
        tv_ddmo_ten.setVisibility(View.GONE);
        tv_ddmo_eleven.setVisibility(View.GONE);
        tv_ddmo_twelve.setVisibility(View.GONE);
        tv_ddmo_thirteen.setVisibility(View.GONE);
        tv_ddmo_fourteen.setVisibility(View.GONE);
        tv_ddmo_fifteen.setVisibility(View.GONE);
        tv_ddmo_sixteen.setVisibility(View.GONE);
        tv_ddmo_seventeen.setVisibility(View.GONE);
        tv_ddmo_eighteen.setVisibility(View.GONE);
        tv_ddmo_ninteen.setVisibility(View.GONE);
        tv_ddmo_twenty.setVisibility(View.GONE);
        tv_ddmo_twentyone.setVisibility(View.GONE);
        tv_ddmo_twentytwo.setVisibility(View.GONE);
        tv_ddmo_twentythree.setVisibility(View.GONE);
        tv_ddmo_twentyfour.setVisibility(View.GONE);
        tv_ddmo_twentyfive.setVisibility(View.GONE);
        tv_ddmo_twentysix.setVisibility(View.GONE);
    }
}