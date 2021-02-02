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
    CadastroPapel cp;
    double valPapelAdquirido = 0.0;
    int quantidade = 0;
    double valPretendidoVenda = 0.0;

    //CUSTOS OPERACIONAIS
    boolean corretagemFixa = true;
    double corretagem = 2.49;
    double calc_Corretagem = 0.0;

    boolean custodiaFixa = true;
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

    double ir_compra=0.0;
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

        EditText et_valPapelAdquirido = (EditText) findViewById(R.id.valPapelAdquirido);
        EditText et_quantidade = (EditText) findViewById(R.id.quantidade2);
        EditText et_valPretendidoVenda = (EditText) findViewById(R.id.valPretendidoVenda);
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);
        EditText pct_Custodia = findViewById(R.id.pctCustodia2);
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao2);
        EditText pct_Iss = findViewById(R.id.pctIss2);
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos2);

        pct_Corretagem.setText(String.valueOf(corretagem));
        pct_Custodia.setText(String.valueOf(custodia));
        pct_Liquidacao.setText(String.valueOf(tx_liquidacao));
        pct_Negociacao.setText(String.valueOf(tx_negociacao));
        pct_Iss.setText(String.valueOf(iss));
        pct_Emolumentos.setText(String.valueOf(tx_liquidacao+tx_negociacao));

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

                    TextView val_Custodia = (TextView) findViewById(R.id.custodia2);
                    Double tempCustodia = Custodia(valPapelAdquirido, quantidade);
                    String str_val_Custodia = df2.format(tempCustodia);
                    val_Custodia.setText(str_val_Custodia);

                    TextView val_tx_liquidacao = (TextView) findViewById(R.id.tax_liquidacao2);
                    Double tempTxLiquidacao = Liquidacao(valPapelAdquirido, quantidade);
                    String str_val_tx_liquidacao = df3.format(tempTxLiquidacao);
                    val_tx_liquidacao.setText(str_val_tx_liquidacao);

                    TextView val_tx_negociacao = (TextView) findViewById(R.id.tax_negociacao2);
                    Double tempTxNegociacao = Negociacao(valPapelAdquirido, quantidade);
                    String str_val_tx_negociacao = df3.format(tempTxNegociacao);
                    val_tx_negociacao.setText(str_val_tx_negociacao);

                    TextView val_emolumentos = (TextView) findViewById(R.id.emolumentos2);
                    Double tempEmolumentos = tempTxLiquidacao + tempTxNegociacao;
                    String str_val_emolumentos = df4.format(tempEmolumentos);
                    val_emolumentos.setText(str_val_emolumentos);

                    TextView pctEmolumentos2 = (TextView) findViewById(R.id.pctEmolumentos2);
                    String tempPctEmolumentos = df3.format(Double.valueOf(tx_liquidacao + tx_negociacao));
                    pctEmolumentos2.setText(tempPctEmolumentos);

                    TextView val_iss = (TextView) findViewById(R.id.iss2);
                    Double tempIss = Iss(valPapelAdquirido, quantidade);
                    String str_val_iss = df3.format(tempIss);
                    val_iss.setText(str_val_iss);

                    TextView val_ir2 = (TextView) findViewById(R.id.impostoDeRenda2);
                    Double tempIr2 = Iss(valPapelAdquirido, quantidade);
                    String str_val_ir2 = df3.format(tempIr2);
                    val_ir2.setText(str_val_ir2);

                    //TODO Criar e chamar os cálculos das custas.

                    TextView tv_valCompraDoPapel = (TextView) findViewById(R.id.valCompraDoPapel2);
                    tv_valCompraDoPapel.setText(String.valueOf((valPapelAdquirido * quantidade)+tempCorretagem+tempCustodia+tempTxLiquidacao+tempTxNegociacao));

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
                    calc_Corretagem = Porcentagem(valPapelCalcular, corretagem);
                    return calc_Corretagem;
                }
            }
        }
        return calc_Corretagem;
    }

    public Double Custodia(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null) {

            if (quantidade >= 1) {
                CheckBox cb_Custodia = findViewById(R.id.cbCustodia2);
                if (cb_Custodia.isChecked()) {
                    custodiaFixa = false;
                    EditText pct_Custodia = findViewById(R.id.pctCustodia2);
                    double db_pct_Custodia = Double.parseDouble(pct_Custodia.getText().toString());
                    custodia = db_pct_Custodia;
                }

                if (custodiaFixa || custodia == 0 || valPapelCalcular == 0) {
                    calc_Custodia = custodia;
                    return calc_Custodia;
                }
                else {
                    calc_Custodia = Porcentagem((valPapelCalcular*quantidade), custodia);
                    return calc_Custodia;
                }
            }
        }
        return calc_Custodia;
    }

    public Double Liquidacao(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null) {

            if (quantidade >= 1) {
                CheckBox cb_Liquidacao = findViewById(R.id.cbLiquidacao2);
                if (cb_Liquidacao.isChecked()) {
                    EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
                    double db_pct_Liquidacao = Double.parseDouble(pct_Liquidacao.getText().toString());
                    tx_liquidacao = db_pct_Liquidacao;
                }
                if (tx_liquidacao == 0 || valPapelCalcular == 0) {
                    calc_tx_liquidacao = tx_liquidacao;
                    return calc_tx_liquidacao;
                }
                else {
                    calc_tx_liquidacao = Porcentagem((valPapelCalcular*quantidade), tx_liquidacao);
                    return calc_tx_liquidacao;
                }
            }
        }
        return calc_tx_liquidacao;
    }

    public Double Negociacao(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null) {

            if (quantidade >= 1) {
                CheckBox cb_Negociacao = findViewById(R.id.cbNegociacao2);
                if (cb_Negociacao.isChecked()) {
                    EditText pct_Negociacao = findViewById(R.id.pctNegociacao2);
                    double db_pct_Negociacao = Double.parseDouble(pct_Negociacao.getText().toString());
                    tx_negociacao = db_pct_Negociacao;
                }
                if (tx_negociacao == 0 || valPapelCalcular == 0) {
                    calc_tx_negociacao = tx_negociacao;
                    return calc_tx_negociacao;
                }
                else {
                    calc_tx_negociacao = Porcentagem((valPapelCalcular*quantidade), tx_negociacao);
                    return calc_tx_negociacao;
                }
            }
        }
        return calc_tx_negociacao;
    }

    public Double Iss(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null) {

            if (quantidade >= 1) {
                CheckBox cb_Iss2 = findViewById(R.id.cbIss2);
                if (cb_Iss2.isChecked()) {
                    issCobrado = false;
                    EditText pct_Iss2 = findViewById(R.id.pctIss2);
                    double db_pct_Iss2 = Double.parseDouble(pct_Iss2.getText().toString());
                    iss = db_pct_Iss2;
                }

                if (issCobrado || iss == 0 || valPapelCalcular == 0) {
                    calc_iss = iss;
                    return calc_iss;
                }
                else {
                    calc_iss = Porcentagem((valPapelCalcular*quantidade), iss);
                    return calc_iss;
                }
            }
        }
        return calc_iss;
    }

    public Double Ir(Double valPapelCalcular, Integer quantidade){

        if(valPapelCalcular != null && quantidade != null) {

            if (quantidade >= 1) {
                CheckBox cb_Ir2 = findViewById(R.id.cbIr2);
                if (cb_Ir2.isChecked()) {
                    EditText pct_Ir2 = findViewById(R.id.pctIr2);
                    double db_pct_Ir2 = Double.parseDouble(pct_Ir2.getText().toString());
                    ir_compra = db_pct_Ir2;
                }
                if (ir_compra == 0 || valPapelCalcular == 0) {
                    calc_ir_compra = ir_compra;
                    return calc_ir_compra;
                }
                else {
                    calc_ir_compra = Porcentagem((valPapelCalcular*quantidade), ir_compra);
                    return calc_ir_compra;
                }
            }
        }
        return calc_ir_compra;
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

    public double Porcentagem(double valor, double porcentagem){
        Double resultado = (valor*porcentagem)/100;
        return resultado;
    }
}