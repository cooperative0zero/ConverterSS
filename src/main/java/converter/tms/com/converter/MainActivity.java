package converter.tms.com.converter;

import android.app.Activity;
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

import java.math.BigInteger;

public class MainActivity extends Activity {

        private String[] syst_ish = {"2-чной","8-чной","10-чной","16-чной"};
        private String[] syst_kon = {"2-чную","8-чную","10-чную","16-чную"};

        
    ClipboardManager clipboardManager ;
    ClipData clipData;
    TextView textCopy;
    String Number;
    BigInteger c;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            
            clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ImageButton c_btn= findViewById(R.id.c_btn);

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, syst_ish);
     Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     Spinner spinner= (Spinner) findViewById(R.id.spinner);
     spinner.setAdapter(Adapter);

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, syst_kon);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(Adapter1);
            
            final EditText editText = findViewById(R.id.editTextTextNumber);
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
        c = new BigInteger(new BigInteger(Number, 10).toString(2));
        textCopy = findViewById(R.id.result);
        textCopy.setText(String.valueOf(c));
    }


}
