package com.example.custasnasopb3;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Wallet extends AppCompatActivity {
    //Coin coin = new Coin();
    ArrayList<Coin> coins = new ArrayList<>();

    public void input (Coin coin){
        coins.add(coin);
    }
    public void update (Coin coin){
        for (int i=0; i<coins.size(); i++){
            if (coins.get(i).getNome().equals(coin.getNome())){
                coins.get(i).setValor(coin.getValor());
                coins.get(i).setQuantidade(coin.getQuantidade());
            }
            else {
                Toast.makeText(this, "Não adicionado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
