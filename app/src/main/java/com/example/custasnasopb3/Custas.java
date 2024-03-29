package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Custas extends AppCompatActivity {

    protected int id;

    protected boolean corretagemFixa;
    protected double corretagem;
    protected double calc_Corretagem;

    protected boolean custodiaFixa;
    protected double custodia;
    protected double calc_Custodia;

    protected double emolumentos;
    protected boolean tx_liquidacaoFixa;
    protected double tx_liquidacao;
    protected double calc_tx_liquidacao;
    protected boolean tx_negociacaoFixa;
    protected double tx_negociacao;
    protected double calc_tx_negociacao;

    protected boolean issCobrado;
    protected boolean issFixo;
    protected double iss;
    protected double calc_iss;

    protected double ir_compra;
    protected double calc_ir_compra;
    protected double ir_venda;
    protected double calc_ir_venda;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custas);

        DataBaseHelper dbhCustas = new DataBaseHelper(this);
        //Custas custasStandard = new Custas(999, 1.99,0.0,0.0275,0.003247, 0.01, 1, 1, 0, 0, 0);
        //dbhCustas.addCustas(custasStandard);

        int custasPadrao = 999;
        Custas custas;
        try {
            double corret = (dbhCustas.getCustas(custasPadrao).getCorretagem());
            double cust = (dbhCustas.getCustas(custasPadrao).getCustodia());
            double liquid = (dbhCustas.getCustas(custasPadrao).getTx_liquidacao());
            double negoc = (dbhCustas.getCustas(custasPadrao).getTx_negociacao());
            double isss = (dbhCustas.getCustas(custasPadrao).getIss());
            boolean blcorr = (dbhCustas.getCustas(custasPadrao).isCorretagemFixa());
            boolean blcust = (dbhCustas.getCustas(custasPadrao).isCustodiaFixa());
            boolean bltxliq = (dbhCustas.getCustas(custasPadrao).isTx_liquidacaoFixa());
            boolean bltxneg = (dbhCustas.getCustas(custasPadrao).isTx_negociacaoFixa());
            boolean bliss = (dbhCustas.getCustas(custasPadrao).isIssFixo());

            custas = new Custas(custasPadrao, corret, cust, liquid, negoc, isss, blcorr, blcust, bltxliq, bltxneg, bliss);
        }
        catch (Exception e){
            Toast.makeText(this,"Parâmetros não definidos!", Toast.LENGTH_SHORT).show();
            custas = new Custas(999, 0.0,0.0,0.0,0.0, 0.0, false, false, false, false, false);
            dbhCustas.addCustas(custas);
        }


        /*EditText pctCorretagem2 = (EditText) findViewById(R.id.pctCorretagem2);
        pctCorretagem2.setText(String.valueOf(custas.getCorretagem()));*/
        ((EditText) findViewById(R.id.pctCorretagem2)).setText(String.valueOf(custas.getCorretagem()));

        /*EditText pctCustodia2 = (EditText) findViewById(R.id.pctCustodia2);
        pctCustodia2.setText(String.valueOf(custas.getCustodia()));*/
        ((EditText) findViewById(R.id.pctCustodia2)).setText(String.valueOf(custas.getCustodia()));

        /*TextView pctEmolumentos2 = (TextView) findViewById(R.id.pctEmolumentos2);
        pctEmolumentos2.setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));*/
        ((TextView) findViewById(R.id.pctEmolumentos2)).setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));

        /*EditText pctLiquidacao2 = (EditText) findViewById(R.id.pctLiquidacao2);
        pctLiquidacao2.setText(String.valueOf(custas.getTx_liquidacao()));*/
        ((EditText) findViewById(R.id.pctLiquidacao2)).setText(String.valueOf(custas.getTx_liquidacao()));

        /*EditText pctNegociacao2 = (EditText) findViewById(R.id.pctNegociacao2);
        pctNegociacao2.setText(String.valueOf(custas.getTx_negociacao()));*/
        ((EditText) findViewById(R.id.pctNegociacao2)).setText(String.valueOf(custas.getTx_negociacao()));

        /*EditText pctIss2 = (EditText) findViewById(R.id.pctIss2);
        pctIss2.setText(String.valueOf(custas.getIss()));*/
        ((EditText) findViewById(R.id.pctIss2)).setText(String.valueOf(custas.getIss()));

        if (dbhCustas.getCustas(custasPadrao).isCorretagemFixa()){
            ((CheckBox) findViewById(R.id.cbCorretagem2)).setChecked(true);
            ((TextView) findViewById(R.id.txtCOCorretagem)).setText(R.string.corretagemReal);
        }
        else {
            ((TextView) findViewById(R.id.txtCOCorretagem)).setText(R.string.corretagemPorcentagem);
        }
        ((CheckBox) findViewById(R.id.cbCorretagem2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ((TextView) findViewById(R.id.txtCOCorretagem)).setText(R.string.corretagemReal);
                }
                else {
                    ((TextView) findViewById(R.id.txtCOCorretagem)).setText(R.string.corretagemPorcentagem);
                }
            }
        });

        //CheckBox cbCustodia2 = (CheckBox) findViewById(R.id.cbCustodia2);
        if (dbhCustas.getCustas(custasPadrao).isCustodiaFixa()){
            ((CheckBox) findViewById(R.id.cbCustodia2)).setChecked(true);
            ((TextView) findViewById(R.id.txtCOCustodia)).setText(R.string.custodiaReal);
        }
        else {
            ((TextView) findViewById(R.id.txtCOCustodia)).setText(R.string.custodiaPorcentagem);
        }
        ((CheckBox) findViewById(R.id.cbCustodia2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    ((TextView) findViewById(R.id.txtCOCustodia)).setText(R.string.custodiaReal);
                }
                else {
                    ((TextView) findViewById(R.id.txtCOCustodia)).setText(R.string.custodiaPorcentagem);
                }
            }
        });

        //CheckBox cbLiquidacao2 = (CheckBox) findViewById(R.id.cbLiquidacao2);
        if (dbhCustas.getCustas(custasPadrao).isTx_liquidacaoFixa()){
            ((CheckBox) findViewById(R.id.cbLiquidacao2)).setChecked(true);
            ((TextView) findViewById(R.id.txtCOLiquidacao)).setText(R.string.TxLiquidacaoReal);
        }
        else {
            ((TextView) findViewById(R.id.txtCOLiquidacao)).setText(R.string.TxLiquidacaoPorcentagem);
        }
        ((CheckBox) findViewById(R.id.cbLiquidacao2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
               if (isChecked){
                   ((TextView) findViewById(R.id.txtCOLiquidacao)).setText(R.string.TxLiquidacaoReal);
               }
               else {
                   ((TextView) findViewById(R.id.txtCOLiquidacao)).setText(R.string.TxLiquidacaoPorcentagem);
               }
           }
        });

        //CheckBox cbNegociacao2 = (CheckBox) findViewById(R.id.cbNegociacao2);
        if (dbhCustas.getCustas(custasPadrao).isTx_negociacaoFixa()){
            ((CheckBox) findViewById(R.id.cbNegociacao2)).setChecked(true);
            ((TextView) findViewById(R.id.txtCONegociacao)).setText(R.string.TxNegociacaoReal);
        }
        else {
            ((TextView) findViewById(R.id.txtCONegociacao)).setText(R.string.TxNegociacaoPorcentagem);
        }
        ((CheckBox) findViewById(R.id.cbNegociacao2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    ((TextView) findViewById(R.id.txtCONegociacao)).setText(R.string.TxNegociacaoReal);
                }
                else {
                    ((TextView) findViewById(R.id.txtCONegociacao)).setText(R.string.TxNegociacaoPorcentagem);
                }
            }
        });

        //CheckBox cbIss2 = (CheckBox) findViewById(R.id.cbIss2);
        if (dbhCustas.getCustas(custasPadrao).isIssFixo()){
            ((CheckBox) findViewById(R.id.cbIss2)).setChecked(true);
            ((TextView) findViewById(R.id.txtCOIss)).setText(R.string.IssReal);
        }
        else {
            ((TextView) findViewById(R.id.txtCOIss)).setText(R.string.IssPorcentagem);
        }
        ((CheckBox) findViewById(R.id.cbIss2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    ((TextView) findViewById(R.id.txtCOIss)).setText(R.string.IssReal);
                }
                else {
                    ((TextView) findViewById(R.id.txtCOIss)).setText(R.string.IssPorcentagem);
                }
            }
        });
        dbhCustas.close();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorretagemFixa() {
        return corretagemFixa;
    }

    public void setCorretagemFixa(boolean corretagemFixa) {
        this.corretagemFixa = corretagemFixa;
    }

    public double getCorretagem() {
        return corretagem;
    }

    public void setCorretagem(double corretagem) {
        this.corretagem = corretagem;
    }

    public double getCalc_Corretagem() {
        return calc_Corretagem;
    }

    public void setCalc_Corretagem(double calc_Corretagem) {
        this.calc_Corretagem = calc_Corretagem;
    }

    public boolean isCustodiaFixa() {
        return custodiaFixa;
    }

    public void setCustodiaFixa(boolean custodiaFixa) {
        this.custodiaFixa = custodiaFixa;
    }

    public double getCustodia() {
        return custodia;
    }

    public void setCustodia(double custodia) {
        this.custodia = custodia;
    }

    public double getCalc_Custodia() {
        return calc_Custodia;
    }

    public void setCalc_Custodia(double calc_Custodia) {
        this.calc_Custodia = calc_Custodia;
    }

    public double getEmolumentos() {
        return emolumentos;
    }

    public void setEmolumentos(double emolumentos) {
        this.emolumentos = emolumentos;
    }

    public double getTx_liquidacao() {
        return tx_liquidacao;
    }

    public void setTx_liquidacao(double tx_liquidacao) {
        this.tx_liquidacao = tx_liquidacao;
    }

    public double getCalc_tx_liquidacao() {
        return calc_tx_liquidacao;
    }

    public void setCalc_tx_liquidacao(double calc_tx_liquidacao) {
        this.calc_tx_liquidacao = calc_tx_liquidacao;
    }

    public double getTx_negociacao() {
        return tx_negociacao;
    }

    public void setTx_negociacao(double tx_negociacao) {
        this.tx_negociacao = tx_negociacao;
    }

    public double getCalc_tx_negociacao() {
        return calc_tx_negociacao;
    }

    public void setCalc_tx_negociacao(double calc_tx_negociacao) {
        this.calc_tx_negociacao = calc_tx_negociacao;
    }

    public boolean isIssCobrado() {
        return issCobrado;
    }

    public void setIssCobrado(boolean issCobrado) {
        this.issCobrado = issCobrado;
    }

    public double getIss() {
        return iss;
    }

    public void setIss(double iss) {
        this.iss = iss;
    }

    public double getCalc_iss() {
        return calc_iss;
    }

    public void setCalc_iss(double calc_iss) {
        this.calc_iss = calc_iss;
    }

    public boolean isTx_liquidacaoFixa() {
        return tx_liquidacaoFixa;
    }

    public void setTx_liquidacaoFixa(boolean tx_liquidacaoFixa) {
        this.tx_liquidacaoFixa = tx_liquidacaoFixa;
    }

    public boolean isTx_negociacaoFixa() {
        return tx_negociacaoFixa;
    }

    public void setTx_negociacaoFixa(boolean tx_negociacaoFixa) {
        this.tx_negociacaoFixa = tx_negociacaoFixa;
    }

    public boolean isIssFixo() {
        return issFixo;
    }

    public void setIssFixo(boolean issFixo) {
        this.issFixo = issFixo;
    }

    public Custas() {
    }

    public Custas(int id) {
        this.id = id;
    }

    public Custas(int id, double corretagem, double custodia, double tx_liquidacao, double tx_negociacao, double iss) {
        this.id = id;
        this.corretagem = corretagem;
        this.custodia = custodia;
        this.tx_liquidacao = tx_liquidacao;
        this.tx_negociacao = tx_negociacao;
        this.iss = iss;
    }

    public Custas(int id, double corretagem, double custodia, double tx_liquidacao, double tx_negociacao, double iss, boolean corretagemFixa, boolean custodiaFixa, boolean tx_liquidacaoFixa, boolean tx_negociacaoFixa, boolean issFixo) {
        this.id = id;
        this.corretagem = corretagem;
        this.custodia = custodia;
        this.tx_liquidacao = tx_liquidacao;
        this.tx_negociacao = tx_negociacao;
        this.iss = iss;
        this.corretagemFixa = corretagemFixa;
        this.custodiaFixa = custodiaFixa;
        this.tx_liquidacaoFixa = tx_liquidacaoFixa;
        this.tx_negociacaoFixa = tx_negociacaoFixa;
        this.issFixo = issFixo;
    }

    public int boolToInt(Boolean bool){
        if(bool){
            return 1;
        }
        else{
            return 0;
        }
    }
    public boolean intToBool(Integer integer){
        if(integer == 1){
            return true;
        }
        else{
            return false;
        }
    }

    //*** OK *** Corrigir e checkar o método de salvar as custas Standard.
    //*** OK *** Vincular as custas o "salvar" do papel no banco de dados - com base no ID - utilizar o uptadeCustas().

    public void salvarCustas(View view, int id){
        DataBaseHelper dbhCustas = new DataBaseHelper(this);
        Custas custas = new Custas();
        if(id!=999){
            custas.setId(id);
        }
        else{
            id=0;
            custas.setId(id);
        }

        EditText pctCorretagem2 = (EditText) findViewById(R.id.pctCorretagem2);
        Double db_pctCorretagem2 = Double.parseDouble(String.valueOf(pctCorretagem2.getText()));
        custas.setCorretagem(db_pctCorretagem2);

        EditText pctCustodia2 = (EditText) findViewById(R.id.pctCustodia2);
        Double db_pctCustodia2 = Double.valueOf(String.valueOf(pctCustodia2.getText()));
        custas.setCustodia(db_pctCustodia2);

        EditText pctLiquidacao2 = (EditText) findViewById(R.id.pctLiquidacao2);
        Double db_pctLiquidacao2 = Double.parseDouble(String.valueOf(pctLiquidacao2.getText()));
        custas.setTx_liquidacao(db_pctLiquidacao2);

        EditText pctNegociacao2 = (EditText) findViewById(R.id.pctNegociacao2);
        Double db_pctNegociacao2 = Double.parseDouble((String.valueOf(pctNegociacao2.getText())));
        custas.setTx_negociacao(db_pctNegociacao2);

        EditText pctIss2 = (EditText) findViewById(R.id.pctIss2);
        Double db_pctIss2 = Double.parseDouble((String.valueOf(pctIss2.getText())));
        custas.setIss(db_pctIss2);

        custas = new Custas(id, db_pctCorretagem2, db_pctCustodia2, db_pctLiquidacao2, db_pctNegociacao2, db_pctIss2);

        dbhCustas.addCustas(custas);
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        dbhCustas.close();
    }
    public void standardCustas(View view){
        DataBaseHelper dbhCustas = new DataBaseHelper(this);
        Custas custas = new Custas();
        id=999;
        custas.setId(id);

        EditText pctCorretagem2 = (EditText) findViewById(R.id.pctCorretagem2);
        Double db_pctCorretagem2 = Double.parseDouble(String.valueOf(pctCorretagem2.getText()));
        custas.setCorretagem(db_pctCorretagem2);

        EditText pctCustodia2 = (EditText) findViewById(R.id.pctCustodia2);
        Double db_pctCustodia2 = Double.valueOf(String.valueOf(pctCustodia2.getText()));
        custas.setCustodia(db_pctCustodia2);

        EditText pctLiquidacao2 = (EditText) findViewById(R.id.pctLiquidacao2);
        Double db_pctLiquidacao2 = Double.parseDouble(String.valueOf(pctLiquidacao2.getText()));
        custas.setTx_liquidacao(db_pctLiquidacao2);

        EditText pctNegociacao2 = (EditText) findViewById(R.id.pctNegociacao2);
        Double db_pctNegociacao2 = Double.parseDouble((String.valueOf(pctNegociacao2.getText())));
        custas.setTx_negociacao(db_pctNegociacao2);

        EditText pctIss2 = (EditText) findViewById(R.id.pctIss2);
        Double db_pctIss2 = Double.parseDouble((String.valueOf(pctIss2.getText())));
        custas.setIss(db_pctIss2);

        CheckBox cbCorretagem2 = (CheckBox) findViewById(R.id.cbCorretagem2);
        Boolean isChecked_cbCorretagem2 = cbCorretagem2.isChecked();
        custas.setCorretagemFixa(isChecked_cbCorretagem2);

        CheckBox cbCustodia2 = (CheckBox) findViewById(R.id.cbCustodia2);
        Boolean isChecked_cbCustodia2 = cbCustodia2.isChecked();
        custas.setCustodiaFixa(isChecked_cbCustodia2);

        CheckBox cbLiquidacao2 = (CheckBox) findViewById(R.id.cbLiquidacao2);
        Boolean isChecked_cbLiquidacao2 = cbLiquidacao2.isChecked();
        custas.setTx_liquidacaoFixa(isChecked_cbLiquidacao2);

        CheckBox cbNegociacao2 = (CheckBox) findViewById(R.id.cbNegociacao2);
        Boolean isChecked_cbNegociacao2 = cbNegociacao2.isChecked();
        custas.setTx_negociacaoFixa(isChecked_cbNegociacao2);

        CheckBox cbIss2 = (CheckBox) findViewById(R.id.cbIss2);
        Boolean isChecked_cbIss2 = cbIss2.isChecked();
        custas.setIssFixo(isChecked_cbIss2);

        custas = new Custas(id, db_pctCorretagem2, db_pctCustodia2, db_pctLiquidacao2, db_pctNegociacao2, db_pctIss2, isChecked_cbCorretagem2, isChecked_cbCustodia2, isChecked_cbLiquidacao2, isChecked_cbNegociacao2, isChecked_cbIss2);
        //custas = new Custas(id, db_pctCorretagem2, db_pctCustodia2, db_pctLiquidacao2, db_pctNegociacao2, db_pctIss2, ((CheckBox) findViewById(R.id.cbCorretagem2)).isChecked(), isChecked_cbCustodia2, isChecked_cbLiquidacao2, isChecked_cbNegociacao2, isChecked_cbIss2);

        try {
            dbhCustas.addCustas(custas);
        }catch (Exception e){
            Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }
        dbhCustas.updateCustas(custas, id);
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        dbhCustas.close();
    }

    public Double calc_Corretagem (Double valorPapel, Integer tempQuantidadeDeCotasPorValDispoivel, Double custa, Boolean fracionario, Boolean custaFixa){

        //Função já disponibiliza o resultado do cálculo de corretagem.

        //O filtro do fracionário é dentro desta função.

        //Função coleta o valor do papel, quantidade - que já é o valor disponível pelo valor do papel
        // - o valor da view de corretagem em uma view, recebe se é fracionário ou não e faz o cálculo de corretagem -
        // na classe utilizada deve ser feito o resto dos tratamentos e recálculo.

        Double db_valorPapel = valorPapel;
        Double db_custa = custa;

        Double resultadoCalcCorretagem;

        try {
            //SE FOR FRACIONARIA
            if (fracionario){
                //DETERMINAR A QUANTIDADE DE MICROLOTES FRACIONÁRIOS (99 COTAS)
                int tempQuantidade = tempQuantidadeDeCotasPorValDispoivel;
                int countCorretagem = 0;
                while (tempQuantidade>= 1){
                    countCorretagem += 1;
                    tempQuantidade -=99;
                }
                //SE FOR CORRETAGEM FIXA
                if (custaFixa) {
                    resultadoCalcCorretagem = db_custa * countCorretagem;
                    return resultadoCalcCorretagem;
                }
                //SE FOR CORRETAGEM COM BASE NA PORCENTAGEM DO MONTANTE TOTAL USADO NA COMPRA
                else {
                    tempQuantidade = tempQuantidadeDeCotasPorValDispoivel;
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_custa);
                    return resultadoCalcCorretagem;
                }
            }
            //SE NÃO FOR FRACIONARIA
            else {
                //SEPARA PARA PEGAR APENAS OS LOTES FECHADOS (100 COTAS POR LOTE)
                int tempQuantidade = (int) (Math.round(tempQuantidadeDeCotasPorValDispoivel) / 100) * 100;
                //SE FOR CORRETAGEM FIXA
                if (custaFixa){
                    resultadoCalcCorretagem = db_custa;
                    return resultadoCalcCorretagem;
                }
                //SE FOR CORRETAGEM COM BASE NA PORCENTAGEM DO MONTANTE TOTAL USADO NA COMPRA
                else {
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_custa);
                    return resultadoCalcCorretagem;
                }
            }
        }
        catch (Exception e){
            return resultadoCalcCorretagem = 0.0;
        }
    }

    //-------------------------------------------------

    public Double calc_Corretagem2 (EditText valorPapel, Integer tempQuantidadeDeCotasPorValDispoivel, EditText custa, Boolean fracionario, Boolean custaFixa){

        //Função já disponibiliza o resultado do cálculo de corretagem.

        //O filtro do fracionário é dentro desta função.

        //Função coleta o valor do papel, quantidade (que já é o valor disponível pelo valor do papel),
        // o valor da view de corretagem em uma view, recebe se é fracionário ou não e faz o cálculo de corretagem
        // (na classe utilizada deve ser feito o resto dos tratamentos e recálculo).

        Double db_valorPapel = Double.valueOf(String.valueOf(valorPapel.getText()));
        Double db_corretagem = Double.valueOf(String.valueOf(custa.getText()));

        Double resultadoCalcCorretagem;

        try {
            //SE FOR FRACIONARIA
            if (fracionario){
                //DETERMINAR A QUANTIDADE DE MICROLOTES FRACIONÁRIOS (99 COTAS)
                int tempQuantidade = tempQuantidadeDeCotasPorValDispoivel;
                int countCorretagem = 0;
                while (tempQuantidade>= 1){
                    countCorretagem += 1;
                    tempQuantidade -=99;
                }
                //SE FOR CORRETAGEM FIXA
                if (custaFixa) {
                    resultadoCalcCorretagem = db_corretagem * countCorretagem;
                    return resultadoCalcCorretagem;
                }
                //SE FOR CORRETAGEM COM BASE NA PORCENTAGEM DO MONTANTE TOTAL USADO NA COMPRA
                else {
                    tempQuantidade = tempQuantidadeDeCotasPorValDispoivel;
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_corretagem);
                    return resultadoCalcCorretagem;
                }
            }
            //SE NÃO FOR FRACIONARIA
            else {
                //SEPARA PARA PEGAR APENAS OS LOTES FECHADOS (100 COTAS POR LOTE)
                int tempQuantidade = (int) (Math.round(tempQuantidadeDeCotasPorValDispoivel) / 100) * 100;
                //SE FOR CORRETAGEM FIXA
                if (custaFixa){
                    resultadoCalcCorretagem = db_corretagem;
                    return resultadoCalcCorretagem;
                }
                //SE FOR CORRETAGEM COM BASE NA PORCENTAGEM DO MONTANTE TOTAL USADO NA COMPRA
                else {
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_corretagem);
                    return resultadoCalcCorretagem;
                }
            }
        }
        catch (Exception e){
            return resultadoCalcCorretagem = 0.0;
        }
    }


    public double calc_Custodia(){
        double resultadoCustodia = 0;

        return resultadoCustodia;
    }





    //--------------------------------------------------

    public double Porcentagem(double valor, double porcentagem){
        Double resultado = (valor*porcentagem)/100;
        return resultado;
    }


    public String toStringId(Integer id) {
        DataBaseHelper dbhCustas = new DataBaseHelper(this);

        int tempid =  dbhCustas.getCustas(id).getId();
        double tempcorretagem = dbhCustas.getCustas(id).getCorretagem();
        double tempcustodia = dbhCustas.getCustas(id).getCustodia();
        double temptx_liquidacao = dbhCustas.getCustas(id).getTx_liquidacao();
        double temptx_negociacao = dbhCustas.getCustas(id).getTx_negociacao();
        double tempiss = dbhCustas.getCustas(id).getIss();
        dbhCustas.close();
        return "Custas:\n" +
                "id=" + tempid +
                ", \n corretagem=" + tempcorretagem +
                ", \n custodia=" + tempcustodia +
                ", \n tx_liquidacao=" + temptx_liquidacao +
                ", \n tx_negociacao=" + temptx_negociacao +
                ", \n iss=" + tempiss + "\n";
    }
}