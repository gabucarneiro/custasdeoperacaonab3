package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

public class Papel extends AppCompatActivity {
    private int id;
    private String nomePapel;
    private Double valor;
    private int quantidade;
    private boolean fracionario;

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

    public boolean isFracionario() {
        return fracionario;
    }

    public void setFracionario(boolean fracionario) {
        this.fracionario = fracionario;
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
    public Papel(int id, String nomePapel, Double valor, int quantidade, boolean fracionario) {
        this.id = id;
        this.nomePapel = nomePapel;
        this.valor = valor;
        this.quantidade = quantidade;
        this.fracionario = fracionario;
    }

    public Papel(int id, String nomePapel, Double valor, int quantidade, boolean fracionario, int dataCompra, int mesCompra, int anoCompra, int dataVenda, int mesVenda, int anoVenda) {
        this.id = id;
        this.nomePapel = nomePapel;
        this.valor = valor;
        this.quantidade = quantidade;
        this.fracionario = fracionario;
    }

    public Papel() {
    }

    public int boolToInt(Boolean bool){
        if(bool){
            return 1;
        }
        else{
            return 0;
        }
    }
    public boolean intToBool(Integer integer){
        if(integer == 1){
            return true;
        }
        else{
            return false;
        }
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
    public String toString4() {
        return "ID: " + id + "\n" +
                "Papel: " + nomePapel + "\n" +
                "Valor: R$" + valor + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Fracionário: " + fracionario + "\n";
    }
}
