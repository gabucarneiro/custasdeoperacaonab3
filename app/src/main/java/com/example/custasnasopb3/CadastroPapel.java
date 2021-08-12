package com.example.custasnasopb3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.RestrictionEntry;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CadastroPapel extends AppCompatActivity {

    //TODO *** EM CASO DE EXCLUSÃO DAS FUNÇÕES COMENTADAS, EXCLUIR TAMBÉM AS DECLARAÇÕES ABAIXO
    //private int idPapel=0;
    //private String cpNomePapel;
    //private Double cpValor;
    //private int cpQuantidade;

    String idaux = "";
    int ID_PAPEL;

    int dataCompra, mesCompra, anoCompra;
    Papel papel = new Papel();
    //ArrayList<Papel> papelList = new ArrayList<>();

    EditText et_nomePapel, et_valCadastroPapel;
    EditText et_IdPapel, et_QuantidadePapel;
    CheckBox cadastroCbFracionario;
    //EditText cadastroEdDataCompra;

    TextView resumoBD;

    Export exp = new Export();

    // *** TASKS ***
    //*** OK *** dar continuidade criação do método de cadastro dos papéis; OK
    //*** OK *** fazer lista de papeis para cadastrar; OK
    //*** OK *** mostrar em list views dinâmicas os papeis cadastrados; OK
    //*** OK *** criar banco de dados; ok
    //*** OK *** implementar a função de exclusão; ok
    //*** OK *** Botão de exclusão de papel da WatchList; ok
    //*** OK *** Montar sistema de pilha para os papeis cadastrados; ok
    //*** OK *** Transformar o cadastro de papel em WatchList; (não será necessário - talvez atualização futura)
    //TODO fazer limpeza no que não for necessário - iniciado: comentada as funções e views a serem excluidas;
    //*** OK *** Retirar lista inicial (Hard-included);
    //*** OK *** Retirar a visibilidade dos contadores;
    //TODO refatorar a classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadadastro_papel);

        Datas datas = new Datas();

        et_nomePapel = findViewById(R.id.nomePapel);
        et_valCadastroPapel = findViewById(R.id.valCadastroPapel);
        et_QuantidadePapel = findViewById(R.id.valQuantidadePapel);
        cadastroCbFracionario = findViewById(R.id.cadastroCbFracionario);
        (findViewById(R.id.RLDatadaCompra)).setVisibility(View.GONE);
        (findViewById(R.id.cadastroDpDataCompra)).setVisibility(View.GONE);


        Spinner spinnerData = findViewById(R.id.spinnerData);
        Spinner spinnerMes = findViewById(R.id.spinnerMes);
        Spinner spinnerAno = findViewById(R.id.spinnerAno);
        SpinnerDataMesAno(this, spinnerData, 1);
        SpinnerDataMesAno(this, spinnerMes, 2);
        SpinnerDataMesAno(this, spinnerAno, 3);


        spinnerData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //dataParaBD = spinnerData.getSelectedItem().toString() + "/" + spinnerMes.getSelectedItem().toString() + "/" + spinnerAno.getSelectedItem().toString();
                dataCompra = Integer.parseInt(spinnerData.getSelectedItem().toString());
                //System.out.println("******* Spinner selecionado no ONITEMSELECTEDLISTENER: "+spinnerData.getSelectedItem().toString());
                //System.out.println("******* DATA SELECIONADA - RUMO AO BD: " + dataCompra);
                //System.out.println("******* AGORA: " + sdf.format(new Date().getTime()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //dataParaBD = spinnerData.getSelectedItem().toString() + "/" + spinnerMes.getSelectedItem().toString() + "/" + spinnerAno.getSelectedItem().toString();
                mesCompra = Integer.parseInt(spinnerMes.getSelectedItem().toString());
                //System.out.println("******* MES SELECIONADO - RUMO AO BD: " + mesCompra);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //dataParaBD = spinnerData.getSelectedItem().toString() + "/" + spinnerMes.getSelectedItem().toString() + "/" + spinnerAno.getSelectedItem().toString();
                anoCompra = Integer.parseInt(spinnerAno.getSelectedItem().toString());
                //System.out.println("******* ANO SELECIONADO - RUMO AO BD: " + anoCompra);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        System.out.println("DATA - MES - ANO para BD: " + spinnerData.getSelectedItem().toString() + "/" + spinnerMes.getSelectedItem().toString() + "/" + spinnerAno.getSelectedItem().toString());

        /* ---- DEIXADO PARA CONSULTA FUTURA ---- TODO Excluir após pesquisa e conclusão ----
        ((EditText) findViewById(R.id.cadastroEdDataCompra)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String dash = "/";
                String clear = "";
                StringBuilder sbData = new StringBuilder();

                if (s.length()<10){
                    System.out.println("s.length = "+s.length());

                    try {
                        int aux = s.length();
                        for (int i = aux; i < 10; i++){
                            System.out.println("length = " + s.length() + " //// i = " + i);

                        }
                        ((EditText) findViewById(R.id.cadastroEdDataCompra)).setText(sbData);
                        System.out.println("Length < 9");
                        System.out.println("StringBuilder: " + sbData);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
                System.out.println("Digitado: " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged");
            }
        });*/


        et_IdPapel = (EditText) findViewById(R.id.idPapel);
        /*et_IdPapel.setText("999");
        Toaster(String.valueOf(et_IdPapel.getText()));*/

        ((CheckBox)findViewById(R.id.CadastroCBDataCompra)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    (findViewById(R.id.RLDatadaCompra)).setVisibility(View.VISIBLE);
//                    System.out.println(" IS IT CHECKED? "+((CheckBox)findViewById(R.id.CadastroCBDataCompra)).isChecked());
//                    datas.datasCompra(((CheckBox)findViewById(R.id.CadastroCBDataCompra)).isChecked(), Integer.parseInt(spinnerData.getSelectedItem().toString()), Integer.parseInt(spinnerMes.getSelectedItem().toString()), Integer.parseInt(spinnerAno.getSelectedItem().toString()));

//                    System.out.println("\n"+ datas.toString());
                }
                else {
                    (findViewById(R.id.RLDatadaCompra)).setVisibility(View.GONE);
//                    datas.setConfirmaCompra(false);
//                    datas.setDataCompra(0);
//                    datas.setMesCompra(0);
//                    datas.setAnoCompra(0);

//                    System.out.println("\n"+ datas.toString());
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
        Date yearLater = calendar.getTime();
        Calendar minCalendar = Calendar.getInstance();
        minCalendar.set(2008, 0, 1);
        Date minDate = minCalendar.getTime();
        ((CalendarView)findViewById(R.id.cadastroDpDataCompra)).setMinDate(minDate.getTime());
        ((CalendarView)findViewById(R.id.cadastroDpDataCompra)).setMaxDate(yearLater.getTime());
        ((ImageButton)findViewById(R.id.cadastroCalendarBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.cadastroDpDataCompra).getVisibility() == View.VISIBLE){
                    (findViewById(R.id.cadastroDpDataCompra)).setVisibility(View.GONE);
                }
                else {
                    (findViewById(R.id.cadastroDpDataCompra)).setVisibility(View.VISIBLE);
                    CalendarViewFunc(findViewById(R.id.cadastroDpDataCompra), spinnerData, spinnerMes, spinnerAno);
                }
            }
        });

        System.out.println("DATA - MES - ANO para BD: " + spinnerData.getSelectedItem().toString() + "/" + spinnerMes.getSelectedItem().toString() + "/" + spinnerAno.getSelectedItem().toString());

        Button btnCustas = (Button) findViewById(R.id.btnCustas);
        btnCustas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogCustas(et_IdPapel);
            }
        });

        //TODO *** DADOS ABAIXO SERÃO EXCLUÍDOS - AINDA EM TESTE
        /*papelList.add(new Papel(00,"CIEL3F", 8.5, 51, true));
        papelList.add(new Papel(01,"ABEV3F", 16.3,81, true));
        papelList.add(new Papel(02,"EMBR3F", 16.9,98, true));
        //papelList.add(new Papel("PETR3F", 14.78));
        papelList.add(new Papel(03,"BBDC4F", 21.65,48, true));
        papelList.add(new Papel(04,"CVCB3F", 16.22, 99, true));
        papelList.add(new Papel(05,"DMMO3F", 8.1,10, true));
        papelList.add(new Papel(06,"COGN3", 6.72,300, false));*/
        //Listar(papelList);
        //*** DADOS ACIMA SERÃO EXCLUÍDOS - AINDA EM TESTE
    }

    //TODO *** FUNÇÃO QUE POSSIVELMENTE SERÁ EXCLUÍDA - AINDA EM ANÁLISE
    /*public void cadastrarPapel (View view){
        try{
            idPapel = Integer.parseInt(et_IdPapel.getText().toString());

            cpNomePapel = et_nomePapel.getText().toString();

            cpValor = Double.parseDouble(et_valCadastroPapel.getText().toString());

            cpQuantidade = Integer.parseInt(et_QuantidadePapel.getText().toString());

            if(cpValor!= null && !(cpValor<= 0) && !(cpNomePapel.equals("")) && !(cpQuantidade<=0)){
                idPapel = papelList.size();
                papel = new Papel(idPapel, cpNomePapel, cpValor, cpQuantidade);
                papelList.add(papel);

                //TextView resumoCadastrarPapel = (TextView) findViewById(R.id.resumoCadastrPapel);
                //resumoCadastrarPapel.setTextColor(ContextCompat.getColor(this, black));

                //resumoCadastrarPapel.setText(papel.toString3());

                CharSequence texto = "Cadastro realizado!";
                int duracao = Toast.LENGTH_SHORT;
                Toaster(texto, duracao);
                Clear();
            }
            else{
                if(cpValor == null){
                    CharSequence texto = "Valor não pode ser nulo";
                    int duracao = Toast.LENGTH_SHORT;
                    Toaster(texto, duracao);
                }
                else if(cpValor <= 0){
                    CharSequence texto = "Valor não pode ser menor ou igual a zero";
                    int duracao = Toast.LENGTH_SHORT;
                    Toaster(texto, duracao);
                }
                else if(cpNomePapel.equals("")){
                    CharSequence texto = "Nome do papel não pode ser nulo";
                    int duracao = Toast.LENGTH_SHORT;
                    Toaster(texto, duracao);
                }
            }*/

            //Listar(papelList);

        /* CRIAÇÃO DO TOAST INLINE
        Context context = getApplicationContext();
        CharSequence text = "Cadastro realizado!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        }
        catch(Exception e){

            String erro = "";
            if(cpValor == null){
                CharSequence texto = "Valor não pode ser nulo";
                int duracao = Toast.LENGTH_SHORT;
                Toaster(texto, duracao);
                erro = "**Valor nulo**";
            }
            else if(cpValor <= 0){
                CharSequence texto = "Valor não pode ser menor ou igual a zero";
                int duracao = Toast.LENGTH_SHORT;
                Toaster(texto, duracao);
                erro = "**Valor menor ou igual a 0**";
            }
            else if(cpNomePapel.equals("")){
                CharSequence texto = "Nome do papel não pode ser nulo";
                int duracao = Toast.LENGTH_SHORT;
                Toaster(texto, duracao);
                erro = "**Nome do papel nulo**";
            }
            CharSequence texto = "Cadastro NÃO realizado!";
            int duracao = Toast.LENGTH_SHORT;
            Toaster(texto, duracao);

            //TextView resumoCadastrarPapel = (TextView) findViewById(R.id.resumoCadastrPapel);
            //resumoCadastrarPapel.setTextColor(ContextCompat.getColor(this, black));
            //resumoCadastrarPapel.setText(erro);
        }

    }*/

    public void Toaster(CharSequence text, int duration){
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void Toaster(CharSequence text){
        Context context = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duracao);
        toast.show();
    }


    //TODO *** FUNÇÃO QUE POSSIVELMENTE SERÁ EXCLUÍDA - AINDA EM ANÁLISE
    /*public void Listar(ArrayList<Papel> papel){
        TextView resumo2 = (TextView) findViewById(R.id.resumo);
        LinearLayout resumoView = (LinearLayout) findViewById(R.id.resumoView);

        for(int i=0; i<papelList.size(); i++){
            TextView listagem = new TextView(this);
            listagem.setText(papelList.toString());
            resumoView.addView(listagem);
        }*//*

        StringBuilder sbListaPapel = new StringBuilder();
        for(int i=0; i<papel.size(); i++){

            sbListaPapel.append("Papel: ");
            sbListaPapel.append(papel.get(i).getNomePapel());
            sbListaPapel.append(" / Valor: R$");
            sbListaPapel.append(papel.get(i).getValor());
            sbListaPapel.append(" / Quantidade: ");
            sbListaPapel.append(papel.get(i).getQuantidade());
            sbListaPapel.append("\n");
            sbListaPapel.append("\n");
        }
        resumo2.setText(sbListaPapel);
    }*/

    //TODO Verificar se podem ser excluídas as duas funções (listar e cadastrarPapel) acima desta linha.

    public void Clear(){
        et_IdPapel.setText("");
        et_nomePapel.setText("");
        et_valCadastroPapel.setText("");
        et_QuantidadePapel.setText("");
        cadastroCbFracionario.setChecked(false);
        ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setChecked(false);
    }



    public void pesquisarPapel(View view){
        encontrarPapel();
    }

    public long encontrarPapel(){
        DataBaseHelper dbh = new DataBaseHelper(this);
        ((EditText) findViewById(R.id.nomePapel)).setEnabled(true);
        ((EditText) findViewById(R.id.valCadastroPapel)).setEnabled(true);
        ((EditText) findViewById(R.id.valQuantidadePapel)).setEnabled(true);
        ((CheckBox) findViewById(R.id.cadastroCbFracionario)).setEnabled(true);

        ((ImageButton)findViewById(R.id.cadastroCalendarBtn)).setEnabled(true);

        ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setEnabled(true);

        ((Spinner)findViewById(R.id.spinnerData)).setEnabled(true);
        ((Spinner)findViewById(R.id.spinnerMes)).setEnabled(true);
        ((Spinner)findViewById(R.id.spinnerAno)).setEnabled(true);

        ((Button)findViewById(R.id.cadastroPapelBtnSalvar)).setEnabled(true);

        et_IdPapel =findViewById(R.id.idPapel);
        idaux = et_IdPapel.getText().toString();

        if(idaux.equals("")){
            Toaster("Insira um ID");
            //et_IdPapel.setError("Buscar por ID");
            dbh.close();
            return 0;
        }
        else{
            ID_PAPEL = Integer.parseInt(idaux);
            Papel papel = dbh.getPapel(ID_PAPEL);

            if(papel.getNomePapel().equals("")){
                Clear();

                Toaster("Nenhum registro encontrado");
                dbh.close();
                return 0;
            }
            else{
                try{
                    et_nomePapel.setText(dbh.getPapel(ID_PAPEL).getNomePapel());
                    et_valCadastroPapel.setText(String.valueOf(dbh.getPapel(ID_PAPEL).getValor()));
                    et_QuantidadePapel.setText(String.valueOf(dbh.getPapel(ID_PAPEL).getQuantidade()));
                    if (dbh.getPapel(ID_PAPEL).isFracionario()){
                        cadastroCbFracionario.setChecked(true);
                    }
                    else {
                        cadastroCbFracionario.setChecked(false);
                    }

                    // ***** DATAS *****
                    try {
                        if (dbh.getDatas(ID_PAPEL).isConfirmaCompra()){
                            ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setChecked(true);
                            try {
                                ((Spinner)findViewById(R.id.spinnerData)).setSelection((dbh.getDatas(ID_PAPEL).getDataCompra()) - 1);
                                ((Spinner)findViewById(R.id.spinnerMes)).setSelection((dbh.getDatas(ID_PAPEL).getMesCompra()) - 1);
                                ArrayList<Integer> ano = new ArrayList<>();
                                for (int i = 2008; i<= (2007 + ((Spinner)findViewById(R.id.spinnerAno)).getCount()); i++){
                                    ano.add(i);
                                }
                                if (ano.contains(dbh.getDatas(ID_PAPEL).getAnoCompra())) {
                                    ((Spinner)findViewById(R.id.spinnerAno)).setSelection(ano.indexOf(dbh.getDatas(ID_PAPEL).getAnoCompra()));
                                }
                                System.out.println("ENCONTRAR PAPEL -> idaux != null -> DATAS -> Try Spinner ok - ANO: " + ((Spinner)findViewById(R.id.spinnerAno)).getSelectedItem().toString());
                                //((Spinner)findViewById(R.id.spinnerAno)).setSelection(dbh.getDatas(ID_PAPEL).getAnoCompra());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setChecked(false);
                            System.out.println("ENCONTRAR PAPEL ->idaux != null -> DATAS -> Try Spinner ELSE - CheckBoxisSelected: " + ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked());
                        }
                    /*System.out.println("IsChecked do ID "+ ID_PAPEL + " é:" + dbh.getDatas(ID_PAPEL).isConfirmaCompra());
                    System.out.println("Data da compra do ID "+ ID_PAPEL + " é:" + dbh.getDatas(ID_PAPEL).getDataCompra());
                    System.out.println("Mês da compra do ID "+ ID_PAPEL + " é:" + dbh.getDatas(ID_PAPEL).getMesCompra());
                    System.out.println("Ano da compra do ID "+ ID_PAPEL + " é:" + dbh.getDatas(ID_PAPEL).getAnoCompra());*/

                        if (dbh.getDatas(ID_PAPEL).isConfirmaVenda()){
                            ((EditText) findViewById(R.id.nomePapel)).setEnabled(false);
                            ((EditText) findViewById(R.id.valCadastroPapel)).setEnabled(false);
                            ((EditText) findViewById(R.id.valQuantidadePapel)).setEnabled(false);
                            ((CheckBox) findViewById(R.id.cadastroCbFracionario)).setEnabled(false);

                            ((ImageButton)findViewById(R.id.cadastroCalendarBtn)).setEnabled(false);

                            ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setEnabled(false);

                            ((Spinner)findViewById(R.id.spinnerData)).setEnabled(false);
                            ((Spinner)findViewById(R.id.spinnerMes)).setEnabled(false);
                            ((Spinner)findViewById(R.id.spinnerAno)).setEnabled(false);

                            ((Button)findViewById(R.id.cadastroPapelBtnSalvar)).setEnabled(false);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    dbh.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toaster("Erro ao buscar!");
                }
                dbh.close();
                return ID_PAPEL;
            }
        }
    }

    public void salvarPapel(View view) {
        et_IdPapel = findViewById(R.id.idPapel);
        idaux = et_IdPapel.getText().toString();
        resumoBD = (TextView) findViewById(R.id.resumoBD);
        cadastroCbFracionario = findViewById(R.id.cadastroCbFracionario);
        DataBaseHelper dbh = new DataBaseHelper(this);

        if (cadastroCbFracionario.isChecked() && Integer.parseInt(((EditText) findViewById(R.id.valQuantidadePapel)).getText().toString()) > 99) {
            System.out.println("É FRACIONÁRIO! MAS É MAIOR DO QUE 99");
            ((EditText) findViewById(R.id.valQuantidadePapel)).setError("Papel fracionário não pode ser maior que 99");
            ((EditText) findViewById(R.id.valQuantidadePapel)).setText("");
        } else if (!(cadastroCbFracionario.isChecked()) && (Integer.parseInt(((EditText) findViewById(R.id.valQuantidadePapel)).getText().toString()) % 100 != 0)) {
            System.out.println("NÃO É FRACIONÁRIO! MAS TAMBÉM NÃO É DIVISÍVEL POR 100");
            ((EditText) findViewById(R.id.valQuantidadePapel)).setError("Papel em lotes de 100");
            ((EditText) findViewById(R.id.valQuantidadePapel)).setText("");
        } else {
            System.out.println("GOOOOOO!");


            if (idaux.equals("")) {
                //Se não insere um ID específico para sobrepor, é adicionado ao último ID

                long longUltimo = dbh.contador();
                if (longUltimo == 0) {
                    longUltimo = 1;
                } else {
                    longUltimo += 1;
                }
                String nomePapel = et_nomePapel.getText().toString();
                Double valorPapel = 0.0;
                Integer quantidade = 0;
                boolean fracionario = false;
                try {
                    valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
                    quantidade = Integer.parseInt(et_QuantidadePapel.getText().toString());
                    fracionario = cadastroCbFracionario.isChecked();
                } catch (Exception e) {
                    valorPapel = 0.0;
                    quantidade = 0;
                    fracionario = false;
                }

                try {
                    int ultimo = Integer.parseInt(String.valueOf(longUltimo));

                    if (valorPapel != null && !(valorPapel <= 0) && !(nomePapel.equals("")) && !(quantidade <= 0) && quantidade != null) {

                        // ***** PAPEL *****
                        papel = new Papel(ultimo, nomePapel, valorPapel, quantidade, fracionario);
                        dbh.addPapel(papel);

                        // ***** CUSTAS *****
                        Integer idCustas;
                        Double emptyTempCheck = dbh.getCustas(998).getCorretagem() + dbh.getCustas(998).getCustodia() + dbh.getCustas(998).getTx_liquidacao() + dbh.getCustas(998).getTx_negociacao() + dbh.getCustas(998).getIss();
                        //Toaster("emptyTempCheck = " + emptyTempCheck);
                        if (emptyTempCheck == 0.0) {
                            idCustas = 999;
                            //TODO excluir o Toaster teste abaixo
                            //Toaster("ID temporário vazio: " + idCustas);
                        } else {
                            idCustas = 998;
                            //TODO excluir o Toaster teste abaixo
                            //Toaster("ID temporário válido: " + idCustas);
                        }
                        Custas custas;

                        Double ultimoCorretagem = dbh.getCustas(idCustas).getCorretagem();
                        //Toaster("Enviado para a possição " + idCustas + " : " + String.valueOf(ultimoCorretagem));
                        Boolean isCorretagemFixa = dbh.getCustas(idCustas).isCorretagemFixa();
                        Double ultimoCustodia = dbh.getCustas(idCustas).getCustodia();
                        Boolean isCustodiaFixa = dbh.getCustas(idCustas).isCustodiaFixa();
                        Double ultimoTxLiquidacao = dbh.getCustas(idCustas).getTx_liquidacao();
                        Boolean isTxLiquidacaoFixa = dbh.getCustas(idCustas).isTx_liquidacaoFixa();
                        Double ultimoTxNegociacao = dbh.getCustas(idCustas).getTx_negociacao();
                        Boolean isTxNegociacaoFixa = dbh.getCustas(idCustas).isTx_negociacaoFixa();
                        Double ultimoIss = dbh.getCustas(idCustas).getIss();
                        Boolean isIssFixo = dbh.getCustas(idCustas).isIssFixo();

                        custas = new Custas(ultimo, ultimoCorretagem, ultimoCustodia, ultimoTxLiquidacao, ultimoTxNegociacao, ultimoIss, isCorretagemFixa, isCustodiaFixa, isTxLiquidacaoFixa, isTxNegociacaoFixa, isIssFixo);
                        if (dbh.getCustas(ultimo).getCorretagem() == 0.0 && dbh.getCustas(ultimo).getCustodia() == 0.0 && dbh.getCustas(ultimo).getTx_liquidacao() == 0.0 && dbh.getCustas(ultimo).getTx_negociacao() == 0.0 && dbh.getCustas(ultimo).getIss() == 0.0) {
                            dbh.addCustas(custas);
                        } else {
                            dbh.updateCustas(custas, ultimo);
                        }

                        // ***** DATA *****
                        try {
                            Datas datas = new Datas();
                            if (((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked()) {
                                datas.datasCompra(((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked(), ultimo, Integer.parseInt(((Spinner) findViewById(R.id.spinnerData)).getSelectedItem().toString()), Integer.parseInt(((Spinner) findViewById(R.id.spinnerMes)).getSelectedItem().toString()), Integer.parseInt(((Spinner) findViewById(R.id.spinnerAno)).getSelectedItem().toString()));
                                if (!dbh.getDatas(ultimo).isConfirmaCompra() && dbh.getDatas(ultimo).getDataCompra() == 0 && dbh.getDatas(ultimo).getMesCompra() == 0 && dbh.getDatas(ultimo).getAnoCompra() == 0) {
                                    dbh.addDataCompra(datas);
                                    System.out.println("CheckBox checked: " + datas.toString());
                                } else {
                                    dbh.updateTempIdDatasCompra(datas, ultimo);
                                    System.out.println("CheckBox checked: UPDATED!!! " + datas.toString());
                                }
                            } else {
                                System.out.println("dbh.addDataCompra" + ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked());
                                datas.datasCompra(((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked(), ultimo, 0, 0, 0);
                                dbh.addDataCompra(datas);
                            }
                            System.out.println("CheckBox outside: " + datas.toString());
                            System.out.println("IsChecked do ID " + ultimo + " é:" + dbh.getDatas(ultimo).isConfirmaCompra());
                            System.out.println("Data da compra do ID " + ultimo + " é:" + dbh.getDatas(ultimo).getDataCompra());
                            System.out.println("Mês da compra do ID " + ultimo + " é:" + dbh.getDatas(ultimo).getMesCompra());
                            System.out.println("Ano da compra do ID " + ultimo + " é:" + dbh.getDatas(ultimo).getAnoCompra());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("NUMBERFORMATEXCEPTION: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        StringBuilder sb = new StringBuilder();
                        sb.append("---- PAPEL INCLUÍDO ----\n");
                        sb.append("- Papel: " + dbh.getPapel(ultimo).getNomePapel() + "\n");
                        sb.append("- Valor: " + dbh.getPapel(ultimo).getValor() + "\n");
                        sb.append("- Quantidade: " + dbh.getPapel(ultimo).getQuantidade() + "\n");
                        sb.append("- Fracionário: " + dbh.getPapel(ultimo).isFracionario() + "\n");
                        sb.append("- TxCorretagem: " + dbh.getCustas(ultimo).getCorretagem() + "\n");
                        sb.append("- TxCustodia: " + dbh.getCustas(ultimo).getCustodia() + "\n");
                        sb.append("- TxLiquidação: " + dbh.getCustas(ultimo).getTx_liquidacao() + "\n");
                        sb.append("- TxNegociação: " + dbh.getCustas(ultimo).getTx_negociacao() + "\n");
                        sb.append("- Iss: " + dbh.getCustas(ultimo).getIss() + "\n");
                        try {
                            exp.loggIt(this, sb.toString());
                        } catch (Exception e) {
                            System.out.println("FALHA AO TENTAR INSERIR NO LOG");
                        }

                        Toaster(papel.getNomePapel() + " foi SALVO com sucesso!");
                    } else {
                        if (valorPapel == null) {
                            CharSequence texto = "Valor não pode ser nulo";
                            Toaster(texto);
                        } else if (valorPapel <= 0) {
                            CharSequence texto = "Valor não pode ser menor ou igual a zero";
                            Toaster(texto);
                        } else if (nomePapel.equals("")) {
                            CharSequence texto = "Nome do papel não pode ser nulo";
                            Toaster(texto);
                        } else if (quantidade <= 0) {
                            CharSequence texto = "Quantidade não pode ser menor ou igual a zero";
                            Toaster(texto);
                        }
                    }
                } catch (Exception e) {
                    if (valorPapel == null) {
                        CharSequence texto = "Valor não pode ser nulo";
                        Toaster(texto);
                    } else if (valorPapel <= 0) {
                        CharSequence texto = "Valor não pode ser menor ou igual a zero";
                        Toaster(texto);
                    } else if (nomePapel.equals("")) {
                        CharSequence texto = "Nome do papel não pode ser nulo";
                        Toaster(texto);
                    } else if (quantidade <= 0) {
                        CharSequence texto = "Quantidade não pode ser menor ou igual a zero";
                        Toaster(texto);
                    }
                }



                /*try {
                    resumoBD.setText(papel.toString2());
                } catch (Exception e){
                    Toaster("Erro de TargetException");
                }*/

                dbh.close();
                //Toaster("Último: " + String.valueOf(ultimo));
            } else {

                System.out.println("SALVAR PAPEL -> idaux != null -> ENTRADA DO ELSE");
                try {
                    ID_PAPEL = 0;
                    ID_PAPEL = Integer.parseInt(idaux);
                    long longUltimo = dbh.contador();
                    if (longUltimo == 0) {
                        longUltimo = 1;
                    } else {
                        longUltimo += 1;
                    }
                    int ultimo = Integer.parseInt(String.valueOf(longUltimo));

                    String nomePapel = et_nomePapel.getText().toString();
                    Double valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
                    Integer quantidade = Integer.parseInt(et_QuantidadePapel.getText().toString());
                    boolean fracionario = cadastroCbFracionario.isChecked();

                    if (valorPapel != null && !(valorPapel <= 0) && !(nomePapel.equals("")) && !(quantidade <= 0) && quantidade != null) {
                        papel = new Papel(ultimo, nomePapel, valorPapel, quantidade, fracionario);

                        System.out.println("SALVAR PAPEL -> idaux != null -> ENTRADA DA VALIDAÇÃO DO PAPEL - Ultimo: " + ultimo + " / ID_PAPEL: " + ID_PAPEL);

                        Datas datasTemp = new Datas();
                        System.out.println("CHECKBOX IS CHECKED?   " + ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked());
                        if (((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked()) {
                            System.out.println("DATATEMP 1.1 - SO FAR - OK!");
                            datasTemp.datasCompra(((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked(), ID_PAPEL, Integer.parseInt(((Spinner) findViewById(R.id.spinnerData)).getSelectedItem().toString()), Integer.parseInt(((Spinner) findViewById(R.id.spinnerMes)).getSelectedItem().toString()), Integer.parseInt(((Spinner) findViewById(R.id.spinnerAno)).getSelectedItem().toString()));
                            System.out.println(datasTemp.toString());
                        } else {
                            System.out.println("DATATEMP 1.2 - SO FAR - OK!");
                            System.out.println("CHECKBOX IS CHECKED AGAIN?   " + ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked());
                            datasTemp.datasCompra(((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked(), ID_PAPEL, 0, 0, 0);
                        }


                        long encontrado = encontrarPapel();

                        if (encontrado > 0) {
                            DataBaseHelper helper = new DataBaseHelper(this);
                            helper.updatePapel(papel, ID_PAPEL);

                            // ***** CUSTAS *****

                            Integer idCustas;
                            Double emptyTempCheck = dbh.getCustas(998).getCorretagem() + dbh.getCustas(998).getCustodia() + dbh.getCustas(998).getTx_liquidacao() + dbh.getCustas(998).getTx_negociacao() + dbh.getCustas(998).getIss();
                            if (emptyTempCheck == 0.0) {
                                idCustas = 999;
                                //TODO excluir o Toaster teste abaixo
                                //Toaster("else if ID temporário vazio: " + idCustas);
                            } else {
                                idCustas = 998;
                                //TODO excluir o Toaster teste abaixo
                                //Toaster("else if ID temporário válido: " + idCustas);
                            }
                            Custas custas;

                            Double ultimoCorretagem = dbh.getCustas(idCustas).getCorretagem();
                            //Toaster("Enviado para a possição " + idCustas + " : " + String.valueOf(ultimoCorretagem));
                            Boolean isCorretagemFixa = dbh.getCustas(idCustas).isCorretagemFixa();
                            Double ultimoCustodia = dbh.getCustas(idCustas).getCustodia();
                            Boolean isCustodiaFixa = dbh.getCustas(idCustas).isCustodiaFixa();
                            Double ultimoTxLiquidacao = dbh.getCustas(idCustas).getTx_liquidacao();
                            Boolean isTxLiquidacaoFixa = dbh.getCustas(idCustas).isTx_liquidacaoFixa();
                            Double ultimoTxNegociacao = dbh.getCustas(idCustas).getTx_negociacao();
                            Boolean isTxNegociacaoFixa = dbh.getCustas(idCustas).isTx_negociacaoFixa();
                            Double ultimoIss = dbh.getCustas(idCustas).getIss();
                            Boolean isIssFixo = dbh.getCustas(idCustas).isIssFixo();

                            custas = new Custas(ID_PAPEL, ultimoCorretagem, ultimoCustodia, ultimoTxLiquidacao, ultimoTxNegociacao, ultimoIss, isCorretagemFixa, isCustodiaFixa, isTxLiquidacaoFixa, isTxNegociacaoFixa, isIssFixo);
                            if (dbh.getCustas(ID_PAPEL).getCorretagem() == 0.0 && dbh.getCustas(ID_PAPEL).getCustodia() == 0.0 && dbh.getCustas(ID_PAPEL).getTx_liquidacao() == 0.0 && dbh.getCustas(ID_PAPEL).getTx_negociacao() == 0.0 && dbh.getCustas(ID_PAPEL).getIss() == 0.0) {
                                dbh.addCustas(custas);
                                //Toaster("Add na possição " + ID_PAPEL + " : " + String.valueOf(ultimoCorretagem));
                            } else {
                                dbh.updateCustas(custas, ID_PAPEL);
                                //Toaster("Updated na possição " + ID_PAPEL + " : " + String.valueOf(ultimoCorretagem));
                            }

                            //***** DATAS *****

                            try {
                                //Datas datas = new Datas();
                                System.out.println("CHECKBOX  do datasTemp WAS CHECKED?   " + (datasTemp.isConfirmaCompra()));
                                if (datasTemp.isConfirmaCompra()) {
                                    System.out.println("1 - SO FAR - OK!");
                                    if (dbh.getDatas(ID_PAPEL).getDataCompra() == 0 && dbh.getDatas(ID_PAPEL).getMesCompra() == 0 && dbh.getDatas(ID_PAPEL).getAnoCompra() == 0) {
                                        System.out.println(datasTemp.toString());
                                        dbh.addDataCompra(datasTemp);
                                        System.out.println("1.1 - SO FAR - OK!");
                                        System.out.println("CheckBox checked: " + datasTemp.toString());
                                    } else {
                                        System.out.println("1.2 - SO FAR - OK!");
                                        dbh.updateTempIdDatasCompra(datasTemp, ID_PAPEL);
                                        System.out.println("CheckBox checked: UPDATED!!! " + datasTemp.toString());
                                    }
                                } else {
                                    System.out.println("2 - SO FAR - OK!");
                                    System.out.println("dbh.addDataCompra: " + ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).isChecked());
                                    //datasTemp.datasCompra(datasTemp.isConfirmaCompra(), ID_PAPEL, 0, 0, 0);
                                    System.out.println("DATATEMP ESTÁ ASSIM: " + datasTemp.toString());
                                    dbh.updateTempIdDatasCompra(datasTemp, ID_PAPEL);
                                }
                                System.out.println("CheckBox outside: " + datasTemp.toString());
                                System.out.println("IsChecked do ID " + ID_PAPEL + " é: " + dbh.getDatas(ID_PAPEL).isConfirmaCompra());
                                System.out.println("Data da compra do ID " + ID_PAPEL + " é: " + dbh.getDatas(ID_PAPEL).getDataCompra());
                                System.out.println("Mês da compra do ID " + ID_PAPEL + " é: " + dbh.getDatas(ID_PAPEL).getMesCompra());
                                System.out.println("Ano da compra do ID " + ID_PAPEL + " é: " + dbh.getDatas(ID_PAPEL).getAnoCompra());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                System.out.println("NUUUMBERFORMATEXCEPTION: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                            StringBuilder sb = new StringBuilder();
                            sb.append("---- PAPEL ALTERADO ----\n");
                            sb.append("- ID: " + dbh.getPapel(ID_PAPEL).getId() + "\n");
                            sb.append("- Papel: " + dbh.getPapel(ID_PAPEL).getNomePapel() + "\n");
                            sb.append("- Valor: " + dbh.getPapel(ID_PAPEL).getValor() + "\n");
                            sb.append("- Quantidade: " + dbh.getPapel(ID_PAPEL).getQuantidade() + "\n");
                            sb.append("- Fracionário: " + dbh.getPapel(ID_PAPEL).isFracionario() + "\n");
                            sb.append("- TxCorretagem: " + dbh.getCustas(ID_PAPEL).getCorretagem() + "\n");
                            sb.append("- TxCustodia: " + dbh.getCustas(ID_PAPEL).getCustodia() + "\n");
                            sb.append("- TxLiquidação: " + dbh.getCustas(ID_PAPEL).getTx_liquidacao() + "\n");
                            sb.append("- TxNegociação: " + dbh.getCustas(ID_PAPEL).getTx_negociacao() + "\n");
                            sb.append("- Iss: " + dbh.getCustas(ID_PAPEL).getIss() + "\n");
                            try {
                                exp.loggIt(this, sb.toString());
                            } catch (Exception e) {
                                System.out.println("FALHA AO TENTAR INSERIR NO LOG");
                            }

                            Clear();
                            Toaster(papel.getNomePapel() + " foi ALTERADO com sucesso!");
                        } else {
                            DataBaseHelper helper = new DataBaseHelper(this);
                            helper.addPapel(papel);

                            Integer idCustas;
                            Double emptyTempCheck = dbh.getCustas(998).getCorretagem() + dbh.getCustas(998).getCustodia() + dbh.getCustas(998).getTx_liquidacao() + dbh.getCustas(998).getTx_negociacao() + dbh.getCustas(998).getIss();
                            if (emptyTempCheck == 0.0) {
                                idCustas = 999;
                                //TODO excluir o Toaster teste abaixo
                                //Toaster("else else ID temporário vazio: " + idCustas);
                            } else {
                                idCustas = 998;
                                //TODO excluir o Toaster teste abaixo
                                //Toaster("else else ID temporário válido: " + idCustas);
                            }
                            Custas custas;

                            Double ultimoCorretagem = dbh.getCustas(idCustas).getCorretagem();
                            //Toaster("Enviado para a possição " + idCustas + " : " + String.valueOf(ultimoCorretagem));
                            Boolean isCorretagemFixa = dbh.getCustas(idCustas).isCorretagemFixa();
                            Double ultimoCustodia = dbh.getCustas(idCustas).getCustodia();
                            Boolean isCustodiaFixa = dbh.getCustas(idCustas).isCustodiaFixa();
                            Double ultimoTxLiquidacao = dbh.getCustas(idCustas).getTx_liquidacao();
                            Boolean isTxLiquidacaoFixa = dbh.getCustas(idCustas).isTx_liquidacaoFixa();
                            Double ultimoTxNegociacao = dbh.getCustas(idCustas).getTx_negociacao();
                            Boolean isTxNegociacaoFixa = dbh.getCustas(idCustas).isTx_negociacaoFixa();
                            Double ultimoIss = dbh.getCustas(idCustas).getIss();
                            Boolean isIssFixo = dbh.getCustas(idCustas).isIssFixo();

                            custas = new Custas(ultimo, ultimoCorretagem, ultimoCustodia, ultimoTxLiquidacao, ultimoTxNegociacao, ultimoIss, isCorretagemFixa, isCustodiaFixa, isTxLiquidacaoFixa, isTxNegociacaoFixa, isIssFixo);
                            if (dbh.getCustas(ultimo).getCorretagem() == 0.0 && dbh.getCustas(ultimo).getCustodia() == 0.0 && dbh.getCustas(ultimo).getTx_liquidacao() == 0.0 && dbh.getCustas(ultimo).getTx_negociacao() == 0.0 && dbh.getCustas(ultimo).getIss() == 0.0) {
                                dbh.addCustas(custas);
                                //Toaster("Add na possição " + ultimo + " : " + String.valueOf(ultimoCorretagem));
                            } else {
                                dbh.updateCustas(custas, ultimo);
                                //Toaster("Updated na possição " + ultimo + " : " + String.valueOf(ultimoCorretagem));
                            }

                            StringBuilder sb = new StringBuilder();
                            sb.append("---- PAPEL INCLUÍDO ----\n");
                            sb.append("- Papel: " + dbh.getPapel(ultimo).getNomePapel() + "\n");
                            sb.append("- Valor: " + dbh.getPapel(ultimo).getValor() + "\n");
                            sb.append("- Quantidade: " + dbh.getPapel(ultimo).getQuantidade() + "\n");
                            sb.append("- Fracionário: " + dbh.getPapel(ultimo).isFracionario() + "\n");
                            sb.append("- TxCorretagem: " + dbh.getCustas(ultimo).getCorretagem() + "\n");
                            sb.append("- TxCustodia: " + dbh.getCustas(ultimo).getCustodia() + "\n");
                            sb.append("- TxLiquidação: " + dbh.getCustas(ultimo).getTx_liquidacao() + "\n");
                            sb.append("- TxNegociação: " + dbh.getCustas(ultimo).getTx_negociacao() + "\n");
                            sb.append("- Iss: " + dbh.getCustas(ultimo).getIss() + "\n");
                            try {
                                exp.loggIt(this, sb.toString());
                            } catch (Exception e) {
                                System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                            }
                            Toaster(papel.getNomePapel() + " foi SALVO com sucesso!");
                        }
                    }
                    dbh.close();
                    Clear();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        exp.loggIt(this, e.getMessage());
                    } catch (Exception eLog) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + eLog.getMessage());
                    }
                    Toaster("Erro ao salvar!");
                }
            }
    }
        //*** OK *** Função para setar 0.0s e falses em todos dados do ID temporário: 998 APÓS SALVAR NO BANCO DE DADOS NO ID REAL;
        dbh.clearTempIdCustas(998);

        recuperarBD(view);
        dbh.close();
    }

    public void recuperarBD(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);

        dbh.clearDB();
        et_IdPapel =findViewById(R.id.idPapel);
        /*idaux = et_IdPapel.getText().toString();
        ID_PAPEL = Integer.parseInt(idaux);*/

        String papel = "Não funcionou...";

        LinearLayout resumoView2BD = (LinearLayout) findViewById(R.id.resumoView2BD);
        //LinearLayout layout_teste = (LinearLayout) findViewById(R.id.layout_teste);
        TextView teste = new TextView(this);
        teste.setText(String.valueOf(dbh.contador()));
        //layout_teste.addView(teste);

        try{
            resumoView2BD.removeAllViewsInLayout();
            TextView resumoBD = new TextView(this);
        } catch (Exception e){
            Toaster("Erro, não foi possível limpar");
        }
        long contador =0;
        contador = dbh.contador();
        String stgcontador = String.valueOf(contador);


// *********** VIEW CRIADA PARA TESTAR O CONTADOR ***********
        /*TextView tvcontador = new TextView(this);
        tvcontador.setText("Papeis encontrados: " + stgcontador+"\n"+"****LISTA****"+"\n");
        resumoView2BD.addView(tvcontador);*/

        for(int i=1; i<=contador; i++){
            try{
                TextView listagem = new TextView(this);
                papel = dbh.getPapel(i).toString3();
                listagem.setText(dbh.getPapel(i).toString4() + (dbh.getDatas(i).toString()) +"\n"+ (dbh.getDatas(i).toString1())+"\n");
                resumoView2BD.addView(listagem);
            }catch (Exception e){
                try {
                    exp.loggIt(this, e.toString());
                } catch (Exception eLog) {
                    System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + eLog.getMessage());
                }
                Toaster("Erro ao listar!");
            }
        }
        TextView resumoBD = new TextView(this);
        resumoBD.setText(papel);
        dbh.close();
    }

    public void excluirPapelBD(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);

        et_IdPapel =findViewById(R.id.idPapel);
        idaux = et_IdPapel.getText().toString();

        pesquisarPapel(et_IdPapel);

        if(idaux.equals("")){
            Toaster("Insira um ID");
        }
        else{
            ID_PAPEL = Integer.parseInt(idaux);
            Papel papel = dbh.getPapel(ID_PAPEL);

            if(papel.getNomePapel().equals("")){
                et_IdPapel.setText("");
                et_nomePapel.setText("");
                et_valCadastroPapel.setText("");
                et_QuantidadePapel.setText("");
                cadastroCbFracionario.setChecked(false);
                ((CheckBox) findViewById(R.id.CadastroCBDataCompra)).setChecked(false);

                try {
                    dbh.excludeCustas(ID_PAPEL);
                    dbh.excludeDatas(ID_PAPEL);
                    dbh.excludeDadosVenda(ID_PAPEL);
                }
                catch (Exception e){
                    try {
                        exp.loggIt(this, e.toString());
                    } catch (Exception eLog) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + eLog.getMessage());
                    }
                    //TODO Remover esta mensagem
                    Toaster("Nada na tabela de custas");
                }

                Toaster("Não encontrado");
            }
            else{
                try{
                    StringBuilder sb = new StringBuilder();
                    sb.append("---- PAPEL EXCLUÍDO ----\n");
                    sb.append("- Papel: "+ dbh.getPapel(ID_PAPEL).getNomePapel() + "\n");
                    sb.append("- Valor: "+ dbh.getPapel(ID_PAPEL).getValor() + "\n");
                    sb.append("- Quantidade: "+ dbh.getPapel(ID_PAPEL).getQuantidade() + "\n");
                    sb.append("- TxCorretagem: "+ dbh.getCustas(ID_PAPEL).getCorretagem() + "\n");
                    sb.append("- TxCustodia: "+ dbh.getCustas(ID_PAPEL).getCustodia() + "\n");
                    sb.append("- TxLiquidação: "+ dbh.getCustas(ID_PAPEL).getTx_liquidacao() + "\n");
                    sb.append("- TxNegociação: "+ dbh.getCustas(ID_PAPEL).getTx_negociacao() + "\n");
                    sb.append("- Iss: "+ dbh.getCustas(ID_PAPEL).getIss() + "\n");
                    try {
                        exp.loggIt(this, sb.toString());
                    } catch (Exception e) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                    }
                    dbh.excludePapel(ID_PAPEL);
                    dbh.excludeCustas(ID_PAPEL);
                    dbh.excludeDatas(ID_PAPEL);
                    dbh.excludeDadosVenda(ID_PAPEL);
                    int ID_PAPELPlus = ID_PAPEL+1;
                    int ID_CUSTASPlus = ID_PAPELPlus;
                    int ID_DATASPlus = ID_PAPELPlus;
                    int ID_DATAS_VENDAPlus = ID_PAPELPlus;
                    while (ID_PAPELPlus!= 0){
                        Papel temp = dbh.getPapel(ID_PAPELPlus);
                        //Custas tempCustas = dbh.getCustas(ID_CUSTASPlus);
                        if(!temp.getNomePapel().equals("")){
                            dbh.updateIDPapel(ID_PAPELPlus);
                            ID_PAPELPlus+=1;
                            dbh.updateIDCustas(ID_CUSTASPlus);
                            ID_CUSTASPlus+=1;
                            dbh.updateIDDatas(ID_DATASPlus);
                            ID_DATASPlus+=1;
                            dbh.updateIDDadosVenda(ID_DATAS_VENDAPlus);
                            ID_DATAS_VENDAPlus+=1;
                        }
                        else{
                            ID_PAPELPlus = 0;
                            ID_CUSTASPlus = 0;
                            ID_DATASPlus = 0;
                            ID_DATAS_VENDAPlus = 0;
                        }

                    }

                    try {
                        exp.loggIt(this, "Papel excluido com sucesso!\n");
                    } catch (Exception e) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                    }
                    Toaster("Papel excluido com sucesso!");
                    Clear();
                }
                catch (Exception e){
                    try {
                        exp.loggIt(this, e.getMessage());
                    } catch (Exception eLog) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG");
                    }
                    e.printStackTrace();
                    Toaster("Erro ao buscar!");
                }
            }
        }
        recuperarBD(view);
        dbh.close();
    }

    //***OK*** Dar continuidade ao desenvolvimento da função - Após cadastro do primeiro papel com parâmetros,
    // testar a função, pois, até então, não possui vinculo com o ID do papel ao salvar.


    //***OK*** IDEIA - Criar um onClickListener para ser chamado pelo botão de buscar as custas, porém,
    // antes de chamar as custas ele vai pegar o ID do papel (se existir, do contrário, pega o padrão 999),
    // jogar em IDTEMP no servidor, e chamar a AlertDialogCustas que buscará o IDTEMP no servidor.


    public void AlertDialogCustas (View view){

        AlertDialog.Builder ad_toStringCustas = new AlertDialog.Builder(this);
        AlertDialog alert = ad_toStringCustas.create();
        alert.setTitle(R.string.parametros);

        DataBaseHelper dbhCustas = new DataBaseHelper(this);

        Integer idPapelCustas;
        try {
            et_IdPapel =findViewById(R.id.idPapel);

            if (et_IdPapel.getText()!=null || !(String.valueOf(et_IdPapel.getText()).equals(""))){
                idPapelCustas = Integer.parseInt(String.valueOf(et_IdPapel.getText()));
                //Toaster("If ativado idPapelCustas: " + idPapelCustas);
            }
            else {
                idPapelCustas = 998;
                //Toaster("Else ativado idPapelCustas: " + idPapelCustas);
            }
        }
        /*catch (NullPointerException npe){
            idPapelCustas = 998;
            Toaster("Nullpointer idPapelCustas: " + idPapelCustas);
            Toaster(npe.getMessage());
        }*/
        catch (Exception e){
            //*** OK *** buscar forma de identificar 998 vazio e então receber 999 como parâmetros; caso 998 contenha dados, recuperar 998 e setar.
            // - Por enquanto, ele apenas não recupera para o AlertDialog as informações do 998, mas salva corretamente as informações do 998 no respectivo id do papel
            //*** OK *** Quando estiver cadastrando um novo papel, clicar no botão das custas, salvar as custas no padrão temporário 998 e fechar, ao clicar novamente no botão das custas, o AlertDialog deverá recuperar o que consta no 998.
            if (String.valueOf(et_IdPapel.getText()).equals("")){
                if (dbhCustas.getCustas(998).getCorretagem() != 0.0 || dbhCustas.getCustas(998).getCustodia() != 0.0 || dbhCustas.getCustas(998).getTx_liquidacao() != 0.0 || dbhCustas.getCustas(998).getTx_negociacao() != 0.0 || dbhCustas.getCustas(998).getIss() != 0.0){
                    idPapelCustas = 998;
                    //Toaster("Else if catch ativado idPapelCustas: " + idPapelCustas);
                }
                else {
                    idPapelCustas = 999;
                    //Toaster("ELSE DENTRO DO catch ativado idPapelCustas: " + idPapelCustas);
                }
            }
            else {
                idPapelCustas = 999;
                //Toaster("ELSE Exception padrão: ID " + idPapelCustas);
            }
            //Toaster("Parâmetro padrão utilizado");
            System.out.println(e.getMessage());
        }



        /*LinearLayout ll_TesteStringCustas = new LinearLayout(this);
        ll_TesteStringCustas.setOrientation(LinearLayout.VERTICAL);
        ll_TesteStringCustas.setPadding(20, 10, 10,10);

        StringBuilder sbCustas = new StringBuilder();
        sbCustas.append("ID: " + (dbhCustas.getCustas(idPapelCustas).getId()) + "\n");
        sbCustas.append("Corretagem: R$" + (dbhCustas.getCustas(idPapelCustas).getCorretagem()) + "\n");
        sbCustas.append("Custodia: R$" + (dbhCustas.getCustas(idPapelCustas).getCustodia()) + "\n");
        sbCustas.append("Taxa de Liquidação: " + (dbhCustas.getCustas(idPapelCustas).getTx_liquidacao()) + "%\n");
        sbCustas.append("Taxa de Negociação: " + (dbhCustas.getCustas(idPapelCustas).getTx_negociacao()) + "%\n");
        //CharSequence arrayCustas[] = new CharSequence[1];

        TextView TV_lltoStringCustas = new TextView(this);
        TV_lltoStringCustas.setTextSize(16);
        TV_lltoStringCustas.setTextColor(getColor(R.color.black));
        String toStringCustas = sbCustas.toString();
        TV_lltoStringCustas.setText(toStringCustas);

        ll_TesteStringCustas.addView(TV_lltoStringCustas);*/
        //arrayCustas[0] = toStringCustas;


        //Layout vertical que recebe todos outros
        LinearLayout ll_ADVertical = new LinearLayout(this);
        ll_ADVertical.setOrientation(LinearLayout.VERTICAL);
        ll_ADVertical.setPadding(20, 10, 10,10);


        //Layout horizontal que recebe o valor de corretagem
        LinearLayout ll_ADHorizontalCorretagem = new LinearLayout(this);
        ll_ADHorizontalCorretagem.setOrientation(LinearLayout.HORIZONTAL);

        TextView TV_lltoStringCorretagem = new TextView(this);
        TV_lltoStringCorretagem.setTextSize(16);
        TV_lltoStringCorretagem.setTextColor(getColor(R.color.black));
        TV_lltoStringCorretagem.setText("Corretagem: R$");

        EditText et_ADCorretagemTemp = new EditText(this);
        et_ADCorretagemTemp.setText(String.valueOf((dbhCustas.getCustas(idPapelCustas).getCorretagem())));
        et_ADCorretagemTemp.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        CheckBox cb_ADCorretagemTemp = new CheckBox(this);
        cb_ADCorretagemTemp.setText("Fixo");
        cb_ADCorretagemTemp.setPadding(5,0,0,0);
        boolean checarSelecionado_ADCorretagemTemp = dbhCustas.getCustas(idPapelCustas).isCorretagemFixa();
        if (checarSelecionado_ADCorretagemTemp){
            cb_ADCorretagemTemp.setChecked(true);
        }
        else {
            cb_ADCorretagemTemp.setChecked(false);
        }


        //Layout horizontal que recebe o valor de custas
        LinearLayout ll_ADHorizontalCustodia = new LinearLayout(this);
        ll_ADHorizontalCustodia.setOrientation(LinearLayout.HORIZONTAL);

        TextView TV_llADCustodia = new TextView(this);
        TV_llADCustodia.setTextSize(16);
        TV_llADCustodia.setTextColor(getColor(R.color.black));
        TV_llADCustodia.setText("Custodia: R$");

        EditText et_ADCustodiaTemp = new EditText(this);
        et_ADCustodiaTemp.setText(String.valueOf((dbhCustas.getCustas(idPapelCustas).getCustodia())));
        et_ADCustodiaTemp.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        CheckBox cb_ADCustodiaTemp = new CheckBox(this);
        cb_ADCustodiaTemp.setText("Fixo");
        cb_ADCustodiaTemp.setPadding(5,0,0,0);
        boolean checarSelecionado_ADCustodiaTemp = dbhCustas.getCustas(idPapelCustas).isCustodiaFixa();
        if (checarSelecionado_ADCustodiaTemp){
            cb_ADCustodiaTemp.setChecked(true);
        }
        else {
            cb_ADCustodiaTemp.setChecked(false);
        }



        //Layout horizontal que recebe o valor de taxa de liquidação
        LinearLayout ll_ADHorizontalTxLiquidacao = new LinearLayout(this);
        ll_ADHorizontalTxLiquidacao.setOrientation(LinearLayout.HORIZONTAL);

        TextView TV_llADTxLiquidacao = new TextView(this);
        TV_llADTxLiquidacao.setTextSize(16);
        TV_llADTxLiquidacao.setTextColor(getColor(R.color.black));
        TV_llADTxLiquidacao.setText("Taxa de Liquidação: %");

        EditText et_ADTxLiquidacaoTemp = new EditText(this);
        et_ADTxLiquidacaoTemp.setText(String.valueOf((dbhCustas.getCustas(idPapelCustas).getTx_liquidacao())));
        et_ADTxLiquidacaoTemp.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        CheckBox cb_ADTxLiquidacaoTemp = new CheckBox(this);
        cb_ADTxLiquidacaoTemp.setText("Fixo");
        cb_ADTxLiquidacaoTemp.setPadding(5,0,0,0);
        boolean checarSelecionado_ADTxLiquidacaoTemp = dbhCustas.getCustas(idPapelCustas).isTx_liquidacaoFixa();
        if (checarSelecionado_ADTxLiquidacaoTemp){
            cb_ADTxLiquidacaoTemp.setChecked(true);
        }
        else {
            cb_ADTxLiquidacaoTemp.setChecked(false);
        }



        //Layout horizontal que recebe o valor de taxa de negociação
        LinearLayout ll_ADHorizontalTxNegociacao = new LinearLayout(this);
        ll_ADHorizontalTxNegociacao.setOrientation(LinearLayout.HORIZONTAL);

        TextView TV_llADTxNegociacao = new TextView(this);
        TV_llADTxNegociacao.setTextSize(16);
        TV_llADTxNegociacao.setTextColor(getColor(R.color.black));
        TV_llADTxNegociacao.setText("Taxa de Negociação: %");

        EditText et_ADTxNegociacaoTemp = new EditText(this);
        et_ADTxNegociacaoTemp.setText(String.valueOf((dbhCustas.getCustas(idPapelCustas).getTx_negociacao())));
        et_ADTxNegociacaoTemp.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        CheckBox cb_ADTxNegociacaoTemp = new CheckBox(this);
        cb_ADTxNegociacaoTemp.setText("Fixo");
        cb_ADTxNegociacaoTemp.setPadding(5,0,0,0);
        boolean checarSelecionado_ADTxNegociacaoTemp = dbhCustas.getCustas(idPapelCustas).isTx_negociacaoFixa();
        if (checarSelecionado_ADTxNegociacaoTemp){
            cb_ADTxNegociacaoTemp.setChecked(true);
        }
        else {
            cb_ADTxNegociacaoTemp.setChecked(false);
        }



        //Layout horizontal que recebe o valor do ISS
        LinearLayout ll_ADHorizontalIss = new LinearLayout(this);
        ll_ADHorizontalIss.setOrientation(LinearLayout.HORIZONTAL);

        TextView TV_llADIss = new TextView(this);
        TV_llADIss.setTextSize(16);
        TV_llADIss.setTextColor(getColor(R.color.black));
        TV_llADIss.setText("Iss: %");

        EditText et_ADIssTemp = new EditText(this);
        et_ADIssTemp.setText(String.valueOf((dbhCustas.getCustas(idPapelCustas).getIss())));
        et_ADIssTemp.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        CheckBox cb_ADIssTemp = new CheckBox(this);
        cb_ADIssTemp.setText("Fixo");
        cb_ADIssTemp.setPadding(5,0,0,0);
        boolean checarSelecionado_ADIssTemp = dbhCustas.getCustas(idPapelCustas).isIssFixo();
        if (checarSelecionado_ADIssTemp){
            cb_ADIssTemp.setChecked(true);
        }
        else {
            cb_ADIssTemp.setChecked(false);
        }

        //Layout horizontal que recebe o botão para salvar no banco de dados no ID temporário
        LinearLayout ll_ADHorizontalBtn = new LinearLayout(this);
        ll_ADHorizontalIss.setOrientation(LinearLayout.HORIZONTAL);

        Button btnConfirmaCustaTemp = new Button(this);

        //SE o papel já estiver vendido, de acordo com a classe Datas.java, o nome do botão mudará para "Cancelar" e não salvará as custas.
        if (dbhCustas.getDatas(idPapelCustas).isConfirmaVenda()){
            //btnConfirmaCustaTemp.setEnabled(false);
            btnConfirmaCustaTemp.setText("Cancelar");
            btnConfirmaCustaTemp.setTextColor(getColor(R.color.white));
            btnConfirmaCustaTemp.setGravity(1);
            btnConfirmaCustaTemp.setBackgroundColor(getColor(R.color.darkblue));
            btnConfirmaCustaTemp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbhCustas.close();
                    alert.cancel();
                    Toaster("Não alterado, papel vendido");
                }
            });
        }
        //SE o papel não estiver vendido, de acordo com a classe Datas.java, o nome do botão murará para "OK" e salvará as custas em um ID temporário
        // até a conclusão do cadastro.
        else {
            //btnConfirmaCustaTemp.setEnabled(true);
        btnConfirmaCustaTemp.setText("OK");
        btnConfirmaCustaTemp.setTextColor(getColor(R.color.white));
        btnConfirmaCustaTemp.setGravity(1);
        btnConfirmaCustaTemp.setBackgroundColor(getColor(R.color.darkblue));
        btnConfirmaCustaTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double db_ADCorretagemTemp;
                Double db_ADCustodiaTemp;
                Double db_ADTxLiquidacaoTemp;
                Double db_ADTxNegociacaoTemp;
                Double db_ADIssTemp;
                Boolean boo_cb_ADCorretagemTemp;
                Boolean boo_cb_ADCustodiaTemp;
                Boolean boo_cb_ADTxLiquidacaoTemp;
                Boolean boo_cb_ADTxNegociacaoTemp;
                Boolean boo_cb_ADIssTemp;
                if (!String.valueOf(et_ADCorretagemTemp.getText()).equals("")){
                    db_ADCorretagemTemp = Double.valueOf(String.valueOf(et_ADCorretagemTemp.getText()));
                    //Toaster("db_ADCorretagemTemp: "+ db_ADCorretagemTemp);
                }
                else {
                    db_ADCorretagemTemp = 0.0;
                }
                if (!String.valueOf(et_ADCustodiaTemp.getText()).equals("")){
                    db_ADCustodiaTemp = Double.valueOf(String.valueOf(et_ADCustodiaTemp.getText()));
                }
                else {
                    db_ADCustodiaTemp = 0.0;
                }
                if (!String.valueOf(et_ADTxLiquidacaoTemp.getText()).equals("")){
                    db_ADTxLiquidacaoTemp = Double.valueOf(String.valueOf(et_ADTxLiquidacaoTemp.getText()));
                }
                else {
                    db_ADTxLiquidacaoTemp = 0.0;
                }
                if (!String.valueOf(et_ADTxNegociacaoTemp.getText()).equals("")){
                    db_ADTxNegociacaoTemp = Double.valueOf(String.valueOf(et_ADTxNegociacaoTemp.getText()));
                }
                else {
                    db_ADTxNegociacaoTemp = 0.0;
                }
                if (!String.valueOf(et_ADIssTemp.getText()).equals("")){
                    db_ADIssTemp = Double.valueOf(String.valueOf(et_ADIssTemp.getText()));
                }
                else {
                    db_ADIssTemp = 0.0;
                }
                if (!cb_ADCorretagemTemp.isChecked()){
                    boo_cb_ADCorretagemTemp = false;
                }
                else {
                    boo_cb_ADCorretagemTemp = true;
                }
                if (!cb_ADCustodiaTemp.isChecked()){
                    boo_cb_ADCustodiaTemp = false;
                }
                else {
                    boo_cb_ADCustodiaTemp = true;
                }
                if (!cb_ADTxLiquidacaoTemp.isChecked()){
                    boo_cb_ADTxLiquidacaoTemp = false;
                }
                else {
                    boo_cb_ADTxLiquidacaoTemp = true;
                }
                if (!cb_ADTxNegociacaoTemp.isChecked()){
                    boo_cb_ADTxNegociacaoTemp = false;
                }
                else {
                    boo_cb_ADTxNegociacaoTemp = true;
                }
                if (!cb_ADIssTemp.isChecked()){
                    boo_cb_ADIssTemp = false;
                }
                else {
                    boo_cb_ADIssTemp = true;
                }

                long ultimo = dbhCustas.contador();
                int idTempCustas = 998;
                Custas custasTemp = new Custas(idTempCustas, db_ADCorretagemTemp, db_ADCustodiaTemp, db_ADTxLiquidacaoTemp, db_ADTxNegociacaoTemp, db_ADIssTemp, boo_cb_ADCorretagemTemp, boo_cb_ADCustodiaTemp, boo_cb_ADTxLiquidacaoTemp, boo_cb_ADTxNegociacaoTemp, boo_cb_ADIssTemp);
                Double checkVazio = dbhCustas.getCustas(idTempCustas).getCorretagem() + dbhCustas.getCustas(idTempCustas).getCustodia() + dbhCustas.getCustas(idTempCustas).getTx_liquidacao() + dbhCustas.getCustas(idTempCustas).getTx_negociacao() + dbhCustas.getCustas(idTempCustas).getIss();
                if (checkVazio == 0.0) {
                    try{
                        dbhCustas.addTempIdCustas(custasTemp);
                        dbhCustas.updateTempIdCustas(custasTemp, idTempCustas);
                        System.out.println("Salvo nas custas vazias! TRY "+ ultimo);
                        try {
                            exp.loggIt(CadastroPapel.this, "Custas salvas");
                        } catch (Exception e) {
                            System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                        }
                    }
                    catch (Exception e){
                        dbhCustas.updateTempIdCustas(custasTemp, idTempCustas);
                        System.out.println("CATCH na hora de salvar as custas! "+ ultimo);
                        try {
                            exp.loggIt(CadastroPapel.this, "Custas atualizadas");
                        } catch (Exception eLog) {
                            System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                        }
                    }
                }
                else {
                    dbhCustas.updateTempIdCustas(custasTemp, idTempCustas);
                    System.out.println("Salvo! Else"+ ultimo);
                    try {
                        exp.loggIt(CadastroPapel.this, "Custas atualizadas");
                    } catch (Exception e) {
                        System.out.println("FALHA AO TENTAR INSERIR NO LOG: " + e.getMessage());
                    }
                }
                //Toaster("Salvo! "+ ultimo);
                dbhCustas.close();
                alert.cancel();
            }
        });
        }

        //Layouts horizontais chamando as Views
        ll_ADHorizontalCorretagem.addView(TV_lltoStringCorretagem);
        ll_ADHorizontalCorretagem.addView(et_ADCorretagemTemp);
        ll_ADHorizontalCorretagem.addView(cb_ADCorretagemTemp);

        ll_ADHorizontalCustodia.addView(TV_llADCustodia);
        ll_ADHorizontalCustodia.addView(et_ADCustodiaTemp);
        ll_ADHorizontalCustodia.addView(cb_ADCustodiaTemp);

        ll_ADHorizontalTxLiquidacao.addView(TV_llADTxLiquidacao);
        ll_ADHorizontalTxLiquidacao.addView(et_ADTxLiquidacaoTemp);
        ll_ADHorizontalTxLiquidacao.addView(cb_ADTxLiquidacaoTemp);

        ll_ADHorizontalTxNegociacao.addView(TV_llADTxNegociacao);
        ll_ADHorizontalTxNegociacao.addView(et_ADTxNegociacaoTemp);
        ll_ADHorizontalTxNegociacao.addView(cb_ADTxNegociacaoTemp);

        ll_ADHorizontalIss.addView(TV_llADIss);
        ll_ADHorizontalIss.addView(et_ADIssTemp);
        ll_ADHorizontalIss.addView(cb_ADIssTemp);

        ll_ADHorizontalBtn.addView(btnConfirmaCustaTemp);


        //Layout vertical que chama os layouts horizontais
        ll_ADVertical.addView(ll_ADHorizontalCorretagem);
        ll_ADVertical.addView(ll_ADHorizontalCustodia);
        ll_ADVertical.addView(ll_ADHorizontalTxLiquidacao);
        ll_ADVertical.addView(ll_ADHorizontalTxNegociacao);
        ll_ADVertical.addView(ll_ADHorizontalIss);
        ll_ADVertical.addView(ll_ADHorizontalBtn);

        //ad_toStringCustas.setView(R.layout.activity_custas);
        alert.setView(ll_ADVertical);

        alert.show();
        dbhCustas.close();
    }

    public void CalendarViewFunc(CalendarView calendarView, Spinner data, Spinner mes, Spinner ano){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //Método que recebe os dados de um CalendarView e seta em Spinners de data, mês e ano
            //Menor ano possível: 2008 (ano do início das operações na B3

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //System.out.println("Valores int do calendar:\n"+ dayOfMonth + "\n" + (month + 1) + "\n" + year);
                //daOfMonth é int e o setSelection pega a posição, onde dayOfMonth recebe 12, a posição 12 corresponde ao dia 13
                data.setSelection(dayOfMonth-1);
                mes.setSelection(month);
                ano.setSelection(year - 2008);
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                //System.out.println("***** Resuldado: "+ date);
                try {
                    calendarView.setVisibility(View.GONE);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public void SpinnerDataMesAno(Context context, Spinner spinner, int selectData1Mes2Ano3){
        //Método que recebe um Spinner e, recebendo um número de 1 à 3, seta as opções para data, mês e ano

        SimpleDateFormat sdf;
        Date dateData = new Date();
        switch (selectData1Mes2Ano3){
            case 1:
                sdf = new SimpleDateFormat("dd", Locale.getDefault());
                ArrayList<Integer> data = new ArrayList<>();
                for (int i = 1; i<=31; i++){
                    data.add(i);
                }
                spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, data));
                int intDateData = Integer.parseInt(sdf.format(dateData));
                spinner.setSelection(intDateData-1);
                break;

            case 2:
                sdf = new SimpleDateFormat("MM", Locale.getDefault());
                ArrayList<Integer> mes = new ArrayList<>();
                for (int i = 1; i<=12; i++){
                    mes.add(i);
                }
                spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, mes));
                int intDateMes = Integer.parseInt(sdf.format(dateData));
                spinner.setSelection(intDateMes-1);
                break;

            case 3:
                sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
                int intYear = Integer.parseInt(sdf.format(dateData));
                int intYearPlus1 = intYear+1;
                ArrayList<Integer> ano = new ArrayList<>();
                for (int i = 2008; i<=intYearPlus1; i++){
                    ano.add(i);
                }
                spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ano));
                if (ano.contains(intYear)) {
                    spinner.setSelection(ano.indexOf(intYear));
                }
                break;
        }
    }
}