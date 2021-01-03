package com.example.custasnasopb3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class QntAcoesValorDisponivel extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qnt_acoes_valor_disponivel);
    }
    public void calQuantidadeAcaoValBruto (View view){
        DecimalFormat df2 = new DecimalFormat("0.00");
        DecimalFormat df3 = new DecimalFormat("0.000");
        DecimalFormat df4 = new DecimalFormat("0.0000");

        EditText valDisponivel = (EditText) findViewById(R.id.valDisponivel);
        EditText valPapel = (EditText) findViewById(R.id.valPapel);

        Double val_Disponivel = 0.0;
        Double val_Papel = 0.0;
        Double custoPorOperacao = 0.0;

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

        if(valDisponivel != null && valPapel != null){
            val_Disponivel = Double.parseDouble(valDisponivel.getText().toString());
            val_Papel = Double.parseDouble(valPapel.getText().toString());

    //partOneValor corresponde ao número bruto da quantidade de papeis que será póssível com o valor bruto.
    //partTwoQuantidade corresponde a quantidade, JÁ INTEIRO, de ações que será possível com o valor bruto. Ou seja, ainda sem tirar as custas - esse valor poderá ser alterado.
    //resul_val_Disponivel corresponte ao valor bruto utilizado para compra das ações. Ou seja, sem cáculo das custas. ESTE É O VALOR UTILIZADO PARA O CÁLCULO DOS EMOLUMENTOS.
    //custoPorOperação corresponde ao valor total das custas e emolumentos;
    //val_DisponivelLiquido corresponde ao valor retirando as custas - JÁ É O VALOR FINAL PARA INVESTIR

            //  *** TASKs ***
            // *** OK *** DEFINIR PRIMEIRO O VALOR BRUTO UTILIZADO PARA A COMPRA DAS AÇÕES.
            //CALCULAR CUSTAS E EMOLUMENTOS.
            //VERIFICAR SE MANTÉM A QUANTIDADE DE AÇÕES CONSIDERANDO O VALOR DISPONÍVEL, CASO NÃO, DIMINUIR A QUANTIDADE EM 1 AÇÃO.

            Double partOneValor = val_Disponivel/val_Papel;
            Integer partTwoQuantidade = Integer.valueOf(partOneValor.intValue());
            double resul_val_Disponivel = partTwoQuantidade*val_Papel;

            if(partTwoQuantidade>=1){

        // CÁLCULO DAS CUSTAS
            //CORRETAGEM

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
    public double Porcentagem(double valor, double porcentagem){
        Double resultado = (valor*porcentagem)/100;
        return resultado;
    }
}
