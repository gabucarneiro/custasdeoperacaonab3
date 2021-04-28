package com.example.custasnasopb3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ValorMinParaVendaSemPerdas extends AppCompatActivity {

    QntAcoesValorDisponivel qavd;
    CadastroPapel cp;
    double valPapelAdquirido = 0.0;
    int quantidade = 0;
    double valPretendidoVenda = 0.0;

    //CUSTOS OPERACIONAIS
    boolean corretagemFixa = true;
    double corretagem;
    double calc_Corretagem = 0.0;

    boolean custodiaFixa = true;
    double custodia = 0.0;
    double calc_Custodia= 0.0;

    double emolumentos;
    boolean tx_liquidacaoFixa = false;
    double tx_liquidacao;
    double calc_tx_liquidacao;
    boolean tx_negociacaoFixa = false;
    double tx_negociacao;
    double calc_tx_negociacao;

    boolean issFixo = false;
    boolean issCobrado = true;
    double iss;
    double calc_iss;

    double ir_compra;
    double calc_ir_compra;
    double ir_venda;
    double calc_ir_venda;

    DecimalFormat df2 = new DecimalFormat("0.00");
    DecimalFormat df3 = new DecimalFormat("0.000");
    DecimalFormat df4 = new DecimalFormat("0.0000");

    //TODO pesquisar se esta é a melhor forma de criar instâncias para EditTexts/TextViews/CheckBoxes.
    EditText et_valPapelAdquirido, et_quantidade, et_valPretendidoVenda, pct_Corretagem, pct_Custodia, pct_Liquidacao, pct_Negociacao, pct_Iss, pctCorretagem2Compra, pctCustodia2Compra, pctLiquidacao2Compra, pctNegociacao2Compra, pctIss2Compra;
    TextView pct_Emolumentos, pctEmolumentos2Compra, corretagem2Compra, custodia2Compra, tax_liquidacao2Compra, tax_negociacao2Compra, iss2Compra, emolumentos2Compra, tvCorretagem, val_Custodia, val_tx_liquidacao, val_tx_negociacao, val_emolumentos, val_iss, tv_valVendaDoPapel, TV_valMinVendaSemPerdas, tv_valCompraDoPapel;
    CheckBox cbCorretagem2, cbCustodia2, cbLiquidacao2, cbNegociacao2, cbIss2, cbCorretagem2Compra, cbCustodia2Compra, cbLiquidacao2Compra, cbNegociacao2Compra, cbIss2Compra;


    private Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_min_para_venda_sem_perdas);

        DataBaseHelper dbhCustas = new DataBaseHelper(this);
        /*double stdCorretagem;
        double stdCustodia;
        double stdTx_liquidacao;
        double stdTx_negociacao;
        double stdIss;
        new Thread() {
            public void run() {
                try {
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            Custas custas = new Custas(999, dbhCustas.getCustas(999).getCorretagem(), dbhCustas.getCustas(999).getCustodia(), dbhCustas.getCustas(999).getTx_liquidacao(), dbhCustas.getCustas(999).getTx_negociacao(), dbhCustas.getCustas(999).getIss(), dbhCustas.getCustas(999).isCorretagemFixa(), dbhCustas.getCustas(999).isCustodiaFixa(), dbhCustas.getCustas(999).isTx_liquidacaoFixa(), dbhCustas.getCustas(999).isTx_negociacaoFixa(), dbhCustas.getCustas(999).isIssFixo());
                            double stdCorretagem = custas.getCorretagem();
                            double stdCustodia = custas.getCustodia();
                            double stdTx_liquidacao = custas.getTx_liquidacao();
                            double stdTx_negociacao = custas.getTx_negociacao();
                            double stdIss = custas.getIss();
                        }
                    });
                }
                catch (Exception e){
                    e.getMessage();
                }
            }
        }.start();*/
        Custas custas = new Custas(999, dbhCustas.getCustas(999).getCorretagem(), dbhCustas.getCustas(999).getCustodia(), dbhCustas.getCustas(999).getTx_liquidacao(), dbhCustas.getCustas(999).getTx_negociacao(), dbhCustas.getCustas(999).getIss(), dbhCustas.getCustas(999).isCorretagemFixa(), dbhCustas.getCustas(999).isCustodiaFixa(), dbhCustas.getCustas(999).isTx_liquidacaoFixa(), dbhCustas.getCustas(999).isTx_negociacaoFixa(), dbhCustas.getCustas(999).isIssFixo());
        double stdCorretagem = custas.getCorretagem();
        double stdCustodia = custas.getCustodia();
        double stdTx_liquidacao = custas.getTx_liquidacao();
        double stdTx_negociacao = custas.getTx_negociacao();
        double stdIss = custas.getIss();
        boolean isCorretagemFixa = custas.isCorretagemFixa();
        boolean isCustodiaFixa = custas.isCustodiaFixa();
        boolean isTx_liquidacaoFixa = custas.isTx_liquidacaoFixa();
        boolean isTx_negociacaoFixa = custas.isTx_negociacaoFixa();
        boolean isIssFixo = custas.isIssFixo();
        new Thread(){
            public void run(){
                try {
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            /*CUSTOS DE VENDA*/
                            pct_Corretagem = findViewById(R.id.pctCorretagem2);
                            pct_Custodia = findViewById(R.id.pctCustodia2);
                            pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
                            pct_Negociacao = findViewById(R.id.pctNegociacao2);
                            pct_Iss = findViewById(R.id.pctIss2);
                            pct_Emolumentos = findViewById(R.id.pctEmolumentos2);

                            //Custas custas = new Custas(999, dbhCustas.getCustas(999).getCorretagem(), dbhCustas.getCustas(999).getCustodia(), dbhCustas.getCustas(999).getTx_liquidacao(), dbhCustas.getCustas(999).getTx_negociacao(), dbhCustas.getCustas(999).getIss(), dbhCustas.getCustas(999).isCorretagemFixa(), dbhCustas.getCustas(999).isCustodiaFixa(), dbhCustas.getCustas(999).isTx_liquidacaoFixa(), dbhCustas.getCustas(999).isTx_negociacaoFixa(), dbhCustas.getCustas(999).isIssFixo());

                            pct_Corretagem.setText(String.valueOf(stdCorretagem));
                            pct_Custodia.setText(String.valueOf(stdCustodia));
                            pct_Liquidacao.setText(String.valueOf(stdTx_liquidacao));
                            pct_Negociacao.setText(String.valueOf(stdTx_negociacao));
                            pct_Iss.setText(String.valueOf(stdIss));
                            pct_Emolumentos.setText(String.valueOf(stdTx_liquidacao+stdTx_negociacao));

                            cbCorretagem2 = findViewById(R.id.cbCorretagem2);
                            cbCorretagem2.setChecked(isCorretagemFixa);

                            cbCustodia2 = findViewById(R.id.cbCustodia2);
                            cbCustodia2.setChecked(isCustodiaFixa);

                            cbLiquidacao2 = findViewById(R.id.cbLiquidacao2);
                            cbLiquidacao2.setChecked(isTx_liquidacaoFixa);

                            cbNegociacao2 = findViewById(R.id.cbNegociacao2);
                            cbNegociacao2.setChecked(isTx_negociacaoFixa);

                            cbIss2 = findViewById(R.id.cbIss2);
                            cbIss2.setChecked(isIssFixo);

                            RelativeLayout custasExtra = findViewById(R.id.custasExtra2);
                            custasExtra.setVisibility(View.GONE);


                            /*pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                            pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                            pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                            pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                            pctIss2Compra = findViewById(R.id.pctIss2Compra);
                            pctEmolumentos2Compra = findViewById(R.id.pctEmolumentos2Compra);

                            pctCorretagem2Compra.setText(String.valueOf(custas.getCorretagem()));
                            pctCustodia2Compra.setText(String.valueOf(custas.getCustodia()));
                            pctLiquidacao2Compra.setText(String.valueOf(custas.getTx_liquidacao()));
                            pctNegociacao2Compra.setText(String.valueOf(custas.getTx_negociacao()));
                            pctIss2Compra.setText(String.valueOf(custas.getIss()));
                            pctEmolumentos2Compra.setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));

                            cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                            if (custas.isCorretagemFixa()){
                                cbCorretagem2Compra.setChecked(true);
                            }
                            else {
                                cbCorretagem2Compra.setChecked(false);
                            }

                            cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                            if (custas.isCustodiaFixa()){
                                cbCustodia2Compra.setChecked(true);
                            }
                            else {
                                cbCustodia2Compra.setChecked(false);
                            }

                            cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);

                            if (custas.isTx_liquidacaoFixa()){
                                cbLiquidacao2Compra.setChecked(true);
                            }
                            else {
                                cbLiquidacao2Compra.setChecked(false);
                            }

                            cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                            if (custas.isTx_negociacaoFixa()){
                                cbNegociacao2Compra.setChecked(true);
                            }
                            else {
                                cbNegociacao2Compra.setChecked(false);
                            }

                            cbIss2Compra = findViewById(R.id.cbIss2Compra);
                            if (custas.isIssFixo()){
                                cbIss2Compra.setChecked(true);
                            }
                            else {
                                cbIss2Compra.setChecked(false);
                            }

                            RelativeLayout RLcustasExtraCompra = findViewById(R.id.custasExtraCompra);
                            RLcustasExtraCompra.setVisibility(View.GONE);*/
                        }
                    });
                }
                catch (Exception e){
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            /*CUSTOS DE COMPRA*/
                            pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                            pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                            pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                            pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                            pctIss2Compra = findViewById(R.id.pctIss2Compra);
                            pctEmolumentos2Compra = findViewById(R.id.pctEmolumentos2Compra);

                            //Custas custas = new Custas(999);

                            pctCorretagem2Compra.setText(String.valueOf(stdCorretagem));
                            pctCustodia2Compra.setText(String.valueOf(stdCustodia));
                            pctLiquidacao2Compra.setText(String.valueOf(stdTx_liquidacao));
                            pctNegociacao2Compra.setText(String.valueOf(stdTx_negociacao));
                            pctIss2Compra.setText(String.valueOf(stdIss));
                            pctEmolumentos2Compra.setText(String.valueOf(stdTx_liquidacao+stdTx_negociacao));

                            cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                            cbCorretagem2Compra.setChecked(isCorretagemFixa);

                            cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                            cbCustodia2Compra.setChecked(isCustodiaFixa);

                            cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);
                            cbLiquidacao2Compra.setChecked(isTx_liquidacaoFixa);

                            cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                            cbNegociacao2Compra.setChecked(isTx_negociacaoFixa);

                            cbIss2Compra = findViewById(R.id.cbIss2Compra);
                            cbIss2Compra.setChecked(isIssFixo);

                            RelativeLayout RLcustasExtraCompra = findViewById(R.id.custasExtraCompra);
                            RLcustasExtraCompra.setVisibility(View.GONE);
                        }
                    });
                }
                catch (Exception e){
                    e.getMessage();
                    e.printStackTrace();
                }

            }
        }.start();

        /*new Thread(){
            public void run(){
                try {
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {

                            //Custas custas = new Custas(999, dbhCustas.getCustas(999).getCorretagem(), dbhCustas.getCustas(999).getCustodia(), dbhCustas.getCustas(999).getTx_liquidacao(), dbhCustas.getCustas(999).getTx_negociacao(), dbhCustas.getCustas(999).getIss(), dbhCustas.getCustas(999).isCorretagemFixa(), dbhCustas.getCustas(999).isCustodiaFixa(), dbhCustas.getCustas(999).isTx_liquidacaoFixa(), dbhCustas.getCustas(999).isTx_negociacaoFixa(), dbhCustas.getCustas(999).isIssFixo());

                            *//*CUSTOS DE COMPRA*//*

                        }
                    });
                }
                catch (Exception e){
                    e.getMessage();
                    e.printStackTrace();
                }

            }
        }.start();*/


        /*CUSTOS DE VENDA*/
        /*EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);
        EditText pct_Custodia = findViewById(R.id.pctCustodia2);
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao2);
        EditText pct_Iss = findViewById(R.id.pctIss2);
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos2);

        Custas custas = new Custas(999, dbhCustas.getCustas(999).getCorretagem(), dbhCustas.getCustas(999).getCustodia(), dbhCustas.getCustas(999).getTx_liquidacao(), dbhCustas.getCustas(999).getTx_negociacao(), dbhCustas.getCustas(999).getIss(), dbhCustas.getCustas(999).isCorretagemFixa(), dbhCustas.getCustas(999).isCustodiaFixa(), dbhCustas.getCustas(999).isTx_liquidacaoFixa(), dbhCustas.getCustas(999).isTx_negociacaoFixa(), dbhCustas.getCustas(999).isIssFixo());

        pct_Corretagem.setText(String.valueOf(custas.getCorretagem()));
        pct_Custodia.setText(String.valueOf(custas.getCustodia()));
        pct_Liquidacao.setText(String.valueOf(custas.getTx_liquidacao()));
        pct_Negociacao.setText(String.valueOf(custas.getTx_negociacao()));
        pct_Iss.setText(String.valueOf(custas.getIss()));
        pct_Emolumentos.setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));

        CheckBox cbCorretagem2 = findViewById(R.id.cbCorretagem2);
        if (custas.isCorretagemFixa()){
            cbCorretagem2.setChecked(true);
        }
        else {
            cbCorretagem2.setChecked(false);
        }

        CheckBox cbCustodia2 = findViewById(R.id.cbCustodia2);
        if (custas.isCustodiaFixa()){
            cbCustodia2.setChecked(true);
        }
        else {
            cbCustodia2.setChecked(false);
        }

        CheckBox cbLiquidacao2 = findViewById(R.id.cbLiquidacao2);
        if (custas.isTx_liquidacaoFixa()){
            cbLiquidacao2.setChecked(true);
        }
        else {
            cbLiquidacao2.setChecked(false);
        }

        CheckBox cbNegociacao2 = findViewById(R.id.cbNegociacao2);
        if (custas.isTx_negociacaoFixa()){
            cbNegociacao2.setChecked(true);
        }
        else {
            cbNegociacao2.setChecked(false);
        }

        CheckBox cbIss2 = findViewById(R.id.cbIss2);
        if (custas.isIssFixo()){
            cbIss2.setChecked(true);
        }
        else {
            cbIss2.setChecked(false);
        }

        */
        /*CUSTOS DE COMPRA*/
        /*EditText pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
        TextView pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
        TextView pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
        TextView pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
        TextView pctIss2Compra = findViewById(R.id.pctIss2Compra);
        TextView pctEmolumentos2Compra = findViewById(R.id.pctEmolumentos2Compra);

        pctCorretagem2Compra.setText(String.valueOf(custas.getCorretagem()));
        pctCustodia2Compra.setText(String.valueOf(custas.getCustodia()));
        pctLiquidacao2Compra.setText(String.valueOf(custas.getTx_liquidacao()));
        pctNegociacao2Compra.setText(String.valueOf(custas.getTx_negociacao()));
        pctIss2Compra.setText(String.valueOf(custas.getIss()));
        pctEmolumentos2Compra.setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));

        CheckBox cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
        if (custas.isCorretagemFixa()){
            cbCorretagem2Compra.setChecked(true);
        }
        else {
            cbCorretagem2Compra.setChecked(false);
        }

        CheckBox cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
        if (custas.isCustodiaFixa()){
            cbCustodia2Compra.setChecked(true);
        }
        else {
            cbCustodia2Compra.setChecked(false);
        }

        CheckBox cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);

        if (custas.isTx_liquidacaoFixa()){
            cbLiquidacao2Compra.setChecked(true);
        }
        else {
            cbLiquidacao2Compra.setChecked(false);
        }

        CheckBox cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
        if (custas.isTx_negociacaoFixa()){
            cbNegociacao2Compra.setChecked(true);
        }
        else {
            cbNegociacao2Compra.setChecked(false);
        }

        CheckBox cbIss2Compra = findViewById(R.id.cbIss2Compra);
        if (custas.isIssFixo()){
            cbIss2Compra.setChecked(true);
        }
        else {
            cbIss2Compra.setChecked(false);
        }



        RelativeLayout custasExtra = findViewById(R.id.custasExtra2);
        custasExtra.setVisibility(View.GONE);

        RelativeLayout RLcustasExtraCompra = findViewById(R.id.custasExtraCompra);
        RLcustasExtraCompra.setVisibility(View.GONE);*/

        dbhCustas.close();
        //AlertDialogListar();

    }

    /*public Double getValPapelAdquirido() {
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
    }*/


    //*** OK *** Dar seguimento à modificação do cálculo, calculando com base nos dados resgatados do banco de dados.
    //*** OK *** Modificar a classe para utilizar a função da classe Custas.java.
    //TODO continuar reduzindo as chamadas para as mesmas views - verificar a possibilidade de torná-las globais.
    //TODO verificar como otimizar ou modificar as view ocultas dos "Custos de Venda" e "Custos de Compra".
    //TODO continuar as modificações na classe e activity.
    //TODO POSTERGADO - inserir uma view para receber a informação de quantas ações serão vendidas. Afinal, você pode escolher não vender todas de uma vez.


    public void calcular(View view){
        try {
            et_valPapelAdquirido = findViewById(R.id.valPapelAdquirido);
            et_quantidade = findViewById(R.id.quantidade2);
            et_valPretendidoVenda = findViewById(R.id.valPretendidoVenda);
            //double zero = 0.0;
            Custas custas = new Custas();

            try {
                valPapelAdquirido = Double.parseDouble(et_valPapelAdquirido.getText().toString());
                quantidade = Integer.parseInt(et_quantidade.getText().toString());
                valPretendidoVenda = Double.parseDouble(et_valPretendidoVenda.getText().toString());

                if (valPapelAdquirido != 0 && !String.valueOf(valPapelAdquirido).equals("")
                        && quantidade != 0 && !String.valueOf(quantidade).equals("")
                        && valPretendidoVenda != 0 && !String.valueOf(valPretendidoVenda).equals("")) {
                    try {
                        valPapelAdquirido = Double.parseDouble(et_valPapelAdquirido.getText().toString());
                        quantidade = Integer.parseInt(et_quantidade.getText().toString());
                        valPretendidoVenda = Double.parseDouble(et_valPretendidoVenda.getText().toString());

                        corretagem2Compra = findViewById(R.id.corretagem2Compra);
                        custodia2Compra = findViewById(R.id.custodia2Compra);
                        tax_liquidacao2Compra = findViewById(R.id.tax_liquidacao2Compra);
                        tax_negociacao2Compra = findViewById(R.id.tax_negociacao2Compra);
                        iss2Compra = findViewById(R.id.iss2Compra);
                        emolumentos2Compra = findViewById(R.id.emolumentos2Compra);

                        pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                        pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                        pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                        pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                        pctIss2Compra = findViewById(R.id.pctIss2Compra);
                        pctEmolumentos2Compra = findViewById(R.id.pctEmolumentos2Compra);

                        CheckBox cbFracionarioDCVP = findViewById(R.id.cbFracionarioDCVP);
                        cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                        cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                        cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);
                        cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                        cbIss2Compra = findViewById(R.id.cbIss2Compra);


                        Double dbCorretagemCompra = Double.parseDouble(pctCorretagem2Compra.getText().toString());
                        Double dbCustodia2Compra = Double.parseDouble(pctCustodia2Compra.getText().toString());
                        Double dbLiquidacao2Compra = Double.parseDouble(pctLiquidacao2Compra.getText().toString());
                        Double dbNegociacao2Compra = Double.parseDouble(pctNegociacao2Compra.getText().toString());
                        Double dbIss2Compra = Double.parseDouble(pctIss2Compra.getText().toString());




                        Double resultadoCorretagemCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbCorretagemCompra, cbFracionarioDCVP.isChecked(), cbCorretagem2Compra.isChecked());
                        corretagem2Compra.setText(df2.format(resultadoCorretagemCompra));

                        Double resultadoCustodiaCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbCustodia2Compra, cbFracionarioDCVP.isChecked(), cbCustodia2Compra.isChecked());
                        custodia2Compra.setText(df2.format(resultadoCustodiaCompra));

                        Double resultadoLiquidacaoCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbLiquidacao2Compra, cbFracionarioDCVP.isChecked(), cbLiquidacao2Compra.isChecked());
                        tax_liquidacao2Compra.setText(df4.format(resultadoLiquidacaoCompra));

                        Double resultadoNegociacaoCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbNegociacao2Compra, cbFracionarioDCVP.isChecked(), cbNegociacao2Compra.isChecked());
                        tax_negociacao2Compra.setText(df4.format(resultadoNegociacaoCompra));

                        Double resultadoIssCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbIss2Compra, cbFracionarioDCVP.isChecked(), cbIss2Compra.isChecked());
                        iss2Compra.setText(df3.format(resultadoIssCompra));

                        Double pctResultadoEmolumentosCompra = dbLiquidacao2Compra + dbNegociacao2Compra;
                        pctEmolumentos2Compra.setText(df3.format(pctResultadoEmolumentosCompra));
                        Double resultadoEmolumentosCompra = resultadoLiquidacaoCompra + resultadoNegociacaoCompra;
                        emolumentos2Compra.setText(df3.format(resultadoEmolumentosCompra));

                        tv_valCompraDoPapel = findViewById(R.id.valCompraDoPapel2);

                        //Toaster(String.valueOf(resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra));
                        //Toaster(df2.format((valPapelAdquirido * quantidade) + (resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra)));
                        Double valTotalCompraDoPapel2 = (valPapelAdquirido * quantidade) + (resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra);
                        tv_valCompraDoPapel.setText(df2.format(valTotalCompraDoPapel2));



                        tvCorretagem = findViewById(R.id.corretagem2);
                        val_Custodia = findViewById(R.id.custodia2);
                        val_tx_liquidacao = findViewById(R.id.tax_liquidacao2);
                        val_tx_negociacao = findViewById(R.id.tax_negociacao2);
                        val_emolumentos = findViewById(R.id.emolumentos2);
                        pct_Emolumentos = findViewById(R.id.pctEmolumentos2);
                        val_iss = findViewById(R.id.iss2);
                        TextView val_ir2 = findViewById(R.id.txtPctIr2);
                        tv_valVendaDoPapel = findViewById(R.id.valVendaDoPapel2);
                        TV_valMinVendaSemPerdas = findViewById(R.id.valMinVendaSemPerdas);

                        pct_Corretagem = findViewById(R.id.pctCorretagem2);
                        pct_Custodia = findViewById(R.id.pctCustodia2);
                        pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
                        pct_Negociacao = findViewById(R.id.pctNegociacao2);
                        pct_Iss = findViewById(R.id.pctIss2);

                        cbCorretagem2 = findViewById(R.id.cbCorretagem2);
                        cbCustodia2 = findViewById(R.id.cbCustodia2);
                        cbLiquidacao2 = findViewById(R.id.cbLiquidacao2);
                        cbNegociacao2 = findViewById(R.id.cbNegociacao2);
                        cbIss2 = findViewById(R.id.cbIss2);


                        Double dbCorretagem2Venda = Double.parseDouble(String.valueOf(pct_Corretagem.getText()));
                        Double dbCustodia2Venda = Double.parseDouble(String.valueOf(pct_Custodia.getText()));
                        Double dbLiquidacao2Venda = Double.parseDouble(String.valueOf(pct_Liquidacao.getText()));
                        Double dbNegociacao2Venda = Double.parseDouble(String.valueOf(pct_Negociacao.getText()));
                        Double dbIss2Venda = Double.parseDouble(String.valueOf(pct_Iss.getText()));


                        Double tempCorretagemVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbCorretagem2Venda, cbFracionarioDCVP.isChecked(), cbCorretagem2.isChecked());
                        tvCorretagem.setText(df2.format(tempCorretagemVenda));

                        Double tempCustodiaVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbCustodia2Venda, cbFracionarioDCVP.isChecked(), cbCustodia2.isChecked());
                        val_Custodia.setText(df2.format(tempCustodiaVenda));

                        Double tempTxLiquidacaoVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbLiquidacao2Venda, cbFracionarioDCVP.isChecked(), cbLiquidacao2.isChecked());
                        val_tx_liquidacao.setText(df4.format(tempTxLiquidacaoVenda));

                        Double tempTxNegociacaoVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbNegociacao2Venda, cbFracionarioDCVP.isChecked(), cbNegociacao2.isChecked());
                        val_tx_negociacao.setText(df4.format(tempTxNegociacaoVenda));


                        Double resultadoEmolumentosVenda = tempTxLiquidacaoVenda + tempTxNegociacaoVenda;
                        val_emolumentos.setText(df3.format(resultadoEmolumentosVenda));
                        String tempPctEmolumentos = df3.format(dbLiquidacao2Venda + dbNegociacao2Venda);
                        pct_Emolumentos.setText(tempPctEmolumentos);


                        Double tempIssVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbIss2Venda, cbFracionarioDCVP.isChecked(), cbIss2.isChecked());
                        val_iss.setText(df3.format(tempIssVenda));

                    /*Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
                    Double tempIr2Venda = Ir(valPretendidoVenda, quantidade);
                    String str_val_ir2 = df3.format(tempIr2Compra);
                    val_ir2.setText(str_val_ir2);*/

                        double temp_CustasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda;
                        Double valExclusivoVendaDoPapel2 = ((valPretendidoVenda * quantidade) - temp_CustasVenda);
                        tv_valVendaDoPapel.setText(df2.format(valExclusivoVendaDoPapel2));


                        TextView resulCalcVendaLiquido = findViewById(R.id.resultVendaLiquido);
                        double calcVendaLiquido = valExclusivoVendaDoPapel2 - valTotalCompraDoPapel2;
                        try {
                            if (calcVendaLiquido>=0){
                                resulCalcVendaLiquido.setTextColor(ContextCompat.getColor(this, R.color.darkgreen));
                            }
                            else{
                                resulCalcVendaLiquido.setTextColor(ContextCompat.getColor(this, R.color.darkred));
                            }
                        }
                        catch (Exception e){Toaster("Cor não funcionando");}

                        String str_venda_Liquido = df2.format(calcVendaLiquido);
                        resulCalcVendaLiquido.setText(str_venda_Liquido);

                        //TV_valMinVendaSemPerdas.setText(df2.format(valMinVenda(valPapelAdquirido, quantidade)));
                        TV_valMinVendaSemPerdas.setText(df2.format(valMinVendaDII(valPapelAdquirido, quantidade, valTotalCompraDoPapel2, cbFracionarioDCVP, dbCorretagem2Venda, cbCorretagem2, dbCustodia2Venda, cbCustodia2, dbLiquidacao2Venda, cbLiquidacao2, dbNegociacao2Venda, cbNegociacao2, dbIss2Venda, cbIss2)));

                    /*
                    //TODO Excluir try-catch com Toaster após conclusão de testes.
                    TRY-CATCH PARA TESTES
                    try {
                        String str_valMinVenda = df2.format(valMinVenda(valPapelAdquirido, quantidade));
                        Toaster(str_valMinVenda);
                    }
                    catch (Exception e){
                        Toaster("Erro para definir valor mínimo!");
                    }*/

                    }
                    catch (Exception e) {
                        Toaster(e.getMessage());
                        Toaster("Erro ao calcular");
                    }
                }
                else{
                    Toaster("Nada a ser calculado!");
                }
            }
            catch (Exception e){
                e.getMessage();
                et_valPapelAdquirido.setError("Não pode ser nulo ou menor que 0");
                et_quantidade.setError("Não pode ser nulo ou menor que 0");
                et_valPretendidoVenda.setError("Não pode ser nulo ou menor que 0");
                Toaster("Parse indisponível");
            }
                /*try {
                    valPapelAdquirido = Double.parseDouble(et_valPapelAdquirido.getText().toString());
                    quantidade = Integer.parseInt(et_quantidade.getText().toString());
                    valPretendidoVenda = Double.parseDouble(et_valPretendidoVenda.getText().toString());

                    corretagem2Compra = findViewById(R.id.corretagem2Compra);
                    custodia2Compra = findViewById(R.id.custodia2Compra);
                    tax_liquidacao2Compra = findViewById(R.id.tax_liquidacao2Compra);
                    tax_negociacao2Compra = findViewById(R.id.tax_negociacao2Compra);
                    iss2Compra = findViewById(R.id.iss2Compra);
                    emolumentos2Compra = findViewById(R.id.emolumentos2Compra);

                    pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                    pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                    pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                    pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                    pctIss2Compra = findViewById(R.id.pctIss2Compra);
                    pctEmolumentos2Compra = findViewById(R.id.pctEmolumentos2Compra);

                    CheckBox cbFracionarioDCVP = findViewById(R.id.cbFracionarioDCVP);
                    cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                    cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                    cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);
                    cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                    cbIss2Compra = findViewById(R.id.cbIss2Compra);


                    Double dbCorretagemCompra = Double.parseDouble(pctCorretagem2Compra.getText().toString());
                    Double dbCustodia2Compra = Double.parseDouble(pctCustodia2Compra.getText().toString());
                    Double dbLiquidacao2Compra = Double.parseDouble(pctLiquidacao2Compra.getText().toString());
                    Double dbNegociacao2Compra = Double.parseDouble(pctNegociacao2Compra.getText().toString());
                    Double dbIss2Compra = Double.parseDouble(pctIss2Compra.getText().toString());




                    Double resultadoCorretagemCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbCorretagemCompra, cbFracionarioDCVP.isChecked(), cbCorretagem2Compra.isChecked());
                    corretagem2Compra.setText(df2.format(resultadoCorretagemCompra));

                    Double resultadoCustodiaCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbCustodia2Compra, cbFracionarioDCVP.isChecked(), cbCustodia2Compra.isChecked());
                    custodia2Compra.setText(df2.format(resultadoCustodiaCompra));

                    Double resultadoLiquidacaoCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbLiquidacao2Compra, cbFracionarioDCVP.isChecked(), cbLiquidacao2Compra.isChecked());
                    tax_liquidacao2Compra.setText(df4.format(resultadoLiquidacaoCompra));

                    Double resultadoNegociacaoCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbNegociacao2Compra, cbFracionarioDCVP.isChecked(), cbNegociacao2Compra.isChecked());
                    tax_negociacao2Compra.setText(df4.format(resultadoNegociacaoCompra));

                    Double resultadoIssCompra = custas.calc_Corretagem(valPapelAdquirido, quantidade, dbIss2Compra, cbFracionarioDCVP.isChecked(), cbIss2Compra.isChecked());
                    iss2Compra.setText(df3.format(resultadoIssCompra));

                    Double pctResultadoEmolumentosCompra = dbLiquidacao2Compra + dbNegociacao2Compra;
                    pctEmolumentos2Compra.setText(df3.format(pctResultadoEmolumentosCompra));
                    Double resultadoEmolumentosCompra = resultadoLiquidacaoCompra + resultadoNegociacaoCompra;
                    emolumentos2Compra.setText(df3.format(resultadoEmolumentosCompra));

                    tv_valCompraDoPapel = findViewById(R.id.valCompraDoPapel2);

                    //Toaster(String.valueOf(resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra));
                    //Toaster(df2.format((valPapelAdquirido * quantidade) + (resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra)));
                    Double valTotalCompraDoPapel2 = (valPapelAdquirido * quantidade) + (resultadoCorretagemCompra + resultadoCustodiaCompra + resultadoLiquidacaoCompra + resultadoNegociacaoCompra + resultadoIssCompra);
                    tv_valCompraDoPapel.setText(df2.format(valTotalCompraDoPapel2));



                    tvCorretagem = findViewById(R.id.corretagem2);
                    val_Custodia = findViewById(R.id.custodia2);
                    val_tx_liquidacao = findViewById(R.id.tax_liquidacao2);
                    val_tx_negociacao = findViewById(R.id.tax_negociacao2);
                    val_emolumentos = findViewById(R.id.emolumentos2);
                    pct_Emolumentos = findViewById(R.id.pctEmolumentos2);
                    val_iss = findViewById(R.id.iss2);
                    TextView val_ir2 = findViewById(R.id.txtPctIr2);
                    tv_valVendaDoPapel = findViewById(R.id.valVendaDoPapel2);
                    TV_valMinVendaSemPerdas = findViewById(R.id.valMinVendaSemPerdas);

                    pct_Corretagem = findViewById(R.id.pctCorretagem2);
                    pct_Custodia = findViewById(R.id.pctCustodia2);
                    pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
                    pct_Negociacao = findViewById(R.id.pctNegociacao2);
                    pct_Iss = findViewById(R.id.pctIss2);

                    cbCorretagem2 = findViewById(R.id.cbCorretagem2);
                    cbCustodia2 = findViewById(R.id.cbCustodia2);
                    cbLiquidacao2 = findViewById(R.id.cbLiquidacao2);
                    cbNegociacao2 = findViewById(R.id.cbNegociacao2);
                    cbIss2 = findViewById(R.id.cbIss2);


                    Double dbCorretagem2Venda = Double.parseDouble(String.valueOf(pct_Corretagem.getText()));
                    Double dbCustodia2Venda = Double.parseDouble(String.valueOf(pct_Custodia.getText()));
                    Double dbLiquidacao2Venda = Double.parseDouble(String.valueOf(pct_Liquidacao.getText()));
                    Double dbNegociacao2Venda = Double.parseDouble(String.valueOf(pct_Negociacao.getText()));
                    Double dbIss2Venda = Double.parseDouble(String.valueOf(pct_Iss.getText()));


                    Double tempCorretagemVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbCorretagem2Venda, cbFracionarioDCVP.isChecked(), cbCorretagem2.isChecked());
                    tvCorretagem.setText(df2.format(tempCorretagemVenda));

                    Double tempCustodiaVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbCustodia2Venda, cbFracionarioDCVP.isChecked(), cbCustodia2.isChecked());
                    val_Custodia.setText(df2.format(tempCustodiaVenda));

                    Double tempTxLiquidacaoVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbLiquidacao2Venda, cbFracionarioDCVP.isChecked(), cbLiquidacao2.isChecked());
                    val_tx_liquidacao.setText(df4.format(tempTxLiquidacaoVenda));

                    Double tempTxNegociacaoVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbNegociacao2Venda, cbFracionarioDCVP.isChecked(), cbNegociacao2.isChecked());
                    val_tx_negociacao.setText(df4.format(tempTxNegociacaoVenda));


                    Double resultadoEmolumentosVenda = tempTxLiquidacaoVenda + tempTxNegociacaoVenda;
                    val_emolumentos.setText(df3.format(resultadoEmolumentosVenda));
                    String tempPctEmolumentos = df3.format(dbLiquidacao2Venda + dbNegociacao2Venda);
                    pct_Emolumentos.setText(tempPctEmolumentos);


                    Double tempIssVenda = custas.calc_Corretagem(valPretendidoVenda, quantidade, dbIss2Venda, cbFracionarioDCVP.isChecked(), cbIss2.isChecked());
                    val_iss.setText(df3.format(tempIssVenda));

                    *//*Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
                    Double tempIr2Venda = Ir(valPretendidoVenda, quantidade);
                    String str_val_ir2 = df3.format(tempIr2Compra);
                    val_ir2.setText(str_val_ir2);*//*

                    double temp_CustasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda;
                    Double valExclusivoVendaDoPapel2 = ((valPretendidoVenda * quantidade) - temp_CustasVenda);
                    tv_valVendaDoPapel.setText(df2.format(valExclusivoVendaDoPapel2));


                    TextView resulCalcVendaLiquido = findViewById(R.id.resultVendaLiquido);
                    double calcVendaLiquido = valExclusivoVendaDoPapel2 - valTotalCompraDoPapel2;
                    try {
                        if (calcVendaLiquido>=0){
                            resulCalcVendaLiquido.setTextColor(ContextCompat.getColor(this, R.color.darkgreen));
                        }
                        else{
                            resulCalcVendaLiquido.setTextColor(ContextCompat.getColor(this, R.color.darkred));
                        }
                    }
                    catch (Exception e){Toaster("Cor não funcionando");}

                    String str_venda_Liquido = df2.format(calcVendaLiquido);
                    resulCalcVendaLiquido.setText(str_venda_Liquido);

                    //TV_valMinVendaSemPerdas.setText(df2.format(valMinVenda(valPapelAdquirido, quantidade)));
                    TV_valMinVendaSemPerdas.setText(df2.format(valMinVendaDII(valPapelAdquirido, quantidade, valTotalCompraDoPapel2, cbFracionarioDCVP, dbCorretagem2Venda, cbCorretagem2, dbCustodia2Venda, cbCustodia2, dbLiquidacao2Venda, cbLiquidacao2, dbNegociacao2Venda, cbNegociacao2, dbIss2Venda, cbIss2)));

                    *//*
                    //TODO Excluir try-catch com Toaster após conclusão de testes.
                    TRY-CATCH PARA TESTES
                    try {
                        String str_valMinVenda = df2.format(valMinVenda(valPapelAdquirido, quantidade));
                        Toaster(str_valMinVenda);
                    }
                    catch (Exception e){
                        Toaster("Erro para definir valor mínimo!");
                    }*//*

                }
                catch (Exception e) {
                    Toaster(e.getMessage());
                    Toaster("Erro ao calcular");
                }*/
        }
        catch (Exception e){
            Toaster("EditTexts vazios");
        }
    }

    //TODO Verificar a possibilidade de ativar o cálculo das funções a medida que os checkbuttons são ativados - onClickListener();

    public Double Corretagem(Double valPapelCalcular, Integer quantidade){

        pct_Corretagem = findViewById(R.id.pctCorretagem2);

        if(valPapelCalcular != null && quantidade != null && quantidade >=1){

            Double tempPct_Corretagem = Double.valueOf(String.valueOf(pct_Corretagem.getText()));
            String stringtempPct_Corretagem = String.valueOf(pct_Corretagem.getText());

            if(!corretagemFixa){
                if (stringtempPct_Corretagem != null  && !(stringtempPct_Corretagem.equals("")) && tempPct_Corretagem != 0.0){
                    calc_Corretagem = Porcentagem((valPapelCalcular * quantidade), tempPct_Corretagem);
                    return calc_Corretagem;
                }
                else {
                    calc_Corretagem = 0.0;
                    return calc_Corretagem;
                }
            }
            else {
                if (stringtempPct_Corretagem != null  && !(stringtempPct_Corretagem.equals("")) && tempPct_Corretagem != 0.0){
                    calc_Corretagem = tempPct_Corretagem;
                    return calc_Corretagem;
                }
                else {
                    calc_Corretagem = 0.0;
                    return calc_Corretagem;
                }
            }
        }
        return calc_Corretagem;
    }

    public Double Custodia(Double valPapelCalcular, Integer quantidade){

        EditText pct_Custodia = findViewById(R.id.pctCustodia2);

        if(valPapelCalcular != null && quantidade != null && quantidade >=1) {
            Double tempPct_Custodia = Double.valueOf(String.valueOf(pct_Custodia.getText()));
            String stringtempPct_Custodia = String.valueOf(pct_Custodia.getText());

            if(!custodiaFixa){
                if (stringtempPct_Custodia != null  && !(stringtempPct_Custodia.equals("")) && tempPct_Custodia != 0.0){
                    calc_Custodia = Porcentagem((valPapelCalcular * quantidade), tempPct_Custodia);
                    return calc_Custodia;
                }
                else {
                    calc_Custodia = 0.0;
                    return calc_Custodia;
                }
            }
            else {
                if (stringtempPct_Custodia != null  && !(stringtempPct_Custodia.equals("")) && tempPct_Custodia != 0.0){
                    calc_Custodia = tempPct_Custodia;
                    return calc_Custodia;
                }
                else {
                    calc_Custodia = 0.0;
                    return calc_Custodia;
                }
            }
        }
        return calc_Custodia;
    }

    public Double Liquidacao(Double valPapelCalcular, Integer quantidade){

        EditText pct_tx_Liquidacao = findViewById(R.id.pctLiquidacao2);

        if(valPapelCalcular != null && quantidade != null && quantidade >=1) {
            Double tempPct_Tx_Liquidacao = Double.valueOf(String.valueOf(pct_tx_Liquidacao.getText()));
            String stringtempPct_Tx_Liquidacao = String.valueOf(pct_tx_Liquidacao.getText());

            if(!tx_liquidacaoFixa){
                if (stringtempPct_Tx_Liquidacao != null  && !(stringtempPct_Tx_Liquidacao.equals("")) && tempPct_Tx_Liquidacao != 0.0){
                    calc_tx_liquidacao = Porcentagem((valPapelCalcular * quantidade), tempPct_Tx_Liquidacao);
                    return calc_tx_liquidacao;
                }
                else {
                    calc_tx_liquidacao = 0.0;
                    return calc_tx_liquidacao;
                }
            }
            else {
                if (stringtempPct_Tx_Liquidacao != null  && !(stringtempPct_Tx_Liquidacao.equals("")) && tempPct_Tx_Liquidacao != 0.0){
                    calc_tx_liquidacao = tempPct_Tx_Liquidacao;
                    return calc_tx_liquidacao;
                }
                else {
                    calc_tx_liquidacao = 0.0;
                    return calc_tx_liquidacao;
                }
            }
        }
        return calc_tx_liquidacao;
    }

    public Double Negociacao(Double valPapelCalcular, Integer quantidade){

        EditText pct_tx_Negociacao = findViewById(R.id.pctNegociacao2);

        if(valPapelCalcular != null && quantidade != null && quantidade >=1) {
            Double tempPct_Tx_Negociacao = Double.valueOf(String.valueOf(pct_tx_Negociacao.getText()));
            String stringtempPct_Tx_Negociacao = String.valueOf(pct_tx_Negociacao.getText());

            if(!tx_negociacaoFixa){
                if (stringtempPct_Tx_Negociacao != null  && !(stringtempPct_Tx_Negociacao.equals("")) && tempPct_Tx_Negociacao != 0.0){
                    calc_tx_negociacao = Porcentagem((valPapelCalcular * quantidade), tempPct_Tx_Negociacao);
                    return calc_tx_negociacao;
                }
                else {
                    calc_tx_negociacao = 0.0;
                    return calc_tx_negociacao;
                }
            }
            else {
                if (stringtempPct_Tx_Negociacao != null  && !(stringtempPct_Tx_Negociacao.equals("")) && tempPct_Tx_Negociacao != 0.0){
                    calc_tx_negociacao = tempPct_Tx_Negociacao;
                    return calc_tx_negociacao;
                }
                else {
                    calc_tx_negociacao = 0.0;
                    return calc_tx_negociacao;
                }
            }
        }
        return calc_tx_negociacao;
    }

    public Double Iss(Double valPapelCalcular, Integer quantidade){

        EditText pct_Iss = findViewById(R.id.pctIss2);

        if(valPapelCalcular != null && quantidade != null && quantidade >=1) {
            Double tempPct_Iss = Double.valueOf(String.valueOf(pct_Iss.getText()));
            String stringtempPct_Iss = String.valueOf(pct_Iss.getText());

            if (issCobrado){
                if(!issFixo){
                    if (stringtempPct_Iss != null  && !(stringtempPct_Iss.equals("")) && tempPct_Iss != 0.0){
                        calc_iss = Porcentagem((valPapelCalcular * quantidade), tempPct_Iss);
                        return calc_iss;
                    }
                    else {
                        calc_iss = 0.0;
                        return calc_iss;
                    }
                }
                else {
                    if (stringtempPct_Iss != null  && !(stringtempPct_Iss.equals("")) && tempPct_Iss != 0.0){
                        calc_iss = tempPct_Iss;
                        return calc_iss;
                    }
                    else {
                        calc_iss = 0.0;
                        return calc_iss;
                    }
                }
            }
            else {
                calc_iss = 0.0;
                return calc_iss;
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


    public double calc_Venda_Liquido(double valPapelAdquirido, int quantidade, double valPretendidoVenda){

        Double tempCorretagemCompra = Corretagem(valPapelAdquirido, quantidade);
        Double tempCorretagemVenda = Corretagem(valPretendidoVenda, quantidade);

        Double tempCustodiaCompra = Custodia(valPapelAdquirido, quantidade);
        Double tempCustodiaVenda = Custodia(valPretendidoVenda, quantidade);

        Double tempTxLiquidacaoCompra = Liquidacao(valPapelAdquirido, quantidade);
        Double tempTxLiquidacaoVenda = Liquidacao(valPretendidoVenda, quantidade);

        Double tempTxNegociacaoCompra = Negociacao(valPapelAdquirido, quantidade);
        Double tempTxNegociacaoVenda = Negociacao(valPretendidoVenda, quantidade);

        Double tempIssCompra = Iss(valPapelAdquirido, quantidade);
        Double tempIssVenda = Iss(valPretendidoVenda, quantidade);

        Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
        Double tempIr2Venda = Ir(valPretendidoVenda, quantidade);


        double custasCompra = tempCorretagemCompra + tempCustodiaCompra + tempTxLiquidacaoCompra + tempTxNegociacaoCompra + tempIssCompra + tempIr2Compra;
        double custasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda + tempIr2Venda;
        Double totalCustas = (valPapelAdquirido * quantidade) + custasCompra + custasVenda;


        //TODO Excluir views adicionadas dinamicamente para teste
        /*VIEWS ADICIONADAS DINAMICAMENTE PARA TESTE
        LinearLayout LL_SaldoVendaLiquido = findViewById(R.id.LLSaldoVendaLiquido);

        TextView TV_LLSaldoVendaLiquido = new TextView(this);
        TextView TV_custasCompra = new TextView(this);
        TextView TV_custasVenda = new TextView(this);
        TextView TV_LLCompraComCustas = new TextView(this);

        TV_LLSaldoVendaLiquido.setText(String.valueOf((valPapelAdquirido*quantidade)+custasCompra));
        TV_custasCompra.setText(String.valueOf(custasCompra));
        TV_custasVenda.setText(String.valueOf(custasVenda));
        TV_LLCompraComCustas.setText(String.valueOf((valPretendidoVenda*quantidade)-custasVenda));

        LL_SaldoVendaLiquido.addView(TV_LLSaldoVendaLiquido);
        LL_SaldoVendaLiquido.addView(TV_custasCompra);
        LL_SaldoVendaLiquido.addView(TV_custasVenda);
        LL_SaldoVendaLiquido.addView(TV_LLCompraComCustas);*/

        return (valPretendidoVenda * quantidade) - totalCustas;
    }

    public double valMinVenda(double valPapelAdquirido, int quantidade){

        double temp_valMinVenda = valPapelAdquirido+0.01;

        Double tempCorretagemCompra = Corretagem(valPapelAdquirido, quantidade);
        Double tempCorretagemVenda = Corretagem(temp_valMinVenda, quantidade);

        Double tempCustodiaCompra = Custodia(valPapelAdquirido, quantidade);
        Double tempCustodiaVenda = Custodia(temp_valMinVenda, quantidade);

        Double tempTxLiquidacaoCompra = Liquidacao(valPapelAdquirido, quantidade);
        Double tempTxLiquidacaoVenda = Liquidacao(temp_valMinVenda, quantidade);

        Double tempTxNegociacaoCompra = Negociacao(valPapelAdquirido, quantidade);
        Double tempTxNegociacaoVenda = Negociacao(temp_valMinVenda, quantidade);

        Double tempIssCompra = Iss(valPapelAdquirido, quantidade);
        Double tempIssVenda = Iss(temp_valMinVenda, quantidade);

        Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
        Double tempIr2Venda = Ir(temp_valMinVenda, quantidade);

        double temp_custasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda + tempIr2Venda;
        double custasCompra = tempCorretagemCompra + tempCustodiaCompra + tempTxLiquidacaoCompra + tempTxNegociacaoCompra + tempIssCompra + tempIr2Compra;

        double resul_vendaLiquido = ((temp_valMinVenda * quantidade)-temp_custasVenda)-((valPapelAdquirido * quantidade)+custasCompra);

        while (resul_vendaLiquido<0){
            temp_valMinVenda +=0.01;
            resul_vendaLiquido = ((temp_valMinVenda * quantidade)-temp_custasVenda)-((valPapelAdquirido * quantidade)+custasCompra);
        }
        return temp_valMinVenda;
    }

    public double valMinVenda(double valPapelAdquirido, int quantidade, Double calc_Corretagem, Double calc_Custodia, Double calc_tx_liquidacao, Double calc_tx_negociacao, Double calc_iss){

        double temp_valMinVenda = valPapelAdquirido+0.01;

        Double tempCorretagemCompra = calc_Corretagem;
        Double tempCorretagemVenda = Corretagem(temp_valMinVenda, quantidade);

        Double tempCustodiaCompra = calc_Custodia;
        Double tempCustodiaVenda = Custodia(temp_valMinVenda, quantidade);

        Double tempTxLiquidacaoCompra = calc_tx_liquidacao;
        Double tempTxLiquidacaoVenda = Liquidacao(temp_valMinVenda, quantidade);

        Double tempTxNegociacaoCompra = calc_tx_negociacao;
        Double tempTxNegociacaoVenda = Negociacao(temp_valMinVenda, quantidade);

        Double tempIssCompra = calc_iss;
        Double tempIssVenda = Iss(temp_valMinVenda, quantidade);

        /*Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
        Double tempIr2Venda = Ir(temp_valMinVenda, quantidade);*/

        double temp_custasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda;
        double custasCompra = tempCorretagemCompra + tempCustodiaCompra + tempTxLiquidacaoCompra + tempTxNegociacaoCompra + tempIssCompra;

        double resul_vendaLiquido = ((temp_valMinVenda * quantidade)-temp_custasVenda)-((valPapelAdquirido * quantidade)+custasCompra);

        while (resul_vendaLiquido<0){
            temp_valMinVenda +=0.01;
            resul_vendaLiquido = ((temp_valMinVenda * quantidade)-temp_custasVenda)-((valPapelAdquirido * quantidade)+custasCompra);
        }
        return temp_valMinVenda;
    }

    public double valMinVendaDII(Double valPapelAdquirido, int quantidadeCompra, Double valTotalCompraDoPapel2, CheckBox cbFracionarioDCVP, Double dbCorretagem2Venda, CheckBox cbCorretagem2, Double dbCustodia2Venda, CheckBox cbCustodia2, Double dbLiquidacao2Venda, CheckBox cbLiquidacao2, Double dbNegociacao2Venda, CheckBox cbNegociacao2, Double dbIss2Venda, CheckBox cbIss2){

        Custas custas = new Custas();
        double temp_valMinVenda = valPapelAdquirido+0.01;

        Double tempCorretagemVenda = custas.calc_Corretagem(temp_valMinVenda, quantidadeCompra, dbCorretagem2Venda, cbFracionarioDCVP.isChecked(), cbCorretagem2.isChecked());
        Double tempCustodiaVenda = custas.calc_Corretagem(temp_valMinVenda, quantidadeCompra, dbCustodia2Venda, cbFracionarioDCVP.isChecked(), cbCustodia2.isChecked());
        Double tempTxLiquidacaoVenda = custas.calc_Corretagem(temp_valMinVenda, quantidadeCompra, dbLiquidacao2Venda, cbFracionarioDCVP.isChecked(), cbLiquidacao2.isChecked());
        Double tempTxNegociacaoVenda = custas.calc_Corretagem(temp_valMinVenda, quantidadeCompra, dbNegociacao2Venda, cbFracionarioDCVP.isChecked(), cbNegociacao2.isChecked());
        Double tempIssVenda = custas.calc_Corretagem(temp_valMinVenda, quantidadeCompra, dbIss2Venda, cbFracionarioDCVP.isChecked(), cbIss2.isChecked());

        double temp_CustasVenda = tempCorretagemVenda + tempCustodiaVenda + tempTxLiquidacaoVenda + tempTxNegociacaoVenda + tempIssVenda;
        Double valExclusivoVendaDoPapel2 = ((temp_valMinVenda * quantidadeCompra) - temp_CustasVenda);


        double resul_vendaLiquido = (valExclusivoVendaDoPapel2 - valTotalCompraDoPapel2);

        while (resul_vendaLiquido<0){
            temp_valMinVenda +=0.01;
            valExclusivoVendaDoPapel2 = ((temp_valMinVenda * quantidadeCompra) - temp_CustasVenda);
            resul_vendaLiquido = (valExclusivoVendaDoPapel2 - valTotalCompraDoPapel2);
        }
        return temp_valMinVenda;
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

    public void AlertDialogListar(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);
        long contador = dbh.contador();
        int contadorInt = (int)contador;
        //TODO Verificar a possibilidade de fazer >>> int contadorInt = (int)dbh.contador();

        CharSequence[] arrayPapeis = new CharSequence[contadorInt];

        try {
            for(int i = 1; i<=contadorInt; i++){
                Papel papel;
                papel = dbh.getPapel(i);
                String tempNomePapel = String.valueOf(papel.getNomePapel());
                if (!(tempNomePapel.equals(""))){
                    arrayPapeis[i-1] = String.valueOf(papel.getNomePapel());
                }
                else{
                    arrayPapeis[i-1] = "Empty";
                }
            }
        }
        catch (Exception e){
            Toaster("Exception AlertDialogListar" + e.getMessage());
        }

        AlertDialog.Builder listaPapeisCadastrados = new AlertDialog.Builder(this);
        listaPapeisCadastrados.setTitle("Selecione o papel:");

        LinearLayout ll_dinamico = new LinearLayout(this);
        ll_dinamico.setOrientation(LinearLayout.HORIZONTAL);

        TextView novaTv = new TextView(this);
        novaTv.setPadding(25,5,5,5);
        novaTv.setText("Mais um teste");

        listaPapeisCadastrados.setItems(arrayPapeis, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                et_valPapelAdquirido = findViewById(R.id.valPapelAdquirido);
                et_quantidade = findViewById(R.id.quantidade2);
                DataBaseHelper dbhCustas = new DataBaseHelper(ValorMinParaVendaSemPerdas.this);

                Papel papel;
                papel = dbh.getPapel(which+1);
                et_valPapelAdquirido.setText(String.valueOf(papel.getValor()));
                et_quantidade.setText(String.valueOf(papel.getQuantidade()));
                TextView dcvpTxtNomePapel = findViewById(R.id.dcvpTxtNomePapel);
                dcvpTxtNomePapel.setText(papel.getNomePapel());
                Toaster(papel.getNomePapel());
                RelativeLayout RLcustasExtraCompra = findViewById(R.id.custasExtraCompra);
                RLcustasExtraCompra.setVisibility(View.VISIBLE);

                //TODO Tentar colocar o código abaixo em um novo Thread -
                // ou criar uma função que recebe uma instância da classe Custas e faz a distribuição nas Views

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Custas custas;
                                    //custas = dbh.getCustas(which+1);
                                    int selected = which+1;
                                    Toaster(String.valueOf(selected));
                                    Custas custas = new Custas(selected, dbhCustas.getCustas(selected).getCorretagem(), dbhCustas.getCustas(selected).getCustodia(), dbhCustas.getCustas(selected).getTx_liquidacao(), dbhCustas.getCustas(selected).getTx_negociacao(), dbhCustas.getCustas(selected).getIss(), dbhCustas.getCustas(selected).isCorretagemFixa(), dbhCustas.getCustas(selected).isCustodiaFixa(), dbhCustas.getCustas(selected).isTx_liquidacaoFixa(), dbhCustas.getCustas(selected).isTx_negociacaoFixa(), dbhCustas.getCustas(selected).isIssFixo());
                                    Toaster(String.valueOf(custas.getCorretagem()));

                                    CheckBox cbFracionarioDCVP = findViewById(R.id.cbFracionarioDCVP);
                                    if (papel.isFracionario()){
                                        cbFracionarioDCVP.setChecked(true);
                                    }
                                    else {
                                        cbFracionarioDCVP.setChecked(false);
                                    }


                                    Double calculoCorretagemCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getCorretagem(), papel.isFracionario(), custas.isCorretagemFixa());
                                    Toaster(calculoCorretagemCompra.toString());
                                    corretagem2Compra = findViewById(R.id.corretagem2Compra);
                                    corretagem2Compra.setText(df2.format(calculoCorretagemCompra));
                                    pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                                    pctCorretagem2Compra.setText(String.valueOf(custas.getCorretagem()));
                                    pctCorretagem2Compra.setEnabled(false);
                                    cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                                    if (custas.isCorretagemFixa()){
                                        cbCorretagem2Compra.setChecked(true);
                                    }
                                    else {
                                        cbCorretagem2Compra.setChecked(false);
                                    }
                                    cbCorretagem2Compra.setEnabled(false);


                                    Double calculoCustodiaCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getCustodia(), papel.isFracionario(), custas.isCustodiaFixa());
                                    //Toaster(calculoCustodiaCompra.toString());
                                    custodia2Compra = findViewById(R.id.custodia2Compra);
                                    custodia2Compra.setText(df2.format(calculoCustodiaCompra));
                                    pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                                    pctCustodia2Compra.setText(String.valueOf(custas.getCustodia()));
                                    pctCustodia2Compra.setEnabled(false);
                                    cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                                    if (custas.isCustodiaFixa()){
                                        cbCustodia2Compra.setChecked(true);
                                    }
                                    else {
                                        cbCustodia2Compra.setChecked(false);
                                    }
                                    cbCustodia2Compra.setEnabled(false);


                                    Double calculoLiquidacaoCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getTx_liquidacao(), papel.isFracionario(), custas.isTx_liquidacaoFixa());
                                    //Toaster(calculoLiquidacaoCompra.toString());
                                    tax_liquidacao2Compra = findViewById(R.id.tax_liquidacao2Compra);
                                    tax_liquidacao2Compra.setText(df2.format(calculoLiquidacaoCompra));
                                    pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                                    pctLiquidacao2Compra.setText(String.valueOf(custas.getTx_liquidacao()));
                                    pctLiquidacao2Compra.setEnabled(false);
                                    cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);
                                    if (custas.isTx_liquidacaoFixa()){
                                        cbLiquidacao2Compra.setChecked(true);
                                    }
                                    else {
                                        cbLiquidacao2Compra.setChecked(false);
                                    }
                                    cbLiquidacao2Compra.setEnabled(false);


                                    Double calculoNegociacaoCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getTx_negociacao(), papel.isFracionario(), custas.isTx_negociacaoFixa());
                                    //Toaster(calculoNegociacaoCompra.toString());
                                    tax_negociacao2Compra = findViewById(R.id.tax_negociacao2Compra);
                                    tax_negociacao2Compra.setText(df2.format(calculoNegociacaoCompra));
                                    pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                                    pctNegociacao2Compra.setText(String.valueOf(custas.getTx_negociacao()));
                                    pctNegociacao2Compra.setEnabled(false);
                                    cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                                    if (custas.isTx_negociacaoFixa()){
                                        cbNegociacao2Compra.setChecked(true);
                                    }
                                    else {
                                        cbNegociacao2Compra.setChecked(false);
                                    }
                                    cbNegociacao2Compra.setEnabled(false);


                                    Double calculoIssCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getIss(), papel.isFracionario(), custas.isIssFixo());
                                    //Toaster(calculoIssCompra.toString());
                                    iss2Compra = findViewById(R.id.iss2Compra);
                                    iss2Compra.setText(df2.format(calculoIssCompra));
                                    pctIss2Compra = findViewById(R.id.pctIss2Compra);
                                    pctIss2Compra.setText(String.valueOf(custas.getIss()));
                                    pctIss2Compra.setEnabled(false);
                                    cbIss2Compra = findViewById(R.id.cbIss2Compra);
                                    if (custas.isIssFixo()){
                                        cbIss2Compra.setChecked(true);
                                    }
                                    else {
                                        cbIss2Compra.setChecked(false);
                                    }
                                    cbIss2Compra.setEnabled(false);

                                    et_valPapelAdquirido.setEnabled(false);
                                    et_quantidade.setEnabled(false);
                                    cbFracionarioDCVP.setEnabled(false);

                                    tv_valCompraDoPapel = findViewById(R.id.valCompraDoPapel2);
                                    tv_valCompraDoPapel.setText(df2.format((papel.getValor() * papel.getQuantidade()) + (calculoCorretagemCompra + calculoCustodiaCompra + calculoLiquidacaoCompra + calculoNegociacaoCompra + calculoIssCompra)));

                                    TV_valMinVendaSemPerdas = findViewById(R.id.valMinVendaSemPerdas);
                                    TV_valMinVendaSemPerdas.setText(df2.format(valMinVenda(papel.getValor(), papel.getQuantidade(), calculoCorretagemCompra, calculoCustodiaCompra, calculoLiquidacaoCompra, calculoNegociacaoCompra, calculoIssCompra)));
                                    //TV_valMinVendaSemPerdas.setText(df2.format(valMinVendaDII(valPapelAdquirido, quantidade, valTotalCompraDoPapel2, cbFracionarioDCVP, dbCorretagem2Venda, cbCorretagem2, dbCustodia2Venda, cbCustodia2, dbLiquidacao2Venda, cbLiquidacao2, dbNegociacao2Venda, cbNegociacao2, dbIss2Venda, cbIss2)));
                                    dbh.close();
                                    dbhCustas.close();
                                }
                            });
                        }
                        catch (Exception e){
                            e.getMessage();
                            e.printStackTrace();
                        }
                    }
                }.start();


                /*//Custas custas;
                //custas = dbh.getCustas(which+1);
                int selected = which+1;
                Custas custas = new Custas(selected, dbhCustas.getCustas(selected).getCorretagem(), dbhCustas.getCustas(selected).getCustodia(), dbhCustas.getCustas(selected).getTx_liquidacao(), dbhCustas.getCustas(selected).getTx_negociacao(), dbhCustas.getCustas(selected).getIss(), dbhCustas.getCustas(selected).isCorretagemFixa(), dbhCustas.getCustas(selected).isCustodiaFixa(), dbhCustas.getCustas(selected).isTx_liquidacaoFixa(), dbhCustas.getCustas(selected).isTx_negociacaoFixa(), dbhCustas.getCustas(selected).isIssFixo());

                CheckBox cbFracionarioDCVP = findViewById(R.id.cbFracionarioDCVP);
                if (papel.isFracionario()){
                    cbFracionarioDCVP.setChecked(true);
                }
                else {
                    cbFracionarioDCVP.setChecked(false);
                }


                Double calculoCorretagemCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getCorretagem(), papel.isFracionario(), custas.isCorretagemFixa());
                //Toaster(calculoCorretagemCompra.toString());
                TextView corretagem2Compra = findViewById(R.id.corretagem2Compra);
                corretagem2Compra.setText(df2.format(calculoCorretagemCompra));
                EditText pctCorretagem2Compra = findViewById(R.id.pctCorretagem2Compra);
                pctCorretagem2Compra.setText(String.valueOf(custas.getCorretagem()));
                pctCorretagem2Compra.setEnabled(false);
                CheckBox cbCorretagem2Compra = findViewById(R.id.cbCorretagem2Compra);
                if (custas.isCorretagemFixa()){
                    cbCorretagem2Compra.setChecked(true);
                }
                else {
                    cbCorretagem2Compra.setChecked(false);
                }
                cbCorretagem2Compra.setEnabled(false);


                Double calculoCustodiaCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getCustodia(), papel.isFracionario(), custas.isCustodiaFixa());
                //Toaster(calculoCustodiaCompra.toString());
                TextView custodia2Compra = findViewById(R.id.custodia2Compra);
                custodia2Compra.setText(df2.format(calculoCustodiaCompra));
                EditText pctCustodia2Compra = findViewById(R.id.pctCustodia2Compra);
                pctCustodia2Compra.setText(String.valueOf(custas.getCustodia()));
                pctCustodia2Compra.setEnabled(false);
                CheckBox cbCustodia2Compra = findViewById(R.id.cbCustodia2Compra);
                if (custas.isCustodiaFixa()){
                    cbCustodia2Compra.setChecked(true);
                }
                else {
                    cbCustodia2Compra.setChecked(false);
                }
                cbCustodia2Compra.setEnabled(false);


                Double calculoLiquidacaoCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getTx_liquidacao(), papel.isFracionario(), custas.isTx_liquidacaoFixa());
                //Toaster(calculoLiquidacaoCompra.toString());
                TextView tax_liquidacao2Compra = findViewById(R.id.tax_liquidacao2Compra);
                tax_liquidacao2Compra.setText(df2.format(calculoLiquidacaoCompra));
                EditText pctLiquidacao2Compra = findViewById(R.id.pctLiquidacao2Compra);
                pctLiquidacao2Compra.setText(String.valueOf(custas.getTx_liquidacao()));
                pctLiquidacao2Compra.setEnabled(false);
                CheckBox cbLiquidacao2Compra = findViewById(R.id.cbLiquidacao2Compra);
                if (custas.isTx_liquidacaoFixa()){
                    cbLiquidacao2Compra.setChecked(true);
                }
                else {
                    cbLiquidacao2Compra.setChecked(false);
                }
                cbLiquidacao2Compra.setEnabled(false);


                Double calculoNegociacaoCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getTx_negociacao(), papel.isFracionario(), custas.isTx_negociacaoFixa());
                //Toaster(calculoNegociacaoCompra.toString());
                TextView tax_negociacao2Compra = findViewById(R.id.tax_negociacao2Compra);
                tax_negociacao2Compra.setText(df2.format(calculoNegociacaoCompra));
                EditText pctNegociacao2Compra = findViewById(R.id.pctNegociacao2Compra);
                pctNegociacao2Compra.setText(String.valueOf(custas.getTx_negociacao()));
                pctNegociacao2Compra.setEnabled(false);
                CheckBox cbNegociacao2Compra = findViewById(R.id.cbNegociacao2Compra);
                if (custas.isTx_negociacaoFixa()){
                    cbNegociacao2Compra.setChecked(true);
                }
                else {
                    cbNegociacao2Compra.setChecked(false);
                }
                cbNegociacao2Compra.setEnabled(false);


                Double calculoIssCompra = custas.calc_Corretagem(papel.getValor(), papel.getQuantidade(), custas.getIss(), papel.isFracionario(), custas.isIssFixo());
                //Toaster(calculoIssCompra.toString());
                TextView iss2Compra = findViewById(R.id.iss2Compra);
                iss2Compra.setText(df2.format(calculoIssCompra));
                EditText pctIss2Compra = findViewById(R.id.pctIss2Compra);
                pctIss2Compra.setText(String.valueOf(custas.getIss()));
                pctIss2Compra.setEnabled(false);
                CheckBox cbIss2Compra = findViewById(R.id.cbIss2Compra);
                if (custas.isIssFixo()){
                    cbIss2Compra.setChecked(true);
                }
                else {
                    cbIss2Compra.setChecked(false);
                }
                cbIss2Compra.setEnabled(false);

                et_valPapelAdquirido.setEnabled(false);
                et_quantidade.setEnabled(false);
                cbFracionarioDCVP.setEnabled(false);

                TextView tv_valCompraDoPapel = (TextView) findViewById(R.id.valCompraDoPapel2);
                tv_valCompraDoPapel.setText(df2.format((papel.getValor() * papel.getQuantidade()) + (calculoCorretagemCompra + calculoCustodiaCompra + calculoLiquidacaoCompra + calculoNegociacaoCompra + calculoIssCompra)));

                TextView TV_valMinVendaSemPerdas = (TextView) findViewById(R.id.valMinVendaSemPerdas);
                TV_valMinVendaSemPerdas.setText(df2.format(valMinVenda(papel.getValor(), papel.getQuantidade(), calculoCorretagemCompra, calculoCustodiaCompra, calculoLiquidacaoCompra, calculoNegociacaoCompra, calculoIssCompra)));
                dbh.close();
                dbhCustas.close();*/
            }
        });


        /*ll_dinamico.addView(novaTv);
        listaPapeisCadastrados.setView(ll_dinamico);*/

        listaPapeisCadastrados.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toaster("Cancelado");
            }
        });

        listaPapeisCadastrados.show();
        dbh.close();
    }

    public void custasVendaVisibility(View view){

        RelativeLayout custasExtra = findViewById(R.id.custasExtra2);
        if (custasExtra.getVisibility() == View.VISIBLE) {
            custasExtra.setVisibility(View.GONE);
        }
        else {
            custasExtra.setVisibility(View.VISIBLE);
        }
    }
    public void custasCompraVisibility(View view){
        RelativeLayout RLcustasExtraCompra = findViewById(R.id.custasExtraCompra);
        if (RLcustasExtraCompra.getVisibility() == View.VISIBLE) {
            RLcustasExtraCompra.setVisibility(View.GONE);
        }
        else {
            RLcustasExtraCompra.setVisibility(View.VISIBLE);
        }
    }
}