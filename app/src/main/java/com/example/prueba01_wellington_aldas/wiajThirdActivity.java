package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class wiajThirdActivity extends AppCompatActivity {

    private EditText wiajEtPrimerNumero;
    private EditText wiajEtSegundoNumero;
    private Button wiajBtnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiaj_activity_third);

        wiajInitViews();

        wiajBtnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiajReturnResults();
            }
        });
    }

    private void wiajInitViews() {
        wiajEtPrimerNumero = findViewById(R.id.wiaj_et_primer_numero_third);
        wiajEtSegundoNumero = findViewById(R.id.wiaj_et_segundo_numero_third);
        wiajBtnCerrar = findViewById(R.id.wiaj_btn_cerrar_third);
    }

    private void wiajReturnResults() {
        String num1Str = wiajEtPrimerNumero.getText().toString();
        String num2Str = wiajEtSegundoNumero.getText().toString();

        int num1 = num1Str.isEmpty() ? 0 : Integer.parseInt(num1Str);
        int num2 = num2Str.isEmpty() ? 0 : Integer.parseInt(num2Str);

        Intent intent = new Intent();
        intent.putExtra("wiaj_dividendo", num1);
        intent.putExtra("wiaj_divisor", num2);

        setResult(RESULT_OK, intent);
        finish();
    }
}