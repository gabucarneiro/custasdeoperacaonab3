package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Papel extends AppCompatActivity {
    private int id;
    private String nomePapel;
    private Double valor;
    private int quantidade;

    public int getId(){return id;}

    public void setId(int id){ this.id=id;}

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String papel) {
        this.nomePapel = papel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Papel(String nomePapel, Double valor) {
        this.nomePapel = nomePapel;
        this.valor = valor;
    }
    public Papel(int id, String nomePapel, Double valor) {
        this.id = id;
        this.nomePapel = nomePapel;
        this.valor = valor;
    }
    public Papel(int id, String nomePapel, Double valor, int quantidade) {
        this.id = id;
        this.nomePapel = nomePapel;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    public Papel() {
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Papel: " + nomePapel + "\n" +
                "Valor: R$" + valor + "\n";
    }
    public String toString2() {
        return "Papel: " + nomePapel + "\n" +
                "Valor: R$" + valor + "\n";
    }
    public String toString3() {
        return "ID: " + id + "\n" +
                "Papel: " + nomePapel + "\n" +
                "Valor: R$" + valor + "\n" +
                "Quantidade: " + quantidade + "\n";
    }
}
