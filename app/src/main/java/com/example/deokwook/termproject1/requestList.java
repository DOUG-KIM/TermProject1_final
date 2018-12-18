package com.example.deokwook.termproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class requestList extends AppCompatActivity {
    String[]person=null;
    String[]product=null;
    int[] howmany=null;
    String []date=null;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button=(Button)findViewById(R.id.button_request);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
    }
}
