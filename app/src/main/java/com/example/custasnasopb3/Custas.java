package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Custas extends AppCompatActivity {

    protected int id;

    protected boolean corretagemFixa;
    protected double corretagem;
    protected double calc_Corretagem = 0.0;

    protected boolean custodiaFixa;
    protected double custodia;
    protected double calc_Custodia= 0.0;

    protected double emolumentos;
    protected boolean tx_liquidacaoFixa;
    protected double tx_liquidacao;
    protected double calc_tx_liquidacao;
    protected boolean tx_negociacaoFixa;
    protected double tx_negociacao;
    protected double calc_tx_negociacao;

    protected boolean issCobrado = true;
    protected boolean issFixo;
    protected double iss;
    protected double calc_iss;

    protected double ir_compra;
    protected double calc_ir_compra;
    protected double ir_venda;
    protected double calc_ir_venda;

    DataBaseHelper dbhCustas = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custas);

        //Custas custasStandard = new Custas(999, 1.99,0.0,0.0275,0.003247, 0.01, 1, 1, 0, 0, 0);
        //dbhCustas.addCustas(custasStandard);

        int custasPadrão = 999;
        Custas custas = new Custas();
        try {
            custas = dbhCustas.getCustas(custasPadrão);
        }
        catch (Exception e){
            Toast.makeText(this,"Erro!", Toast.LENGTH_SHORT);
        }


        EditText pctCorretagem2 = (EditText) findViewById(R.id.pctCorretagem2);
        pctCorretagem2.setText(String.valueOf(custas.getCorretagem()));

        EditText pctCustodia2 = (EditText) findViewById(R.id.pctCustodia2);
        pctCustodia2.setText(String.valueOf(custas.getCustodia()));

        TextView pctEmolumentos2 = (TextView) findViewById(R.id.pctEmolumentos2);
        pctEmolumentos2.setText(String.valueOf(custas.getTx_liquidacao()+custas.getTx_negociacao()));

        EditText pctLiquidacao2 = (EditText) findViewById(R.id.pctLiquidacao2);
        pctLiquidacao2.setText(String.valueOf(custas.getTx_liquidacao()));

        EditText pctNegociacao2 = (EditText) findViewById(R.id.pctNegociacao2);
        pctNegociacao2.setText(String.valueOf(custas.getTx_negociacao()));

        EditText pctIss2 = (EditText) findViewById(R.id.pctIss2);
        pctIss2.setText(String.valueOf(custas.getIss()));

        CheckBox cbCorretagem2 = (CheckBox) findViewById(R.id.cbCorretagem2);
        if (dbhCustas.getCustas(custasPadrão).isCorretagemFixa()){
            cbCorretagem2.setChecked(true);
        }
        else {
            cbCorretagem2.setChecked(false);
        }

        CheckBox cbCustodia2 = (CheckBox) findViewById(R.id.cbCustodia2);
        if (dbhCustas.getCustas(custasPadrão).isCustodiaFixa()){
            cbCustodia2.setChecked(true);
        }
        else {
            cbCustodia2.setChecked(false);
        }

        CheckBox cbLiquidacao2 = (CheckBox) findViewById(R.id.cbLiquidacao2);
        if (dbhCustas.getCustas(custasPadrão).isTx_liquidacaoFixa()){
            cbLiquidacao2.setChecked(true);
        }
        else {
            cbLiquidacao2.setChecked(false);
        }

        CheckBox cbNegociacao2 = (CheckBox) findViewById(R.id.cbNegociacao2);
        if (dbhCustas.getCustas(custasPadrão).isTx_negociacaoFixa()){
            cbNegociacao2.setChecked(true);
        }
        else {
            cbNegociacao2.setChecked(false);
        }

        CheckBox cbIss2 = (CheckBox) findViewById(R.id.cbIss2);
        if (dbhCustas.getCustas(custasPadrão).isIssFixo()){
            cbIss2.setChecked(true);
        }
        else {
            cbIss2.setChecked(false);
        }
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

    //TODO Corrigir e checkar o método de salvar as custas Standard.
    //TODO Vincular as custas o "salvar" do papel no banco de dados - com base no ID - utilizar o uptadeCustas().

    public void salvarCustas(View view, int id){
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

    }
    public void standardCustas(View view){
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

        //dbhCustas.addCustas(custas);
        dbhCustas.updateCustas(custas, id);
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        dbhCustas.close();
    }

    public Double calc_Corretagem (EditText valorDisponivel, EditText valorPapel, EditText corretagem, Boolean corretagemFixa, Boolean fracionario){

        //Função já disponibiliza o resultado do cálculo de corretagem.

        //O filtro do fracionário é dentro desta função.

        //Função coleta o valor do papel, quantidade - que já é o valor disponível pelo valor do papel
        // - o valor da view de corretagem em uma view, recebe se é fracionário ou não e faz o cálculo de corretagem -
        // na classe utilizada deve ser feito o resto dos tratamentos e recálculo.

        Double db_valorDiponivel = Double.valueOf(String.valueOf(valorDisponivel.getText()));
        Double db_valorPapel = Double.valueOf(String.valueOf(valorPapel.getText()));
        Double db_corretagem = Double.valueOf(String.valueOf(corretagem.getText()));

        Double parcialQuantidade_valdisponvelDivididovalPapel = db_valorDiponivel/db_valorPapel;

        Double resultadoCalcCorretagem =0.0;

        try {
            //SE FOR FRACIONARIA
            if (fracionario){
                int tempQuantidade = (int) Math.round(parcialQuantidade_valdisponvelDivididovalPapel) / 99;
                int tempparcialQuantidade_valdisponvelDivididovalPapel = (int) Math.round(parcialQuantidade_valdisponvelDivididovalPapel);

                int countCorretagem = 0;
                while (tempparcialQuantidade_valdisponvelDivididovalPapel>= 1){
                    countCorretagem += 1;
                    tempparcialQuantidade_valdisponvelDivididovalPapel -=99;
                }

                if (corretagemFixa) {
                    resultadoCalcCorretagem = db_corretagem * countCorretagem;
                    return resultadoCalcCorretagem;
                }
                else {
                    tempQuantidade = Integer.valueOf(parcialQuantidade_valdisponvelDivididovalPapel.intValue());;
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_corretagem);
                    return resultadoCalcCorretagem;
                }
            }
            //SE NÃO FOR FRACIONARIA
            else {
                int tempQuantidade = (int) (Math.round(parcialQuantidade_valdisponvelDivididovalPapel) / 100) * 100;
                if (corretagemFixa){
                    resultadoCalcCorretagem = db_corretagem;
                    return resultadoCalcCorretagem;
                }
                else {
                    resultadoCalcCorretagem = Porcentagem((db_valorPapel * tempQuantidade), db_corretagem);
                    return resultadoCalcCorretagem;
                }
            }
        }
        catch (Exception e){
            return resultadoCalcCorretagem;
        }
    }

    public double Porcentagem(double valor, double porcentagem){
        Double resultado = (valor*porcentagem)/100;
        return resultado;
    }


    public String toStringId(Integer id) {
        return "Custas:\n" +
                "id=" + dbhCustas.getCustas(id).getId() +
                ", \n corretagem=" + dbhCustas.getCustas(id).getCorretagem() +
                ", \n calc_Corretagem=" + dbhCustas.getCustas(id).getCalc_Corretagem() +
                ", \n custodia=" + dbhCustas.getCustas(id).getCustodia() +
                ", \n calc_Custodia=" + dbhCustas.getCustas(id).getCalc_Custodia() +
                ", \n tx_liquidacao=" + dbhCustas.getCustas(id).getTx_liquidacao() +
                ", \n calc_tx_liquidacao=" + dbhCustas.getCustas(id).getCalc_tx_liquidacao() +
                ", \n tx_negociacao=" + dbhCustas.getCustas(id).getTx_negociacao() +
                ", \n calc_tx_negociacao=" + dbhCustas.getCustas(id).getCalc_tx_negociacao() +
                ", \n iss=" + dbhCustas.getCustas(id).getIss() +
                ", \n calc_iss=" + dbhCustas.getCustas(id).getCalc_iss() + "\n";
    }
}