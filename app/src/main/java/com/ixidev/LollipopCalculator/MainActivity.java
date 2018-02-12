package com.ixidev.LollipopCalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputtext;
    private TextView resulttext;
    private Button but0;
    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Button but6;
    private Button but7;
    private Button but8;
    private Button but9;
    private ImageButton butadd;
    private ImageButton butmin;
    private ImageButton butmulti;
    private Button butdivi;
    private ImageButton butdelet;
    private Button butc;
    private Button butbra;
    private Button but100;
    private Button butequl;
    private Button butsing;
    private Button butvir;
    private boolean stateError;
    private boolean isNumber;
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisializeButtons();
        setOnclick();
        // Hiding and disable keyboard
        inputtext.setRawInputType(InputType.TYPE_NULL);
        inputtext.addTextChangedListener(textWatcher);

    }


    private void inisializeButtons() {
        this.inputtext = findViewById(R.id.input);
        this.resulttext = findViewById(R.id.result);
        this.but0 = findViewById(R.id.but0);
        this.but1 = findViewById(R.id.but1);
        this.but2 = findViewById(R.id.but2);
        this.but3 = findViewById(R.id.but3);
        this.but4 = findViewById(R.id.but4);
        this.but5 = findViewById(R.id.but5);
        this.but6 = findViewById(R.id.but6);
        this.but7 = findViewById(R.id.but7);
        this.but8 = findViewById(R.id.but8);
        this.but9 = findViewById(R.id.but9);
        this.but100 = findViewById(R.id.but100);
        this.butadd = findViewById(R.id.butplus);
        this.butmin = findViewById(R.id.butmin);
        this.butmulti = findViewById(R.id.butmult);
        this.butdivi = findViewById(R.id.butdivi);
        this.butdelet = findViewById(R.id.butdelet);
        this.butbra = findViewById(R.id.butbra);
        this.butsing = findViewById(R.id.butsin);
        this.butc = findViewById(R.id.butc);
        this.butequl = findViewById(R.id.butequl);
        this.butvir = findViewById(R.id.butv);

    }

    private void setOnclick() {
        this.but0.setOnClickListener(this);
        this.but1.setOnClickListener(this);
        this.but2.setOnClickListener(this);
        this.but3.setOnClickListener(this);
        this.but4.setOnClickListener(this);
        this.but5.setOnClickListener(this);
        this.but6.setOnClickListener(this);
        this.but7.setOnClickListener(this);
        this.but8.setOnClickListener(this);
        this.but9.setOnClickListener(this);
        this.but100.setOnClickListener(this);
        this.butadd.setOnClickListener(this);
        this.butmulti.setOnClickListener(this);
        this.butmin.setOnClickListener(this);
        this.butdelet.setOnClickListener(this);
        this.butdivi.setOnClickListener(this);
        this.butvir.setOnClickListener(this);
        this.butequl.setOnClickListener(this);
        this.butc.setOnClickListener(this);
        this.butdelet.setOnClickListener(this);
        this.butsing.setOnClickListener(this);
        this.butbra.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int Id = v.getId();
        switch (Id) {
            case R.id.but0:
                append("0");
                isNumber = true;
                break;
            case R.id.but1:
                append("1");
                isNumber = true;
                break;
            case R.id.but2:
                append("2");
                isNumber = true;
                break;
            case R.id.but3:
                append("3");
                isNumber = true;
                break;
            case R.id.but4:
                append("4");
                isNumber = true;
                break;
            case R.id.but5:
                append("5");
                isNumber = true;
                break;
            case R.id.but6:
                append("6");
                isNumber = true;
                break;
            case R.id.but7:
                append("7");
                isNumber = true;
                break;
            case R.id.but8:
                append("8");
                isNumber = true;
                break;
            case R.id.but9:
                append("9");
                isNumber = true;
                break;
            case R.id.but100:
                if (!isEmpty() && isNumber)
                    append("%");
                break;
            case R.id.butplus:
                if (!isEmpty())
                    if (endsWithOperatore())
                        replace("+");
                    else
                        append("+");
                isNumber = false;
                lastDot = false;
                break;
            case R.id.butmin:
                if (endsWithOperatore())
                    replace("-");
                else
                    append("-");
                isNumber = false;
                lastDot = false;
                break;
            case R.id.butmult:
                if (!isEmpty())
                    if (endsWithOperatore())
                        replace("x");
                    else
                        append("x");
                isNumber = false;
                lastDot = false;
                break;
            case R.id.butdivi:
                if (!isEmpty())
                    if (endsWithOperatore())
                        replace("\u00F7");
                    else
                        append("\u00F7");
                isNumber = false;
                lastDot = false;
                break;
            case R.id.butv:
                if (isNumber && !stateError && !lastDot) {
                    append(".");
                    isNumber = false;
                    lastDot = true;
                } else if (isEmpty()) {
                    append("0.");
                    isNumber = false;
                    lastDot = true;
                }
                break;
            case R.id.butdelet:
                delete();
                break;
            case R.id.butc:
                clear();
                break;
            case R.id.butbra:
                bracket();
                break;
            case R.id.butequl:
                calcule(true);
                break;
            case R.id.butsin:
                setSing();
                break;
            default:
                break;
        }

    }

    private void setSing() {
        if (isEmpty()){
            append("(-");
        }else if (isNumber && !endsWithOperatore()){
            int index1;
            int index2;
            int index3;
            int index4;
            int lastone = 0;
            index1 = getinput().lastIndexOf("x") + 1;
            index2 = getinput().lastIndexOf("+") + 1;
            index3 = getinput().lastIndexOf("-") + 1;
            index4 = getinput().lastIndexOf("/") + 1;
            if (index1 > index2 && index1 > index3 && index1 > index4)
                lastone = index1;
            else if (index2 > index1 && index2 > index3 && index2 > index4)
                lastone = index2;
            else if (index3 > index2 && index3 > index1 && index3 > index4)
                lastone = index1;
            else if (index4 > index1 && index4 > index3 && index4 > index2)
                lastone = index1;
            char ch=getinput().charAt(lastone);
            appendsing("(-" + String.valueOf(ch), lastone);

        }
    }

    private void appendsing(String str,int index) {

        inputtext.getText().replace(index,index+1,str);
    }


    private void bracket() {
        if ((!stateError && !isEmpty() && !endsWithbra() && isNumber) || isclosed()) {
            append("x(");
        } else if (isEmpty() || endsWithOperatore() || endsWithbra()) {
            append("(");
        } else if (!isEmpty() && !endsWithbra()) {
            append(")");
        }
    }

    private boolean endsWithbra() {
        return getinput().endsWith("(");
    }

    private boolean isclosed() {
        return getinput().endsWith(")");
    }

    private boolean endsWithOperatore() {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("/") || getinput().endsWith("x");
    }

    private void replace(String str) {
        inputtext.getText().replace(getinput().length() - 1, getinput().length(), str);
    }

    private void clear() {
        lastDot = false;
        isNumber = false;
        stateError = false;
        inputtext.getText().clear();
    }

    private void append(String str) {
        this.inputtext.getText().append(str);
    }

    private void delete() {
        if (!isEmpty()) {
            this.inputtext.getText().delete(getinput().length() - 1, getinput().length());

        } else clear();
    }

    private String getinput() {
        return this.inputtext.getText().toString();
    }

    private boolean isEmpty() {
        return getinput().isEmpty();
    }

    private void calcule(boolean isequlclick) {

        String input = getinput();
        try {
            if (!isEmpty() && !endsWithOperatore()) {
                if (input.contains("x")) {
                    input = input.replaceAll("x", "*");
                }

                if(input.contains("\u00F7")){
                    input=input.replaceAll("\u00F7","/");
                }
                Expression expression = new ExpressionBuilder(input).build();
                double result = expression.evaluate();
                if (isequlclick) {
                    inputtext.setText(String.valueOf(result));
                    resulttext.setText("");
                } else
                    resulttext.setText(String.valueOf(result));
            } else resulttext.setText("");
        } catch (Exception e) {
            stateError = true;
            isNumber = false;
        }

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calcule(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
