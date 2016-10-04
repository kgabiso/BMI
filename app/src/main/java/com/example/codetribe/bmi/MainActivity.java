package com.example.codetribe.bmi;

import android.annotation.TargetApi;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    DecimalFormat dm = new DecimalFormat("#.##");
   public static SeekBar seekBar;
    TextView resultView ,kgView,MeterView,GenderView,ProgressView;
    EditText editText;
    RadioGroup radioWeight;
    RadioGroup radioGender;
    String Gender;
    int  progressWeight;
     Double weight ,height = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressView = (TextView)findViewById(R.id.txtProcess);
        kgView = (TextView)findViewById(R.id.txtkillogram) ;
        MeterView = (TextView)findViewById(R.id.txtmeters) ;
        GenderView = (TextView)findViewById(R.id.txtGender) ;
        seekBar = (SeekBar)findViewById(R.id.seekWeight);
        resultView = (TextView)findViewById(R.id.result) ;
        editText =(EditText)findViewById(R.id.txtHeight) ;
        height = Double.parseDouble(editText.getText().toString());
        radioGender =(RadioGroup)findViewById(R.id.rGender);
        radioWeight =(RadioGroup)findViewById(R.id.rWeight);
        radioGender.check(R.id.male);
        Gender = "Gender: Male";
        GenderView.setText( Gender );

        radioWeight.clearCheck();
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                   if(i == R.id.femal){
                      Gender = "Gender: Female";
                       GenderView.setText( Gender );
                   }
                else if(i == R.id.male){
                       Gender = "Gender: Male";
                       GenderView.setText( Gender );
                   }
                else {
                       Gender = "Gender : Maybe, Probably, Hopefully";
                       GenderView.setText( Gender );
                   }
            }
        });

radioWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if (i == R.id.txt59) {
            height = Double.parseDouble(editText.getText().toString());
            weight = 59.;
            kgView.setText("Kilogram: "+ weight.toString()+"kg");
            MeterView.setText("Meter: "+String.valueOf(height)+"m");
            String total = dm.format(CalBMI(weight,height));
            resultView.setText("BMI: "+total);
            if(weight==59.) {
                seekBar.setProgress((int) Math.round(weight));
            }
            else {

            }
        }
        else if (i == R.id.txt89) {
            height = Double.parseDouble(editText.getText().toString());
            weight = 89.;
            kgView.setText("Kilogram: "+ weight.toString()+"kg");
            MeterView.setText("Meter: "+String.valueOf(height)+"m");
            String total = dm.format(CalBMI(weight,height));
            resultView.setText("BMI: "+total);
            seekBar.setProgress((int)Math.round(weight));
        }
        else {
            height = Double.parseDouble (editText.getText().toString());
            weight = 100.;
            kgView.setText("Kilogram: "+ weight.toString()+"kg");
            MeterView.setText("Meter: "+String.valueOf(height)+"m");
            String total = dm.format(CalBMI(weight,height));
            resultView.setText("BMI: "+total);
            seekBar.setProgress((int)Math.round(weight));


        }
    }
});
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                height = Double.parseDouble (editText.getText().toString());
                progressWeight = seekBar.getProgress();
                ProgressView.setText(""+progressWeight +"kg");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar track) {
              weight = Double.parseDouble(String.valueOf(track.getProgress()));
             height = Double.parseDouble (editText.getText().toString());
            resultView.setText("BMI: "+dm.format(CalBMI(weight,height)));
            kgView.setText("Kilogram: "+ weight+"kg");
               MeterView.setText("Meter: "+Integer.parseInt (editText.getText().toString())+"m");
              GenderView.setText("Gender:"+Gender );

               if(weight == 0 || weight <= 59){
                    radioWeight.check(R.id.txt59);
               }
                else if(weight >= 60 || weight <= 89)
                {
                    radioWeight.check(R.id.txt89);
                }
                else if(weight <= 90) {
                   radioWeight.check(R.id.txt100);
               }
            }
        });

    }
    public double CalBMI(double w,double h){
        double BMI =  (w /(h * h));
        return BMI;
    }

}
