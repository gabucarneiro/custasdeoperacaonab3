package com.example.custasnasopb3;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class QntAcoesValorDisponivel extends AppCompatActivity {

    //  *** TASKs ***
    // *** OK *** DEFINIR PRIMEIRO O VALOR BRUTO UTILIZADO PARA A COMPRA DAS AÇÕES.
    // *** OK *** CALCULAR CUSTAS E EMOLUMENTOS.
    // *** OK *** VERIFICAR SE MANTÉM A QUANTIDADE DE AÇÕES CONSIDERANDO O VALOR DISPONÍVEL, CASO NÃO, DIMINUIR A QUANTIDADE EM 1 AÇÃO.
    // *** OK *** Incluir checkbutton para alterar valores, como tx_liquidacao, tx_negociacao, custodia, corretagem, iss
    // quando marcado o checkbutton, inserir um editView para permitir a alteração dos valores.
    // *** OK *** Checkar os checkbuttons e valores;
    // *** OK *** Criar o banco de dados para os Custos Operacionais.
    //TODO Dar continuidade às implementações do Checkbutton para opção de papel fracionário -
    // testar e corrigir os cálculos - colocar para mudança automática da quantidade de corretagens
    // necessárias e recalculo.
    //TODO Separrar os cálculos das custas em funções.
    // Lembrar que o que for salvo no banco de dados, deverá ser estático,
    // Portatnto, deverá ser inserido em uma String antes de ir para o banco de dados.



    /*Go after, do and keep practicing what you think is utopic, and it'll become reality. Gabriel, 20/02/2021 12:45*/


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qnt_acoes_valor_disponivel);

        DecimalFormat df2 = new DecimalFormat("0.00");
        DecimalFormat df3 = new DecimalFormat("0.000");
        DecimalFormat df4 = new DecimalFormat("0.0000");

        EditText valDisponivel = (EditText) findViewById(R.id.valDisponivel);
        EditText valPapel = (EditText) findViewById(R.id.valPapel);
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem);
        EditText pct_Custodia = findViewById(R.id.pctCustodia);
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao);
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao);
        EditText pct_Iss = findViewById(R.id.pctIss);
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos);

        CheckBox cbFracionaria = (CheckBox) findViewById(R.id.cbFracionario);

/*
        //CUSTOS OPERACIONAIS
        boolean corretagemFixa = true;
        double corretagem;
        double calc_Corretagem;

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
        double calc_ir_venda;*/

        DataBaseHelper dbhCustas = new DataBaseHelper(this);

        pct_Corretagem.setText(String.valueOf(dbhCustas.getCustas(999).getCorretagem()));
        pct_Custodia.setText(String.valueOf(dbhCustas.getCustas(999).getCustodia()));
        pct_Liquidacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()));
        pct_Negociacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_negociacao()));
        pct_Iss.setText(String.valueOf(dbhCustas.getCustas(999).getIss()));
        pct_Emolumentos.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()+dbhCustas.getCustas(999).getTx_negociacao()));


        DataBaseHelper dbhCheckbox = new DataBaseHelper(this);

        CheckBox cbCorretagem = findViewById(R.id.cbCorretagem);
        if (dbhCheckbox.getCustas(999).isCorretagemFixa()){
            cbCorretagem.setChecked(true);
        }
        else {
            cbCorretagem.setChecked(false);
        }

        CheckBox cbCustodia = findViewById(R.id.cbCustodia);
        if (dbhCheckbox.getCustas(999).isCustodiaFixa()){
            cbCustodia.setChecked(true);
        }
        else {
            cbCustodia.setChecked(false);
        }

        CheckBox cbLiquidacao = findViewById(R.id.cbLiquidacao);
        if (dbhCheckbox.getCustas(999).isTx_liquidacaoFixa()){
            cbLiquidacao.setChecked(true);
        }
        else {
            cbLiquidacao.setChecked(false);
        }

        CheckBox cbNegociacao = findViewById(R.id.cbNegociacao);
        if (dbhCheckbox.getCustas(999).isTx_negociacaoFixa()){
            cbNegociacao.setChecked(true);
        }
        else {
            cbNegociacao.setChecked(false);
        }

        CheckBox cbIss = findViewById(R.id.cbIss);
        if (dbhCheckbox.getCustas(999).isIssFixo()){
            cbIss.setChecked(true);
        }
        else {
            cbIss.setChecked(false);
        }
        dbhCheckbox.close();

        dbhCustas.close();
    }
    public void calQuantidadeAcaoValBruto (View view){
        DecimalFormat df2 = new DecimalFormat("0.00");
        DecimalFormat df3 = new DecimalFormat("0.000");
        DecimalFormat df4 = new DecimalFormat("0.0000");


        //TODO EXCLUIR APÓS TESTES
        LinearLayout TesteDeCustas = findViewById(R.id.TesteDeCustas);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        TextView tv3 = new TextView(this);
        TextView tv4 = new TextView(this);
        TextView tv5 = new TextView(this);
        TextView tv6 = new TextView(this);
        TextView tv7 = new TextView(this);
        //TODO EXCLUIR APÓS TESTES




        EditText valDisponivel = (EditText) findViewById(R.id.valDisponivel);

        EditText valPapel = (EditText) findViewById(R.id.valPapel);
        if (valPapel.getText().equals("")){
            valPapel.setError("Campo obrigatório");
        }
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem);
        if (pct_Corretagem.getText().equals("")){
            pct_Corretagem.setError("Campo obrigatório");
        }
        EditText pct_Custodia = findViewById(R.id.pctCustodia);
        if (pct_Custodia.getText().equals("")){
            pct_Custodia.setError("Campo obrigatório");
        }
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao);
        if (pct_Liquidacao.getText().equals("")){
            pct_Liquidacao.setError("Campo obrigatório");
        }
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao);
        if (pct_Negociacao.getText().equals("")){
            pct_Negociacao.setError("Campo obrigatório");
        }
        EditText pct_Iss = findViewById(R.id.pctIss);
        if (pct_Iss.getText().equals("")){
            pct_Iss.setError("Campo obrigatório");
        }
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos);
        TextView tv_quantidade = (TextView) findViewById(R.id.quantidade);


        //Faz o cálculo do valor disponível pelo valor do papel, pega a quantidade

        //-------- CÁLCULO DE CORRETAGEM ----------

        Custas custas = new Custas();
        DataBaseHelper dbhCheckbox = new DataBaseHelper(this);

        CheckBox cbFracionaria = findViewById(R.id.cbFracionario);
        Boolean bool_cbFracionaria = cbFracionaria.isChecked();

        CheckBox cbCorretagem = findViewById(R.id.cbCorretagem);
        Boolean bool_cbCorretagem = cbCorretagem.isChecked();

        CheckBox cbCustodia = findViewById(R.id.cbCustodia);
        Boolean bool_cbCustodia = cbCustodia.isChecked();

        CheckBox cbLiquidacao = findViewById(R.id.cbLiquidacao);
        Boolean bool_cbLiquidacao = cbLiquidacao.isChecked();

        CheckBox cbNegociacao = findViewById(R.id.cbNegociacao);
        Boolean bool_cbNegociacao = cbNegociacao.isChecked();

        CheckBox cbIss = findViewById(R.id.cbIss);
        Boolean bool_cbIss = cbIss.isChecked();

        dbhCheckbox.close();

        Double val_Disponivel;
        Double val_Papel;
        Double custoPorOperacao;
        Double resultadoCalcCorretagem;
        Double resultadoCalcCustodia;
        Double resultadoCalcLiquidacao;
        Double resultadoCalcNegociacao;
        Double resultadoCalcIss;
        Integer tempQuantidadeDeCotasPorValDispoivel;
        Integer resultadoQuantidadeDeCotasPorValDispoivel;
        Double sumResultadoCalculoCustas;

        if (!valPapel.toString().equals("") && !valDisponivel.toString().equals("") && Double.parseDouble(valPapel.getText().toString()) != 0 && Double.parseDouble(valDisponivel.getText().toString()) != 0){
            try {
                val_Disponivel = Double.valueOf(String.valueOf(valDisponivel.getText()));
                val_Papel = Double.valueOf(String.valueOf(valPapel.getText()));
                tempQuantidadeDeCotasPorValDispoivel = (int) Math.round(val_Disponivel/val_Papel);
                Toast.makeText(this, "Quantidade temporária de ações: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, "Falha para obter os editTexts", Toast.LENGTH_SHORT).show();
                val_Disponivel = 0.0;
                val_Papel = 0.0;
                tempQuantidadeDeCotasPorValDispoivel = 0;
            }
            //OS CÁLCULOS ABAIXO ESTÃO CORRETOS.
            resultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
            tv1.setText("Corretagem 1: " + String.valueOf(resultadoCalcCorretagem));
            //Toast.makeText(this, "1 Calculo de Corretagem: " + resultadoCalcCorretagem, Toast.LENGTH_SHORT).show();
            resultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
            tv2.setText("Custodia 1: " + String.valueOf(resultadoCalcCustodia));
            //Toast.makeText(this, "1 Calculo de Custodia: " + resultadoCalcCustodia, Toast.LENGTH_SHORT).show();
            resultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
            tv3.setText("Liquidação 1: " + String.valueOf(resultadoCalcLiquidacao));
            //Toast.makeText(this, "1 Calculo de Liquidação: " + resultadoCalcLiquidacao, Toast.LENGTH_SHORT).show();
            resultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
            tv4.setText("Negociação 1: " + String.valueOf(resultadoCalcNegociacao));
            //Toast.makeText(this, "1 Calculo de Negociação: " + resultadoCalcNegociacao, Toast.LENGTH_SHORT).show();
            resultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);
            tv5.setText("Iss 1: " + String.valueOf(resultadoCalcIss));
            //Toast.makeText(this, "1 Calculo de ISS: " + resultadoCalcIss, Toast.LENGTH_SHORT).show();

            sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;
            tv6.setText("Soma da custas 1: " + String.valueOf(sumResultadoCalculoCustas));
            //Toast.makeText(this, "1 sumResultadoCalculoCustas: " + sumResultadoCalculoCustas, Toast.LENGTH_SHORT).show();

            if (!bool_cbFracionaria){
                tempQuantidadeDeCotasPorValDispoivel = qntLote(tempQuantidadeDeCotasPorValDispoivel) * 100;
            }
            double tempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);
            tv7.setText("Custas + (Valor do papel * quantidade) 1: " + String.valueOf(tempQuantidadeDeCotasPorValDispoivel) + " Resultado: " + String.valueOf(tempValorTotaldaCompra));
            //Toast.makeText(this, "1 Quantidade tempQuantidadeDeCotasPorValDispoivel: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();

            //Toast.makeText(this, "1 Soma com emolumentos: R$" + tempValorTotaldaCompra, Toast.LENGTH_SHORT).show();

            //TODO CONTINUAR......

            /*Integer tempqnt = tempQuantidadeDeCotasPorValDispoivel-=1;
            Custas custas2 = new Custas();
            Double tempresultadoCalcLiquidacao;
            CheckBox tempcbFracionaria = findViewById(R.id.cbFracionario);
            Boolean tempbool_cbFracionaria = tempcbFracionaria.isChecked();
            EditText tempvalPapel = (EditText) findViewById(R.id.valPapel);
            Double ttempvalPapel = Double.parseDouble(tempvalPapel.getText().toString());
            EditText temppct_Liquidacao = findViewById(R.id.pctLiquidacao);
            Double ttemppct_Liquidacao = Double.parseDouble(temppct_Liquidacao.getText().toString());
            CheckBox tempcbLiquidacao = findViewById(R.id.cbLiquidacao);
            Boolean tempbool_cbLiquidacao = tempcbLiquidacao.isChecked();*/


            /*Toast.makeText(this, "2 VPapel: " + ttempvalPapel, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "2 Qnt: " + tempqnt, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "2 pct Liquidação: " + ttemppct_Liquidacao, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "2 bool frac: " + tempbool_cbFracionaria, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "2 bool liq: " + tempbool_cbLiquidacao, Toast.LENGTH_SHORT).show();*/

            /*tempresultadoCalcLiquidacao = custas2.calc_Corretagem(ttempvalPapel, tempqnt, ttemppct_Liquidacao, tempbool_cbFracionaria, tempbool_cbLiquidacao);
            Toast.makeText(this, "2 Calculo de Liquidação: " + tempresultadoCalcLiquidacao, Toast.LENGTH_SHORT).show();*/







            /*if(tempValorTotaldaCompra > val_Disponivel){
                tempQuantidadeDeCotasPorValDispoivel-=1;
                *//*Custas custas2 = new Custas();

                Double tempresultadoCalcLiquidacao;
                CheckBox tempcbFracionaria = findViewById(R.id.cbFracionario);
                Boolean tempbool_cbFracionaria = tempcbFracionaria.isChecked();
                EditText tempvalPapel = (EditText) findViewById(R.id.valPapel);
                Double ttempvalPapel = Double.parseDouble(tempvalPapel.getText().toString());
                EditText temppct_Liquidacao = findViewById(R.id.pctLiquidacao);
                Double ttemppct_Liquidacao = Double.parseDouble(temppct_Liquidacao.getText().toString());
                CheckBox tempcbLiquidacao = findViewById(R.id.cbLiquidacao);
                Boolean tempbool_cbLiquidacao = tempcbLiquidacao.isChecked();*//*

                *//*resultadoCalcCorretagem = custas2.calc_Corretagem(ttempvalPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                Toast.makeText(this, "2 Calculo de Corretagem: " + resultadoCalcCorretagem, Toast.LENGTH_SHORT).show();
                resultadoCalcCustodia = custas2.calc_Corretagem(ttempvalPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                Toast.makeText(this, "2 Calculo de Custodia: " + resultadoCalcCustodia, Toast.LENGTH_SHORT).show();*//*

                //tempresultadoCalcLiquidacao = custas2.calc_Corretagem(ttempvalPapel, tempQuantidadeDeCotasPorValDispoivel, ttemppct_Liquidacao, tempbool_cbFracionaria, tempbool_cbLiquidacao);
                //Toast.makeText(this, "2 Calculo de Liquidação: " + tempresultadoCalcLiquidacao, Toast.LENGTH_SHORT).show();

                *//*resultadoCalcNegociacao = custas2.calc_Corretagem(ttempvalPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                Toast.makeText(this, "2 Calculo de Negociação: " + resultadoCalcNegociacao, Toast.LENGTH_SHORT).show();
                resultadoCalcIss = custas2.calc_Corretagem(ttempvalPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);
                Toast.makeText(this, "2 Calculo de ISS: " + resultadoCalcIss, Toast.LENGTH_SHORT).show();

                sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;
                Toast.makeText(this, "2 sumResultadoCalculoCustas: " + sumResultadoCalculoCustas, Toast.LENGTH_SHORT).show();*//*

                *//*tempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);
                Toast.makeText(this, "2 Quantidade tempQuantidadeDeCotasPorValDispoivel: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "2 Soma com emolumentos: R$" + tempValorTotaldaCompra, Toast.LENGTH_SHORT).show();*//*














                *//*Custas custodia = new Custas();
                Custas liquidacao = new Custas();

                valPapel = (EditText) findViewById(R.id.valPapel);
                pct_Corretagem = findViewById(R.id.pctCorretagem);
                pct_Custodia = findViewById(R.id.pctCustodia);
                pct_Liquidacao = findViewById(R.id.pctLiquidacao);
                pct_Negociacao = findViewById(R.id.pctNegociacao);
                pct_Iss = findViewById(R.id.pctIss);

                dbhCheckbox = new DataBaseHelper(this);

                cbFracionaria = findViewById(R.id.cbFracionario);
                bool_cbFracionaria = cbFracionaria.isChecked();

                cbCorretagem = findViewById(R.id.cbCorretagem);
                bool_cbCorretagem = cbCorretagem.isChecked();

                cbCustodia = findViewById(R.id.cbCustodia);
                bool_cbCustodia = cbCustodia.isChecked();

                cbLiquidacao = findViewById(R.id.cbLiquidacao);
                bool_cbLiquidacao = cbLiquidacao.isChecked();

                cbNegociacao = findViewById(R.id.cbNegociacao);
                bool_cbNegociacao = cbNegociacao.isChecked();

                cbIss = findViewById(R.id.cbIss);
                bool_cbIss = cbIss.isChecked();

                dbhCheckbox.close();

                pct_Iss = findViewById(R.id.pctIss);
                tempQuantidadeDeCotasPorValDispoivel-=1;*//*


                *//*Toast.makeText(this, "2 Valor do papel: " + val_Papel, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "2 Quantidade de cotas: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "2 Valor da liquidacao: " + pct_Liquidacao.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "2 Fracionaria? " + bool_cbFracionaria, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "2 Liquidacao fixa? " + bool_cbLiquidacao, Toast.LENGTH_SHORT).show();*//*


                LinearLayout TesteDeCustas = findViewById(R.id.TesteDeCustas);
                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);
                TextView tv4 = new TextView(this);
                TextView tv5 = new TextView(this);
                TextView tv6 = new TextView(this);
                TextView tv7 = new TextView(this);

                Double tempresultadoCalcCorretagem=0.0;
                if (!pct_Corretagem.getText().equals(0)) {
                    tempresultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                    //Toast.makeText(this, "2 DENTRO DO IF " + tempresultadoCalcCorretagem.toString(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, "2 FORA DO IF corretagem " + tempresultadoCalcCorretagem.toString(), Toast.LENGTH_SHORT).show();
                tv1.setText(tempresultadoCalcCorretagem.toString());

                Double tempresultadoCalcCustodia=0.0;
                if (!pct_Custodia.getText().equals(0)){
                    tempresultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                    //Toast.makeText(this, "2 CUSTODIA DENTRO DO IF " + tempresultadoCalcCustodia.toString(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, "2 CUSTODIA FORA DO IF de custodia " + tempresultadoCalcCustodia.toString(), Toast.LENGTH_SHORT).show();
                tv2.setText(tempresultadoCalcCustodia.toString());

                *//*EditText temppct_Liquidacao = findViewById(R.id.pctLiquidacao);
                temppct_Liquidacao.getText();
                Toast.makeText(this, "** Liquidação pego no EditText " + temppct_Liquidacao.getText().toString(), Toast.LENGTH_SHORT).show();*//*

                Double tempresultadoCalcLiquidacao=0.0;
                if (!pct_Liquidacao.getText().equals(0)) {
                    tempresultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
                    //Toast.makeText(this, "2 Liquidação DENTRO DO IF " + tempresultadoCalcLiquidacao.toString(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, "2 Liquidação FORA DO IF liquidacao " + tempresultadoCalcLiquidacao.toString(), Toast.LENGTH_SHORT).show();
                tv3.setText(tempresultadoCalcLiquidacao.toString());


                Double tempresultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                //Toast.makeText(this, "2 Resultado do calculo de negociacao " + tempresultadoCalcNegociacao.toString(), Toast.LENGTH_SHORT).show();
                tv4.setText(tempresultadoCalcNegociacao.toString());

                Double tempresultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);
                //Toast.makeText(this, "2 Resultado do calculo de Iss " + tempresultadoCalcIss.toString(), Toast.LENGTH_SHORT).show();
                tv5.setText(tempresultadoCalcIss.toString());


                Double tempsumResultadoCalculoCustas = tempresultadoCalcCorretagem + tempresultadoCalcCustodia + tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;
                //Double tempsumResultadoCalculoCustas = tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;
                //Toast.makeText(this, "2 sumResultadoCalculoCustas: " + tempsumResultadoCalculoCustas, Toast.LENGTH_SHORT).show();
                tv6.setText(tempsumResultadoCalculoCustas.toString());

                Double ixetempValorTotaldaCompra = tempsumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);
                //Toast.makeText(this, "2 Soma com emolumentos: R$" + ixetempValorTotaldaCompra, Toast.LENGTH_SHORT).show();
                tv7.setText(tempQuantidadeDeCotasPorValDispoivel.toString() + " multiplicado por " + val_Papel.toString() + " mais " + tempsumResultadoCalculoCustas.toString() + " Resultado:" + ixetempValorTotaldaCompra.toString());


                TesteDeCustas.addView(tv1);
                TesteDeCustas.addView(tv2);
                TesteDeCustas.addView(tv3);
                TesteDeCustas.addView(tv4);
                TesteDeCustas.addView(tv5);
                TesteDeCustas.addView(tv6);
                TesteDeCustas.addView(tv7);
            }*/

            //TODO EXCLUIR APÓS TESTES
            TextView tv8 = new TextView(this);
            TextView tv9 = new TextView(this);
            TextView tv10 = new TextView(this);
            TextView tv11 = new TextView(this);
            TextView tv12 = new TextView(this);
            TextView tv13 = new TextView(this);
            TextView tv14 = new TextView(this);
            //TODO EXCLUIR APÓS TESTES

            while ((sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel)) > val_Disponivel && tempQuantidadeDeCotasPorValDispoivel>1){



                tempQuantidadeDeCotasPorValDispoivel-=1;
                resultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                //TODO EXCLUIR APÓS TESTES
                tv8.setText(String.valueOf(resultadoCalcCorretagem));
                resultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                //TODO EXCLUIR APÓS TESTES
                tv9.setText(String.valueOf(resultadoCalcCustodia));
                resultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
                //TODO EXCLUIR APÓS TESTES
                tv10.setText(String.valueOf(resultadoCalcLiquidacao));
                resultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                //TODO EXCLUIR APÓS TESTES
                tv11.setText(String.valueOf(resultadoCalcNegociacao));
                resultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);
                //TODO EXCLUIR APÓS TESTES
                tv12.setText(String.valueOf(resultadoCalcIss));

                sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;
                //TODO EXCLUIR APÓS TESTES
                tv13.setText(String.valueOf(sumResultadoCalculoCustas));

                Double ixetempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);
                //TODO EXCLUIR APÓS TESTES
                tv14.setText(String.valueOf(ixetempValorTotaldaCompra));

                //TODO EXCLUIR APÓS TESTES
                TesteDeCustas.addView(tv8);
                TesteDeCustas.addView(tv9);
                TesteDeCustas.addView(tv10);
                TesteDeCustas.addView(tv11);
                TesteDeCustas.addView(tv12);
                TesteDeCustas.addView(tv13);
                TesteDeCustas.addView(tv14);

                //TODO EXCLUIR APÓS TESTES
            }
            TesteDeCustas.addView(tv1);
            TesteDeCustas.addView(tv2);
            TesteDeCustas.addView(tv3);
            TesteDeCustas.addView(tv4);
            TesteDeCustas.addView(tv5);
            TesteDeCustas.addView(tv6);
            TesteDeCustas.addView(tv7);

        }
        else {
            val_Disponivel = 0.0;
            custoPorOperacao = 0.0;
            resultadoCalcCorretagem = 0.0;
            resultadoCalcCustodia = 2.0;
            resultadoCalcLiquidacao = 3.0;
            resultadoCalcNegociacao = 4.0;
            resultadoCalcIss = 5.0;
            tempQuantidadeDeCotasPorValDispoivel = 0;
            sumResultadoCalculoCustas = 0.0;
        }
        /*Toast.makeText(this, "Resultado Corretagem: R$" + resultadoCalcCorretagem, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Resultado Custodia: R$" + resultadoCalcCustodia, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Resultado Liquidação: R$" + resultadoCalcLiquidacao, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Resultado Negociação: R$" + resultadoCalcNegociacao, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Resultado ISS: R$" + resultadoCalcIss, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Soma dos emolumentos: R$" + sumResultadoCalculoCustas, Toast.LENGTH_SHORT).show();
*/







        //CUSTOS OPERACIONAIS
        boolean corretagemFixa = true;
        double corretagem = 1.99;
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

        /*pct_Corretagem.setText(String.valueOf(corretagem));
        pct_Custodia.setText(String.valueOf(custodia));
        pct_Liquidacao.setText(String.valueOf(tx_liquidacao));
        pct_Negociacao.setText(String.valueOf(tx_negociacao));
        pct_Iss.setText(String.valueOf(iss));
        pct_Emolumentos.setText(String.valueOf(tx_liquidacao+tx_negociacao));*/

        if(valDisponivel != null && valPapel != null){
            val_Disponivel = Double.parseDouble(valDisponivel.getText().toString());
            val_Papel = Double.parseDouble(valPapel.getText().toString());

    //partOneValor corresponde ao número bruto da quantidade de papeis que será póssível com o valor bruto.
    //partTwoQuantidade corresponde a quantidade, JÁ INTEIRO, de ações que será possível com o valor bruto. Ou seja, ainda sem tirar as custas - esse valor poderá ser alterado.
    //resul_val_Disponivel corresponte ao valor bruto utilizado para compra das ações. Ou seja, sem cáculo das custas. ESTE É O VALOR UTILIZADO PARA O CÁLCULO DOS EMOLUMENTOS.
    //custoPorOperação corresponde ao valor total das custas e emolumentos;
    //val_DisponivelLiquido corresponde ao valor retirando as custas - JÁ É O VALOR FINAL PARA INVESTIR



            Double partOneValor = val_Disponivel/val_Papel;
            Integer partTwoQuantidade = Integer.valueOf(partOneValor.intValue());
            double resul_val_Disponivel = partTwoQuantidade*val_Papel;

            if(partTwoQuantidade>=1){

        // CÁLCULO DAS CUSTAS
            //CORRETAGEM

                CheckBox cb_Corretagem = findViewById(R.id.cbCorretagem);
                if (cb_Corretagem.isChecked()){
                    corretagemFixa=false;
                    double db_pct_Corretagem = Double.parseDouble(pct_Corretagem.getText().toString());
                    corretagem = db_pct_Corretagem;
                }

                if(!corretagemFixa || corretagem!=0 || val_Disponivel==0){
                    calc_Corretagem = corretagem;
                }
                else{
                    calc_Corretagem = Porcentagem(resul_val_Disponivel, corretagem);
                }

                TextView tvCorretagem = (TextView) findViewById(R.id.corretagem);
                String calculo_Corretagem = String.valueOf(calc_Corretagem);
                tvCorretagem.setText(calculo_Corretagem);

            //CUSTODIA

                CheckBox cb_Custodia = findViewById(R.id.cbCustodia);
                if (cb_Custodia.isChecked()){
                    custodiaFixa=false;
                    pct_Custodia.setClickable(true);
                    double db_pct_Custodia = Double.parseDouble(pct_Custodia.getText().toString());
                    custodia = db_pct_Custodia;
                }

                if(!custodiaFixa || custodia!=0 || val_Disponivel==0){
                    calc_Custodia = custodia;
                }
                else{
                    calc_Custodia = Porcentagem(resul_val_Disponivel, custodia);
                }

                String str_val_Custodia = df2.format(calc_Custodia);
                TextView val_Custodia = (TextView) findViewById(R.id.custodia);
                val_Custodia.setText(str_val_Custodia);

            //EMOLUMENTOS
                //TAXA DE LIQUIDAÇÃO

                CheckBox cb_Liquidacao = findViewById(R.id.cbLiquidacao);
                if (cb_Liquidacao.isChecked()){
                    pct_Liquidacao.setClickable(true);
                    double db_pct_Liquidacao = Double.parseDouble(pct_Liquidacao.getText().toString());
                    tx_liquidacao = db_pct_Liquidacao;
                }
                if(tx_liquidacao ==0 || val_Disponivel==0){
                    calc_tx_liquidacao = tx_liquidacao;
                }
                else{
                    calc_tx_liquidacao = Porcentagem(resul_val_Disponivel, tx_liquidacao);
                }

                String str_val_tx_liquidacao = df3.format(calc_tx_liquidacao);
                TextView val_tx_liquidacao = (TextView) findViewById(R.id.tax_liquidacao);
                val_tx_liquidacao.setText(str_val_tx_liquidacao);

                //TAXA DE NEGOCIAÇÃO

                CheckBox cb_Negociacao = findViewById(R.id.cbNegociacao);
                if (cb_Negociacao.isChecked()){
                    pct_Negociacao.setClickable(true);
                    double db_pct_Negociacao = Double.parseDouble(pct_Negociacao.getText().toString());
                    tx_negociacao = db_pct_Negociacao;
                }
                if(tx_negociacao ==0 || val_Disponivel==0){
                    calc_tx_negociacao = tx_negociacao;
                }
                else{
                    calc_tx_negociacao = Porcentagem(resul_val_Disponivel, tx_negociacao);
                }

                String str_val_tx_negociacao = df4.format(calc_tx_negociacao);
                TextView val_tx_negociacao = (TextView) findViewById(R.id.tax_negociacao);
                val_tx_negociacao.setText(str_val_tx_negociacao);

                //calculando os emolumentos
                emolumentos = calc_tx_liquidacao + calc_tx_negociacao;

                TextView tv_emolumentos = (TextView) findViewById(R.id.emolumentos);
                String str_emolumentos = df4.format(emolumentos);
                tv_emolumentos.setText(str_emolumentos);

            //ISS
                CheckBox cb_Iss = findViewById(R.id.cbIss);
                if (cb_Iss.isChecked()){
                    pct_Iss.setClickable(true);
                    double db_pct_Iss = Double.parseDouble(pct_Iss.getText().toString());
                    tx_negociacao = db_pct_Iss;
                }
                if(!issCobrado || iss ==0 || val_Disponivel==0){
                    calc_iss = iss;
                }
                else{
                    calc_iss = Porcentagem(resul_val_Disponivel, iss);
                }

                String str_calc_iss = df4.format(calc_iss);
                TextView tv_iss = (TextView) findViewById(R.id.iss);
                tv_iss.setText(str_calc_iss);

                custoPorOperacao =+ calc_Corretagem + calc_Custodia + calc_tx_liquidacao + calc_tx_negociacao + calc_iss;

                Double val_DisponivelLiquido = (val_Disponivel-custoPorOperacao)/val_Papel;


                Integer qnt = Integer.valueOf(val_DisponivelLiquido.intValue());
                Double val_AInvestir = val_Papel * qnt + custoPorOperacao;
                String valAInvestirFormatado = df2.format(val_AInvestir);

                TextView quantidade = (TextView) findViewById(R.id.quantidade);
                quantidade.setText(qnt.toString());

                TextView valExclusivoDoPapel = (TextView) findViewById(R.id.valCompraDoPapel);
                String str_resul_val_Disponivel = df2.format(partTwoQuantidade*val_Papel);
                valExclusivoDoPapel.setText(str_resul_val_Disponivel);


        //VALOR APÓS CÁLCULOS DE CUSTAS

                TextView valAInvestir = (TextView) findViewById(R.id.valNecessarioParaInvestir);
                valAInvestir.setText(valAInvestirFormatado);

                TextView val_PapelResumo = (TextView) findViewById(R.id.valPapelResumo);
                val_PapelResumo.setText(val_Papel.toString());

                TextView quantidadeResumo = (TextView) findViewById(R.id.quantidadeResumo);
                quantidadeResumo.setText(qnt.toString());

                TextView val_CustaEmolImpostos = (TextView) findViewById(R.id.valCustaEmolImpostos);
                String custo_PorOperacao = String.valueOf(0-custoPorOperacao);
                val_CustaEmolImpostos.setText(custo_PorOperacao);

                try {

                    CheckBox cb_Fracionario = findViewById(R.id.cbFracionario);
                    cb_Fracionario.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (cb_Fracionario.isChecked()){
                                String temp = String.valueOf(qntFracionaria(qnt));
                                cb_Fracionario.setText(temp);
                            }
                            else {
                                String temp = String.valueOf(qntLote(qnt));
                                cb_Fracionario.setText(temp);
                            }
                        }
                    });
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(this, "Damn! O fracionario não funcionou", Toast.LENGTH_SHORT);
                    toast.show();
                }

                /*TextView resumo = (TextView) findViewById(R.id.resumo);
                resumo.setText(val_DisponivelLiquido.toString());*/
            }
            else{
                val_Disponivel = 0.0;
                val_Papel = 0.0;
                /*TextView resumo = (TextView) findViewById(R.id.resumo);
                resumo.setText("Aw no!");*/
            }
        }
    }

    //Ex.: tem 50, precisamos saber o valor de 25% do 50 citado. Resultado: 12,5.
    public double Porcentagem(double valor, double porcentagem){
        Double resultado = (valor*porcentagem)/100;
        return resultado;
    }

    public int qntFracionaria(int quantidade){
        int qntFracionaria = Math.round(quantidade / 99);
        return qntFracionaria;
    }
    public int qntLote (int quantidade){
        int qntLote = Math.round(quantidade / 100);
        return qntLote;
    }
}
