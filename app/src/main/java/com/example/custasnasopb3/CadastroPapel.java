package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.custasnasopb3.R.color.black;

public class CadastroPapel extends AppCompatActivity {

    private int idPapel=0;
    private String cpNomePapel;
    private Double cpValor;
    private int cpQuantidade;

    String idaux = "";
    int ID_PAPEL;

    Papel papel = new Papel();
    ArrayList<Papel> papelList = new ArrayList<>();

    EditText et_nomePapel, et_valCadastroPapel;
    EditText et_IdPapel, et_QuantidadePapel;

    TextView resumoBD;

    // *** TASKS ***
    //*** OK *** dar continuidade criação do método de cadastro dos papéis; OK
    //*** OK *** fazer lista de papeis para cadastrar; OK
    //*** OK *** mostrar em list views dinâmicas os papeis cadastrados; OK
    //*** OK *** criar banco de dados; ok
    //*** OK *** implementar a função de exclusão; ok
    //*** OK *** Botão de exclusão de papel da WatchList; ok
    //*** OK *** Montar sistema de pilha para os papeis cadastrados; ok
    //*** OK *** Transformar o cadastro de papel em WatchList; (não será necessário - talvez atualização futura)
    //TODO fazer limpeza no que não for necessário - iniciado - comentada as funções e views a serem excluidas;
    // Retirar lista inicial (Hard-included);
    // Retirar a visibilidade dos contadores;
    //TODO refatorar a classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadadastro_papel);

        et_nomePapel = findViewById(R.id.nomePapel);
        et_valCadastroPapel = findViewById(R.id.valCadastroPapel);
        et_QuantidadePapel = findViewById(R.id.valQuantidadePapel);

        //TODO *** DADOS ABAIXO SERÃO EXCLUÍDOS - AINDA EM TESTE
        papelList.add(new Papel(00,"CIEL3F", 8.5, 51));
        papelList.add(new Papel(01,"ABEV3F", 16.3,81));
        papelList.add(new Papel(02,"EMBR3F", 16.9,98));
        //papelList.add(new Papel("PETR3F", 14.78));
        papelList.add(new Papel(03,"BBDC4F", 21.65,48));
        papelList.add(new Papel(04,"CVCB3F", 16.22, 99));
        papelList.add(new Papel(05,"DMMO3F", 8.1,10));
        papelList.add(new Papel(06,"COGN3", 6.72,300));
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
            }

            Listar(papelList);

        *//* CRIAÇÃO DO TOAST INLINE
        Context context = getApplicationContext();
        CharSequence text = "Cadastro realizado!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*//*

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
            *//*
            CharSequence texto = "Cadastro NÃO realizado!";
            int duracao = Toast.LENGTH_SHORT;
            Toaster(texto, duracao);*//*

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
    }



    public void pesquisarPapel(View view){
        encontrarPapel();
    }

    public long encontrarPapel(){
        DataBaseHelper dbh = new DataBaseHelper(this);

        et_IdPapel =findViewById(R.id.idPapel);
        idaux = et_IdPapel.getText().toString();

        if(idaux.equals("")){
            Toaster("Insira um ID");
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
                    /*int i = ID_PAPEL;
                    dbh.getPapel(i).getId();
                    int idTemp = dbh.getPapel(ID_PAPEL).getId();
                    String nomeTemp = dbh.getPapel(ID_PAPEL).getNomePapel();
                    Double valorTemp = dbh.getPapel(ID_PAPEL).getValor();
                    String nomeTmp = papel.getNomePapel();*/

                    //et_IdPapel.setText(dbh.getPapel(ID_PAPEL).getId());
                    et_nomePapel.setText(dbh.getPapel(ID_PAPEL).getNomePapel());
                    et_valCadastroPapel.setText(dbh.getPapel(ID_PAPEL).getValor().toString());
                    et_QuantidadePapel.setText(String.valueOf(dbh.getPapel(ID_PAPEL).getQuantidade()));
                    //Toaster("Sucesso!");
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
        et_IdPapel =findViewById(R.id.idPapel);
        idaux = et_IdPapel.getText().toString();
        resumoBD = (TextView) findViewById(R.id.resumoBD);

        if (idaux.equals("")) {
            //Se não insere um ID específico para sobrepor, é adicionado ao último ID

            DataBaseHelper dbh = new DataBaseHelper(this);
            long longUltimo = dbh.contador();
            String nomePapel = et_nomePapel.getText().toString();
            Double valorPapel=0.0;
            Integer quantidade=0;
            try {
                valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
                quantidade = Integer.parseInt(et_QuantidadePapel.getText().toString());
            }
            catch (Exception e){
                valorPapel=0.0;
                quantidade = 0;
            }


            try {
                int ultimo = Integer.parseInt(String.valueOf(longUltimo));

                if(valorPapel!= null && !(valorPapel<= 0) && !(nomePapel.equals("")) && !(quantidade<= 0) && quantidade!= null){
                    papel = new Papel(ultimo, nomePapel, valorPapel, quantidade);
                    dbh.addPapel(papel);

                    Toaster(papel.getNomePapel()+ " foi SALVO com sucesso!");
                }
                else{
                    if(valorPapel == null){
                        CharSequence texto = "Valor não pode ser nulo";
                        Toaster(texto);
                    }
                    else if(valorPapel <= 0){
                        CharSequence texto = "Valor não pode ser menor ou igual a zero";
                        Toaster(texto);
                    }
                    else if(nomePapel.equals("")){
                        CharSequence texto = "Nome do papel não pode ser nulo";
                        Toaster(texto);
                    }
                    else if(quantidade <= 0){
                        CharSequence texto = "Quantidade não pode ser menor ou igual a zero";
                        Toaster(texto);
                    }
                }
            }
            catch(Exception e){
                if(valorPapel == null){
                    CharSequence texto = "Valor não pode ser nulo";
                    Toaster(texto);
                }
                else if(valorPapel <= 0){
                    CharSequence texto = "Valor não pode ser menor ou igual a zero";
                    Toaster(texto);
                }
                else if(nomePapel.equals("")){
                    CharSequence texto = "Nome do papel não pode ser nulo";
                    Toaster(texto);
                }
                else if(quantidade <= 0){
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
        }
        else {
            try {
                ID_PAPEL = 0;
                ID_PAPEL = Integer.parseInt(idaux);
                DataBaseHelper dbh = new DataBaseHelper(this);
                long longUltimo = dbh.contador();
                int ultimo = Integer.parseInt(String.valueOf(longUltimo));

                String nomePapel = et_nomePapel.getText().toString();
                Double valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
                Integer quantidade = Integer.parseInt(et_QuantidadePapel.getText().toString());

                if(valorPapel!= null && !(valorPapel<= 0) && !(nomePapel.equals("")) && !(quantidade<= 0) && quantidade!= null){
                    papel = new Papel(ultimo, nomePapel, valorPapel, quantidade);

                    long encontrado = encontrarPapel();

                    if(encontrado>0){
                        DataBaseHelper helper = new DataBaseHelper(this);
                        helper.updatePapel(papel, ID_PAPEL);
                        Clear();

                        Toaster(papel.getNomePapel()+ " foi ALTERADO com sucesso!");
                    }
                    else{
                        DataBaseHelper helper = new DataBaseHelper(this);
                        helper.addPapel(papel);

                        Toaster(papel.getNomePapel()+ " foi SALVO com sucesso!");
                    }
                }
                dbh.close();
                Clear();
            }
            catch (Exception e){
                e.printStackTrace();
                Toaster("Erro ao salvar!");
            }
        }
        recuperarBD(view);
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

        for(int i=0; i<contador; i++){
            try{
                TextView listagem = new TextView(this);
                papel = dbh.getPapel(i).toString3();
                listagem.setText(dbh.getPapel(i).toString3());
                resumoView2BD.addView(listagem);
            }catch (Exception e){
                Toaster("Erro ao listar!");
            }
        }
        TextView resumoBD = new TextView(this);
        resumoBD.setText(papel);
        dbh.close();
    }

    public void excluirPapelBD(View view){
        Toaster("Excluído com sucesso!");
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

                Toaster("Não encontrado");
            }
            else{
                try{
                    dbh.excludePapel(ID_PAPEL);
                    int ID_PAPELPlus = ID_PAPEL+1;
                    while (ID_PAPELPlus!= 0){
                        Papel temp = dbh.getPapel(ID_PAPELPlus);
                        if(temp.getNomePapel() != ""){
                            dbh.updateIDPapel(ID_PAPELPlus);
                            ID_PAPELPlus+=1;
                        }
                        else{
                            ID_PAPELPlus = 0;
                        }

                    }

                    Toaster("Papel excluido com sucesso!");
                    Clear();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toaster("Erro ao buscar!");
                }
            }
        }
        recuperarBD(view);
        dbh.close();
    }
}