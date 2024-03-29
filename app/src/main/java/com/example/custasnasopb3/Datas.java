package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

public class Datas extends AppCompatActivity {

    private int id;
    private boolean confirmaCompra;
    private int dataCompra;
    private int mesCompra;
    private int anoCompra;
    private boolean confirmaVenda;
    private int dataVenda;
    private int mesVenda;
    private int anoVenda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConfirmaCompra() {
        return confirmaCompra;
    }

    public void setConfirmaCompra(boolean confirmaCompra) {
        this.confirmaCompra = confirmaCompra;
    }

    public int getDataCompra(){
        return dataCompra;
    }

    public void setDataCompra(int dataCompra){
        this.dataCompra = dataCompra;
    }

    public int getMesCompra() {
        return mesCompra;
    }

    public void setMesCompra(int mesCompra) {
        this.mesCompra = mesCompra;
    }

    public int getAnoCompra() {
        return anoCompra;
    }

    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }

    public boolean isConfirmaVenda() {
        return confirmaVenda;
    }

    public void setConfirmaVenda(boolean confirmaVenda) {
        this.confirmaVenda = confirmaVenda;
    }

    public int getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(int dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getMesVenda() {
        return mesVenda;
    }

    public void setMesVenda(int mesVenda) {
        this.mesVenda = mesVenda;
    }

    public int getAnoVenda() {
        return anoVenda;
    }

    public void setAnoVenda(int anoVenda) {
        this.anoVenda = anoVenda;
    }

    public Datas() {
    }

    public Datas(int id, boolean confirmaCompra, int dataCompra, int mesCompra, int anoCompra, boolean confirmaVenda, int dataVenda, int mesVenda, int anoVenda) {
        this.id = id;
        this.confirmaCompra = confirmaCompra;
        this.dataCompra = dataCompra;
        this.mesCompra = mesCompra;
        this.anoCompra = anoCompra;
        this.confirmaVenda = confirmaVenda;
        this.dataVenda = dataVenda;
        this.mesVenda = mesVenda;
        this.anoVenda = anoVenda;
    }

    public void datasCompra(boolean confirmaCompra, int id, int dataCompra, int mesCompra, int anoCompra) {
        if (confirmaCompra){
            this.id = id;
            this.confirmaCompra= confirmaCompra;
            this.dataCompra = dataCompra;
            this.mesCompra = mesCompra;
            this.anoCompra = anoCompra;
        }
    }

    public void datasVenda (boolean confirmaVenda, int id, int dataVenda, int mesVenda, int anoVenda){
        if (confirmaVenda){
            this.id = id;
            this.confirmaVenda = confirmaVenda;
            this.dataVenda = dataVenda;
            this.mesVenda = mesVenda;
            this.anoVenda = anoVenda;
        }
    }

    @Override
    public String toString() {
        return "Confirma Compra: " + confirmaCompra +
                "\nData da compra: " + dataCompra +
                "/" + mesCompra +
                "/" + anoCompra;
    }

    public String toString1() {
        return "Confirma Venda: " + confirmaVenda +
                "\nData da venda: " + dataVenda +
                "/" + mesVenda +
                "/" + anoVenda;
    }
}
