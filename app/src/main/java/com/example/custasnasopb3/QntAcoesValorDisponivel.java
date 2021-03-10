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
    // *** OK *** Dar continuidade às implementações do Checkbutton para opção de papel fracionário -
    // testar e corrigir os cálculos - colocar para mudança automática da quantidade de corretagens
    // necessárias e recalculo.
    // *** OK *** Separrar os cálculos das custas em funções.
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

        DataBaseHelper dbhCustas = new DataBaseHelper(this);

        pct_Corretagem.setText(String.valueOf(dbhCustas.getCustas(999).getCorretagem()));
        pct_Custodia.setText(String.valueOf(dbhCustas.getCustas(999).getCustodia()));
        pct_Liquidacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()));
        pct_Negociacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_negociacao()));
        pct_Iss.setText(String.valueOf(dbhCustas.getCustas(999).getIss()));
        pct_Emolumentos.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()+dbhCustas.getCustas(999).getTx_negociacao()));


        LinearLayout custasValordopapelBruto = (LinearLayout) findViewById(R.id.custasValordopapelBruto);
        LinearLayout custas = (LinearLayout) findViewById(R.id.idcustas);
        LinearLayout custasExtra = (LinearLayout) findViewById(R.id.custasExtra);
        custasValordopapelBruto.setVisibility(View.GONE);
        custas.setVisibility(View.GONE);
        custasExtra.setVisibility(View.GONE);

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

        //TODO Criar um Try-Catch para evitar crash por edit com dado nulo ou 0.

        if (!valPapel.toString().equals("") && !valDisponivel.toString().equals("") && Double.parseDouble(valPapel.getText().toString()) != 0 && Double.parseDouble(valDisponivel.getText().toString()) != 0){
            try {
                val_Disponivel = Double.valueOf(String.valueOf(valDisponivel.getText()));
                val_Papel = Double.valueOf(String.valueOf(valPapel.getText()));
                tempQuantidadeDeCotasPorValDispoivel = (int) Math.round(val_Disponivel/val_Papel);
                //Toast.makeText(this, "Quantidade temporária de ações: " + tempQuantidadeDeCotasPorValDispoivel, Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, "Falha para obter os editTexts", Toast.LENGTH_SHORT).show();
                val_Disponivel = 0.0;
                val_Papel = 0.0;
                tempQuantidadeDeCotasPorValDispoivel = 0;
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
                resultadoCalcCorretagem = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Corretagem, bool_cbFracionaria, bool_cbCorretagem);
                resultadoCalcCustodia = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Custodia, bool_cbFracionaria, bool_cbCustodia);
                resultadoCalcLiquidacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Liquidacao, bool_cbFracionaria, bool_cbLiquidacao);
                resultadoCalcNegociacao = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Negociacao, bool_cbFracionaria, bool_cbNegociacao);
                resultadoCalcIss = custas.calc_Corretagem2(valPapel, tempQuantidadeDeCotasPorValDispoivel, pct_Iss, bool_cbFracionaria, bool_cbIss);

                sumResultadoCalculoCustas = resultadoCalcCorretagem + resultadoCalcCustodia + resultadoCalcLiquidacao + resultadoCalcNegociacao + resultadoCalcIss;

                tempValorTotaldaCompra = sumResultadoCalculoCustas + (tempQuantidadeDeCotasPorValDispoivel * val_Papel);

            }
            TextView result_val_Corretagem = (TextView) findViewById(R.id.corretagem);
            String calculo_Corretagem = String.valueOf(resultadoCalcCorretagem);
            result_val_Corretagem.setText(calculo_Corretagem);

            TextView result_val_Custodia = (TextView) findViewById(R.id.custodia);
            String str_val_Custodia = df2.format(resultadoCalcCustodia);
            result_val_Custodia.setText(str_val_Custodia);

            TextView result_val_tx_liquidacao = (TextView) findViewById(R.id.tax_liquidacao);
            String str_val_tx_liquidacao = df3.format(resultadoCalcLiquidacao);
            result_val_tx_liquidacao.setText(str_val_tx_liquidacao);

            TextView result_val_tx_negociacao = (TextView) findViewById(R.id.tax_negociacao);
            String str_val_tx_negociacao = df3.format(resultadoCalcNegociacao);
            result_val_tx_negociacao.setText(str_val_tx_negociacao);

            TextView result_tv_emolumentos = (TextView) findViewById(R.id.emolumentos);
            String str_emolumentos = df4.format(resultadoCalcLiquidacao + resultadoCalcNegociacao);
            result_tv_emolumentos.setText(str_emolumentos);

            TextView result_tv_iss = (TextView) findViewById(R.id.iss);
            String str_calc_iss = df4.format(resultadoCalcIss);
            result_tv_iss.setText(str_calc_iss);

            TextView quantidade = (TextView) findViewById(R.id.quantidade);
            quantidade.setText(tempQuantidadeDeCotasPorValDispoivel.toString());

            TextView valExclusivoDoPapel = (TextView) findViewById(R.id.valCompraDoPapel);
            String str_resul_val_Disponivel = df2.format(tempQuantidadeDeCotasPorValDispoivel * val_Papel);
            valExclusivoDoPapel.setText(str_resul_val_Disponivel);

            TextView valAInvestir = (TextView) findViewById(R.id.valNecessarioParaInvestir);
            String str_resul_valAInvestir = df2.format(tempValorTotaldaCompra);
            valAInvestir.setText(str_resul_valAInvestir);

            TextView val_PapelResumo = (TextView) findViewById(R.id.valPapelResumo);
            val_PapelResumo.setText(val_Papel.toString());

            TextView quantidadeResumo = (TextView) findViewById(R.id.quantidadeResumo);
            quantidadeResumo.setText(tempQuantidadeDeCotasPorValDispoivel.toString());

            TextView val_CustaEmolImpostos = (TextView) findViewById(R.id.valCustaEmolImpostos);
            String custo_PorOperacao = df3.format(0-sumResultadoCalculoCustas);
            val_CustaEmolImpostos.setText(custo_PorOperacao);


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

        LinearLayout custasValordopapelBruto = (LinearLayout) findViewById(R.id.custasValordopapelBruto);
        LinearLayout custas = (LinearLayout) findViewById(R.id.idcustas);
        LinearLayout custasExtra = (LinearLayout) findViewById(R.id.custasExtra);
        if (custas.getVisibility() == View.VISIBLE || custasValordopapelBruto.getVisibility() == View.VISIBLE || custasExtra.getVisibility() == View.VISIBLE) {
            custas.setVisibility(View.GONE);
            custasValordopapelBruto.setVisibility(View.GONE);
            custasExtra.setVisibility(View.GONE);
        }
        else {
            custas.setVisibility(View.VISIBLE);
            custasValordopapelBruto.setVisibility(View.VISIBLE);
            custasExtra.setVisibility(View.VISIBLE);
        }
    }
}
