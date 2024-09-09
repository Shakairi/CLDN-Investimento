package com.example.financeform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button button = findViewById(R.id.btn_secondscreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText valorEditText = (EditText) findViewById(R.id.aplicacao);
                EditText dataEditText = (EditText) findViewById(R.id.data);
                EditText colEditText = (EditText) findViewById(R.id.col);

                double valor = Integer.parseInt(valorEditText.getText().toString());
                String data = dataEditText.getText().toString();
                double col = Integer.parseInt(colEditText.getText().toString());

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("valor", valor);
                intent.putExtra("data", data);
                intent.putExtra("col", col);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
