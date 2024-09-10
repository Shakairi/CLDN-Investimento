package com.example.financeform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

                // Adicionar TextWatcher para o campo de data
                dataEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String data = dataEditText.getText().toString();

                        // Expressão regular para validar o formato dd/mm/yyyy
                        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
                        if (!data.matches(regex)) {
                            dataEditText.setError("Insira a data no formato dd/mm/yyyy");
                        } else {
                            dataEditText.setError(null); // Remover o erro se a data estiver correta
                        }
                    }
                });

                // Validar e obter os valores inseridos
                double valor = Integer.parseInt(valorEditText.getText().toString());
                String data = dataEditText.getText().toString();
                double col = Integer.parseInt(colEditText.getText().toString());

                // Verificar se a data está no formato correto antes de iniciar a nova Activity
                String regex = "^\\d{2}/\\d{2}/\\d{4}$";
                if (data.matches(regex)) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("valor", valor);
                    intent.putExtra("data", data);
                    intent.putExtra("col", col);
                    startActivity(intent);
                } else {
                    dataEditText.setError("Insira a data no formato correto");
                }
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
