package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

public class SettlementActivity extends AppCompatActivity {

    private Intent settlementIntent ;
    private double settle_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);

        settle_price = getIntent().getBundleExtra("totalCount").getDouble("totalCount");
        Toast.makeText(this, "传过来的数据"+settle_price, Toast.LENGTH_SHORT).show();
    }
}
