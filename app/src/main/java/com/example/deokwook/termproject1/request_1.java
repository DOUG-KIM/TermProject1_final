package com.example.deokwook.termproject1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class request_1 extends AppCompatActivity {
    EditText et1, et2, et3, et4,et5;
    private GpsInfo gpsInfo;
    private TextView locationText;
    private Button btnGetLocation;
    private Button btnRequest;

    private double latitude;
    private double longitude;
    private String r_name;
    private String money;
    private String r_text;
    private String phone;

    private DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_1);

        gpsInfo = new GpsInfo(request_1.this);
        locationText = (TextView) findViewById(R.id.textView_location);
        btnGetLocation = (Button)findViewById(R.id.button_getLocation);
        btnRequest = (Button) findViewById(R.id.button_request);

        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);




        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gpsInfo.isGetLocation()) {
                    latitude = gpsInfo.getLatitude();
                    longitude = gpsInfo.getLongitude();
                    locationText.setText(latitude + ", " + longitude);
                } else {
                    gpsInfo.showSettingsAlert();
                    gpsInfo.isGetLocation = true;
                }
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_name = et1.getText().toString();
                money = et2.getText().toString();
                r_text = et3.getText().toString();
                phone = et4.getText().toString();
                registerRequest(latitude, longitude, r_name, money, r_text, phone);
                Toast.makeText(getApplicationContext(), "요청이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerRequest(double latitude, double longitude, String r_name, String money, String r_text, String phone) {
        dbHelper.insertDB(String.valueOf(latitude), String.valueOf(longitude), r_name, money, r_text, phone);
    }

    public void delete(View v){

        dbHelper.deleteDB();
    }

}
