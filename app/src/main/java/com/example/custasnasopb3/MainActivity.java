package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ***OK*** Fazer o View do sistema de verificação da venda mínima sem perdas;
    // ***OK*** Fazer um side View para calcular ganhos/perdas com a venda num determinado valor;
    // ***OK*** Criar tela para parâmetros

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void qnt_acoes_valor_disponivel(View view){
        Intent intent = new Intent(this, QntAcoesValorDisponivel.class);
        startActivity(intent);
    }
    public void valor_min_para_venda_sem_perdas(View view){
        Intent intent = new Intent(this, ValorMinParaVendaSemPerdas.class);
        startActivity(intent);
    }
    public void cadastro_papel(View view){
        Intent intent = new Intent(this, CadastroPapel.class);
        startActivity(intent);
    }
    public void parametros(View view){
        Intent intent = new Intent(this, Custas.class);
        startActivity(intent);
    }
    public void testeDeThread(View view){
        new Thread(){
            public void run(){
                try {
                    Button testeDeThread = findViewById(R.id.testeDeThread);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, ValorMinParaVendaSemPerdas.class);
                            startActivity(intent);
                        }
                    });

                }
                catch (Exception e){
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        }.start();
    }
}