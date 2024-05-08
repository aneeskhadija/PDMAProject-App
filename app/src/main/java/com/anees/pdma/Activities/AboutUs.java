package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.anees.pdma.R;

public class AboutUs extends AppCompatActivity {

    TextView textView, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        textView = (TextView) findViewById(R.id.text);
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
        textView5 = (TextView) findViewById(R.id.text5);
        textView6 = (TextView) findViewById(R.id.text6);
        textView7 = (TextView) findViewById(R.id.text7);
        textView8 = (TextView) findViewById(R.id.text8);
        textView9 = (TextView) findViewById(R.id.text9);

        textView.setText("PROVINCIAL DISASTER MANAGEMENT AUTHORITY (PDMA)");

        textView1.setText("An institution at Provincial level, mandated to effectively set up a system to look after disasters and calamities whether natural, man induced or accidents.");

        textView2.setText("ESTABLISHMENT OF PDMA");

        textView3.setText("As a sequel to the devastating earthquake of 8 October 2005, the National Disaster Management Ordinance was promulgated with a view to establish and regulate an enhanced and progressive Disaster Management Framework at the National, Provincial and Local level for disaster mitigation, preparedness and response. The NDMO provided the establishment of a Provincial Disaster Management Commission (PDMC) as well as Authority (PDMA) to cope with the challenges of Disaster Management in a professional and efficient manner. Both the Organizations have been mandated to effectively set up a system to look after disasters and calamities whether natural or human induced. The Government of Khyber Pakhtunkhwa has established a Provincial Disaster Management Commission PDMC) as well as a Provincial Disaster Management Authority (PDMA) on 27 October 2008, to promote enhanced disaster preparedness and management within the province. The establishment of PMDC and PDMA is based on the National Disaster Management Ordinance (NDMO) of 23rd December 2006 which forms the legal basis for the implementation of the National Disaster Management Fame work (NDMF) provided by the National Disaster Management Authority (NDMA). Previously the Provincial Relief Commissionerate had been responsible for the relief, compensation and rehabilitation of people affected by natural disasters. With the establishment of PDMA, the functions of the Relief Commissionerate have been incorporated into the new Organization.");

        textView4.setText("Functions of PDMA");

        textView5.setText("PDMA Khyber Pakhtunkhwa is responsible for Disaster Risk Management. It formulates policies of disaster risk management, mitigation and preparedness and hazard risk reduction. It coordinates and communicates with all stakeholders (Federal Government, District Government, INGOs, IPs) before and after a disaster for preparedness and response. PDMA provides relief to disaster affected population of Khyber Pakhtunkhwa. It helps in the Recovery and Rehabilitation of affected communities. It handles the crises of IDPs and manages the camps established for displaced population. It also works on Reconstruction and Development projects in the affected areas for the restoration of life in hazard stricken areas. PDMA acts as Donor's facilitation and coordination desk, while, it coordinates with donors for relief and rehabilitation on behalf of Provincial Government.");

        textView6.setText("District Disaster Management Units (DDMUs)");

        textView7.setText("Under NDM Act the DDMAs are formed which is a focal point for all DRM related activities in the district.");

        textView8.setText("Functions of DDMUs");

        textView9.setText("-DDMO is the focal point for disaster management related activities in district.\n" +
                "-Obtain, process, disseminate and manage all data, information.\n" +
                "-Forward contingency plan to PDMA.\n" +
                "-Coordination and Communications of relief and operation during a disaster.\n" +
                "-Policy formulation Planning and Decision making for disaster risk reduction at district level.\n" +
                "-Plays lead role in rescue Operations.\n" +
                "-Resource requesting, dispatch needed items to affected population.\n" +
                "-Information collection, analysis and dissemination.\n" +
                "-Preparing operational updates, situation reports and forward compensation cases.\n" +
                "-Facilitate task and Problem management at district level.\n");
    }
}