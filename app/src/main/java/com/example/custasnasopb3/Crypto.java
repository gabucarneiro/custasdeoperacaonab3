package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.view.View.GONE;

public class Crypto extends AppCompatActivity {

    DecimalFormat df8 = new DecimalFormat("0.00000000");
    DecimalFormat df6 = new DecimalFormat("0.000000");
    DecimalFormat df4 = new DecimalFormat("0.0000");

    double taxBuy = 0.1;
    double taxExchage = 0.175;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);


        ((EditText) findViewById(R.id.pctCompraCrpt)).setText(df4.format(taxBuy));
        ((EditText) findViewById(R.id.pctTransacaoCrpt)).setText(df4.format(taxExchage));
        ((EditText) findViewById(R.id.pctCrptSell)).setText(df4.format(taxBuy));
        ((EditText) findViewById(R.id.pctTransacaoCrptSell)).setText(df4.format(taxExchage));
        (findViewById(R.id.custosValordaMoedaBruto)).setVisibility(GONE);
        (findViewById(R.id.custosCrpt)).setVisibility(GONE);

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
    public void ListarCoins (LinearLayout view, Wallet wallet){
        //LinearLayout ll_ADHorizontal = findViewById(R.id.LL_ShowCoins);
        StringBuilder sb = new StringBuilder();
        TextView txtCoin = new TextView(this);

        Toast.makeText(this, String.valueOf(wallet.coins.size()), Toast.LENGTH_SHORT).show();

        for (int i=0; i<wallet.coins.size(); i++){
            //ll_ADHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            sb.append(wallet.coins.get(i).getNome()).append(" - ").append(wallet.coins.get(i).getValor()).append(" - ").append(wallet.coins.get(i).getQuantidade()).append("\n");
        }
        txtCoin.setText(sb);
        /*ll_ADHorizontal.removeAllViews();
        ll_ADHorizontal.addView(txtCoin);*/
        view.removeAllViews();
        view.addView(txtCoin);
    }
    /*public void Calcular (View view){
        Market market = new Market();

        double calculo = market.Conversion(Double.parseDouble(((EditText) findViewById(R.id.edCoinValor)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edCoinQuantidade)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edSellGoalCoinValor)).getText().toString()), 0.1);
        ((TextView) findViewById(R.id.edBuyGoalCoinQuantidade)).setText(df6.format(calculo));
        Toast.makeText(this, String.valueOf(calculo), Toast.LENGTH_SHORT).show();
    }*/

    public void Buy (View view){
        Market market = new Market();

        try {
            try {
                taxBuy = Double.parseDouble(((EditText)findViewById(R.id.pctCompraCrpt)).getText().toString());
                try {
                    ((TextView) findViewById(R.id.txCompraCrpt)).setText( df6.format(((Double.parseDouble(((EditText)findViewById(R.id.edBuyCoinDisponivel)).getText().toString())) / (Double.parseDouble(((EditText)findViewById(R.id.edBuyGoalCoinValor)).getText().toString()))) * (taxBuy/100)));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.txCompraCrpt)).setText(String.valueOf(0.0));
                }
            }
            catch (Exception e){
                taxBuy = 0.0;
                ((EditText)findViewById(R.id.pctCompraCrpt)).setText(String.valueOf(taxBuy));
                try {
                    ((TextView) findViewById(R.id.txCompraCrpt)).setText( df6.format((Double.parseDouble(((EditText)findViewById(R.id.edBuyCoinDisponivel)).getText().toString())) / (Double.parseDouble(((EditText)findViewById(R.id.edBuyGoalCoinValor)).getText().toString()))));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.txCompraCrpt)).setText(String.valueOf(0.0));
                }
            }
            try {
                taxExchage = Double.parseDouble(((EditText)findViewById(R.id.pctTransacaoCrpt)).getText().toString());
                try {
                    ((TextView) findViewById(R.id.TransacaoCrpt)).setText( df6.format(((Double.parseDouble(((EditText)findViewById(R.id.edBuyCoinDisponivel)).getText().toString())) / (Double.parseDouble(((EditText)findViewById(R.id.edBuyGoalCoinValor)).getText().toString()))) * (taxExchage/100)));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.TransacaoCrpt)).setText(String.valueOf(0.0));
                }
            }
            catch (Exception e){
                taxExchage = 0.0;
                ((EditText)findViewById(R.id.pctTransacaoCrpt)).setText(String.valueOf(taxExchage));
                try {
                    ((TextView) findViewById(R.id.TransacaoCrpt)).setText( df6.format((Double.parseDouble(((EditText)findViewById(R.id.edBuyCoinDisponivel)).getText().toString())) / (Double.parseDouble(((EditText)findViewById(R.id.edBuyGoalCoinValor)).getText().toString()))));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.TransacaoCrpt)).setText(String.valueOf(0.0));
                }
            }

            double calculo = market.Buy(Double.parseDouble(((EditText) findViewById(R.id.edBuyCoinDisponivel)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edBuyGoalCoinValor)).getText().toString()), (taxBuy+taxExchage));
            ((TextView) findViewById(R.id.edBuyGoalCoinQuantidade)).setText(df6.format(calculo));

            Toast.makeText(this, String.valueOf((taxBuy+taxExchage)), Toast.LENGTH_SHORT).show();
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
            try {
                taxBuy = Double.parseDouble(((EditText)findViewById(R.id.pctCrptSell)).getText().toString());
                try {
                    ((TextView) findViewById(R.id.txCrptSell)).setText( df6.format(((Double.parseDouble(((EditText)findViewById(R.id.edSellGoalCoinValor)).getText().toString())) * (Double.parseDouble(((EditText)findViewById(R.id.edSellQuantidadeCoinDisponivel)).getText().toString()))) * (taxBuy/100)));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.TransacaoCrptSell)).setText(String.valueOf(0.0));
                }
            }
            catch (Exception e){
                taxBuy = 0.0;
                ((EditText)findViewById(R.id.pctCrptSell)).setText(String.valueOf(taxBuy));
                ((TextView) findViewById(R.id.TransacaoCrptSell)).setText(String.valueOf(0.0));
            }
            try {
                taxExchage = Double.parseDouble(((EditText)findViewById(R.id.pctTransacaoCrptSell)).getText().toString());
                try {
                    ((TextView) findViewById(R.id.TransacaoCrptSell)).setText( df6.format(((Double.parseDouble(((EditText)findViewById(R.id.edSellGoalCoinValor)).getText().toString())) * (Double.parseDouble(((EditText)findViewById(R.id.edSellQuantidadeCoinDisponivel)).getText().toString()))) * (taxExchage/100)));
                }
                catch (Exception buy0value){
                    ((TextView) findViewById(R.id.TransacaoCrptSell)).setText(String.valueOf(0.0));
                }
            }
            catch (Exception e){
                taxExchage = 0.0;
                ((EditText)findViewById(R.id.pctTransacaoCrptSell)).setText(String.valueOf(taxExchage));
            }
            double calculo = market.Sell(Double.parseDouble(((EditText) findViewById(R.id.edSellQuantidadeCoinDisponivel)).getText().toString()), Double.parseDouble(((EditText) findViewById(R.id.edSellGoalCoinValor)).getText().toString()), (taxBuy+taxExchage));
            ((TextView) findViewById(R.id.edSellGoalCoinSaldo)).setText(df4.format(calculo));
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

    public void custosVisibility(View view){

        if ((findViewById(R.id.custosCrpt)).getVisibility() == View.VISIBLE || (findViewById(R.id.custosValordaMoedaBruto)).getVisibility() == View.VISIBLE) {
            (findViewById(R.id.custosCrpt)).setVisibility(GONE);
            (findViewById(R.id.custosValordaMoedaBruto)).setVisibility(GONE);
        }
        else {
            (findViewById(R.id.custosCrpt)).setVisibility(View.VISIBLE);
            (findViewById(R.id.custosValordaMoedaBruto)).setVisibility(View.VISIBLE);
        }
    }
}