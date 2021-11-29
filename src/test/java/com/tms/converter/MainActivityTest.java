package converter.tms.com.converter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.mockito.Mockito;

public class MainActivityTest {
    MainActivity c = new MainActivity();
    Context context = Mockito.mock(Context.class);
    Bundle bundle = Mockito.mock(Bundle.class);
    View view = Mockito.mock(View.class);
    EditText editText = Mockito.mock(EditText.class);
    TextView textCopy = Mockito.mock(TextView.class);
    FreeMethods methods = Mockito.mock(FreeMethods.class);
    SharedPreferences save = Mockito.mock(SharedPreferences.class);

    @Test
    public void onCreateTest() {
        MainActivity main = new MainActivity();
//    main.OpenHistory(view);
//    main.CopyClick(view);
        main.methods = methods;
        main.textCopy = textCopy;
        main.editText = editText;
        main.save = save;
        // Mockito.when(textCopy.setTextColor(63283))
        main.a = 2;
        Mockito.when(methods.getCalculatingNumber())
                .thenReturn("010101");
        main.number = "1001";
        main.Convertize(view);
        main.a = 8;
        main.Convertize(view);
        main.a = 10;
        main.Convertize(view);
        main.a = 16;
        main.Convertize(view);
    }

}

