package converter.tms.com.converter;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends Activity {

    SharedPreferences save;
    String listchild;
    ArrayList<String> list = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView historylist = findViewById(R.id.historylist);
        save = getSharedPreferences("result",Context.MODE_PRIVATE);
        // Создаём адаптер ArrayAdapter, чтобы привязать массив к ListView
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
        // Привяжем массив через адаптер к ListView
        historylist.setAdapter(adapter);

       if(save.contains("length")) {
           for (int c=0;c < save.getInt("length", 0);c++) {
               list.add(c, save.getString("res"+c, ""));
               adapter.notifyDataSetChanged();
           }
       }
     //   historylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
     //       @Override
     //       public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position,
     //                                   long id) {
//
 //           save.edit().remove("res"+position).apply();
  //          list.remove(position);
 //              adapter.notifyDataSetChanged();
 //               save.edit().putInt("length",save.getInt("length",0)-1).apply();
 //               for (int c=position+1;c < save.getInt("length", 0);c++) {
//                    listchild = save.getString("res"+c, "");
 //                   save.edit().putString("res"+(c-1),listchild).apply();
  //              }


  //              return false;
  //          }
  //      });


    }

}
