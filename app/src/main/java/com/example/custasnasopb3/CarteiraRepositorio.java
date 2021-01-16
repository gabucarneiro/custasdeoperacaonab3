package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CarteiraRepositorio extends AppCompatActivity {

    //Aqui criaremos uma carteira com os dados salvos do banco de dados, (banco de dados coletando
    // informação de um outro banco de dados - Chama do outro BD, insere os dados em string e double,
    // adiciona a quantidade e inclui no novo BD) porém, incluindo a quantidade comprada.
    //Após fase inicial concluida, como versão seguinte, pensei em disponibilizar já a informação
    // de venda mínima sem perdas.
    //Ao clicar no papel, (estudar a função 'clickable') disponibilizar a opção de ver o saldo caso
    // seja vendido por um -inserido- valor.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira_repositorio);

        int quantidade;
        List<Papel> carteira = new ArrayList<>();
    }
}