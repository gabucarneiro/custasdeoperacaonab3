package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

public class Market extends AppCompatActivity {

    private double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double Conversion (double coin, double quantidade, double goalCoin, double fee){
        double valorMenosFee = (coin * quantidade)-((coin * quantidade)*(fee/100));

        return (valorMenosFee / goalCoin);
    }
    public double Buy (double valorDisponivel, double goalCoin, double fee){

        //Retorna o a possível quantidade de compra da nova moeda
        return (valorDisponivel / goalCoin) - ((valorDisponivel / goalCoin) * (fee/100));

    }
    public double Sell (double quantidade, double goalCoin, double fee){

        //Retorna o a possível quantidade de compra da nova moeda
        return (quantidade * goalCoin) - ((quantidade * goalCoin) * (fee/100));

    }
}
