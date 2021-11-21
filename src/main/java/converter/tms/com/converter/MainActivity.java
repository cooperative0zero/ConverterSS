package converter.tms.com.converter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.TextView;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String[] ish = {"2-чной","8-чной","10-чной","16-чной"};
        private String[] kon = {"2-чную","8-чную","10-чную","16-чную"};


    ClipboardManager clipboardManager ;
    ClipData clipData;
    TextView textCopy;
    String number;
    String c;
    String listchild;
    SharedPreferences save;
    int a;
    int b;    
    Boolean check;
    EditText editText;
    List<String> Convertations = new ArrayList<String>();
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          editText = findViewById(R.id.editTextTextNumber);
          textCopy = findViewById(R.id.result);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        save = getSharedPreferences("result",Context.MODE_PRIVATE);

        if(save.contains("length")&&Convertations.isEmpty()) {
            for (int c=0;c < save.getInt("length", 0);c++) {
                Convertations.add(c, save.getString("res"+c, ""));
            }
        }




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
                                              number = editText.getText().toString();
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );
            
    }

        public void CopyClick(View view) {
            clipData = ClipData.newPlainText("text", textCopy.getText());
            clipboardManager.setPrimaryClip(clipData);

        }

    public void Convertize(View view) {
        number = editText.getText().toString();
        if (((a==2)&&(number.matches("[0-1-]+")))||((a==8)&&(number.matches("[0-7-]+")))||((a==10)&&(number.matches("[0-9-]+")))||((a==16)&&(number.matches("[0-9a-fA-F-]+"))))
            {
                textCopy.setTextColor(getResources().getColor(R.color.green));
                c = String.valueOf( new BigInteger(number, a).toString(b));

                Toast.makeText(getApplicationContext(), String.valueOf(Convertations.size()),Toast.LENGTH_SHORT).show();

                    if (Convertations.size()==0)
                    {
                        Convertations.add(0,c);
                        save.edit().putString("number0",number).apply();
                        listchild = "Перевод числа "+save.getString("number"+0, "")+" ("+a+"-чная система счисления) в число "+Convertations.get(0)+"("+b+"-чная система счисления);";
                        save.edit().putString("res"+0,listchild).apply();
                    }
                    else
                    {
                        Convertations.add(Convertations.size(),c);
                        save.edit().putString("number"+(Convertations.size()-1),number).apply();
                        listchild = "Перевод числа "+number+" ("+a+"-чная система счисления) в число "+Convertations.get(Convertations.size()-1)+"("+b+"-чная система счисления);";
                        save.edit().putString("res"+(Convertations.size()-1),listchild).apply();


                    }
                    save.edit().putInt("length",Convertations.size()).apply();

                //for(int i = Convertations.size()-1; i < Convertations.size(); i++) {
                    //listchild = "Перевод числа "+save.getString("number"+i, "")+" ("+a+"-чная система счисления) в число "+Convertations.get(i)+"("+b+"-чная система счисления);";
                   // save.edit().putString("res"+i,listchild).apply();
                //}



                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setTextColor(getResources().getColor(R.color.red));
                textCopy.setText(getResources().getString(R.string.errror));
            }
        }

       /* if (a==8){
            if (number.matches("[0-7-]+"))
            {
                textCopy.setTextColor(getResources().getColor(R.color.green));
                String c = String.valueOf( new BigInteger(number, a).toString(b));
                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setTextColor(getResources().getColor(R.color.red));
                textCopy.setText(getResources().getString(R.string.errror));
            }
        }
        if (a==10){
            if (number.matches("[0-9-]+"))
            {
                textCopy.setTextColor(getResources().getColor(R.color.green));
                String c = String.valueOf( new BigInteger(number, a).toString(b));
                textCopy.setText(String.valueOf(c.toUpperCase()));
            }
            else{
                textCopy.setTextColor(getResources().getColor(R.color.red));
                textCopy.setText(getResources().getString(R.string.errror));
            }
        }
        if (a==16){


                check=false;
                if (number.matches("[0-9a-fA-F-]+")) {
                    textCopy.setTextColor(getResources().getColor(R.color.green));
                    String c = String.valueOf( new BigInteger(number, a).toString(b));
                    textCopy.setText(String.valueOf(c.toUpperCase()));
                    check = true;
                }

                if (Boolean.FALSE.equals(check)) {
                    textCopy.setTextColor(getResources().getColor(R.color.red));
                    textCopy.setText(getResources().getString(R.string.errror));
                }
            }*/

    
    public void OpenHistory(View view) {
        Intent intent = new Intent(this,HistoryActivity.class);
        startActivity(intent);
    }


}
