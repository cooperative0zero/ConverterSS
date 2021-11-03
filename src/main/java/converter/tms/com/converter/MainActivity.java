package converter.tms.com.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.TextView;
import java.util.Locale;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.os.Build;
import java.math.BigInteger;

public class MainActivity extends Activity {

        private String[] ish = {"2-чной","8-чной","10-чной","16-чной"};
        private String[] kon = {"2-чную","8-чную","10-чную","16-чную"};

        
    ClipboardManager clipboardManager ;
    ClipData clipData;
    TextView textCopy;
    String Number;
    ImageButton c_btn;
    int a;
    int b;    
    Boolean check;
    EditText editText;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            
            editText = findViewById(R.id.editTextTextNumber);
            textCopy = findViewById(R.id.result);
            
            clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        ArrayAdapter<String> adapt = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, ish);
     adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     Spinner spinner= (Spinner) findViewById(R.id.spinner);
     spinner.setAdapter(adapt);
            
                    AdapterView.OnItemSelectedListener itemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {

                if (position1 == 0) {
                    a= 2;
                }
                else if (position1 == 1) {
                    a= 8;
                }
                else if (position1 == 2) {
                    a= 10;
                }
                else if (position1 == 3) {
                    a= 16;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    throw new UnsupportedOperationException();
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener1);

        ArrayAdapter<String> adapt1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, kon);
        adapt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapt1);
            
            AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {

                if (position2 == 0) {
                    b= 2;
                }
                else if (position2 == 1) {
                    b= 8;
                }
                else if (position2 == 2) {
                    b= 10;
                }
                else if (position2 == 3) {
                    b= 16;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    throw new UnsupportedOperationException();
            }
        };


        spinner1.setOnItemSelectedListener(itemSelectedListener2);
            
             
        editText.setOnKeyListener(new View.OnKeyListener()
                                  {
                                      public boolean onKey(View v, int keyCode, KeyEvent event)
                                      {
                                          if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER))
                                          {
                                              // сохраняем текст, введённый до нажатия Enter в переменную
                                              Number = editText.getText().toString();
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );
            
    }
        public void CopyClick(View view) {
        clipData = ClipData.newPlainText("text",textCopy.getText());
        clipboardManager.setPrimaryClip(clipData);

    }


    public void Convertize(View view) {
        Number = editText.getText().toString();
        if (a==2){
            if (Number.matches("[0-1-]+"))
            {
                String c = new String(new BigInteger(Number, a).toString(b));
                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setText("Error!");
            }
        }
        
        if (a==8){
            if (Number.matches("[0-7-]+"))
            {
                String c = new String(new BigInteger(Number, a).toString(b));
                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setText("Error!");
            }
        }
        if (a==10){
            if (Number.matches("[0-9-]+"))
            {
                String c = new String(new BigInteger(Number, a).toString(b));
                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setText("Error!");
            }
        }
        if (a==16){


                check=false;
                if (Number.matches("[0-9a-fA-F-]+")) {
                    String c = new String(new BigInteger(Number, a).toString(b));
                    textCopy.setText(String.valueOf(c.toUpperCase()));
                    check = true;
                }

                if (check == false) {
                    textCopy.setText("Error!");
                }
            }


    }

    public void OpenHistory(View view) {
        Intent intent = new Intent(this,HistoryActivity.class);
        startActivity(intent);
    }
}
