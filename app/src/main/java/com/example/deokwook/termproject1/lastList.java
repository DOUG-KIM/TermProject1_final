package com.example.deokwook.termproject1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class lastList extends AppCompatActivity {

    private String r_name;
    private String money;
    private String r_text;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_list);
        Button call=(Button)findViewById(R.id.call);
        TextView tv = (TextView)findViewById(R.id.textView11);
        TextView tv2 = (TextView)findViewById(R.id.textView12);
        TextView tv3 = (TextView)findViewById(R.id.textView13);
        TextView tv4 = (TextView)findViewById(R.id.textView14);
        TextView tv5 = (TextView)findViewById(R.id.textView15);
        TextView tv6 = (TextView)findViewById(R.id.textView16);
        TextView tv7 = (TextView)findViewById(R.id.textView17);
        TextView tv8 = (TextView)findViewById(R.id.textView18);
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
        tv.setTextColor(Color.BLACK);
        tv3.setTypeface(null, Typeface.BOLD_ITALIC);
        tv3.setTextColor(Color.BLACK);
        tv5.setTypeface(null, Typeface.BOLD_ITALIC);
        tv5.setTextColor(Color.BLACK);
        tv7.setTypeface(null, Typeface.BOLD_ITALIC);
        tv7.setTextColor(Color.BLACK);

        Intent intent =getIntent();
        r_name = intent.getStringExtra("r_name");
        money = intent.getStringExtra("money");
        r_text = intent.getStringExtra("r_text");
        phone = intent.getStringExtra("phone");

        tv2.setText(r_name);
        tv4.setText(money);
        tv6.setText(r_text);
        tv8.setText(phone);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
    startActivity(intent);
            }
        });
    }

}
