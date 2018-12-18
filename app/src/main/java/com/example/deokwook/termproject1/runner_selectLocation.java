package com.example.deokwook.termproject1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class runner_selectLocation extends AppCompatActivity {
    private double latitude;
    private double longitude;
    private String r_name;
    private String money;
    private String r_text;
    private String phone;

    private DBHelper dbHelper;
    SQLiteDatabase db;
    //static final int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_select_location);
        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        Button button4 = (Button)findViewById(R.id.button4);
        Button search = (Button)findViewById(R.id.search);
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),runner_region1.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                searchLocation(v);
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("r_name", r_name);
                intent.putExtra("money", money);
                intent.putExtra("r_text", r_text);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }
    public void searchLocation(View v) {

        //Cursor cursor; // 레코드의 포인터
        //cursor = db.rawQuery("select name, tel, salary from contacts where name='"+name+"';", null); //질의문
        Cursor cursor=dbHelper.searchDB();

        while (cursor.moveToNext()) {

            latitude = Double.parseDouble(cursor.getString(4));//name=0 , latitude=1 ,longitude=2
            longitude = Double.parseDouble(cursor.getString(5));
            r_name = cursor.getString(0);
            money = cursor.getString(1);
            r_text = cursor.getString(2);
            phone = cursor.getString(3);

        }
    }
}
