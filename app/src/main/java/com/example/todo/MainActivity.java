package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button btnAdd;
    private EditText editText;
    Calendar c;
    private String todayDate;
    private String currentTime;
    List<Todo> todo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, R.layout.list_view_layout, items);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        setUpListViewListener();
        editText = findViewById(R.id.addTxt);
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.addTxt);
        String itemText = input.getText().toString();

        //get current date and time
        c = Calendar.getInstance();
        todayDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        if (!(itemText.equals(""))){
            items.add(itemText);
            itemsAdapter.notifyDataSetChanged();
            //new code
            Todo todo = new Todo(itemText, todayDate, currentTime);
            TodoDatabase db = new TodoDatabase(this);
            db.addNote(todo);

            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(),"Enter text",Toast.LENGTH_LONG).show();
        }
    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Item has been removed.", Toast.LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


}