package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.jsc.Main;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    TextView inputTxt, outputTxt;
    MaterialButton btn_clr,btn_opn,btn_cls,btn_bs,btn_sign,btn_eq;
    MaterialButton btn_mod,btn_mu,btn_div,btn_p,btn_mi,btn_sqr,btn_d;
    MaterialButton btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        inputTxt=findViewById(R.id.inputTxt);
        outputTxt=findViewById(R.id.outputTxt);

        assignid(btn_clr,R.id.btn_clr);
        assignid(btn_mu,R.id.btn_mu);
        assignid(btn_mod,R.id.btn_mod);
        assignid(btn_div,R.id.btn_div);
        assignid(btn_7,R.id.btn_7);
        assignid(btn_8,R.id.btn_8);
        assignid(btn_9,R.id.btn_9);
        assignid(btn_mi,R.id.btn_mi);
        assignid(btn_4,R.id.btn_4);
        assignid(btn_5,R.id.btn_5);
        assignid(btn_6,R.id.btn_6);
        assignid(btn_p,R.id.btn_p);
        assignid(btn_1,R.id.btn_1);
        assignid(btn_2,R.id.btn_2);
        assignid(btn_3,R.id.btn_3);
        assignid(btn_d,R.id.btn_d);
        assignid(btn_0,R.id.btn_0);
        assignid(btn_bs,R.id.btn_bs);
        assignid(btn_eq,R.id.btn_eq);

        
    }
    
    void assignid(MaterialButton button, int id)
    {
        button=findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        MaterialButton btn=(MaterialButton) view;
        String btnText=btn.getText().toString();
        String calculate = inputTxt.getText().toString();

        if (btnText.equals("C")){
            inputTxt.setText("");
            outputTxt.setText("0");
            return;
        }
        if (btnText.equals("=")){
            inputTxt.setText(outputTxt.getText());
            return;
        }
        if (btnText.equals("⌫")){
            calculate=calculate.substring(0,calculate.length()-1);
        }
        else {
            calculate=calculate+btnText;
        }


        inputTxt.setText(calculate);
        String result= getResult(calculate);

        if (!result.equals("Err")){
            outputTxt.setText(result);
        }
    }

    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (result.endsWith(".0"))
            {
                result=result.replace(".0","");
            }
            return result;
        }
        catch (Exception e){
            return "Err";
        }
    }
}