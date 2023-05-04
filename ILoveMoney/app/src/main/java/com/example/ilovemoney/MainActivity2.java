package com.example.ilovemoney;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    public Button btn_stock;
    public TextView txt_stock,txt_1000;
    public EditText ed_a,ed_b;
    public String aa,bb;
    public float a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_stock=findViewById(R.id.btn_stock);
        txt_stock=findViewById(R.id.txt_stock);
        txt_1000=findViewById(R.id.txt_1000);
        btn_stock.setOnClickListener(btbListener);
        ed_a=findViewById(R.id.ed_a);
        ed_b=findViewById(R.id.ed_b);
    }
    public Button.OnClickListener btbListener= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Get Text
            aa=ed_a.getText().toString();
            bb=ed_b.getText().toString();
            setDefault();
            //Set Text
            txt_stock.setText(Float.toString(cal()));
            txt_1000.setText(Float.toString(cal()*1000));
        }
        public float cal(){
            //float a,b;
            a=Float.parseFloat(aa);
            b=Float.parseFloat(bb);
            return (float) (b-(b*0.004425)-a-(a*0.001425));
        }
        public void setDefault(){
            if(aa.isEmpty()){
                aa="0";
            }if (bb.isEmpty()){
                aa="0";
            }
        }
    };
}