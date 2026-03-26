package com.example.prueba01_wellington_aldas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class wiajMainActivity extends AppCompatActivity {

    private EditText wiajEtNombre;
    private EditText wiajEtApellidos;
    private EditText wiajEtNum1;
    private EditText wiajEtNum2;
    private EditText wiajEtMultiplicacion;
    private EditText wiajEtPotencia;
    private EditText wiajEtFactorial;
    private Button wiajBtnSiguiente;
    private Button wiajBtnMostrarResultados;

    private Intent wiajResultData;
    private static final int WIAJ_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiaj_activity_main);

        wiajInitViews();
        wiajClearFields();

        wiajBtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiajClearFields();
                wiajStartSecondActivity();
            }
        });

        wiajBtnMostrarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wiajResultData != null) {
                    wiajDisplayResults(wiajResultData);
                    wiajBtnMostrarResultados.setEnabled(false);
                }
            }
        });
    }

    private void wiajInitViews() {
        wiajEtNombre = findViewById(R.id.wiaj_et_nombre_main);
        wiajEtApellidos = findViewById(R.id.wiaj_et_apellidos_main);
        wiajEtNum1 = findViewById(R.id.wiaj_et_num1_main);
        wiajEtNum2 = findViewById(R.id.wiaj_et_num2_main);
        wiajEtMultiplicacion = findViewById(R.id.wiaj_et_multiplicacion_main);
        wiajEtPotencia = findViewById(R.id.wiaj_et_potencia_main);
        wiajEtFactorial = findViewById(R.id.wiaj_et_factorial_main);
        wiajBtnSiguiente = findViewById(R.id.wiaj_btn_siguiente_main);
        wiajBtnMostrarResultados = findViewById(R.id.wiaj_btn_mostrar_resultados_main);
    }

    private void wiajClearFields() {
        wiajEtNombre.setText("");
        wiajEtApellidos.setText("");
        wiajEtNum1.setText("");
        wiajEtNum2.setText("");
        wiajEtMultiplicacion.setText("");
        wiajEtPotencia.setText("");
        wiajEtFactorial.setText("");
        wiajBtnMostrarResultados.setEnabled(false);
    }

    private void wiajStartSecondActivity() {
        Intent intent = new Intent(this, wiajSecondActivity.class);
        startActivityForResult(intent, WIAJ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WIAJ_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            wiajResultData = data;
            wiajBtnMostrarResultados.setEnabled(true);
        }
    }

    private void wiajDisplayResults(Intent data) {
        String nombre = data.getStringExtra("wiaj_nombre");
        String apellidos = data.getStringExtra("wiaj_apellidos");
        int num1 = data.getIntExtra("wiaj_dividendo", 0);
        int num2 = data.getIntExtra("wiaj_divisor", 0);

        long multiplicacion = wiajSumBasedMultiply(num1, num2);
        long potencia = wiajSumBasedPower(num1, num2);
        long factorial = wiajSumBasedFactorial(num1);

        wiajEtNombre.setText(nombre);
        wiajEtApellidos.setText(apellidos);
        wiajEtNum1.setText(String.valueOf(num1));
        wiajEtNum2.setText(String.valueOf(num2));
        wiajEtMultiplicacion.setText(String.valueOf(multiplicacion));
        wiajEtPotencia.setText(String.valueOf(potencia));
        wiajEtFactorial.setText(String.valueOf(factorial));
    }

    private long wiajSumBasedMultiply(long a, long b) {
        long result = 0;
        for (long i = 0; i < b; i++) {
            result += a;
        }
        return result;
    }

    private long wiajSumBasedPower(int base, int exponente) {
        if (exponente == 0) return 1;
        long result = base;
        for (int i = 1; i < exponente; i++) {
            result = wiajSumBasedMultiply(result, base);
        }
        return result;
    }

    private long wiajSumBasedFactorial(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = wiajSumBasedMultiply(result, i);
        }
        return result;
    }
}