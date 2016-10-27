package com.example.qwert.merger;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.qwert.merger.MainActivity.SELECTED_CURRENCY;

/**
 * Created by SourceIt on 25.10.2016.
 */
public class CurrencyActivity extends AppCompatActivity {

    private TextView name;
    private TextView full_name;
    private TextView description;
    private ImageView imageView;

    private ListView exchangeRates;
    private CurrencyItemAdapter currencyItemAdapter;
    private Currency currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_activity);

        name = (TextView) findViewById(R.id.info_name);
        full_name = (TextView) findViewById(R.id.info_subname);
        description = (TextView) findViewById(R.id.info_description);
        imageView = (ImageView) findViewById(R.id.imageView);
        exchangeRates = (ListView) findViewById(R.id.list_courrency_course);

        Intent intent = getIntent();
        int currIndex = intent.getIntExtra(SELECTED_CURRENCY, 0);

        setDataToScreen(currIndex);

        exchangeRates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void setDataToScreen(int index) {
        currency = CurrenciesList.getInstance().get(index);

        name.setText(currency.base);
        full_name.setText(currency.sname);
        description.setText(currency.longSname);
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                this.getResources().getIdentifier(currency.base.toLowerCase(), "drawable", "com.example.qwert.merger")));

        currencyItemAdapter = new CurrencyItemAdapter(this, currency.rates);

        exchangeRates.setAdapter(currencyItemAdapter);
    }
}
