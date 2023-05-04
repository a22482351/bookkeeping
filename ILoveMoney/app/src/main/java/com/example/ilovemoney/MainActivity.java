package com.example.ilovemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btn_check,btn_write,btn_month,btn_stock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("來記帳🥙🥗🌮🌯");
        setContentView(R.layout.activity_main);
        btn_check=findViewById(R.id.btn_check);
        btn_write=findViewById(R.id.btn_write);
        btn_month=findViewById(R.id.btn_month);
        btn_check=findViewById(R.id.btn_check);
        btn_stock=findViewById(R.id.btn_stock);
        btn_write.setOnClickListener(btnClickListener_btn_write);
        btn_check.setOnClickListener(btnClickListener_form);
        btn_month.setOnClickListener(btnClickListener_month);
        btn_stock.setOnClickListener(btnClickListener_stock);
    }
    Button.OnClickListener btnClickListener_stock=new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent_stock=new Intent(MainActivity.this,MainActivity2.class );
            Bundle bundle=new Bundle();
            startActivity(intent_stock);
        }
    };
     Button.OnClickListener btnClickListener_btn_write=new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent_write=new Intent(MainActivity.this,writing.class );
            Bundle bundle=new Bundle();
            startActivity(intent_write);
        }
    };
    public Button.OnClickListener btnClickListener_form= new Button.OnClickListener() {//go to google sheet website.
        @Override
        public void onClick(View v) {
            Uri uri=Uri.parse("https://XXXXXXXX");
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    } ;
    public Button.OnClickListener btnClickListener_month= new Button.OnClickListener() {//go to google sheet website.
        @Override
        public void onClick(View v) {
            Uri uri=Uri.parse("https://XXXXXXXX");
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    } ;
}