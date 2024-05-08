package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anees.pdma.R;

public class Natural_Disasters_Urdu extends AppCompatActivity implements View.OnClickListener {

    //for vector icons
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    TextView textView, textView1, textView_cyclone, textView_cyclone_definition, textView_cyclone_description, textView_earthquake, textView_earthquake_definition,
            textView_earthquake_description, textView_flood, textView_flood_definition, textView_flood_description, textView_landslide,
            textView_landslide_definition, textView_thunder, textView_thunder_definition, textView_tornado, textView_tornado_definition,
            textView_tornado_description, textView_wildfire, textView_wildfire_description;

    ImageView imageView_earthquake, imageView_earthquake_pre, imageView_flood, imageView_flood_pre, imageView_landslide,
            imageView_landslide_pre, imageView_thunder, imageView_thunder_pre, imageView_cyclone, imageView_cyclone_pre,
            imageView_wildfire, imageView_wildfire_pre, imageView_tornado, imageView_tornado_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natural_disasters_urdu);

        textView = (TextView) findViewById(R.id.text);
        textView1 = (TextView) findViewById(R.id.text_one);

        textView_earthquake = (TextView) findViewById(R.id.text_earthquake);
        textView_earthquake_definition = (TextView) findViewById(R.id.earthquake_definition);
        textView_earthquake_definition.setVisibility(View.GONE);
        textView_earthquake_description = (TextView) findViewById(R.id.earthquake_description);
        textView_earthquake_description.setVisibility(View.GONE);
        imageView_earthquake_pre = (ImageView) findViewById(R.id.earthquake_pre_image);
        imageView_earthquake = (ImageView) findViewById(R.id.earthquake_dropdown);

        textView_flood = (TextView) findViewById(R.id.text_flood);
        textView_flood_definition = (TextView) findViewById(R.id.flood_definition);
        textView_flood_definition.setVisibility(View.GONE);
        textView_flood_description = (TextView) findViewById(R.id.flood_description);
        textView_flood_description.setVisibility(View.GONE);
        imageView_flood_pre = (ImageView) findViewById(R.id.flood_pre_image);
        imageView_flood = (ImageView) findViewById(R.id.flood_dropdown);

        textView_landslide = (TextView) findViewById(R.id.text_landslide);
        textView_landslide_definition = (TextView) findViewById(R.id.landslide_definition);
        textView_landslide_definition.setVisibility(View.GONE);
        imageView_landslide_pre = (ImageView) findViewById(R.id.landslide_pre_image);
        imageView_landslide = (ImageView) findViewById(R.id.landslide_dropdown);

        textView_thunder = (TextView) findViewById(R.id.text_thunder);
        textView_thunder_definition = (TextView) findViewById(R.id.thunder_definition);
        textView_thunder_definition.setVisibility(View.GONE);
        imageView_thunder_pre = (ImageView) findViewById(R.id.thunder_pre_image);
        imageView_thunder = (ImageView) findViewById(R.id.thunder_dropdown);

        textView_cyclone = (TextView) findViewById(R.id.text_cyclone);
        textView_cyclone_definition = (TextView) findViewById(R.id.cyclone_definition);
        textView_cyclone_definition.setVisibility(View.GONE);
        textView_cyclone_description = (TextView) findViewById(R.id.cyclone_description);
        textView_cyclone_description.setVisibility(View.GONE);
        imageView_cyclone_pre = (ImageView) findViewById(R.id.cyclone_pre_image);
        imageView_cyclone = (ImageView) findViewById(R.id.cyclone_dropdown);

        textView_wildfire = (TextView) findViewById(R.id.text_wildfire);
        textView_wildfire_description = (TextView) findViewById(R.id.wildfire_description);
        textView_wildfire_description.setVisibility(View.GONE);
        imageView_wildfire_pre = (ImageView) findViewById(R.id.wildfire_pre_image);
        imageView_wildfire = (ImageView) findViewById(R.id.wildfire_dropdown);

        textView_tornado = (TextView) findViewById(R.id.text_tornado);
        textView_tornado_definition = (TextView) findViewById(R.id.tornado_definition);
        textView_tornado_definition.setVisibility(View.GONE);
        textView_tornado_description = (TextView) findViewById(R.id.tornado_description);
        textView_tornado_description.setVisibility(View.GONE);
        imageView_tornado_pre = (ImageView) findViewById(R.id.tornado_pre_image);
        imageView_tornado = (ImageView) findViewById(R.id.tornado_dropdown);

        imageView_earthquake.setOnClickListener(this);
        imageView_flood.setOnClickListener(this);
        imageView_thunder.setOnClickListener(this);
        imageView_wildfire.setOnClickListener(this);
        imageView_tornado.setOnClickListener(this);
        imageView_landslide.setOnClickListener(this);
        imageView_cyclone.setOnClickListener(this);

        textView.setText("قدرتی آفات");
        textView1.setText("قدرتی آفت کرہ ارض پر قدرتی عوامل کی وجہ سے رونما ہونے والا کوئی بھی سانحہ ہے جیسا کہ سیلاب، طوفان،بادوباراں،آتش فشاں کا پھٹنا،زلزلہ،سونامی اور دیگر ارضیاتی عوامل۔ قدرتی آفت جانی یا پھر مالی نقصان کا باعث بننے کے علاوہ معاشی تباہی کا باعث بھی بن سکتی ہے تاہم اس کا دارومدار اس آفت کی نوعیت اور اس کی شدت اور اس کے بعد اس حوالے سے اٹھائے جانے والے حفاظتی اقدامات یا دستیاب انفراسٹرکچرپر منحصر ہوتا ہے۔");

        textView_earthquake.setText("\t\tزلزلہ");
        textView_earthquake_definition.setText("زمین کی سطح سے اچانک خارج ہونے والی انرجی زلزلے کا باعث بنتی ہے ۔ زمین پر زلزلے زمینی ارتعاش،ہلچل اور بعض اوقات سطح زمین کے اپنی جگہ سے سرکنے کی صورت میں ظاہر ہوتے ہیں۔زلزلے ارضیاتی نقائص کی بناء پر زمین کے کھسکنے کی وجہ سے پیدا ہوتے ہیں۔زمین کے اندر زلزلے کے مرکز کو سسمک فوکس کہا جاتا ہے ۔فوکس سے اوپر سطح زمین کے پوائنٹ کو زلزلے کا مرکز کہا جاتا ہے۔زلزلے انسانی یا حیوانی اموات کا باعث بنتے ہیں۔زلزلے عمارتوں کے انہدام ،آگ،سونامی اور آتش فشانی کا باعث بھی بنتے ہیں۔ان حادثات میں سے بیشتر حادثات سے معیاری تعمیرات،تحفظ کے نظام ، پیشگی وارننگ اور منصوبہ بندی کے اصولوں پر کار بند ہوکر بچا جا سکتا ہے۔");
        textView_earthquake_description.setText(
                "قدرتی آفات سے بچنے کیلئے کیا کیا جائے اور کیا نہ کیا جائے\n" +
                        "زلزلے سے پہلے کیا کرنا چاہئے\n" +
                        "* سیلنگ اور فاؤنڈیشن میں پڑنے والے گہرے شگافوں کی مرمت کریں۔اگر عمارت میں کسی قسم کے ڈھانچہ جاتی نقص کی نشاندہی ہو تو کسی ماہر کی خدمات حاصل کریں۔\n" +
                        "* اوور ہیڈ لائٹنگ فکسچرز کو سیلنگ کے ساتھ جوڑیں۔\n" +
                        "* بلڈنگ اسٹینڈرڈ کے حوالے سے اپنے علاقے سے متعلقہ BISکوڈ پر عمل درآمد کریں۔\n" +
                        "* شیلفوں کو دیواروں کے ساتھ محفوظ طریقے سے باندھیں۔\n" +
                        "* نچلی شیلفوں پر بڑے جسامت کی کوئی چیز رکھیں۔\n" +
                        "* ٹوٹنے والی چیزیں جیسا کہ بوتلیں،گلاس وغیرہ الماریوں میں رکھیں اور الماریوں کو آگے سے کونڈی لگائیں۔\n" +
                        "* تصاویر اور شیشے وغیرہ کی چیزیں بیڈز،صوفے یا بیٹھنے کی دیگر چیزوں سے قدر ے فاصلے پر لٹکائیں۔\n" +
                        "* اوورہیڈ لائٹ اور فین فکسچر کو باندھ کر رکھیں۔\n" +
                        "* بجلی کی خراب تاروں اور گیس کے اخراج ہونے والے کنکشن کی مرمت کروائیں۔\n" +
                        "* واٹر ہیٹرز،ایل پی جی سلنڈرز وغیرہ کو دیوار کے ساتھ لگا کر یا زمین کے ساتھ مضبوطی کے ساتھ باندھ کر رکھیں۔\n" +
                        "* جڑی بوٹیاں تلف کرنے والی دوائی،کیڑے مار ادویات اور آتش گیر مصنوعات کو کسی چیز میں ڈال کر اسے اوپر سے بند کر کے رکھیں۔\n" +
                        "* ان ڈور اور آؤٹ ڈور محفوظ جگہوں کی پہلے سے نشاندہی کر کے رکھیں جیسا کہ\n" +
                        "* مضبوط ڈائننگ ٹیبل،بیڈ کے نیچے \n" +
                        "* اندرونی دیواروں سے دور\n" +
                        "* ایسی جگہوں سے دور جہاں کھڑکیوں کے شیشے،تصاویر ،کتابوں کی الماریاں اور دیگر فرنیچر ٹوٹ کر لگنے کا خطرہ ہو۔\n" +
                        "* کھلی جگہ پر عمارتوں،درختوں،ٹیلی فون اور بجلی کی تاروں،فلائی اوور اور پُلوں سے دور رہیں۔\n" +
                        "* ایمرجنسی ٹیلی فون نمبرز جیسا کہ ڈاکٹرز،ہسپتالوں،پولیس وغیرہ کے نمبرز ذہن نشین کر کے رکھیں۔\n" +
                        "* اپنے آپ اور اپنے خاندان کے افراد کو مطلع رکھیں\n" +
                        "\n" +
                        "ڈیزاسٹر ایمرجنسی کٹ تیار رکھیں\n" +
                        "* بیٹری سے چلنے والی ٹارچ کے ہمراہ اضافی بیٹریاں\n" +
                        "* بیٹری سے چلنے والا ریڈیو\n" +
                        "* فرسٹ ایڈ کٹ اینڈ مینول\n" +
                        "* ایمرجنسی فوڈ(ڈرائی آئٹم) اور پانی(پیک و سیل)\n" +
                        "* پانی سے محفوظ کسی کنٹینر میں موم بتیاں اور ماچس وغیرہ\n" +
                        "* چھری\n" +
                        "* کلورین کی گولیاں یا پانی صاف کرنے والا پاؤڈر\n" +
                        "* کین اوپنر\n" +
                        "* ضروری ادویات\n" +
                        "* کیش و کریڈٹ کارڈز\n" +
                        "* رسی\n" +
                        "* مضبوط جوتے\n" +
                        "ایمرجنسی کمیونیکشن پلان ترتیب دیں:\n" +
                        "* اگر زلزلے کے دوران خاندان کے افراد ایک دوسرے سے بچھڑ جائیں (خاص کر ایسی صورت میں اس کا احتمال زیادہ ہوتا ہے جب بڑے اپنے کام پر ہوتے ہیں اور بچے سکول میں)تو دوبارہ ملنے کا پلان ترتیب دیں۔\n" +
                        "* ایسی صورت میں ملک سے باہر کسی رشتہ دار کی خدمت لیں جو ڈیزاسٹر کے بعد رابطے کو یقینی بنائے کیو نکہ ڈیزاسٹر کے بعد طویل فاصلے سے کال کرنا قدرے آسان ہوتا ہے۔یہ بھی یقینی بنائیں کہ آپ کے خاندان میں ہر کسی کے پاس اس شخص کا نام ،پتہ اور فون نمبر ہونا چاہئے۔\n" +
                        "اپنی کمیونٹی کی تیاریوں کے حوالے سے مدد کریں\n" +
                        "* اپنے مقامی اخبار میں زلزلے سے متعلق معلومات پر مبنی خصوصی اشاعت کو یقینی بنائیں۔مقامی ایمرجنسی سروسز فراہم کرنے والے اداروں اور ہسپتالوں کے نمبرپرنٹ کر کے انہیں تقسیم کریں۔\n" +
                        "* گھر میں خطرات کا پتہ لگانے کے حوالے سے ہفتہ بھر سیریز کا اہتمام کریں۔\n" +
                        "* نقل مکانی کرنے سے معذور افراد کیلئے مقامی ایمرجنسی سروسز فراہم کرنے والے اداروں کے ساتھ ملکر ایک اسپیشل رپورٹ تیار کریں کہ انہیں زلزلے کے دوران کیا کرنا چاہئے۔\n" +
                        "* گھر میں زلزلے سے متعلق مشق کا اہتمام کرنے کیلئے اپنی رائے فراہم کریں۔\n" +
                        "* گیس، بجلی اور پانی فراہم کرنے والی کمپنیوں کے نمائندوں کے انٹرویو کا اہتمام کریں کہ کیسے ہنگامی حالت میں ان چیزوں کو بند کیا جا سکتا ہے۔\n" +
                        "* بلڈنگ کوڈز،ریٹروفٹنگ پروگرامز،ہمسایہ اور فیملی ایمرجنسی پلان ترتیب دینے کیلئے اپنی کمیونٹی کے ساتھ ملکر کام کریں۔\n" +
                        "زلزلے کے دوران کیا کرنا چاہئے\n" +
                        "دوران زلزلہ جہاں تک ہوسکے اپنے تحفظ کو یقینی بنائیں۔یہ گمان مت کریں کہ کسی ہلکے جھٹکے کے ساتھ ہی زلزلہ ختم ہو جائے گا اس سلسلے میں بڑا زلزلہ بھی آسکتا ہے جس کیلئے اپنے آپ کو تیار رکھیں۔کسی محفوظ جگہ پہنچتے ہی اپنی حرکت کو اس وقت تک روک دیں جب تک آپ یہ نہ جان لیں کہ اب زلزلے کی شدت کم ہو گئی ہے اور آپ بحفاظت باہر نکل سکتے ہیں۔\n" +
                        "اگر آپ گھر کے اندر ہوں تو آپ کو کیا کرنا چاہئے:\n" +
                        "* زمین پر لیٹ جائیں اور گھر میں رکھے کسی مضبوط ٹیبل یا کسی دوسرے فرنیچر کے نیچے ہو جائیں اور اس وقت تک وہیں رہیں جب تک کہ زلزلے کے جھٹکے تھم نہ جائیں۔اگر آپ کے نزدیک کوئی مضبوط ٹیبل یا ڈیسک موجود نہیں تو ایسی صورت میں اپنے چہرے اور سر کو اپنے بازوں سے ڈھانپ لیں اور عمارت کے اندرونی جانب کو جھک جائیں۔\n" +
                        "* اندرونی دروازے کی چوکھٹ،کمرے کے کسی کونے یا پھر ٹیبل یا بیڈکے نیچے بیٹھ کر اپنا تحفظ یقینی بنائیں\n" +
                        "* شیشوں،کھڑکیوں،آؤٹ سائیڈ ڈورز اور دیواروں یا دیگر ایسی چیزوں جن کے گرنے کا احتمال ہو(جیسا کہ لائٹنگ فکسچرز یا فرنیچر) سے دور رہیں۔\n" +
                        "* اگر آپ بستر پر ہیں اور زلزلہ آ گیا ہے تو ایسی صورت میں اپنے بستر پر ہی لیٹے رہیں اور اپنے سر کو تکیے سے ڈھانپ لیں لیکن یہ ضرور یقینی بنائیں کہ آپ کے اوپر کوئی باری بھر کم لائٹنگ فکسچر گرنے کا خطرہ نہیں ہے۔اگر ایسی صورتحال ہو تو پھر کسی قریبی محفوظ جگہ چلے جائیں۔\n" +
                        "* آپ کسی بند دروازے کی چوکھٹ کو بھی پناہ گاہ کے طور پر استعمال کر سکتے ہیں اگر آپ یہ جانتے ہیں کہ یہ مضبوط اور وزن برداشت کرنے والی چوکھٹ ہے۔\n" +
                        "* جب تک جھٹکے ختم نہیں ہو جاتے اندر ہی رہیں اور اس کے بعد ہی باہر نکلنا محفوظ ہے۔تحقیقات سے پتہ چلا ہے کہ بیشتر لوگ اس وجہ سے زخمی ہوتے ہیں کہ وہ عمارت کے اندر ہونے کی صورت میں کسی دوسری جگہ منتقل ہونے یا پھر عمارت سے نکلنے کی کوشش کرتے ہیں۔\n" +
                        "* خبر دار رہیں کہ بجلی بند اور فائر الارم سسٹم آن ہے۔\n" +
                        "اگر آپ گھر سے باہر ہوں تو آپ کو کیا کرنا چاہئے:\n" +
                        "* آپ جہاں ہیں وہیں کھڑے رہیں تا ہم عمارتوں،درختوں،سٹریٹ لائٹس اور کھمبوں سے دور رہیں۔\n" +
                        "* اگر آپ کھلی فضا میں ہیں تو زلزلے کے جھٹکے ختم ہونے تک وہیں ٹھہریں۔دوران زلزلہ اکثر ہلاکتیں دیواریں گرنے،شیشے ٹوٹنے یا دیگر چیزوں کے گر کر لگنے کی وجہ سے ہوتی ہیں۔\n" +
                        "اگر آپ چلتی ہوئی گاڑی میں ہیں تو:\n" +
                        "* فوری طور پر گاڑی روک لیں اور گاڑی میں ہی بیٹھے رہیں تا ہم کسی عمارت ،درختوں،اوور پاسز اور بجلی کے کھمبوں کے قریب کھڑا ہونے سے اجتناب کریں۔\n" +
                        "* زلزلہ ختم ہوجانے پر ہی گاڑی دوبارہ احتیاط سے چلائیں تا ہم اس سلسلے میں ایسی سڑکوں،پلوں اور ریمپس پر جانے سے گریز کریں جنہیں زلزلے کی وجہ سے نقصان پہنچا ہو۔\n" +
                        "اگر آپ ملبے کے ڈھیر کے نیچے پھنس چکے ہیں تو:\n" +
                        "* ماچس کی تیلی ہر گز نہ جلائیں\n" +
                        "* حرکت کرنے یا اپنے اوپر پڑی مٹی کو صاف کرنے کی کوشش نہ کریں\n" +
                        "* اپنے منہ کو رومال یا کسی کپڑے سے ڈھانپ لیں\n" +
                        "* کسی پائپ یا وال پر دستک دیں تا کہ امدادی کارکن آپ کو ڈھونڈ سکیں۔اگر کوئی دستیاب ہو تو ایسی صورت میں سیٹی بجائیں اس سلسلے میں چلانا آپ کے پاس آخری انتخاب ہے کیونکہ چلانے سے آپ پر زیادہ مٹی آنے کا خطرہ ہوتا ہے جس سے آپ کی سانس بند ہو سکتی ہے۔\n");

        textView_flood.setText("\t\t۔ سیلاب");
        textView_flood_definition.setText("سیلاب پانی کے اس بہاؤ کو کہتے ہیں جس کی وجہ سے زمین زیر آب آ جاتی ہے۔EUفلڈ ڈائریکٹو کے مطابق سیلاب پانی کا وہ وقتی بہاؤ ہے\n" +
                "جس کی وجہ سے زمین کا وہ حصہ جو عام طور پر زیر آب نہیں ہوتا زیر آب آ جاتا ہے۔چلتے پانی کی صورت میں یہ لفظ لہروں نکلنے پر بھی صادق آتا ہے۔سیلاب پانی کے کسی ذخیزے میں تبدیلی یعنی\n" +
                "دریا یا جھیل میں پانی کے بڑھ جانے کی وجہ سے بھی رونما ہوسکتے ہیں تا ہم پانی کے یہ ذخیزے یعنی جھیل وغیرہ موسمیاتی تبدیلیوں کی وجہ سے بھی تبدیل ہو سکتے ہیں جیسا کہ برف پگھلنے کی وجہ سے تاہم انہیں سیلاب شمار نہیں کیا جا سکتا جب تک کہ پانی کسی گاؤں،شہر اور کسی رہائشی علاقے،سڑکوں وغیرہ کو زیر آب نہ کرلے۔\n" );
        textView_flood_description.setText(" " +
                "سیلاب سے بچنے کیلئے کیا کیا جائے اور کیا نہ کیا جائے\n" +
                "سیلاب سے پہلے کیا کرنا چاہئے\n" +
                "سیلاب سے بچاؤ کیلئے آپ کو چاہئے کہ\n" +
                "* سیلاب زدہ علاقوں میں عمارتوں کی تعمیر سے گریز کریں\n" +
                "* گھر میں واٹر ہیٹرز،الیکٹرک پینلز کو اوپر والے فلور پر رکھیں\n" +
                "* گھر کے سیوریج سسٹم سے غیر ضروری پانی کو داخل ہونے سے روکنے کیلئے چیک والوز نصب کریں\n" +
                "* سیلاب کے پانی کو گھروں میں داخل ہونے سے روکنے کے حوالے سے منصوبہ ساز ی کیلئے کمیونٹی آفیشلز سے رابطے میں رہیں۔\n" +
                "* سیپیج سے بچنے کیلئے اپنی بیسمنٹ کی دیوار کو واٹر پروف کمپاؤنڈ سے سیل کریں\n" +
                "اگر آپ کے علاقے میں سیلاب آنے کا خطرہ ہوتو آپ کو چاہئے کہ\n" +
                "* معلومات کے حصول کیلئے ریڈیو اور ٹیلی وژن دیکھتے رہیں کیونکہ پی ڈی ایم اے اس حوالے سے اپ ڈیٹ ا ن اداروں کے سات شیئر کرتا رہتا ہے۔\n" +
                "* شدید سیلاب سے نمٹنے کیلئے تیار رہیں۔اگر ایسی صورتحال کا سامنا ہوتو فوراً کسی اونچے مقام پر منتقل ہو جائیں۔اس سلسلے میں کسی بھی قسم کی ہدایت کا انتظا ر نہ کریں۔\n" +
                "* ندی نالوں،نکاسی آب،کینال اور دیگر ایسے علاقوں سے دور رہیں جہاں اچانک سیلاب زور پکڑ سکتا ہے۔ایسی علاقوں میں بادل یا پھر شدید بارش کے بغیر بھی شدید سیلاب آ سکتا ہے۔\n" +
                "اگر آپ انخلاء کیلئے تیار ہیں تو آپ کو چاہئے کہ\n" +
                "* اپنے گھر کے تحفظ کو یقینی بنائیں۔اگر آپ کے پاس وقت ہے تو باہر پڑے فرنیچر کو گھر کے اندر لے آئیں۔ضروری سامان کو اوپر والے فلور پر منتقل کر یں۔\n" +
                "* یوٹیلٹی کنکشن کو مین جگہوں سے بند کر دیں یا ان کے والو بند کردیں۔بجلی کے جملہ آلات کو اتار کر رکھیں۔اگر آپ گیلے ہیں یا آپ پانی میں کھڑے ہیں تو ایسی صورت میں بجلی کے آلات کو مت چھوئیں۔\n" +
                "\n" +
                "اگر آپ کو اپنا گھر چھوڑنا ہے تو انخلاء کیلئے حسب ذیل تجاویز کو ذہن نشیں رکھیں:\n" +
                "* چلتے ہوئے پانی میں چلنے سے گریز کریں۔چھ انچ تک کا چلتا ہوا پانی آپ کو گرا سکتا ہے۔اگر آپ کو پانی میں چلنا پڑ رہا ہے تو اس سمت میں نہ چلیں جس طرف پانی چل رہا ہے۔پانی کی گہرائی معلوم کرنے کیلئے چھڑی کا استعمال کریں۔\n" +
                "* سیلاب زدہ علاقے میں گاڑی چلانے سے گریز کریں۔اگر سیلاب کا پانی آپ کی گاڑی سے اونچا ہو رہا ہے تو ایسی صورت میں اپنی گاڑی کو چھوڑ کر کسی اونچی جگہ پر منتقل ہو جائیں بصورت دیگر آپ اپنی گاڑی کے ساتھ پانی میں بہہ سکتے ہیں۔\n");

        textView_landslide.setText("\t\t۔ لینڈ سلائیڈنگ");
        textView_landslide_definition.setText(" " +
                "لینڈ سلائیڈنگ کسی ڈھلوانی مواد یعنی کسی چٹان،مٹی،راکھ یا حتی ٰ کہ ان چیزوں کے مجموعے کا بڑی تعداد میں اوپر کو اٹھنے یا نیچے کی طرف گرنے کا عمل ہے۔\n" +
                "\n" +
                "( احتیا طی تد ابیر اختیا ر کیجیے) \n" +
                "پہاڑی علاقوں کی طرف سفرمحکمہ موسمیات کی اطلاعات کے مطابق کرنا چاہیے۔\n" +
                "لینڈ سلائڈنگ والی جگہ اور بہاوٗ والی وادی سے جلد از جلد کوچ کرلینا چاہیے۔\n" +
                "نالیاں کو صاف رکھنی چاہئے۔\n" +
                "نالیوں کو گندگی،پٹے، پلاسٹک بیگ اور ایسی دوسری اشیاء جن سے نالی بند ہونے کاخطرہ ہو، سے بچانا چاہیے۔ \n" +
                "نالیوں کی سوراخیں کھلی رکھنی چا ہیے زیادہ سے زیاد ہ درخت لگائے تاکہ زمین مظبو ط رہے \n" +
                "پتھر گرنے کی جگہ پہچان لی جائے ان کے گرد احتیاطی عمارات قا ئم کئے جائے\n" +
                "تحصیل اور ضلعی ہیڈ کوارٹزذ کے نزدیک سگنلز پر رابط کر نا چا ہیے \n" +
                "درختو ں کو جڑوں سے نہ اوکھاڑے تاکہ زمین مظبو ط رہے \n" +
                "غیر متعلقہ آ واز سنے جیسے درختو ں کا گر نہ بو لڈرز کھڑ کھڑانا\n" +
                "چست اور چاک و چوبندجاگتے رہے \n" +
                "محفوظ جگہ کو تلا ش کر کے ادھرمنتقل ہو جا ئے\n" +
                "اپنے خاندان اور احبا ب کے سا تھ رہے\n" +
                "زخمی اور پھنسے ہو ئے لو گو ں کو تلا ش کر ے \n" +
                "جگہ جگہ پر نشا ن چھوڑے تا کہ آ پ جنگل میں گم نہ ہو جائے\n" +
                "ایمر جنسی کے وقت سگنل دینا اور لو گوں سے مدد حا صل کر نے کا طریقہ سیکھ لیں\n" +
                "( ایسے اقدامات سے گریز کیجیے گا )\n" +
                "ؐؐؐؐؐؐؐؐخطروں والی جگہ پر تعمیرات اور رہنے سے اجتناب کر ے \n" +
                "رونے سے اپنی توانا ئی کم نہ کر ے اورخو ف زدہ نہ ہو \n" +
                "نازک چیز و ں کو ہا تھ نہ لگا ئے نہ ان پر چا لیں نہ ہی بجلی کے کھمبو ں اور تا روں کو ہا تھ نہ لگا ئے \n" +
                "کھا ئی والی جگہو ں پر گھر نہ بنا ئیں \n" +
                "در یاؤں اور کنوؤں سے گندہ پا نی نہ پیئے بہرحال بارش کا پا نی پیا جا سکتا ہے \n" +
                "کسی بھی زخمی کو ابتدائی طبی امداد دیے بغیر ایک جگہ سے دوسر ی جگہ منتقل نہ کرے\n");

        textView_thunder.setText("\t\tگرج کا طوفان");
        textView_thunder_definition.setText(" " +
                "طو فا نی بجلی سے مراد بجلی کا گرجنا یا بجلی کا چمکنا ہے طو فا نی بجلی اس و قت سامنے آ تی ہے \n" +
                "بادلوں کے ٹکرانے سے بَیَک وقت جو آواز اور برقی رَو پیدا ہوتی ہے اْسے ’’بادل کی گرج‘‘ کہتے ہیں۔ اِس کا انحصار بادلوں کے ٹکرانے کی نوعیت اور سْننے والے کے فاصلے پر ہوتا ہے۔ یہ دھماکا کبھی بہت زوردار اور واضح چمک کے ساتھ ہوتا ہے اور کبھی اِس کا بالکل اْلٹ۔ بادلوں کے ٹکراؤ کی وجہ سے دباؤ اور حرارت میں اچانک جو اضافہ ہوتا ہے یہ بہت تیزی کے ساتھ ایک محدود دائرے میں ہوا کے دباؤ میں اضافہ کردیتا ہے۔ نتیجے کے طور پر ہوا کے دباؤ میں یہ اضافہ ہوا کا ایک جھٹکا سا پیدا کرتا ہے جس کی وجہ سے دھماکا ہوتا ہے اور اسی کو ’’بادلوں کا ٹکرانا یا گرجنا‘‘ کہا جاتا ہے۔آسمانی برقی رَو دیکھنے اور دھماکے کی آواز سْننے کے دورانیے سے برقی رَو کا فاصلہ معلوم کیا جاسکتا ہے۔ ابتدامیں اسے بادلوں کا ٹکراؤ سمجھا جاتا تھا۔ 19ویں صدی میں یہ تصور پیش کیا گیا کہ اس آسمانی برقی رَو کی وجہ سے ایک خلاء پیدا ہوجاتا ہے۔ 20ویں صدی میں اس بات پر اتفاقِ رائے ہوگیا کہ بادلوں کی گرج ہوا میں ایک زبردست جھٹکے سے شروع ہوتی ہے۔ اس پورے عمل میں حرارت میں غیرمعمولی تیزی سے اضافہ ہوتا ہے اور پھر آہستہ آہستہ کم ہوتا ہے جس کی وجہ سے بیرونی دائرے میں اس کا پھیلاؤ بڑھتا جاتا ہے اور قرب و جوار کی سَرد ہوائیں آواز کی رفتار سے زیادہ تیزی کے ساتھ اْس کی جگہ لے لیتی ہیں۔\n");

        textView_cyclone.setText("\t\tتیز آندھی");
        textView_cyclone_definition.setText("جب خشک موسم میں میدانی علاقوں میں تلاطم خیز ہوائیں گرد و غبار کے بادلوں کے ساتھ آتی ہیں تو انہیں گرد و غبار کے طوفان یا آندھی کہا جاتا ہے۔ یہ طوفان اپنے ساتھ سیاہ یا زرد رنگ کے گرد و غبار کے بادل لاتے ہیں جنہیں بالترتیب سیاہ یا زرد آندھی کہا جاتا ہے۔جب یہ طوفان آتے ہیں تو سیاہ آندھی کی صورت میں ماحول پر گھٹا ٹوپ اندھیرا چھا جاتا ہے اور زرد آندھی کی صورت میں ہر شے زرد نظر آتی ہے۔ ہر دو صورتوں میں خوب ریت برستی ہے۔ ہوا کی رفتار بے انتہا تیز ہوتی ہے جس کا نتیجہ زبردست تباہی ہوتا ہے۔ درخت اور کھمبے وغیرہ جڑ سے اکھڑ جاتے ہیں، فصلیں تباہ ہو جاتی ہیں، مویشی مر جاتے ہیں، مکانات کی چھتیں اڑ جاتی ہیں، غرض کہ وسیع پیمانے پر جانی و مالی نقصان ہوتا ہے۔ یہ طوفان بڑے طویل فاصلے طے کرتے ہیں اور اب تک کے مطالعے کے مطابق یہ فاصلہ دو سے ڈھائی ہزار میل ہے۔\n");
        textView_cyclone_description.setText(" " +
                "(تیز آندھی سے پہلے کی احتیاطی تدابیر)\n" +
                "گھر کی جانچ پڑتال کرو و،دروازے اور کھڑکیوں کی مرمت کر والے \n" +
                "مردہ شاخوں کو ہٹا دیں گھر کے نزدیک لچکدار اشیاء جیسے لکڑی کے ڈھیر، ڈھیلا ٹن چادریں، ڈھیلی اینٹوں، ردی کی ٹوکری کے کین، نشانی بورڈ وغیرہ جو مضبوط ہواؤں میں پرواز کر سکتی ہیں بھی ہٹا دیں\n" +
                "کچھ لکڑی کے بورڈوں کو تیار رکھیں تاکہ ضرورت ہو تو شیشے کی ونڈوز کو استعما ل کیا جا سکے\n" +
                "بیٹری سے چلنے والی مشعل اورکیروسین آئل سے بھرا ہوا ایک لالٹین پا س رکھیں\n" +
                "کمزورں عما ر تو ں کو مسمارکر دے \n" +
                "ٹرانسٹسٹرز کے لئے کچھ اضافی بیٹریاں رکھیں\n" +
                "کچھ خشک غیر تباہ کن خوراک ہمیشہ ہنگامی صورت میں استعمال کے لئے تیار رکھیں\n" +
                "(ضروری اقدامات)\n" +
                "سائکلون کے خطرے کی صورت میں کیے جانے والے اقدامات کو وسیع طور پراس طرح تقسیم کیا جا سکتا ہے\n" +
                "سائکو ن کے مو سم سے پہلے ہی \n" +
                "جب سائکلون کے خدشات سے مطلع کیا جاتا ہے\n" +
                "جس رہا ئشیو ں کو مشورہ د یا جا تا ہے \n" +
                "جب سائیکلون ساحل سے گزر جائے\n" +
                "\n" +
                "(جب سائکلون شروع ہو)\n" +
                "\n" +
                "ریڈیو سنے\n" +
                "انتباہات کی نگرانی کریں. یہ آپ کی سائیکلوں ہنگامی صورتحال کے لئے تیاری میں مدد کرے گا\n" +
                "دوسروں کو معلو ما ت سے آگاہ کر ئے\n" +
                "افواہوں کو نظر انداز کریں اور نہ پھیلائیں؛ یہ گھبراہٹ والے حالات سے بچنے میں مدد دے گی\n" +
                "سر کاری معلوما ت پر یقین رکھے \n" +
                "جب آ پ کے علا قہ میں سا ئیکلون ارلٹ نا فذ ہو ں تو اپنا کا م جاری رکھے اور ریڈیو کو سنتے رہیں\n" +
                "اگلے 24 گھنٹوں میں انتباہ کے طور پر الرٹ رہیں اس کا مطلب یہ ہے کہ خطرہ24 گھنٹوں تک مو جود رہے گا ۔\n" +
                "(جب آپ کے علا قے میں سا ئیکلو ن کا خطر ہ ہو تو نچلی سا حلی سطح سے دور رہیں)\n" +
                "انچی سطح سیلا ب کی زد میں آجا ئے اس سے پہلے وہ جگہ چھوڑ دیں\n" +
                "تنہا رہ جا نے کے خطرہ سے بچنے کے لئے جلدی کریں\n" +
                "اگر آپ کے گھر کو محفوظ طریقے سے اعلی سطح پر تعمیر کیا گیا ہے تو گھر کے محفوظ حصے میں پناہ گزین رہے. البتہ وہا ں سے نکل جانے کی ہدایت سے نہ گھبرائیں اور جگہ خا لی کر دیں183\n" +
                "شیشے کی کھڑکیوں کا تختہ بند رکھے یا پھر اس جگہ میں طوفان سے بچھا ؤ کے لئے شٹر ڈالیں.\n" +
                "باہر کے دروازے کے لئے مضبوط مناسب مدد فراہم کریں.\n" +
                "گر آپ کے پاس لکڑی کے بورڈوں کے نہیں ہے تو، splinters کو روکنے کے لئے شیشے پر کاغذ سٹرپس پیسٹ کریں.\n" +
                "تاہم، یہ ونڈوز توڑنے سے بچنے سے بچ نہیں سکتی۔\n" +
                "اضافی خوراک حاصل کریں، جو کھانا پکانے کے بغیر کھایا جا سکتا ہے. مناسب طریقے سے پینے کے پانی کو ذخیرہ کرے\n" +
                "اگر آ پ کو گھر سے نکلنا پڑے تو اپنی قیمتی اشیا ء کو دو سر ی منز ل پر منتقل کرے تا کہ سیلاب سے بچ سکے\n" +
                "اس بات کو یقینی بنانا کہ آپ کی لالٹین، مشعل یا دیگر ہنگامی لائٹس کام کرنے کی حالت میں ہیں اور انہیں برقرار رکھیں\n" +
                "چھوٹے اور ڈھیلی چیزیں، جو تیز ہواؤں میں پرواز کر سکتے ہیں، کو کمرے میں محفوظ طریقے سے رکھ دے \n" +
                "اس بات کا یقین رکھے کہ ایک کھڑکی اور دروازے ہوا کے ضمنی مخالف پر کھولا جا سکتا ہو.\n" +
                "بچوں اور بالغوں کے لئے خاص غذا کا خیا ل رکھے ۔\n" +
                "اگر سائیکلون کا مرکز آپ کے گھر میں براہ راست گزر رہا ہے، تو ہوا اور بارش آدھے گھنٹہ تک جا ری رہے گی . اس وقت کے دوران با ہر نہیں جاناکیونکہ اس کے بعد فوری طور پر، بہت مضبوط ہواؤں کو مخالف سمت سے دھکا دیا جائے گا.\n" +
                "اپنے گھر میں بجلی کی کو بند رکھے.\n" +
                "پرسکون رہیں\n" +
                "(جب نکلنے کی ہدایت دی جائے)\n" +
                "آپ کے خاندان کے لئے کچھ دنوں کے لئے لازمی اشیاء پیک کریں. ان میں دواؤں،\n" +
                "بچوں اور بچویوں یا بزرگوں کے لئے خصوصی کھانارکھے\n" +
                "منا سب پنا ہ گاہ کی طر ف ر جو ع کرے\n" +
                "اپنی جا ئیداد کے بار ے میں فکر نہ کر یں۔\n" +
                "پناہ گاہ میں شخص کی ہدایات پر عمل کریں.\n" +
                "پناہ گاہ میں رہو جب تک آپ کو جانے کے بارے میں مطلع نہیں کیا جاتا\n" +
                "(سائیکلون کے بعد کے اقدامات )\n" +
                "آپ کو پناہ گاہ میں رہنا چاہئے جب تک کہ آپ اپنے گھر واپس لوٹ سکتے ہیں.\n" +
                "چراغ کے کسی بھی ڈھیلا اور خطرناک تار سے سختی سے بچیں.\n" +
                "اگر آپ کو گا ڑ ی چلانی ہو تو، احتیاط سے چلائیں\n" +
                "آپ اپنے احاطے کو فوراً صاف کریں.\n" +
                "مناسب ضوابط مناسب حکام کو رپورٹ کریں\n");

        textView_wildfire.setText("\t\tجنگل کی آگ");
        textView_wildfire_description.setText(" " +
                "جنگلی آگ یا جنگل میں آگ، کسی بھی خطے میں ‘‘بے قابو‘‘ درجے کی لگنے والی وہ آگ ہے جو جنگل میں یا کسی غیر آباد سرسبز علاقے میں قدرتی طور پر بھڑک اٹھے۔ جنگلی آگ کو عمومی طور پر خطرناک تصور کیا جاتا ہے اور ماحول کے لیے گذشتہ صدی میں بڑھتا ہوا خطرہ گردانا گیا ہے۔ سائنسی لحاظ سے جنگلی آگ کو خطے اور مقامات جہاں پر یہ بھڑکنے کے حساب سے درجہ بندی میں تقسیم کیا گیا ہے۔\n" +
                "جنگل میں لگنے والی یہ آگ تکنیکی لحاظ سے اپنی رفتار اور اس کے نتیجے میں برپا ہونے والے اثرات کی وجہ سے دوسری آگ کی اقسام سے ممتاز ہوتی ہے۔ یہ غیر متوقع طور پر اپنی سمت تبدیل کرتی ہے اور یہ سطح پر موجود فاصلوں جیسے سڑکیں، دریا، نہر، ندیاں، اور حفاظتی اقدامات کو اپنی رفتار اور شدت کی وجہ سے عبور کر سکتی ہے۔ جنگل میں قدرتی طور پر بھڑکنے والی آگ کی وجوہات، رفتار و شدت، جلنے والے ایندھن کی نوعیت اور موسمی حالات اس کی خصوصیات میں شامل ہیں۔جنگل میں آگ کے واقعات دنیا میں انٹارکٹکا کے علاوہ ہر براعظم پر برپا ہوتے ہیں۔ ارضی تاریخ اور انسانی تاریخ سے ثابت ہے کہ زمانہ قدیم سے یہ موجود رہی ہے اور اس کے مخصوص خطے میں برپا ہونے میں ایک توازن دریافت ہوا ہے۔ جنگل میں برپا ہونے والی آگ وسیع پیمانے پر نقصان کا باعث بنتی ہے، جس میں املاک، ماحول اور انسانی جانوں کا نقصان شامل ہے، مگر کئی علاقوں میں اس آگ کے گھنے جنگلوں میں کئی فوائد بھی مشاہدے میں آئے ہیں۔ پودوں کی کئی اقسام ایسی دریافت کی گئی ہیں جو اس آگ کی گرمی اور اس سے پیدا ہونے والی راکھ کی بنیاد پر ہی بڑھوتری کر سکتے ہیں۔ عمومی تناسب کے لحاظ سے جنگل میں آگ کے فوائد کی نسبت نقصانات زیادہ سمجھے جا سکتے ہیں۔\n");

        textView_tornado.setText("\t\tٹورنیڈو");
        textView_tornado_definition.setText("ٹورنیڈو قدرت کے انتہائی تباہ کن طوفان ہیں۔ وہ بغیر کسی انتباہ کے اچانک آسکتے ہیں اور دھول اور ملبہ اٹھانے یا بادلوں کے قیف نما شکل لینے سے قبل غائب ہوسکتے ہیں۔ اس بات کی منصوبہ بندی اور اس عمل شرکت کہ آ پ کس طرح اور کہاں پناہ لیتے ہیں زندگی بچانے کا معاملہ بن جاتا ہے۔ تیزی سے کارروائی کرنے کیلئے تیار رہیں۔ اس بات کو ذہن میں رکھیں کہ اگرچہ مغرب وسطی، جنوب مشرق اور جنوب مغرب میں ٹورنیڈو کافی عام ہیں، تاہم وہ کسی بھی ریاست میں اور سال کے کسی بھی وقت آسکتے ہیں، جس کیلئے پیشگی تیاری انتہائی ضروری ہے۔\n");
        textView_tornado_description.setText(" " +
                "اقدام 1: ایک کٹ حاصل کریں\n" +
                "ہنگامی سازوسامان کا تھیلا لیں جس میں نہ گلنے سڑنے والی غذائیں، پانی، بیٹری والی یا ہینڈ کرینک ریڈیو، اضافی فلش لائٹیں اور بیٹریاں شامل ہیں۔\n" +
                "اسے اپنی پناہ والی جگہ پر محفوظ کرلیں\n" +
                "اقدام 2: ایک منصوبہ بنائیں\n" +
                "اپنے خاندان کو تیار کریں خاندان کیلئے ہنگامی منصوبہ بنائیں۔ ممکن ہے کہ آفت کے وقت آپ کا خاندان ایک جگہ نہ ہو، لہذا یہ جاننا ضروری ہے کہ آپ کس طرح ایک دوسرے سے رابطہ کریں گے، آپ کس طرح آپس میں ملیں گے اور ہنگامی حالت میں کیا کریں گے۔ ایسے مقامات کی منصوبہ بندی کریں جہاں آپ کا خاندان ملے گا، یہ جگہ آپ کے پاس پڑوس کے علاقے اور اس علاقے سے باہر دونوں جگہ ہو۔ ایک طویل فاصلے کی کال کرنا پورے شہر میں کال کرنے سے زیادہ آسان ہوسکتا ہے، لہذا شہر سے باہر رابطے کا نمبر الگ ہوچکے اہل خاندان کے درمیان گفتگو کیلئے بہتر ہوسکتا ہے۔ آپ ایسی جگہوں سے متعلق ہنگامی منصوبوں کے بارے میں معلومات حاصل کرنا چاہ سکتے ہیں جہاں آپ کا خاندان وقت گزارتا ہے: کام، یومیہ نگہداشت اور اسکول۔ اگر کوئی منصوبہ نہیں ہے تو، منصوبہ بنانے کیلئے رضاکارانہ خدمات پرغور کریں۔ پیشگی طور پر اس بات کا تعین کریں کہ ٹورنیڈو کے انتباہ کی صورت میں آپ کہاں پناہ لیں گے: طوفان کیلئے تہہ خانہ یا زمین دوز کمرے بہترین تحفظ فراہم کرتے ہیں۔ اگر زیر زمین پناہ گاہ دستیاب نہ ہو تو، ممکنہ طور پر سب سے نچلی منزل کے اندرونی کمرے یا راہداری میں چلے جائیں۔ زیادہ اونچی عمارتوں میں، ممکنہ طور پر سب سے نچلی منزل کے چھوٹے اندرونی کمرے یا راہداری میں چلے جائیں۔ کھڑکیوں، دروازوں اور باہری دیواروں سے دور رہیں۔ کمرے کے مرکز میں چلے جائیں۔ کونے سے دور رہیں کیونکہ وہاں ملبہ آسکتا ہے۔ گآڑی، ٹریلر یا گشتی گھر (موبائل ہوم) بہتر تحفظ فراہم نہیں کرتے۔ اگر ممکن ہو تو فوراً کسی مضبوط بنیاد والی عمارت میں جانے کا منصوبہ بنائیں۔ اگرپناہ دستیاب نہ ہو تو، کسی گڑھے یا نشیبی حصے میں سیدھے لیٹ جائیں۔ کسی اوورپاس یا پل کے نیچے نہ جائیں۔ آپ نشیبی، مسطح مقام پر محفوظ ہیں۔ جب تک خطرہ ٹل نہ جائے پناہ والی جگہ پر رہنے کا منصوبہ رکھیں۔ اپنے مقامی سیٹیزن کورپس چیپٹر (انگریزی میں) سے معاشرتی ہنگامی کارروائی کی ٹیم (CERT) کی کلاس لیں۔ اپنی تربیت کو تاحال تازہ رکھیں۔ درج ذیل پر جاکر معلوم کریں کہ کس طرح کسی ہنگامی صورتحال کے دوران یا بعد میں غذا کو محفوظ رکھیں۔\n" +
                "اقدام 3: باخبر رہیں\n" +
                "\n" +
                "ان اصطلاحات سے واقفیت حاصل کریں جن کا استعمال ٹورنیڈو کے خطرے کی شناخت کیلئے کیا جاتا ہے۔ ٹورنیڈو کی نگرانی کا مطلب ہے کہ آپ کے علاقے میں ٹورنیڈو کا آنا ممکن ہے۔ ٹورنیڈو کا انتباہ ایسی صورت میں ہوتا ہے جب واقعتاً ٹورنیڈو آرہا ہوتا ہے، ایسی صورت میں فوراً پناہ گاہ میں چلے جائیں۔ مقامی افسران کو سنیں اپنے علاقے میں اپنے ریاستی اور مقامی انتظامیہ کے ذریعہ تیار کردہ ہنگامی منصوبوں کے بارے میں معلوم کریں۔ ہنگامی صورتحال میں، ہمیشہ، مقامی ایمرجنسی منیجمنٹ کے اہلکاران کے ذریعہ دی گئی ہدایات کو سنیں۔ ٹورنیڈوز کیلئے منصوبہ بندی اور تیاری کرنے کے طریقوں ساتھ ہی ٹورنیڈو کے دوران اور بعد میں کیا کریں\n");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                    imageView_landslide_pre.setVisibility(View.VISIBLE);
                    imageView_landslide.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    imageView_landslide_pre.setVisibility(View.GONE);
                    textView_landslide_definition.setVisibility(View.GONE);
                    imageView_landslide.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.thunder_dropdown:
                if (textView_thunder_definition.getVisibility() == View.GONE) {
                    textView_thunder_definition.setVisibility(View.VISIBLE);
                    imageView_thunder_pre.setVisibility(View.VISIBLE);
                    imageView_thunder.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    textView_thunder_definition.setVisibility(View.GONE);
                    imageView_thunder_pre.setVisibility(View.GONE);
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
                    textView_cyclone_definition.setVisibility(View.GONE);
                    textView_cyclone_description.setVisibility(View.GONE);
                    imageView_cyclone_pre.setVisibility(View.GONE);
                    imageView_cyclone.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.wildfire_dropdown:
                if (textView_wildfire_description.getVisibility() == View.GONE) {
                    textView_wildfire_description.setVisibility(View.VISIBLE);
                    imageView_wildfire_pre.setVisibility(View.VISIBLE);
                    imageView_wildfire.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    textView_wildfire_description.setVisibility(View.GONE);
                    imageView_wildfire_pre.setVisibility(View.GONE);
                    imageView_wildfire.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.tornado_dropdown:
                if (textView_tornado_description.getVisibility() == View.GONE) {
                    textView_tornado_definition.setVisibility(View.VISIBLE);
                    textView_tornado_description.setVisibility(View.VISIBLE);
                    imageView_tornado_pre.setVisibility(View.VISIBLE);
                    imageView_tornado.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                } else {
                    textView_tornado_definition.setVisibility(View.GONE);
                    textView_tornado_description.setVisibility(View.GONE);
                    imageView_tornado_pre.setVisibility(View.GONE);
                    imageView_tornado.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;
        }
    }
}