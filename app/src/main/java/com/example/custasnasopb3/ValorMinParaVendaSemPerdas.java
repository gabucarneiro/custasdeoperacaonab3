package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ValorMinParaVendaSemPerdas extends AppCompatActivity {

    QntAcoesValorDisponivel qavd;
    double valPapelAdquirido = 0.0;
    int quantidade = 0;
    double valPretendidoVenda = 0.0;

    //CUSTOS OPERACIONAIS
    boolean corretagemFixa = true;
    double corretagem = 2.49;
    double calc_Corretagem = 0.0;

    boolean custodiaFixa = false;
    double custodia = 0.0;
    double calc_Custodia= 0.0;

    double emolumentos;
    double tx_liquidacao = 0.0275;
    double calc_tx_liquidacao;
    double tx_negociacao = 0.003247;
    double calc_tx_negociacao;

    boolean issCobrado = true;
    double iss = 0.01;
    double calc_iss;

    double ir_compra;
    double calc_ir_compra;
    double ir_venda;
    double calc_ir_venda;

    DecimalFormat df2 = new DecimalFormat("0.00");
    DecimalFormat df3 = new DecimalFormat("0.000");
    DecimalFormat df4 = new DecimalFormat("0.0000");


    /*EditText et_valPapelAdquirido = (EditText) findViewById(R.id.valPapelAdquirido);
    EditText et_quantidade = (EditText) findViewById(R.id.quantidade2);
    EditText et_valPretendidoVenda = (EditText) findViewById(R.id.valPretendidoVenda);
    EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);
    EditText pct_Custodia = findViewById(R.id.pctCustodia2);
    EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
    EditText pct_Negociacao = findViewById(R.id.pctNegociacao2);
    EditText pct_Iss = findViewById(R.id.pctIss2);
    TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos2);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_min_para_venda_sem_perdas);

    }

    public Double getValPapelAdquirido() {
        return valPapelAdquirido;
    }

    public void setValPapelAdquirido(Double valPapelAdquirido) {
        this.valPapelAdquirido = valPapelAdquirido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValPretendidoVenda() {
        return valPretendidoVenda;
    }

    public void setValPretendidoVenda(Double valPretendidoVenda) {
        this.valPretendidoVenda = valPretendidoVenda;
    }

    public void calcular(View view){
        try {
            EditText et_valPapelAdquirido = (EditText) findViewById(R.id.valPapelAdquirido);
            EditText et_quantidade = (EditText) findViewById(R.id.quantidade2);
            EditText et_valPretendidoVenda = (EditText) findViewById(R.id.valPretendidoVenda);

            if (et_valPapelAdquirido != null && et_quantidade != null && et_valPretendidoVenda != null) {
                try {
                    valPapelAdquirido = Double.parseDouble(et_valPapelAdquirido.getText().toString());
                    quantidade = Integer.parseInt(et_quantidade.getText().toString());
                    valPretendidoVenda = Double.parseDouble(et_valPretendidoVenda.getText().toString());


                    TextView tvCorretagem = (TextView) findViewById(R.id.corretagem2);
                    Double tempCorretagem = Corretagem(valPapelAdquirido, quantidade);
                    String calculo_Corretagem = String.valueOf(tempCorretagem);
                    tvCorretagem.setText(calculo_Corretagem);

                    //TODO Criar e chamar os cálculos das custas.

                    TextView tv_valCompraDoPapel = (TextView) findViewById(R.id.valCompraDoPapel2);
                    tv_valCompraDoPapel.setText(String.valueOf((valPapelAdquirido * quantidade)+tempCorretagem));

                }
                catch (Exception e) {
                    Toaster("Erro ao calcular");
                }
            }
            else{
                Toaster("Nada a ser calculado!");
            }
        }
        catch (Exception e){
            Toaster("EditTexts vazios");
        }
    }

    //TODO Dar continuidade à separação dos cálculos das custas/emolumentos/taxas.

    public Double Corretagem(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null){

            if(quantidade>=1) {

                CheckBox cb_Corretagem = findViewById(R.id.cbCorretagem2);
                if (cb_Corretagem.isChecked()) {
                    corretagemFixa = false;
                    EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);
                    double db_pct_Corretagem = Double.parseDouble(pct_Corretagem.getText().toString());
                    corretagem = db_pct_Corretagem;
                }

                if (!corretagemFixa || corretagem != 0 || valPapelCalcular == 0) {
                    calc_Corretagem = corretagem;
                    return calc_Corretagem;
                }
                else {
                    calc_Corretagem = qavd.Porcentagem(valPapelCalcular, corretagem);
                    return calc_Corretagem;
                }
            }
        }
        return calc_Corretagem;
    }





    public double valMinSemPerdas(double valPapelAdquirido, int quantidade){
        double totalSum = valPapelAdquirido * quantidade;
        totalSum -= totalSum - corretagem - tx_liquidacao - tx_negociacao;

        double valMinSemPerdas=0.0;
        return valMinSemPerdas;
    }

    public void Toaster(CharSequence text){
        Context context = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duracao);
        toast.show();
    }
}