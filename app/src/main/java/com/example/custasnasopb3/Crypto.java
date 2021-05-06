package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Crypto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);

    }
    public void WalletInput (View view){
        Wallet wallet = new Wallet();
        Coin coin = new Coin(((EditText) findViewById(R.id.edCoinNome)).getText().toString(), Double.parseDouble(((EditText) findViewById(R.id.edCoinValor)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.edCoinQuantidade)).getText().toString()));

        wallet.input(coin);


        LinearLayout ll_ADHorizontal = findViewById(R.id.LL_ShowCoins);
        for (int i=0; i<wallet.coins.size(); i++){
            //ll_ADHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            TextView txtCoin = new TextView(this);
            txtCoin.setText(wallet.coins.get(i).getNome() + " - " + wallet.coins.get(i).getValor() + " - " + wallet.coins.get(i).getQuantidade());
            ll_ADHorizontal.removeAllViews();
            ll_ADHorizontal.addView(txtCoin);
            //txtCoin.setText(wallet.coins.get(i).getNome() + " - " + wallet.coins.get(i).getValor() + " - " + wallet.coins.get(i).getQuantidade());
            //((LinearLayout) findViewById(R.id.LL_ShowCoins)).removeAllViews();
            //((LinearLayout) findViewById(R.id.LL_ShowCoins)).addView(ll_ADHorizontal);
            //ll_ADHorizontal.addView(ll_ADHorizontal);
        }
    }
}