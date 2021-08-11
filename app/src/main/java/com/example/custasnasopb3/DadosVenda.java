package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;

public class DadosVenda extends AppCompatActivity {

    protected int id;
    protected double valorVenda;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
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

    public boolean isTx_liquidacaoFixa() {
        return tx_liquidacaoFixa;
    }

    public void setTx_liquidacaoFixa(boolean tx_liquidacaoFixa) {
        this.tx_liquidacaoFixa = tx_liquidacaoFixa;
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

    public boolean isTx_negociacaoFixa() {
        return tx_negociacaoFixa;
    }

    public void setTx_negociacaoFixa(boolean tx_negociacaoFixa) {
        this.tx_negociacaoFixa = tx_negociacaoFixa;
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

    public boolean isIssFixo() {
        return issFixo;
    }

    public void setIssFixo(boolean issFixo) {
        this.issFixo = issFixo;
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

    public DadosVenda() {
    }

    public DadosVenda(int id, double valorVenda, boolean corretagemFixa, double corretagem, boolean custodiaFixa, double custodia, boolean tx_liquidacaoFixa, double tx_liquidacao, boolean tx_negociacaoFixa, double tx_negociacao, boolean issFixo, double iss) {
        this.id = id;
        this.valorVenda = valorVenda;
        this.corretagemFixa = corretagemFixa;
        this.corretagem = corretagem;
        this.custodiaFixa = custodiaFixa;
        this.custodia = custodia;
        this.tx_liquidacaoFixa = tx_liquidacaoFixa;
        this.tx_liquidacao = tx_liquidacao;
        this.tx_negociacaoFixa = tx_negociacaoFixa;
        this.tx_negociacao = tx_negociacao;
        this.issFixo = issFixo;
        this.iss = iss;
    }

    @Override
    public String toString() {
        return "DadosVenda{" +
                "id=" + id +
                ", valorVenda=" + valorVenda +
                ", corretagemFixa=" + corretagemFixa +
                ", corretagem=" + corretagem +
                ", custodiaFixa=" + custodiaFixa +
                ", custodia=" + custodia +
                ", tx_liquidacaoFixa=" + tx_liquidacaoFixa +
                ", tx_liquidacao=" + tx_liquidacao +
                ", tx_negociacaoFixa=" + tx_negociacaoFixa +
                ", tx_negociacao=" + tx_negociacao +
                ", issFixo=" + issFixo +
                ", iss=" + iss +
                '}';
    }
}
