package com.example.financeform;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Intent intent = getIntent();

        double valor = intent.getDoubleExtra("valor", 0.0);
        String data = intent.getStringExtra("data");
        double col = intent.getDoubleExtra("col", 0.0);

        TextView valorTextView = findViewById(R.id.resultado);
        TextView dataTextView = findViewById(R.id.data);
        TextView colTextView = findViewById(R.id.col);

        DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
        String formattedValue = decimalFormat.format(valor);

        valorTextView.setText(formattedValue);
        dataTextView.setText(data);
        colTextView.setText("" + col);

        Button button = findViewById(R.id.btn_firstscreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}