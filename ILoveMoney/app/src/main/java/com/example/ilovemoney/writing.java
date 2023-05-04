package com.example.ilovemoney;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//    以下是我參考的網站和網址
//    https://mnya.tw/cc/word/1495.html
//    https://www.youtube.com/watch?v=v2Az8yIU1lE
//    https://www.crazycodersclub.com/android/how-to-use-google-sheet-as-database-for-android-app-1-insert-operation/

public class writing extends AppCompatActivity {
    TextView txt_item,txt_cal,txt_name,txt_money;
    EditText edt_item,edt_money;
    CalendarView cal;
    Button btn_ok,btn_chkmoney,btn_dad,btn_mom,btn_pin,btn_mini,btn_form;
    Spinner spn_item;
    RadioGroup RadioGroup_else;
    RadioButton rb_else,rb_choose;
    String s1,s2,s3,s4,s5="阿花",a;
    String[]items =new String[]  {"NO","早餐","午餐","晚餐","飲料","🀄打麻將","停車費","健身房","加油錢","手機費","水、電費"
            ,"瓦斯費","大賣場_愛買","大賣場_全聯","大賣場_美聯社","大賣場_家樂福","看醫生","網路費","衣服","鞋子","文具","悠遊卡加值"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);
        findViewById();
        spn_item.setOnItemSelectedListener(spnListener);
        ArrayAdapter<String> adapt_who=new ArrayAdapter <String>
                (this,android.R.layout.simple_spinner_item,items);
        adapt_who.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_item.setAdapter(adapt_who);
        spn_item.setOnItemSelectedListener(spnListener);
        cal.setOnDateChangeListener( dateChangeListener);
        btn_chkmoney.setOnClickListener(btnClickListener_money);
        btn_dad.setOnClickListener(btnClickListener_who);
        btn_mom.setOnClickListener(btnClickListener_who);
        btn_pin.setOnClickListener(btnClickListener_who);
        btn_mini.setOnClickListener(btnClickListener_who);
        btn_ok.setOnClickListener(btnClickListener);
        btn_form.setOnClickListener(btnClickListener_form);
        RadioGroup_else.setOnCheckedChangeListener(radListener);


    }
    private RadioGroup.OnCheckedChangeListener radListener =new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
            if(checkid==R.id.rb_choose){
                txt_item.setText("未填選");
                s1=txt_item.toString();

            }
            else if(checkid==R.id.rb_else) {
                txt_item.setText(edt_item.getText());
                //s1=txt_item.toString();
                s1=txt_item.getText().toString();
            }

        }
    };
    //s1
    private Spinner.OnItemSelectedListener spnListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parents, View view, int i, long l) {
            s1=parents.getSelectedItem().toString();

            txt_item.setText(s1);

            //s1=txt_item.toString();
            Log.d("sel",s1);

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) { }

    };
    //s2
    private CalendarView.OnDateChangeListener dateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
            //String a=i+"/"+(i1+1)+"/"+i2;
            a=i+"/"+(i1+1)+"/"+i2;
            txt_cal.setText(a);
            s2=a;
        }
    };
    //s3
    private Button.OnClickListener btnClickListener_money= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            txt_money.setText(edt_money.getText());
            s3=edt_money.getText().toString();
        }

    };
    //s4
    private Button.OnClickListener btnClickListener_who= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_dad:
                    s4=btn_dad.getText().toString();
                    break;
                case R.id.btn_mom:
                    s4=btn_mom.getText().toString();
                    break;
                case R.id.btn_pin:
                    s4=btn_pin.getText().toString();
                    break;
                case R.id.btn_mini:
                    s4=btn_mini.getText().toString();
                    break;
            }
            txt_name.setText(s4);
            //s4=txt_name.toString();
        }

    };


    public Button.OnClickListener btnClickListener_form= new Button.OnClickListener() {//go to google sheet website.
        @Override
        public void onClick(View v) {
            Uri uri=Uri.parse("");//your google sheet url 
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    } ;



    private Button.OnClickListener btnClickListener= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==btn_ok){
                addItemToSheet();
                //Define what to do when button is clicked
            }
        }
    };

    void findViewById() {
        btn_ok=findViewById(R.id.btn_ok);
        btn_dad=findViewById(R.id.btn_dad);
        btn_mini=findViewById(R.id.btn_mini);
        btn_mom=findViewById(R.id.btn_mom);
        btn_pin=findViewById(R.id.btn_pin);
        btn_chkmoney=findViewById(R.id.btn_chkmoney);
        btn_form=findViewById(R.id.btn_form);

        spn_item=findViewById(R.id.spn_item);
        edt_item=findViewById(R.id.edt_item);
        edt_money=findViewById(R.id.edt_money);
        txt_cal=findViewById(R.id.txt_cal);
        txt_item=findViewById(R.id.txt_item);
        txt_name=findViewById(R.id.txt_name);
        txt_money=findViewById(R.id.txt_money);
        cal=findViewById(R.id.cal);
        rb_else=findViewById(R.id.rb_else);
        rb_choose=findViewById(R.id.rb_choose);

        RadioGroup_else=findViewById(R.id.RadioGroup_else);
    }
    void addItemToSheet(){
        final ProgressDialog loading=ProgressDialog.show(this,"加入中!!!","請稍待");
        final String choose=s1.trim();//s1
        final String date=s2.trim();
        final String money=s3.trim();
        final String family=s4.trim();
        //System.out.print(s1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/exec",//your google app script url
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                parmas.put("choose",choose);
                parmas.put("date",date);
                parmas.put("family",family);
                parmas.put("money",money);
                Log.d("s1",choose);
                //Log.d("s2",date);
                return parmas;
            }
        };
        int socketTimeOut = 70000;// u can change this .. here it is 5 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}