package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public byte first =0;

    Button sin1,cos1,tg1,ctg1,calc,add;
    EditText editText1,editText2;
    TextView rez1,textView3;
    public int count=0;
    public ArrayList<Byte> a =new ArrayList<Byte>();
    public ArrayList<Integer> k =new ArrayList<>();
    public ArrayList<Integer> k1 =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.add);
        sin1 =findViewById(R.id.sin1);
        cos1 =findViewById(R.id.cos1);
        tg1  =findViewById(R.id.tg1);
        ctg1 =findViewById(R.id.ctg1);
        calc =findViewById(R.id.calc);
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        sin1.setOnClickListener(this);
        cos1.setOnClickListener(this);
        tg1.setOnClickListener(this);
        ctg1.setOnClickListener(this);
        calc.setOnClickListener(this);
        rez1=(findViewById(R.id.rez1));
        textView3=(findViewById(R.id.textView3));
        add.setOnClickListener(this);

    }
    public float f(float i){
            float z =0;
        for (int j = 0; j < count; j++) {
            switch(a.get(j)){
                case 0:
                    z+= k.get(j)*(float) Math.sin(i*k1.get(j));
                    break;
                case 1:
                    z+= k.get(j)*(float) Math.cos(i*k1.get(j));
                    break;
                case 2:
                    z+= k.get(j)*(float) Math.tan(i*k1.get(j));
                    break;
                case 3:
                    z+= k.get(j)*(float) (1/Math.tan(i*k1.get(j)));
                    break;
            }
        }

            return(Math.abs(z));
    }
    public Float count() {
        float x = f(0);
        float y = 0;
        for (float i = -2; i < 2; i+=0.1) {
            float h=f(i);
            if (x > h) {
                x = f(i);
                y = i;
            }
        }
            float i = (float) 0.1;
            for (int j = 0; j < 100; j += 1) {
                i /= 2;
                if (f(y-i)> f(y+i)) {
                    x = f(y);
                    y += i;
                }else{
                        x = f(y-i);
                        y -= i;
                }
            }
            return y;
        }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sin1:
                first=0;
                break;
            case R.id.cos1:
                first=1;
                break;
            case R.id.tg1:
                first=2;
                break;
            case R.id.ctg1:
                first=3;
                break;
            case R.id.add:
                a.add(first);
                k.add(Integer.parseInt(editText1.getText().toString()));
                k1.add(Integer.parseInt(editText2.getText().toString()));
                int z=k.get(count);
                int f=k1.get(count);
                if(z<0){
                    rez1.setText(rez1.getText()+Integer.toString(z));



                }else {
                    if(count==0){
                        rez1.setText(rez1.getText()+Integer.toString(z));
                    }else {
                        rez1.setText(rez1.getText()+"+"+Integer.toString(z));
                    }
                }
                switch(first){
                case 0:
                    rez1.setText(rez1.getText()+"sin(");
                    break;
                case 1:
                    rez1.setText(rez1.getText()+"cos(");
                    break;
                case 2:
                    rez1.setText(rez1.getText()+"tg(");
                    break;
                case 3:
                    rez1.setText(rez1.getText()+"ctg(");
                    break;
            }
                rez1.setText(rez1.getText()+Integer.toString(f)+"x)");

                count+=1;
                break;
            case R.id.calc:
                float rezult=count();
                if(f(rezult)<0.1){//роверка на правильность тк решения может не быть
                    textView3.setText(Float.toString(rezult)+"π");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }

}