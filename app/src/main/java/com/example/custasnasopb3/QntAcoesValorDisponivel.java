package com.example.custasnasopb3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Locale;

public class QntAcoesValorDisponivel extends AppCompatActivity {

    //  *** TASKs ***
    // *** OK *** DEFINIR PRIMEIRO O VALOR BRUTO UTILIZADO PARA A COMPRA DAS AÇÕES.
    // *** OK *** CALCULAR CUSTAS E EMOLUMENTOS.
    // *** OK *** VERIFICAR SE MANTÉM A QUANTIDADE DE AÇÕES CONSIDERANDO O VALOR DISPONÍVEL, CASO NÃO, DIMINUIR A QUANTIDADE EM 1 AÇÃO.
    // *** OK *** Incluir checkbutton para alterar valores, como tx_liquidacao, tx_negociacao, custodia, corretagem, iss
    // quando marcado o checkbutton, inserir um editView para permitir a alteração dos valores.
    // *** OK *** Checkar os checkbuttons e valores;
    // *** OK *** Criar o banco de dados para os Custos Operacionais.
    // *** OK *** Dar continuidade às implementações do Checkbutton para opção de papel fracionário -
    // testar e corrigir os cálculos - colocar para mudança automática da quantidade de corretagens
    // necessárias e recalculo.
    // *** OK *** Separrar os cálculos das custas em funções.
    // Lembrar que o que for salvo no banco de dados, deverá ser estático,
    // Portatnto, deverá ser inserido em uma String antes de ir para o banco de dados.



    /*Go after, do and keep practicing what you think is utopic, and it'll become reality. Gabriel, 20/02/2021 12:45*/

    private Handler handler3 = new Handler();

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qnt_acoes_valor_disponivel);

        /*EditText valDisponivel = (EditText) findViewById(R.id.valDisponivel);
        EditText valPapel = (EditText) findViewById(R.id.valPapel);
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem);
        EditText pct_Custodia = findViewById(R.id.pctCustodia);
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao);
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao);
        EditText pct_Iss = findViewById(R.id.pctIss);
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos);*/

        //CheckBox cbFracionaria = (CheckBox) findViewById(R.id.cbFracionario);

        DataBaseHelper dbhCustas = new DataBaseHelper(this);
        ((EditText)findViewById(R.id.pctStoLoss)).setText(String.valueOf(10));


        int custasPadrao = 999;

        ((EditText) findViewById(R.id.pctCorretagem)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getCorretagem()));
        //pct_Corretagem.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getCorretagem()));
        ((EditText) findViewById(R.id.pctCustodia)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getCustodia()));
        //pct_Custodia.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getCustodia()));
        ((EditText) findViewById(R.id.pctLiquidacao)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_liquidacao()));
        //pct_Liquidacao.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_liquidacao()));
        ((EditText) findViewById(R.id.pctNegociacao)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_negociacao()));
        //pct_Negociacao.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_negociacao()));
        ((EditText) findViewById(R.id.pctIss)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getIss()));
        //pct_Iss.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getIss()));
        ((TextView) findViewById(R.id.pctEmolumentos)).setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_liquidacao()+dbhCustas.getCustas(999).getTx_negociacao()));
        //pct_Emolumentos.setText(String.valueOf(dbhCustas.getCustas(custasPadrao).getTx_liquidacao()+dbhCustas.getCustas(999).getTx_negociacao()));


        ((LinearLayout) findViewById(R.id.custasValordopapelBruto)).setVisibility(View.GONE);
        ((LinearLayout) findViewById(R.id.idcustas)).setVisibility(View.GONE);
        ((RelativeLayout) findViewById(R.id.custasExtra)).setVisibility(View.GONE);

        DataBaseHelper dbhCheckbox = new DataBaseHelper(this);

        //CheckBox cbCorretagem = findViewById(R.id.cbCorretagem);
        if (dbhCheckbox.getCustas(custasPadrao).isCorretagemFixa()){
            //cbCorretagem.setChecked(true);
            ((CheckBox) findViewById(R.id.cbCorretagem)).setChecked(true);
        }
        /*else {
            cbCorretagem.setChecked(false);
        }*/

        //CheckBox cbCustodia = findViewById(R.id.cbCustodia);
        if (dbhCheckbox.getCustas(custasPadrao).isCustodiaFixa()){
            //cbCustodia.setChecked(true);
            ((CheckBox) findViewById(R.id.cbCustodia)).setChecked(true);
        }
        /*else {
            cbCustodia.setChecked(false);
        }*/

        //CheckBox cbLiquidacao = findViewById(R.id.cbLiquidacao);
        if (dbhCheckbox.getCustas(custasPadrao).isTx_liquidacaoFixa()){
            //cbLiquidacao.setChecked(true);
            ((CheckBox) findViewById(R.id.cbLiquidacao)).setChecked(true);
        }
        /*else {
            cbLiquidacao.setChecked(false);
        }*/

        //CheckBox cbNegociacao = findViewById(R.id.cbNegociacao);
        if (dbhCheckbox.getCustas(custasPadrao).isTx_negociacaoFixa()){
            //cbNegociacao.setChecked(true);
            ((CheckBox) findViewById(R.id.cbNegociacao)).setChecked(true);
        }
        /*else {
            cbNegociacao.setChecked(false);
        }*/

        //CheckBox cbIss = findViewById(R.id.cbIss);
        if (dbhCheckbox.getCustas(custasPadrao).isIssFixo()){
            //cbIss.setChecked(true);
            ((CheckBox) findViewById(R.id.cbIss)).setChecked(true);
        }
        /*else {
            cbIss.setChecked(false);
        }*/
        dbhCheckbox.close();

        dbhCustas.close();
    }
    public void calQuantidadeAcaoValBruto (View view){
        DecimalFormat df2 = new DecimalFormat("0.00");
        DecimalFormat df3 = new DecimalFormat("0.000");
        DecimalFormat df4 = new DecimalFormat("0.0000");


        //TODO EXCLUIR APÓS TESTES
        /*LinearLayout TesteDeCustas = findViewById(R.id.TesteDeCustas);
        TesteDeCustas.setVisibility(View.GONE);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        TextView tv3 = new TextView(this);
        TextView tv4 = new TextView(this);
        TextView tv5 = new TextView(this);
        TextView tv6 = new TextView(this);
        TextView tv7 = new TextView(this);
        TextView tv8 = new TextView(this);*/
        //TODO EXCLUIR APÓS TESTES




        EditText valDisponivel = (EditText) findViewById(R.id.valDisponivel);
        EditText valPapel = (EditText) findViewById(R.id.valPapel);
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem);
        EditText pct_Custodia = findViewById(R.id.pctCustodia);
        if (pct_Custodia.getText().toString().equals("")){
            pct_Custodia.setError("Campo obrigatório");
        }
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao);
        if (pct_Liquidacao.getText().toString().equals("")){
            pct_Liquidacao.setError("Campo obrigatório");
        }
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao);
        if (pct_Negociacao.getText().toString().equals("")){
            pct_Negociacao.setError("Campo obrigatório");
        }
        EditText pct_Iss = findViewById(R.id.pctIss);
        if (pct_Iss.getText().toString().equals("")){
            pct_Iss.setError("Campo obrigatório");
        }

        //Faz o cálculo do valor disponível pelo valor do papel, pega a quantidade

        //-------- CÁLCULO DE CUSTAS ----------

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


        boolean valPapelOk;
        boolean valDisponivelOk;

        try {
            if (valDisponivel.getText().toString().equals("")){
                valDisponivel.setError("Campo obrigatório");
                valDisponivelOk = false;
            }
            else if (Double.parseDouble(valDisponivel.getText().toString()) == 0.0){
                valDisponivel.setError("Diferente de 0,0");
                valDisponivelOk = false;
            }
            else {
                valDisponivelOk = true;
            }
        }
        catch (Exception eValDisponivel){
            valDisponivel.setError("Campo obrigatório diferente de 0,0");
            valDisponivelOk = false;
        }

        try {
            if (valPapel.getText().toString().equals("")){
                valPapel.setError("Campo obrigatório");
                valPapelOk = false;
            }
            else if (Double.parseDouble(valPapel.getText().toString()) == 0.0){
                valPapel.setError("Diferente de 0,0");
                valPapelOk = false;
            }
            else {
                valPapelOk = true;
            }
        }
        catch (Exception eValPapel){
            Toast.makeText(this, "Falha para obter os valor do papel", Toast.LENGTH_SHORT).show();
            valPapel.setError("Campo obrigatório diferente de 0,0");
            valPapelOk = false;
        }

        try {
            if (valPapelOk && valDisponivelOk){
                try {
                    val_Disponivel = Double.valueOf(String.valueOf(valDisponivel.getText()));
                    val_Papel = Double.valueOf(String.valueOf(valPapel.getText()));
                    tempQuantidadeDeCotasPorValDispoivel = (int) Math.round(val_Disponivel/val_Papel);
                    //Toast.makeText(this, "Quantidade temporária de ações: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(this, "Valores nulos ou 0,0", Toast.LENGTH_SHORT).show();
                    val_Disponivel = 0.0;
                    val_Papel = 0.0;
                    tempQuantidadeDeCotasPorValDispoivel = 0;
                }


                try {
                    if (pct_Corretagem.getText().toString().equals("")){
                        pct_Corretagem.setError("Campo obrigatório");
                    }
                }
                catch (Exception ePct_Corretagem){
                    Toast.makeText(this, "Valor nulo: Corretagem", Toast.LENGTH_SHORT).show();
                    pct_Corretagem.setError("Campo obrigatório");
                }

                try {
                    if (pct_Custodia.getText().toString().equals("")){
                        pct_Custodia.setError("Campo obrigatório");
                    }
                }
                catch (Exception ePct_Custodia){
                    Toast.makeText(this, "Valor nulo: Custodia", Toast.LENGTH_SHORT).show();
                    pct_Custodia.setError("Campo obrigatório");
                }

                try {
                    if (pct_Liquidacao.getText().toString().equals("")){
                        pct_Liquidacao.setError("Campo obrigatório");
                    }
                }
                catch (Exception ePct_Liquidacao){
                    Toast.makeText(this, "Valor nulo: Taxa de Liquidação", Toast.LENGTH_SHORT).show();
                    pct_Liquidacao.setError("Campo obrigatório");
                }

                try {
                    if (pct_Negociacao.getText().toString().equals("")){
                        pct_Negociacao.setError("Campo obrigatório");
                    }
                }
                catch (Exception ePct_Negociacao){
                    Toast.makeText(this, "Valor nulo: Taxa de Negociação", Toast.LENGTH_SHORT).show();
                    pct_Negociacao.setError("Campo obrigatório");
                }

                try {
                    if (pct_Iss.getText().toString().equals("")){
                        pct_Iss.setError("Campo obrigatório");
                    }
                }
                catch (Exception ePct_Iss){
                    Toast.makeText(this, "Valor nulo: ISS", Toast.LENGTH_SHORT).show();
                    pct_Iss.setError("Campo obrigatório");
                }

                resultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                resultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                resultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
                resultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                resultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);

                sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;

                if (!bool_cbFracionaria){
                    tempQuantidadeDeCotasPorValDispoivel = qntLote(tempQuantidadeDeCotasPorValDispoivel) * 100;
                }
                double tempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);

                while (tempValorTotaldaCompra > val_Disponivel && tempQuantidadeDeCotasPorValDispoivel>1){

                    tempQuantidadeDeCotasPorValDispoivel-=1;
                    if (!bool_cbFracionaria){
                        tempQuantidadeDeCotasPorValDispoivel = qntLote(tempQuantidadeDeCotasPorValDispoivel) * 100;
                    }
                    resultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                    resultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                    resultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
                    resultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                    resultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);

                    sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;

                    tempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);

                }
                /*TextView result_val_Corretagem = (TextView) findViewById(R.id.corretagem);
                String calculo_Corretagem = String.valueOf(resultadoCalcCorretagem);*/
                ((TextView) findViewById(R.id.corretagem)).setText(String.valueOf(resultadoCalcCorretagem));

                /*TextView result_val_Custodia = (TextView) findViewById(R.id.custodia);
                String str_val_Custodia = df2.format(resultadoCalcCustodia);*/
                ((TextView) findViewById(R.id.custodia)).setText(df2.format(resultadoCalcCustodia));

                /*TextView result_val_tx_liquidacao = (TextView) findViewById(R.id.tax_liquidacao);
                String str_val_tx_liquidacao = df3.format(resultadoCalcLiquidacao);*/
                ((TextView) findViewById(R.id.tax_liquidacao)).setText(df3.format(resultadoCalcLiquidacao));

                /*TextView result_val_tx_negociacao = (TextView) findViewById(R.id.tax_negociacao);
                String str_val_tx_negociacao = df3.format(resultadoCalcNegociacao);*/
                ((TextView) findViewById(R.id.tax_negociacao)).setText(df3.format(resultadoCalcNegociacao));

                /*TextView result_tv_emolumentos = (TextView) findViewById(R.id.emolumentos);
                String str_emolumentos = df4.format(resultadoCalcLiquidacao + resultadoCalcNegociacao);*/
                ((TextView) findViewById(R.id.emolumentos)).setText(df4.format(resultadoCalcLiquidacao + resultadoCalcNegociacao));

                /*TextView result_tv_iss = (TextView) findViewById(R.id.iss);
                String str_calc_iss = df4.format(resultadoCalcIss);*/
                ((TextView) findViewById(R.id.iss)).setText(df4.format(resultadoCalcIss));

                ((TextView) findViewById(R.id.quantidade)).setText(String.valueOf(tempQuantidadeDeCotasPorValDispoivel));

                //String str_resul_val_Disponivel = df2.format(tempQuantidadeDeCotasPorValDispoivel * val_Papel);
                ((TextView) findViewById(R.id.valCompraDoPapel)).setText(df2.format(tempQuantidadeDeCotasPorValDispoivel * val_Papel));

                /*TextView valAInvestir = (TextView) findViewById(R.id.valNecessarioParaInvestir);
                String str_resul_valAInvestir = df2.format(tempValorTotaldaCompra);*/
                ((TextView) findViewById(R.id.valNecessarioParaInvestir)).setText(String.format(Locale.ENGLISH, "%.2f", tempValorTotaldaCompra));

                //TextView val_PapelResumo = (TextView) findViewById(R.id.valPapelResumo);
                ((TextView) findViewById(R.id.valPapelResumo)).setText(df4.format(val_Papel));

                TextView quantidadeResumo = (TextView) findViewById(R.id.quantidadeResumo);
                ((TextView) findViewById(R.id.quantidadeResumo)).setText(String.valueOf(tempQuantidadeDeCotasPorValDispoivel));

                /*TextView val_CustaEmolImpostos = (TextView) findViewById(R.id.valCustaEmolImpostos);
                String custo_PorOperacao = df3.format(0-sumResultadoCalculoCustas);*/
                ((TextView) findViewById(R.id.valCustaEmolImpostos)).setText(df3.format(0-sumResultadoCalculoCustas));


                try {
                    ((TextView) findViewById(R.id.valMinVenda)).setText(String.format(Locale.ENGLISH, "%.2f" , new ValorMinParaVendaSemPerdas().valMinVendaDII((Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.valPapel)).getText()))), Integer.parseInt(String.valueOf(((TextView) findViewById(R.id.quantidade)).getText())), (Double.parseDouble(String.valueOf(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText()))), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.pctCorretagem)).getText()))), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.pctCustodia)).getText()))), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.pctLiquidacao)).getText()))), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble((String.valueOf(((EditText) findViewById(R.id.pctNegociacao)).getText())))), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.pctIss)).getText()))), ((CheckBox)findViewById(R.id.cbIss)))));
                    //((TextView) findViewById(R.id.valMinVenda)).setText("13.20");
                }
                catch (Exception e){
                    Toast.makeText(custas, "Erro: Valor mínimo para venda", Toast.LENGTH_SHORT).show();
                }

                new Thread(){
                    public void run(){
                        try {
                            handler3.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        // 1 - Recebe o valor mínimo de venda
                                        // 2 - Multiplica pela quantidade de ações compradas
                                        // 3 - Tira do valor achado a porcentagem do STOPLOSS

                                        //((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString()))));
                                        //(Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100);
                                        double tempMaxStopLoss = (((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) - ((((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100))));

                                        double tempresultadoCalcCorretagem = new Custas().calc_Corretagem((tempMaxStopLoss / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctCorretagem)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbCorretagem)).isChecked());
                                        double tempresultadoCalcCustodia = new Custas().calc_Corretagem((tempMaxStopLoss / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctCustodia)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbCustodia)).isChecked());
                                        double tempresultadoCalcLiquidacao = new Custas().calc_Corretagem((tempMaxStopLoss / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctLiquidacao)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbLiquidacao)).isChecked());
                                        double tempresultadoCalcNegociacao = new Custas().calc_Corretagem((tempMaxStopLoss / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctNegociacao)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbNegociacao)).isChecked());
                                        double tempresultadoCalcIss = new Custas().calc_Corretagem((tempMaxStopLoss / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctIss)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbIss)).isChecked());

                                        double tempsumResultadoCalculoCustas = tempresultadoCalcCorretagem + tempresultadoCalcCustodia + tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;

                                        double tempMaxStopLossLiquido = tempMaxStopLoss - tempsumResultadoCalculoCustas;
                                        double temp2MaxStopLossLiquido = tempMaxStopLossLiquido;
                                        double aux = 0.1;

                                        //Toast.makeText(QntAcoesValorDisponivel.this, String.valueOf(tempMaxStopLoss), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(QntAcoesValorDisponivel.this, String.valueOf(tempsumResultadoCalculoCustas), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(QntAcoesValorDisponivel.this, String.valueOf(temp2MaxStopLossLiquido), Toast.LENGTH_SHORT).show();

                                        while (temp2MaxStopLossLiquido < tempMaxStopLoss){

                                            if (aux/2 == 0) {
                                                Toast.makeText(QntAcoesValorDisponivel.this, String.valueOf(temp2MaxStopLossLiquido), Toast.LENGTH_SHORT).show();
                                            }

                                            temp2MaxStopLossLiquido += aux;
                                            tempresultadoCalcCorretagem = new Custas().calc_Corretagem((temp2MaxStopLossLiquido / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctCorretagem)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbCorretagem)).isChecked());
                                            tempresultadoCalcCustodia = new Custas().calc_Corretagem((temp2MaxStopLossLiquido / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctCustodia)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbCustodia)).isChecked());
                                            tempresultadoCalcLiquidacao = new Custas().calc_Corretagem((temp2MaxStopLossLiquido / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctLiquidacao)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbLiquidacao)).isChecked());
                                            tempresultadoCalcNegociacao = new Custas().calc_Corretagem((temp2MaxStopLossLiquido / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctNegociacao)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbNegociacao)).isChecked());
                                            tempresultadoCalcIss = new Custas().calc_Corretagem((temp2MaxStopLossLiquido / Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), (Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString())), Double.parseDouble(((EditText)findViewById(R.id.pctIss)).getText().toString()), ((CheckBox) findViewById(R.id.cbFracionario)).isChecked(), ((CheckBox) findViewById(R.id.cbIss)).isChecked());

                                            tempsumResultadoCalculoCustas = tempresultadoCalcCorretagem + tempresultadoCalcCustodia + tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;

                                            aux++;
                                            temp2MaxStopLossLiquido -= tempsumResultadoCalculoCustas;
                                        }
                                        //Toast.makeText(QntAcoesValorDisponivel.this, String.valueOf(temp2MaxStopLossLiquido), Toast.LENGTH_SHORT).show();

                                        ((TextView)findViewById(R.id.valStopLoss)).setText(String.format(Locale.ENGLISH, "%.2f", temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())));



                                        //new ValorMinParaVendaSemPerdas().valMinVendaDII(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss)));
                                        //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format ((new ValorMinParaVendaSemPerdas().valMinVendaDII((((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) ), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss)))) ));



                                        //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format((new ValorMinParaVendaSemPerdas().valMinVendaDII(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss))))  /  (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                                        //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) / (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                                    }
                                    catch (Exception e) {
                                        if (((TextView)findViewById(R.id.pctStoLoss)).getText().toString().isEmpty()) {
                                            ((TextView)findViewById(R.id.pctStoLoss)).setText("0.0");
                                            Toast.makeText(QntAcoesValorDisponivel.this, "StopLoss nulo - não calculado", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(QntAcoesValorDisponivel.this, "StopLoss inválido - não calculado", Toast.LENGTH_SHORT).show();
                                            //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString())) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) / (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                                        }
                                    }
                                }
                            });
                        }
                        catch (Exception e){
                            Toast.makeText(QntAcoesValorDisponivel.this, "StopLoss - Exception", Toast.LENGTH_SHORT).show();
                            e.getMessage();
                            e.printStackTrace();
                        }
                    }
                }.start();

                /*try {
                    // 1 - Recebe o valor mínimo de venda
                    // 2 - Multiplica pela quantidade de ações compradas
                    // 3 - Tira do valor achado a porcentagem do STOPLOSS

                    //((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString()))));
                    //(Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100);
                    double tempMaxStopLoss = (((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) - ((((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100))));


                    double tempresultadoCalcCorretagem = custas.calc_Corretagem((tempMaxStopLoss / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctCorretagem)).getText().toString()), bool_cbFracionaria, bool_cbCorretagem);
                    double tempresultadoCalcCustodia = custas.calc_Corretagem((tempMaxStopLoss / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctCustodia)).getText().toString()), bool_cbFracionaria, bool_cbCustodia);
                    double tempresultadoCalcLiquidacao = custas.calc_Corretagem((tempMaxStopLoss / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctLiquidacao)).getText().toString()), bool_cbFracionaria, bool_cbLiquidacao);
                    double tempresultadoCalcNegociacao = custas.calc_Corretagem((tempMaxStopLoss / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctNegociacao)).getText().toString()), bool_cbFracionaria, bool_cbNegociacao);
                    double tempresultadoCalcIss = custas.calc_Corretagem((tempMaxStopLoss / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctIss)).getText().toString()), bool_cbFracionaria, bool_cbIss);

                    double tempsumResultadoCalculoCustas = tempresultadoCalcCorretagem + tempresultadoCalcCustodia + tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;

                    *//*if (!bool_cbFracionaria){
                        tempQuantidadeDeCotasPorValDispoivel = qntLote(tempQuantidadeDeCotasPorValDispoivel) * 100;
                    }*//*
                    double tempMaxStopLossLiquido = tempMaxStopLoss - tempsumResultadoCalculoCustas;
                    double temp2MaxStopLossLiquido = tempMaxStopLossLiquido;

                    while (temp2MaxStopLossLiquido < tempMaxStopLoss){

                        temp2MaxStopLossLiquido+=0.1;
                        tempresultadoCalcCorretagem = custas.calc_Corretagem((temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctCorretagem)).getText().toString()), bool_cbFracionaria, bool_cbCorretagem);
                        tempresultadoCalcCustodia = custas.calc_Corretagem((temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctCustodia)).getText().toString()), bool_cbFracionaria, bool_cbCustodia);
                        tempresultadoCalcLiquidacao = custas.calc_Corretagem((temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctLiquidacao)).getText().toString()), bool_cbFracionaria, bool_cbLiquidacao);
                        tempresultadoCalcNegociacao = custas.calc_Corretagem((temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctNegociacao)).getText().toString()), bool_cbFracionaria, bool_cbNegociacao);
                        tempresultadoCalcIss = custas.calc_Corretagem((temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())), tempQuantidadeDeCotasPorValDispoivel, Double.parseDouble(((EditText)findViewById(R.id.pctIss)).getText().toString()), bool_cbFracionaria, bool_cbIss);

                        tempsumResultadoCalculoCustas = tempresultadoCalcCorretagem + tempresultadoCalcCustodia + tempresultadoCalcLiquidacao + tempresultadoCalcNegociacao + tempresultadoCalcIss;

                        temp2MaxStopLossLiquido = temp2MaxStopLossLiquido - tempsumResultadoCalculoCustas;
                    }
                    Toast.makeText(custas, String.valueOf(temp2MaxStopLossLiquido), Toast.LENGTH_SHORT).show();

                    ((TextView)findViewById(R.id.valStopLoss)).setText(df2.format(temp2MaxStopLossLiquido / Double.parseDouble(((TextView) findViewById(R.id.quantidade)).getText().toString())));




                    //new ValorMinParaVendaSemPerdas().valMinVendaDII(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss)));
                    //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format ((new ValorMinParaVendaSemPerdas().valMinVendaDII((((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) ), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss)))) ));



                    //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format((new ValorMinParaVendaSemPerdas().valMinVendaDII(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))), Integer.parseInt(((TextView) findViewById(R.id.quantidade)).getText().toString()), (Double.parseDouble(((TextView) findViewById(R.id.valNecessarioParaInvestir)).getText().toString())), ((CheckBox)findViewById(R.id.cbFracionario)), (Double.parseDouble(((EditText) findViewById(R.id.pctCorretagem)).getText().toString())), ((CheckBox)findViewById(R.id.cbCorretagem)), (Double.parseDouble(((EditText) findViewById(R.id.pctCustodia)).getText().toString())), ((CheckBox)findViewById(R.id.cbCustodia)), (Double.parseDouble(((EditText) findViewById(R.id.pctLiquidacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbLiquidacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctNegociacao)).getText().toString())), ((CheckBox)findViewById(R.id.cbNegociacao)), (Double.parseDouble(((EditText) findViewById(R.id.pctIss)).getText().toString())), ((CheckBox)findViewById(R.id.cbIss))))  /  (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                    //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format(((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString()))  * (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) / (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                }
                catch (Exception e) {
                    if (((TextView)findViewById(R.id.pctStoLoss)).getText().toString().isEmpty()) {
                        ((TextView)findViewById(R.id.pctStoLoss)).setText("0.0");
                        Toast.makeText(custas, "StopLoss nulo - não calculado", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(custas, "StopLoss inválido - não calculado", Toast.LENGTH_SHORT).show();
                        //((TextView)findViewById(R.id.valStopLoss)).setText(df2.format((Double.parseDouble(((TextView)findViewById(R.id.valMinVenda)).getText().toString())) * (Double.parseDouble(((EditText)findViewById(R.id.pctStoLoss)).getText().toString())/100) / (Integer.parseInt((((TextView)findViewById(R.id.quantidadeResumo)).getText().toString())))));
                    }
                }*/







                /*tv1.setText("Corretagem FINAL: " + String.valueOf(resultadoCalcCorretagem));
                tv2.setText("Custodia FINAL: " + String.valueOf(resultadoCalcCustodia));
                tv3.setText("Liquidação FINAL: " + String.valueOf(resultadoCalcLiquidacao));
                tv4.setText("Negociação FINAL: " + String.valueOf(resultadoCalcNegociacao));
                tv5.setText("ISS FINAL: " + String.valueOf(resultadoCalcIss));
                tv6.setText("Soma das custas FINAL: " + String.valueOf(sumResultadoCalculoCustas));
                tv7.setText("Custas com valor do papel FINAL: " + String.valueOf(tempValorTotaldaCompra));
                tv8.setText("Quantidade de cotas FINAL: "+ String.valueOf(tempQuantidadeDeCotasPorValDispoivel));
                //TODO EXCLUIR APÓS TESTES

                TesteDeCustas.addView(tv1);
                TesteDeCustas.addView(tv2);
                TesteDeCustas.addView(tv3);
                TesteDeCustas.addView(tv4);
                TesteDeCustas.addView(tv5);
                TesteDeCustas.addView(tv6);
                TesteDeCustas.addView(tv7);
                TesteDeCustas.addView(tv8);*/

            }
            else {
                //Toast.makeText(this, "Falha para obter os editTexts", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            String excpt = e.getMessage();
            Toast.makeText(this, excpt, Toast.LENGTH_SHORT).show();
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
    public void custasVisibility(View view){

        LinearLayout custas = (LinearLayout) findViewById(R.id.idcustas);
        if (custas.getVisibility() == View.VISIBLE || ((LinearLayout) findViewById(R.id.custasValordopapelBruto)).getVisibility() == View.VISIBLE || ((RelativeLayout) findViewById(R.id.custasExtra)).getVisibility() == View.VISIBLE) {
            custas.setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.custasValordopapelBruto)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.custasExtra)).setVisibility(View.GONE);
        }
        else {
            custas.setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.custasValordopapelBruto)).setVisibility(View.VISIBLE);
            ((RelativeLayout) findViewById(R.id.custasExtra)).setVisibility(View.VISIBLE);
        }
    }
}
