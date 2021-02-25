package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Custas extends AppCompatActivity {

    protected int id;

    protected boolean corretagemFixa = true;
    protected double corretagem;
    protected double calc_Corretagem = 0.0;

    protected boolean custodiaFixa = false;
    protected double custodia;
    protected double calc_Custodia= 0.0;

    protected double emolumentos;
    protected double tx_liquidacao;
    protected double calc_tx_liquidacao;
    protected double tx_negociacao;
    protected double calc_tx_negociacao;

    protected boolean issCobrado = true;
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

        //Custas custasStandard = new Custas(999, 1.99,0.0,0.0275,0.003247, 0.01);
        //dbhCustas.addCustas(custasStandard);

        Custas custas = new Custas();
        try {
            custas = dbhCustas.getCustas(999);
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

        dbhCustas.updateCustas(custas, 999);
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();

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