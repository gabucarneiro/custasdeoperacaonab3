package com.example.custasnasopb3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    DataBaseHelper dbhCustas = new DataBaseHelper(this);


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

        EditText et_valPapelAdquirido = findViewById(R.id.valPapelAdquirido);
        EditText et_quantidade = findViewById(R.id.quantidade2);
        EditText et_valPretendidoVenda = findViewById(R.id.valPretendidoVenda);
        EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);
        EditText pct_Custodia = findViewById(R.id.pctCustodia2);
        EditText pct_Liquidacao = findViewById(R.id.pctLiquidacao2);
        EditText pct_Negociacao = findViewById(R.id.pctNegociacao2);
        EditText pct_Iss = findViewById(R.id.pctIss2);
        TextView pct_Emolumentos = findViewById(R.id.pctEmolumentos2);


        pct_Corretagem.setText(String.valueOf(dbhCustas.getCustas(999).getCorretagem()));
        pct_Custodia.setText(String.valueOf(dbhCustas.getCustas(999).getCustodia()));
        pct_Liquidacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()));
        pct_Negociacao.setText(String.valueOf(dbhCustas.getCustas(999).getTx_negociacao()));
        pct_Iss.setText(String.valueOf(dbhCustas.getCustas(999).getIss()));
        pct_Emolumentos.setText(String.valueOf(dbhCustas.getCustas(999).getTx_liquidacao()+dbhCustas.getCustas(999).getTx_negociacao()));

        dbhCustas.close();
        //AlertDialogListar();

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
            Double zero = 0.0;

            if (et_valPapelAdquirido != null && !et_valPapelAdquirido.equals(zero)
                    && et_quantidade != null && !et_quantidade.equals(zero)
                    && et_valPretendidoVenda != null && !et_valPretendidoVenda.equals(zero)) {
                try {
                    valPapelAdquirido = Double.parseDouble(et_valPapelAdquirido.getText().toString());
                    quantidade = Integer.parseInt(et_quantidade.getText().toString());
                    valPretendidoVenda = Double.parseDouble(et_valPretendidoVenda.getText().toString());


                    TextView tvCorretagem = (TextView) findViewById(R.id.corretagem2);
                    Double tempCorretagemCompra = Corretagem(valPapelAdquirido, quantidade);
                    Double tempCorretagemVenda = Corretagem(valPretendidoVenda, quantidade);
                    String calculo_Corretagem = String.valueOf(tempCorretagemCompra);
                    tvCorretagem.setText(calculo_Corretagem);

                    TextView val_Custodia = (TextView) findViewById(R.id.custodia2);
                    Double tempCustodiaCompra = Custodia(valPapelAdquirido, quantidade);
                    Double tempCustodiaVenda = Custodia(valPretendidoVenda, quantidade);
                    String str_val_Custodia = df2.format(tempCustodiaCompra);
                    val_Custodia.setText(str_val_Custodia);

                    TextView val_tx_liquidacao = (TextView) findViewById(R.id.tax_liquidacao2);
                    Double tempTxLiquidacaoCompra = Liquidacao(valPapelAdquirido, quantidade);
                    Double tempTxLiquidacaoVenda = Liquidacao(valPretendidoVenda, quantidade);
                    String str_val_tx_liquidacao = df3.format(tempTxLiquidacaoCompra);
                    val_tx_liquidacao.setText(str_val_tx_liquidacao);

                    TextView val_tx_negociacao = (TextView) findViewById(R.id.tax_negociacao2);
                    Double tempTxNegociacaoCompra = Negociacao(valPapelAdquirido, quantidade);
                    Double tempTxNegociacaoVenda = Negociacao(valPretendidoVenda, quantidade);
                    String str_val_tx_negociacao = df3.format(tempTxNegociacaoCompra);
                    val_tx_negociacao.setText(str_val_tx_negociacao);

                    TextView val_emolumentos = (TextView) findViewById(R.id.emolumentos2);
                    Double tempEmolumentosCompra = tempTxLiquidacaoCompra + tempTxNegociacaoCompra;
                    Double tempEmolumentosVenda = tempTxLiquidacaoVenda + tempTxNegociacaoVenda;
                    String str_val_emolumentos = df4.format(tempEmolumentosCompra);
                    val_emolumentos.setText(str_val_emolumentos);

                    TextView pctEmolumentos2 = (TextView) findViewById(R.id.pctEmolumentos2);
                    String tempPctEmolumentos = df3.format(Double.valueOf(tx_liquidacao + tx_negociacao));
                    pctEmolumentos2.setText(tempPctEmolumentos);

                    TextView val_iss = (TextView) findViewById(R.id.iss2);
                    Double tempIssCompra = Iss(valPapelAdquirido, quantidade);
                    Double tempIssVenda = Iss(valPretendidoVenda, quantidade);
                    String str_val_iss = df3.format(tempIssCompra);
                    val_iss.setText(str_val_iss);

                    TextView val_ir2 = (TextView) findViewById(R.id.impostoDeRenda2);
                    Double tempIr2Compra = Ir(valPapelAdquirido, quantidade);
                    Double tempIr2Venda = Ir(valPretendidoVenda, quantidade);
                    String str_val_ir2 = df3.format(tempIr2Compra);
                    val_ir2.setText(str_val_ir2);

                    TextView tv_valCompraDoPapel = (TextView) findViewById(R.id.valCompraDoPapel2);
                    TextView tv_valVendaDoPapel = (TextView) findViewById(R.id.valVendaDoPapel2);
                    double temp_CustasCompra = tempCorretagemCompra+tempCustodiaCompra+tempTxLiquidacaoCompra+tempTxNegociacaoCompra+tempIssCompra+tempIr2Compra;
                    double temp_CustasVenda = tempCorretagemVenda+tempCustodiaVenda+tempTxLiquidacaoVenda+tempTxNegociacaoVenda+tempIssVenda+tempIr2Venda;
                    tv_valCompraDoPapel.setText(df2.format((valPapelAdquirido * quantidade)+temp_CustasCompra));
                    tv_valVendaDoPapel.setText(df2.format((valPretendidoVenda * quantidade)-temp_CustasVenda));

                    TextView resulCalcVendaLiquido = (TextView) findViewById(R.id.resultVendaLiquido);
                    double calcVendaLiquido = calc_Venda_Liquido(valPapelAdquirido, quantidade, valPretendidoVenda);
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

                    TextView TV_valMinVendaSemPerdas = (TextView) findViewById(R.id.valMinVendaSemPerdas);
                    TV_valMinVendaSemPerdas.setText(df2.format(valMinVenda(valPapelAdquirido, quantidade)));


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

    //TODO Verificar a possibilidade de ativar o cálculo das funções a medida que os checkbuttons são ativados - onClickListener();

    public Double Corretagem(Double valPapelCalcular, Integer quantidade){

        EditText pct_Corretagem = findViewById(R.id.pctCorretagem2);

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

        Double venda_Liquida = (valPretendidoVenda * quantidade) - totalCustas;
        return venda_Liquida;
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

        CharSequence arrayPapeis[] = new CharSequence[contadorInt];

        try {
            for(int i = 0; i<contadorInt; i++){
                Papel papel;
                papel = dbh.getPapel(i);
                String tempNomePapel = String.valueOf(papel.getNomePapel());
                if (!(tempNomePapel.equals(""))){
                    arrayPapeis[i] = String.valueOf(papel.getNomePapel());
                }
                else{
                    arrayPapeis[i] = "Empty";
                }
            }
        }
        catch (Exception e){
            Toaster("Deu ruim!");
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
                EditText et_valPapelAdquirido = findViewById(R.id.valPapelAdquirido);
                EditText et_quantidade = findViewById(R.id.quantidade2);

                Papel papel;
                papel = dbh.getPapel(which);
                et_valPapelAdquirido.setText(String.valueOf(papel.getValor()));
                et_quantidade.setText(String.valueOf(papel.getQuantidade()));
                Toaster(papel.getNomePapel());
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
    }
}