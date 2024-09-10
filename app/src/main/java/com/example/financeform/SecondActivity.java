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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Intent intent = getIntent();
        double valorInicial = intent.getDoubleExtra("valor", 0.0);
        String dataResgate = intent.getStringExtra("data");
        double taxaCol = intent.getDoubleExtra("col", 0.0); // taxa do investimento ou CDI

        // Views
        TextView valorInicialTextView = findViewById(R.id.inicial);
        TextView dataTextView = findViewById(R.id.data);
        TextView colTextView = findViewById(R.id.col);
        TextView valorBrutoTextView = findViewById(R.id.valor_bruto);
        TextView rendimentoTextView = findViewById(R.id.rendimento);
        TextView irTextView = findViewById(R.id.ir);
        TextView valorLiquidoTextView = findViewById(R.id.valor_liquido);
        TextView diasCorridosTextView = findViewById(R.id.dias_corridos);
        TextView rendimentoMensalTextView = findViewById(R.id.rendimento_mensal);
        TextView rentabilidadeAnualTextView = findViewById(R.id.rentabilidade_anual);
        TextView rentabilidadePeriodoTextView = findViewById(R.id.rentabilidade_periodo);

        // Formatador para valor monetário
        DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");

        // Exibe o valor inicial formatado
        String valorInicialFormatado = decimalFormat.format(valorInicial);
        valorInicialTextView.setText(valorInicialFormatado);
        dataTextView.setText(dataResgate);
        colTextView.setText("" + taxaCol);

        // Calcula os dias corridos entre hoje e a data de resgate
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long diasCorridos = 0;
        try {
            Date dataAtual = new Date();
            Date dataFim = sdf.parse(dataResgate);
            long diff = dataFim.getTime() - dataAtual.getTime();
            diasCorridos = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        diasCorridosTextView.setText("" + diasCorridos);

        // Fórmulas de cálculo
        double rendimentoMensal = taxaCol / 12;
        double valorBruto = valorInicial * Math.pow((1 + rendimentoMensal / 100), diasCorridos / 30.0);
        double rendimento = valorBruto - valorInicial;
        double ir = rendimento * 0.15; // IR de 15% sobre o rendimento
        double valorLiquido = valorBruto - ir;
        double rentabilidadeAnual = Math.pow(1 + rendimentoMensal / 100, 12) - 1;
        double rentabilidadePeriodo = (valorBruto / valorInicial) - 1;

        // Exibe os valores calculados
        valorBrutoTextView.setText(decimalFormat.format(valorBruto));
        rendimentoTextView.setText(decimalFormat.format(rendimento));
        irTextView.setText(decimalFormat.format(ir));
        valorLiquidoTextView.setText(decimalFormat.format(valorLiquido));
        rendimentoMensalTextView.setText(String.format("%.2f%%", rendimentoMensal));
        rentabilidadeAnualTextView.setText(String.format("%.2f%%", rentabilidadeAnual * 100));
        rentabilidadePeriodoTextView.setText(String.format("%.2f%%", rentabilidadePeriodo * 100));

        // Botão para voltar para a primeira tela
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