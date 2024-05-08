package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anees.pdma.R;

public class Natural_Disasters_English extends AppCompatActivity implements View.OnClickListener {

    //for vector icons
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    TextView textView, textView1, textView2, textView_cyclone, textView_cyclone_definition, textView_cyclone_description, textView_earthquake,
            textView_earthquake_definition, textView_earthquake_description, textView_flood, textView_flood_definition,
            textView_flood_description, textView_landslide, textView_landslide_definition, textView_landslide_description,
            textView_thunder, textView_thunder_definition, textView_tornado, textView_tornado_definition,
            textView_wildfire, textView_wildfire_definition;

    ImageView imageView_earthquake, imageView_earthquake_pre, imageView_flood, imageView_flood_pre, imageView_landslide,
            imageView_landslide_pre, imageView_thunder, imageView_thunder_pre, imageView_cyclone, imageView_cyclone_pre,
            imageView_wildfire,
            imageView_wildfire_pre, imageView_tornado, imageView_tornado_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natural_disasters_english);

        textView = (TextView) findViewById(R.id.text);
        textView1 = (TextView) findViewById(R.id.text_one);
        textView2 = (TextView) findViewById(R.id.text_two);

        textView_earthquake = (TextView) findViewById(R.id.text_earthquake);
        textView_earthquake_definition = (TextView) findViewById(R.id.earthquake_definition);
        textView_earthquake_definition.setVisibility(View.GONE);
        textView_earthquake_description = (TextView) findViewById(R.id.earthquake_description);
        textView_earthquake_description.setVisibility(View.GONE);
        imageView_earthquake_pre = (ImageView) findViewById(R.id.earthquake_pre_image);
        imageView_earthquake = (ImageView) findViewById(R.id.earthquake_dropdown);
        imageView_earthquake.setOnClickListener(this);

        textView_flood = (TextView) findViewById(R.id.text_flood);
        textView_flood_definition = (TextView) findViewById(R.id.flood_definition);
        textView_flood_definition.setVisibility(View.GONE);
        textView_flood_description = (TextView) findViewById(R.id.flood_description);
        textView_flood_description.setVisibility(View.GONE);
        imageView_flood_pre = (ImageView) findViewById(R.id.flood_pre_image);
        imageView_flood = (ImageView) findViewById(R.id.flood_dropdown);
        imageView_flood.setOnClickListener(this);

        textView_landslide = (TextView) findViewById(R.id.text_landslide);
        textView_landslide_definition = (TextView) findViewById(R.id.landslide_definition);
        textView_landslide_definition.setVisibility(View.GONE);
        textView_landslide_description = (TextView) findViewById(R.id.landslide_description);
        textView_landslide_description.setVisibility(View.GONE);
        imageView_landslide_pre = (ImageView) findViewById(R.id.landslide_pre_image);
        imageView_landslide = (ImageView) findViewById(R.id.landslide_dropdown);
        imageView_landslide.setOnClickListener(this);

        textView_thunder = (TextView) findViewById(R.id.text_thunder);
        textView_thunder_definition = (TextView) findViewById(R.id.thunder_definition);
        textView_thunder_definition.setVisibility(View.GONE);
        imageView_thunder_pre = (ImageView) findViewById(R.id.thunder_pre_image);
        imageView_thunder = (ImageView) findViewById(R.id.thunder_dropdown);
        imageView_thunder.setOnClickListener(this);

        textView_cyclone = (TextView) findViewById(R.id.text_cyclone);
        textView_cyclone_definition = (TextView) findViewById(R.id.cyclone_definition);
        textView_cyclone_definition.setVisibility(View.GONE);
        textView_cyclone_description = (TextView) findViewById(R.id.cyclone_description);
        textView_cyclone_description.setVisibility(View.GONE);
        imageView_cyclone_pre = (ImageView) findViewById(R.id.cyclone_pre_image);
        imageView_cyclone = (ImageView) findViewById(R.id.cyclone_dropdown);
        imageView_cyclone.setOnClickListener(this);

        textView_wildfire = (TextView) findViewById(R.id.text_wildfire);
        textView_wildfire_definition = (TextView) findViewById(R.id.wildfire_definition);
        textView_wildfire_definition.setVisibility(View.GONE);
        imageView_wildfire_pre = (ImageView) findViewById(R.id.wildfire_pre_image);
        imageView_wildfire = (ImageView) findViewById(R.id.wildfire_dropdown);
        imageView_wildfire.setOnClickListener(this);

        textView_tornado = (TextView) findViewById(R.id.text_tornado);
        textView_tornado_definition = (TextView) findViewById(R.id.tornado_definition);
        textView_tornado_definition.setVisibility(View.GONE);
        imageView_tornado_pre = (ImageView) findViewById(R.id.tornado_pre_image);
        imageView_tornado = (ImageView) findViewById(R.id.tornado_dropdown);
        imageView_tornado.setOnClickListener(this);

        textView.setText("Natural Disaster");
        textView1.setText("A natural disaster is a major adverse event resulting from natural processes of the Earth; examples include floods, " +
                "hurricanes, tornadoes, volcanic eruptions, earthquakes, tsunamis, and other geologic processes. A natural disaster can cause loss of life or property damage, and typically leaves some economic damage in its wake, the severity of which depends on the affected population's resilience, or ability to recover and also on the infrastructure available.");
        textView2.setText("Top Natural Disasters");

        textView_earthquake.setText("Earthquake");
        textView_earthquake_definition.setText("An earthquake is the result of a sudden release of energy in the Earth's crust that creates seismic waves. At the Earth's surface, earthquakes manifest themselves by vibration, shaking, and sometimes displacement of the ground. Earthquakes are caused by slippage within geological faults. The underground point of origin of the earthquake is called the seismic focus. The point directly above the focus on the surface is called the epicenter. Earthquakes by themselves rarely kill people or wildlife. It is usually the secondary events that they trigger such as building collapse, fires, tsunamis (seismic sea waves) and volcanoes. Many of these could possibly be avoided by better construction, safety systems, early warning and planning.");
        textView_earthquake_description.setText("Do's & Dont's\n\n" +
                "What to Do Before an Earthquake\n" +
                "•\tRepair deep plaster cracks in ceilings and foundations. Get expert advice if there are signs of structural defects.\n" +
                "•\tAnchor overhead lighting fixtures to the ceiling.\n" +
                "•\tFollow BIS codes relevant to your area for building standards\n" +
                "•\tFasten shelves securely to walls.\n" +
                "•\tPlace large or heavy objects on lower shelves.\n" +
                "•\tStore breakable items such as bottled foods, glass, and closed cabinets with latches.\n" +
                "•\tHang heavy items such as pictures and mirrors away from beds, settees, and anywhere that people sit.\n" +
                "•\tBrace overhead light and fan fixtures.\n" +
                "•\tRepair defective electrical wiring and leaky gas connections. These are potential fire risks.\n" +
                "•\tSecure water heaters, LPG cylinders etc., by strapping them to the walls or bolting to the floor.\n" +
                "•\tStore weed killers, pesticides, and flammable products securely in closed cabinets with latches and on bottom shelves.\n" +
                "•\tIdentify safe places indoors and outdoors.\n" +
                "•\tUnder strong dining table, bed\n" +
                "•\tAgainst an inside wall\n" +
                "•\tAway from where glass could shatter around windows, mirrors, pictures, or where heavy bookcases or other heavy furniture could fall over\n" +
                "•\tIn the open, away from buildings, trees, telephone and electrical lines, flyovers and bridges\n" +
                "•\tKnow emergency telephone numbers (such as those of doctors, hospitals, the police, etc)\n" +
                "•\tEducate yourself and family members\n" +
                "\nHave a disaster emergency kit ready\n" +
                "•\tBattery operated torch with extra batteries\n" +
                "•\tBattery operated radio\n" +
                "•\tFirst aid kit and manual\n" +
                "•\tEmergency food (dry items) and water (packed and sealed)\n" +
                "•\tCandles and matches in a waterproof container\n" +
                "•\tKnife\n" +
                "•\tChlorine tablets or powdered water purifiers\n" +
                "•\tCan opener.\n" +
                "•\tEssential medicines\n" +
                "•\tCash and credit cards\n" +
                "•\tThick ropes and cords\n" +
                "•\tSturdy shoes\n" +
                "\nDevelop an emergency communication plan\n" +
                "•\tIn case family members are separated from one another during an earthquake (a real possibility during the day when adults are at work and children are at school), develop a plan for reuniting after the disaster.\n" +
                "•\tAsk an out-of-state relative or friend to serve as the 'family contact' after the disaster; it is often easier to call long distance. Make sure everyone in the family knows the name, address, and phone number of the contact person.\n" +
                "\nHelp your community get ready\n" +
                "•\tPublish a special section in your local newspaper with emergency information on earthquakes. Localize the information by printing the phone numbers of local emergency services offices and hospitals.\n" +
                "•\tConduct week-long series on locating hazards in the home.\n" +
                "•\tWork with local emergency services and officials to prepare special reports for people with mobility impairment on what to do during an earthquake.\n" +
                "•\tProvide tips on conducting earthquake drills in the home.\n" +
                "•\tInterview representatives of the gas, electric, and water companies about shutting off utilities.\n" +
                "•\tWork together in your community to apply your knowledge to building codes, retrofitting programmes, hazard hunts, and neighborhood and family emergency plans.\n" +
                "\nWhat to Do During an Earthquake\n" +
                "Stay as safe as possible during an earthquake. Be aware that some earthquakes are actually foreshocks and a larger earthquake might occur. Minimize your movements to a few steps that reach a nearby safe place and stay indoors until the shaking has stopped and you are sure exiting is safe.\n" +
                "\nIf indoors\n" +
                "•\tDROP to the ground; take COVER by getting under a sturdy table or other piece of furniture; and HOLD ON until the shaking stops. If there is no a table or desk near you, cover your face and head with your arms and crouch in an inside corner of the building.\n" +
                "•\tProtect yourself by staying under the lintel of an inner door, in the corner of a room, under a table or even under a bed.\n" +
                "•\tStay away from glass, windows, outside doors and walls, and anything that could fall, (such as lighting fixtures or furniture).\n" +
                "•\tStay in bed if you are there when the earthquake strikes. Hold on and protect your head with a pillow, unless you are under a heavy light fixture that could fall. In that case, move to the nearest safe place.\n" +
                "•\tUse a doorway for shelter only if it is in close proximity to you and if you know it is a strongly supported, load bearing doorway.\n" +
                "•\tStay inside until the shaking stops and it is safe to go outside. Research has shown that most injuries occur when people inside buildings attempt to move to a different location inside the building or try to leave.\n" +
                "•\tBe aware that the electricity may go out or the sprinkler systems or fire alarms may turn on.\n" +
                "\nIf outdoors\n" +
                "•\tDo not move from where you are. However, move away from buildings, trees, streetlights, and utility wires.\n" +
                "•\tIf you are in open space, stay there until the shaking stops. The greatest danger exists directly outside buildings; at exits; and alongside exterior walls. Most earthquake-related casualties result from collapsing walls, flying glass, and falling objects.\n" +
                "\nIf in a moving vehicle\n" +
                "•\tStop as quickly as safety permits and stay in the vehicle. Avoid stopping near or under buildings, trees, overpasses, and utility wires.\n" +
                "•\tProceed cautiously once the earthquake has stopped. Avoid roads, bridges, or ramps that might have been damaged by the earthquake.\n" +
                "\nIf trapped under debris\n" +
                "•\tDo not light a match.\n" +
                "•\tDo not move about or kick up dust.\n" +
                "•\tCover your mouth with a handkerchief or clothing.\n" +
                "•\tTap on a pipe or wall so rescuers can locate you. Use a whistle if one is available. Shout only as a last resort. Shouting can cause you to inhale dangerous amounts of dust.\n");

        textView_flood.setText("Flood");
        textView_flood_definition.setText("A flood is an overflow of water that 'submerges' land. The EU Floods Directive defines a flood as a temporary covering by water of land which is usually not covered by water. In the sense of 'flowing water', the word may also be applied to the inflow of the tides. Flooding may result from the volume of water within a body of water, such as a river or lake, which overflows causing the result that some of the water escapes its usual boundaries. While the size of a lake or other body of water will vary with seasonal changes in precipitation and snow melt, it is not a significant flood unless the water covers land used by man like a village, city or other inhabited area, roads, expanses of farmland, etc.");
        textView_flood_description.setText("Do's & Dont's\n\n" +
                "What to do before a flood\n" +
                "\n" +
                "To prepare for a flood, you should:\n" +
                "•\tBuildings should be avoided in flood prone areas.\n" +
                "•\tWater heaters, electric panels should be kept at upper floors.\n" +
                "•\tCheck valves should be installed on time to prevent unwanted water from backing up into the sewerage system of your homes.\n" +
                "•\tBe in contact with community officials to find out their plans for stopping flood water of the houses in your community.\n" +
                "•\tTo avoid seepage, seal your basement walls with waterproof compounds.\n" +
                "\n" +
                "If a flood is likely to hit your area, you should:\n" +
                "•\tListen to the radio or television for information. As PDMA shares all updates with them.\n" +
                "•\tBe aware that flash flooding can occur. If there is any possibility of a flash flood, move immediately to higher ground. Do not wait for instructions to move.\n" +
                "•\tBe aware of streams, drainage channels, canyons, and other areas known to flood suddenly. Flash floods can occur in these areas with or without such typical warnings as rain clouds or heavy rain.\n" +
                "\n" +
                "If you must prepare to evacuate, you should:\n" +
                "•\tSecure your home. If you have time, bring in outdoor furniture. Move essential items to an upper floor.\n" +
                "•\tTurn off utilities at the main switches or valves if instructed to do so. Disconnect electrical appliances. Do not touch electrical equipment if you are wet or standing in water.\n" +
                "\n" +
                "If you have to leave your home, remember these evacuation tips:\n" +
                "•\tDo not walk through moving water. Six inches of moving water can make you fall. If you have to walk in water, walk where the water is not moving. Use a stick to check the firmness of the ground in front of you.\n" +
                "•\tDo not drive into flooded areas. If floodwaters rise around your car, abandon the car and move to higher ground if you can do so safely. You and the vehicle can be quickly swept away.\n");

        textView_landslide.setText("Landslide");
        textView_landslide_definition.setText("A landslide is described as an outward and downward slope movement of an abundance of slope-forming materials including rock, soil, artificial, or even a combination of these things.");
        textView_landslide_description.setText("Do's & Dont's\n" +
                "\nDo's\n" +
                "•\tPrepare tour to hilly region according to information given by weather department or news channel.\n" +
                "•\tMove away from landslide path or downstream valleys quickly without wasting time.\n" +
                "•\tDrains should be kept clean.\n" +
                "•\tInspect drains for - litter, leaves, plastic bags, rubble etc.\n" +
                "•\tKeep the weep holes open.\n" +
                "•\tGrow more trees that can hold the soil through roots,\n" +
                "•\tIdentify areas of rock fall and subsidence of buildings, cracks that indicate landslides and move to safer areas. Even muddy river waters indicate landslides upstream.\n" +
                "•\tNotice such signals and contact the nearest Tehsil or District Head Quarters.\n" +
                "•\tEnsure that toe of slope is not cut, remains protected, don't uproot trees unless re-vegetation is planned.\n" +
                "•\tListen for unusual sounds such as trees cracking or boulders knocking together.\n" +
                "•\tStay alert, awake and active (3A's) during the impact or probability of impact.\n" +
                "•\tLocate and go to shelters,\n" +
                "•\tTry to stay with your family and companions.\n" +
                "•\tCheck for injured and trapped persons.\n" +
                "•\tMark path of tracking so that you can't be lost in middle of the forest.\n" +
                "•\tKnow how to give signs or how to communicate during emergency time to flying helicopters and rescue team.\n" +
                "\nDon'ts\n" +
                "•\tTry to avoid construction and staying in vulnerable areas.\n" +
                "•\tDo not panic and loose energy by crying.\n" +
                "•\tDo not touch or walk over loose material and electrical wiring or pole.\n" +
                "•\tDo not built houses near steep slopes and near drainage path.\n" +
                "•\tDo not drink contaminated water directly from rivers, springs, wells but rain water if collected directly without is fine.\n" +
                "•\tDo not move an injured person without rendering first aid unless the casualty is in immediate danger.\n");

        textView_thunder.setText("Thunderstorm");
        textView_thunder_definition.setText("A thunderstorm, also known as an electrical storm, lightning storm, or thundershower, is a storm characterized by the presence of lightning and its acoustic effect on the Earth's atmosphere, known as thunder. Thunderstorms occur in association with a type of cloud known as a cumulonimbus. They are usually accompanied by strong winds, heavy rain, and sometimes snow, sleet, hail, or, in contrast, no precipitation at all.");

        textView_cyclone.setText("Cyclone");
        textView_cyclone_definition.setText("Cyclone, tropical cyclone, hurricane, and typhoon are different names for the same phenomenon, which is a cyclonic storm system that forms over the oceans. The determining factor on which term is used is based on where they originate. In the Atlantic and Northeast Pacific, the term \"hurricane\" is used; in the Northwest Pacific it is referred to as a \"typhoon\" and \"cyclones\" occur in the South Pacific and Indian Ocean.");
        textView_cyclone_description.setText("Before the Cyclone season:\n" +
                "•\tCheck the house; secure loose tiles and carry out repairs of doors and windows\n" +
                "•\tRemove dead branches or dying trees close to the house; anchor removable objects such as lumber piles, loose tin sheets, loose bricks, garbage cans, sign-boards etc. which can fly in strong winds\n" +
                "•\tKeep some wooden boards ready so that glass windows can be boarded if needed\n" +
                "•\tKeep a hurricane lantern filled with kerosene, battery operated torches and enough dry cells\n" +
                "•\tDemolish condemned buildings\n" +
                "•\tKeep some extra batteries for transistors\n" +
                "•\tKeep some dry non-perishable food always ready for use in emergency\n" +
                "\nNecessary actions\n" +
                "The actions that need to be taken in the event of a cyclone threat can broadly be divided into:\n" +
                "•\tImmediately before the cyclone season\n" +
                "•\tWhen cyclone alerts and warnings are communicated\n" +
                "•\tWhen evacuations are advised\n" +
                "•\tWhen the cyclone has crossed the coast\n" +
                "\nWhen the Cyclone starts\n" +
                "•\tListen to the radio.\n" +
                "•\tKeep monitoring the warnings. This will help you prepare for a cyclone emergency.\n" +
                "•\tPass the information to others.\n" +
                "•\tIgnore rumors and do not spread them; this will help to avoid panic situations.\n" +
                "•\tBelieve in the official information\n" +
                "•\tWhen a cyclone alert is on for your area continue normal working but stay alert to the radio warnings.\n" +
                "•\tStay alert for the next 24 hours as a cyclone alert means that the danger is within 24 hours.\n" +
                "\nWhen your area is under cyclone warning get away from low-lying beaches or other low-lying areas close to the coast\n" +
                "•\tLeave early before your way to high ground or shelter gets flooded\n" +
                "•\tDo not delay and run the risk of being marooned\n" +
                "•\tIf your house is securely built on high ground take shelter in the safe part of the house. However, if asked to evacuate do not hesitate to leave the place.\n" +
                "•\tBoard up glass windows or put storm shutters in place.\n" +
                "•\tProvide strong suitable support for outside doors.\n" +
                "•\tIf you do not have wooden boards handy, paste paper strips on glasses to prevent splinters. However, this may not avoid breaking windows.\n" +
                "•\tGet extra food, which can be eaten without cooking. Store extra drinking water in suitably covered vessels.\n" +
                "•\tIf you have to evacuate the house move your valuable articles to upper floors to minimize flood damage.\n" +
                "•\tEnsure that your hurricane lantern, torches or other emergency lights are in working condition and keep them handy.\n" +
                "•\tSmall and loose things, which can fly in strong winds, should be stored safely in a room.\n" +
                "•\tBe sure that a window and door can be opened only on the side opposite to the one facing the wind.\n" +
                "•\tMake provision for children and adults requiring special diet.\n" +
                "•\tIf the centre of the cyclone is passing directly over your house there will be a lull in the wind and rain lasting for half an hour or so. During this time do not go out; because immediately after that, very strong winds will blow from the opposite direction.\n" +
                "•\tSwitch off the electrical mains in your house.\n" +
                "•\tRemain calm.\n" +
                "\nWhen Evacuation is instructed\n" +
                "•\tPack essentials for yourself and your family to last a few days. These should include medicines, special food for babies and children or elders.\n" +
                "•\tHead for the proper shelter or evacuation points indicated for your area.\n" +
                "•\tDo not worry about your property\n" +
                "•\tAt the shelter follow instructions of the person in charge.\n" +
                "•\tRemain in the shelter until you are informed to leave\n" +
                "\nPost-cyclone measures\n" +
                "•\tYou should remain in the shelter until informed that you can return to your home.\n" +
                "•\tYou must get inoculated against diseases immediately.\n" +
                "•\tStrictly avoid any loose and dangling wires from lamp posts.\n" +
                "•\tIf you have to drive, do drive carefully.\n" +
                "•\tClear debris from your premises immediately.\n" +
                "•\tReport the correct losses to appropriate authorities.\n");

        textView_wildfire.setText("Wildfire");
        textView_wildfire_definition.setText("Wildfires are large fires which often start in wildland areas. Common causes include lightning and drought but wildfires may also be started by human negligence or arson. They can spread to populated areas and can thus be a threat to humans and property, as well as wildlife.");

        textView_tornado.setText("Tornado");
        textView_tornado_definition.setText("A tornado is a violent and dangerous rotating column of air that is in contact with both the surface of the earth and a cumulonimbus cloud, or the base of a cumulus cloud in rare cases. It is also referred to as a twister or a cyclone, although the word cyclone is used in meteorology in a wider sense, to refer to any closed low pressure circulation. Tornadoes come in many shapes and sizes, but are typically in the form of a visible condensation funnel, whose narrow end touches the earth and is often encircled by a cloud of debris and dust. Most tornadoes have wind speeds less than 110 miles per hour (177 km/h), are approximately 250 feet (80 m) across, and travel a few miles (several kilometers) before dissipating. The most extreme tornadoes can attain wind speeds of more than 300 mph (480 km/h), stretch more than two miles (3 km) across, and stay on the ground for dozens of miles (perhaps more than 100 km).");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.earthquake_dropdown:
                if (textView_earthquake_definition.getVisibility() == View.GONE) {
                    textView_earthquake_definition.setVisibility(View.VISIBLE);
                    textView_earthquake_description.setVisibility(View.VISIBLE);
                    imageView_earthquake_pre.setVisibility(View.VISIBLE);
                    imageView_earthquake.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    textView_earthquake_definition.setVisibility(View.GONE);
                    textView_earthquake_description.setVisibility(View.GONE);
                    imageView_earthquake_pre.setVisibility(View.GONE);
                    imageView_earthquake.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.flood_dropdown:
                if (textView_flood_definition.getVisibility() == View.GONE) {
                    textView_flood_definition.setVisibility(View.VISIBLE);
                    textView_flood_description.setVisibility(View.VISIBLE);
                    imageView_flood_pre.setVisibility(View.VISIBLE);
                    imageView_flood.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_flood_pre.setVisibility(View.GONE);
                    textView_flood_definition.setVisibility(View.GONE);
                    textView_flood_description.setVisibility(View.GONE);
                    imageView_flood.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.landslide_dropdown:
                if (textView_landslide_definition.getVisibility() == View.GONE) {
                    textView_landslide_definition.setVisibility(View.VISIBLE);
                    textView_landslide_description.setVisibility(View.VISIBLE);
                    imageView_landslide_pre.setVisibility(View.VISIBLE);
                    imageView_landslide.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_landslide_pre.setVisibility(View.GONE);
                    textView_landslide_definition.setVisibility(View.GONE);
                    textView_landslide_description.setVisibility(View.GONE);
                    imageView_landslide.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.thunder_dropdown:
                if (textView_thunder_definition.getVisibility() == View.GONE) {
                    textView_thunder_definition.setVisibility(View.VISIBLE);
                    imageView_thunder_pre.setVisibility(View.VISIBLE);
                    imageView_thunder.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_thunder_pre.setVisibility(View.GONE);
                    textView_thunder_definition.setVisibility(View.GONE);
                    imageView_thunder.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.cyclone_dropdown:
                if (textView_cyclone_definition.getVisibility() == View.GONE) {
                    textView_cyclone_definition.setVisibility(View.VISIBLE);
                    textView_cyclone_description.setVisibility(View.VISIBLE);
                    imageView_cyclone_pre.setVisibility(View.VISIBLE);
                    imageView_cyclone.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_cyclone_pre.setVisibility(View.GONE);
                    textView_cyclone_definition.setVisibility(View.GONE);
                    textView_cyclone_description.setVisibility(View.GONE);
                    imageView_cyclone.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.wildfire_dropdown:
                if (textView_wildfire_definition.getVisibility() == View.GONE) {
                    textView_wildfire_definition.setVisibility(View.VISIBLE);
                    imageView_wildfire_pre.setVisibility(View.VISIBLE);
                    imageView_wildfire.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_wildfire_pre.setVisibility(View.GONE);
                    textView_wildfire_definition.setVisibility(View.GONE);
                    imageView_wildfire.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.tornado_dropdown:
                if (textView_tornado_definition.getVisibility() == View.GONE) {
                    textView_tornado_definition.setVisibility(View.VISIBLE);
                    imageView_tornado_pre.setVisibility(View.VISIBLE);
                    imageView_tornado.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_tornado_pre.setVisibility(View.GONE);
                    textView_tornado_definition.setVisibility(View.GONE);
                    imageView_tornado.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;
        }
    }
}