package converter.tms.com.converter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

        private String[] syst_ish = {"2-чной","8-чной","10-чной","16-чной"};
        private String[] syst_kon = {"2-чную","8-чную","10-чную","16-чную"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, syst_ish);
     Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     Spinner spinner= (Spinner) findViewById(R.id.spinner);
     spinner.setAdapter(Adapter);

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, syst_kon);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(Adapter1);
    }

}
