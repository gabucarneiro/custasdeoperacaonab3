package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Crypto extends AppCompatActivity {

    DecimalFormat df2 = new DecimalFormat("0.00");
    DecimalFormat df3 = new DecimalFormat("0.000");
    DecimalFormat df4 = new DecimalFormat("0.0000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);


    }
    public void WalletInput (View view){
        Wallet wallet = new Wallet();
        try {
            Coin coin = new Coin(((EditText) findViewById(R.id.edCoinNome)).getText().toString(), Double.parseDouble(((EditText) findViewById(R.id.edCoinValor)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.edCoinQuantidade)).getText().toString()));

            wallet.input(coin);
            Toast.makeText(this, coin.getNome(), Toast.LENGTH_SHORT).show();
            ListarCoins(findViewById(R.id.LL_ShowCoins), wallet);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
    public void ListarCoins (View view, Wallet wallet){
        LinearLayout ll_ADHorizontal = findViewById(R.id.LL_ShowCoins);
        StringBuilder sb = new StringBuilder();
        TextView txtCoin = new TextView(this);

        Toast.makeText(this, String.valueOf(wallet.coins.size()), Toast.LENGTH_SHORT).show();

        for (int i=0; i<wallet.coins.size(); i++){
            //ll_ADHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            sb.append(wallet.coins.get(i).getNome()).append(" - ").append(wallet.coins.get(i).getValor()).append(" - ").append(wallet.coins.get(i).getQuantidade()).append("\n");
        }
        txtCoin.setText(sb);
        ll_ADHorizontal.removeAllViews();
        ll_ADHorizontal.addView(txtCoin);
    }
    public void Calcular (View view){
        Market market = new Market();

        double calculo = market.Conversion(Double.parseDouble(((EditText) findViewById(R.id.edCoinValor)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edCoinQuantidade)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edSellGoalCoinValor)).getText().toString()), 0.1);
        ((TextView) findViewById(R.id.edBuyGoalCoinQuantidade)).setText(String.valueOf(calculo));
        Toast.makeText(this, String.valueOf(calculo), Toast.LENGTH_SHORT).show();
    }

    public void Buy (View view){
        Market market = new Market();

        try {
            double calculo = market.Buy(Double.parseDouble(((EditText) findViewById(R.id.edBuyCoinDisponivel)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edBuyGoalCoinValor)).getText().toString()), 0.1);
            ((TextView) findViewById(R.id.edBuyGoalCoinQuantidade)).setText(String.valueOf(df4.format(calculo)));
            Toast.makeText(this, String.valueOf(calculo), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            if(((EditText) findViewById(R.id.edBuyCoinDisponivel)).getText().toString().equals("")){
                ((EditText) findViewById(R.id.edBuyCoinDisponivel)).setError("Valor requerido");
            }
            if(((EditText) findViewById(R.id.edBuyGoalCoinValor)).getText().toString().equals("")){
                ((EditText) findViewById(R.id.edBuyGoalCoinValor)).setError("Valor requerido");
            }
            Toast.makeText(this, "Falha ao calcular", Toast.LENGTH_SHORT).show();
        }

    }
    public void Sell (View view){
        Market market = new Market();
        try {
            double calculo = market.Sell(Double.parseDouble(((EditText) findViewById(R.id.edSellQuantidadeCoinDisponivel)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edSellGoalCoinValor)).getText().toString()), 0.1);
            ((TextView) findViewById(R.id.edSellGoalCoinSaldo)).setText(String.valueOf(df4.format(calculo)));
            Toast.makeText(this, String.valueOf(calculo), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            if(((EditText) findViewById(R.id.edSellQuantidadeCoinDisponivel)).getText().toString().equals("")){
                ((EditText) findViewById(R.id.edSellQuantidadeCoinDisponivel)).setError("Valor requerido");
            }
            if(((EditText) findViewById(R.id.edSellGoalCoinValor)).getText().toString().equals("")){
                ((EditText) findViewById(R.id.edSellGoalCoinValor)).setError("Valor requerido");
            }
            Toast.makeText(this, "Falha ao calcular", Toast.LENGTH_SHORT).show();
        }
    }
}