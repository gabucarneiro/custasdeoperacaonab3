package com.example.custasnasopb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import static com.example.custasnasopb3.R.color.black;
import static com.example.custasnasopb3.R.color.red;

public class CadastroPapel extends AppCompatActivity {

    private int idPapel=0;
    private String cpNomePapel;
    private Double cpValor;

    String idaux = "";
    int ID_PAPEL;

    Papel papel = new Papel();
    ArrayList<Papel> papelList = new ArrayList<>();

    EditText et_nomePapel, et_valCadastroPapel;
    TextView tvIdPapel;

    TextView resumoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadadastro_papel);

        et_nomePapel = findViewById(R.id.nomePapel);
        et_valCadastroPapel = findViewById(R.id.valCadastroPapel);

        papelList.add(new Papel("CIEL3F", 8.5));
        papelList.add(new Papel("ABEV3F", 16.3));
        papelList.add(new Papel("EMBR3F", 16.9));
        papelList.add(new Papel("PETR3F", 14.78));
        papelList.add(new Papel("BBDC4F", 21.65));
        papelList.add(new Papel("CVCB3F", 16.22));
        papelList.add(new Papel("DMMO3F", 8.1));
        papelList.add(new Papel("COGN3", 6.72));
        Listar(papelList);
    }
    public void cadastrarPapel (View view){
        try{
            cpNomePapel = et_nomePapel.getText().toString();

            cpValor = Double.parseDouble(et_valCadastroPapel.getText().toString());

            if(cpValor!= null && !(cpValor<= 0) && !(cpNomePapel.equals(""))){

                papel = new Papel(cpNomePapel, cpValor);
                papelList.add(papel);

                TextView resumoCadastrarPapel = (TextView) findViewById(R.id.resumoCadastrPapel);
                resumoCadastrarPapel.setTextColor(ContextCompat.getColor(this, black));

                resumoCadastrarPapel.setText(papel.toString());

                CharSequence texto = "Cadastro realizado!";
                int duracao = Toast.LENGTH_SHORT;
                Toaster(texto, duracao);
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

        /* CRIAÇÃO DO TOAST INLINE
        Context context = getApplicationContext();
        CharSequence text = "Cadastro realizado!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/






            //dar continuidade criação do método de cadastro dos papéis; OK
            //fazer lista de papeis para cadastrar; OK
            //mostrar em list views dinâmicas os papeis cadastrados; OK
            //criar banco de dados; ok
            //implementar a função de exclusão; ok
            //Botão de exclusão de papel da WatchList; ok
            //Montar sistema de pilha para os papeis cadastrados;
            //Transformar o cadastro de papel em WatchList;
            //Fazer o View do sistema de verificação da venda mínima sem perdas;
            //Fazer um side View para calcular ganhos/perdas com a venda num determinado valor;


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
            /*
            CharSequence texto = "Cadastro NÃO realizado!";
            int duracao = Toast.LENGTH_SHORT;
            Toaster(texto, duracao);*/

            TextView resumoCadastrarPapel = (TextView) findViewById(R.id.resumoCadastrPapel);
            resumoCadastrarPapel.setTextColor(ContextCompat.getColor(this, black));
            resumoCadastrarPapel.setText(erro);
        }

    }

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
    public void Listar(ArrayList<Papel> papel){
        TextView resumo2 = (TextView) findViewById(R.id.resumo);
        /*LinearLayout resumoView = (LinearLayout) findViewById(R.id.resumoView);

        for(int i=0; i<papelList.size(); i++){
            TextView listagem = new TextView(this);
            listagem.setText(papelList.toString());
            resumoView.addView(listagem);
        }*/

        StringBuilder sbListaPapel = new StringBuilder();
        for(int i=0; i<papel.size(); i++){

            sbListaPapel.append("Papel: ");
            sbListaPapel.append(papel.get(i).getNomePapel());
            sbListaPapel.append(" / Valor: R$");
            sbListaPapel.append(papel.get(i).getValor());
            sbListaPapel.append("\n");
            sbListaPapel.append("\n");
        }
        resumo2.setText(sbListaPapel);
    }

    public void Clear(){
        tvIdPapel.setText("0");
        et_nomePapel.setText("");
        et_valCadastroPapel.setText("");
    }



    public void pesquisarPapel(View view){
        encontrarPapel();
    }

    public long encontrarPapel(){
        DataBaseHelper dbh = new DataBaseHelper(this);

        tvIdPapel=findViewById(R.id.idPapel);
        idaux = tvIdPapel.getText().toString();

        if(idaux.equals("")){
            Toaster("Insira um ID");
            return 0;
        }
        else{
            ID_PAPEL = Integer.parseInt(idaux);
            Papel papel = dbh.getPapel(ID_PAPEL);

            if(papel.getNomePapel().equals("")){
                Clear();
                /*tvIdPapel.setText("0");
                et_nomePapel.setText("");
                et_valCadastroPapel.setText("");*/

                Toaster("Nenhum registro encontrado");
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

                    //tvIdPapel.setText(dbh.getPapel(ID_PAPEL).getId());
                    et_nomePapel.setText(dbh.getPapel(ID_PAPEL).getNomePapel());
                    et_valCadastroPapel.setText(dbh.getPapel(ID_PAPEL).getValor().toString());
                    //Toaster("Sucesso!");
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toaster("Erro ao buscar!");
                }
                return ID_PAPEL;
            }
        }
    }

    public void salvarPapel(View view) {
        tvIdPapel=findViewById(R.id.idPapel);
        idaux = tvIdPapel.getText().toString();
        resumoBD = (TextView) findViewById(R.id.resumoBD);

        if (idaux.equals("")) {
            //Se não insere um ID específico para sobrepor, é adicionado ao último ID

            DataBaseHelper dbh = new DataBaseHelper(this);
            long longUltimo = dbh.contador();

            int ultimo = Integer.parseInt(String.valueOf(longUltimo));
            String nomePapel = et_nomePapel.getText().toString();
            Double valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
            papel = new Papel(ultimo, nomePapel, valorPapel);


            /*try {
                resumoBD.setText(papel.toString2());
            } catch (Exception e){
                Toaster("Erro de TargetException");
            }*/

            dbh.addPapel(papel);

            Toaster(papel.getNomePapel()+ " foi SALVO com sucesso!");
            dbh.close();

            //Toaster("Último: " + String.valueOf(ultimo));
        } else {
            try {
                ID_PAPEL = 0;
                ID_PAPEL = Integer.parseInt(idaux);
                DataBaseHelper dbh = new DataBaseHelper(this);
                long longUltimo = dbh.contador();
                int ultimo = Integer.parseInt(String.valueOf(longUltimo));

                String nomePapel = et_nomePapel.getText().toString();
                Double valorPapel = Double.parseDouble(et_valCadastroPapel.getText().toString());
                papel = new Papel(ultimo, nomePapel, valorPapel);

                resumoBD.setText(papel.toString());

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
            catch (Exception e){
                e.printStackTrace();
                Toaster("Erro ao salvar!");
            }
        }
        recuperarBD(view);
    }

    public void recuperarBD(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);

        tvIdPapel=findViewById(R.id.idPapel);
        /*idaux = tvIdPapel.getText().toString();
        ID_PAPEL = Integer.parseInt(idaux);*/

        String papel = "Não funcionou...";

        LinearLayout resumoView2BD = (LinearLayout) findViewById(R.id.resumoView2BD);
        LinearLayout layout_teste = (LinearLayout) findViewById(R.id.layout_teste);
        TextView teste = new TextView(this);
        teste.setText(String.valueOf(dbh.idLastNomeNotNull()));
        layout_teste.addView(teste);

        try{
            resumoView2BD.removeAllViewsInLayout();
            TextView resumoBD = new TextView(this);
        } catch (Exception e){
            Toaster("Erro, não foi possível limpar");
        }
        long contador =0;
        contador = dbh.idLastNomeNotNull();
        String stgcontador = String.valueOf(contador);


// *********** VIEW CRIADA PARA TESTAR O CONTADOR ***********
        /*TextView tvcontador = new TextView(this);
        tvcontador.setText("Papeis encontrados: " + stgcontador+"\n"+"****LISTA****"+"\n");
        resumoView2BD.addView(tvcontador);*/

        for(int i=0; i<contador; i++){
            try{
                TextView listagem = new TextView(this);
                papel = dbh.getPapel(i).toString();
                listagem.setText(dbh.getPapel(i).toString());
                resumoView2BD.addView(listagem);
            }catch (Exception e){
                Toaster("Erro ao listar!");
            }
        }
        TextView resumoBD = new TextView(this);
        resumoBD.setText(papel);
        dbh.close();
    }

    //IMPLEMENTAR EXCLUIR PAPEL


    public void excluirPapelBD(View view){
        Toaster("Excluído com sucesso!");
        DataBaseHelper dbh = new DataBaseHelper(this);

        tvIdPapel=findViewById(R.id.idPapel);
        idaux = tvIdPapel.getText().toString();

        if(idaux.equals("")){
            Toaster("Insira um ID");
        }
        else{
            ID_PAPEL = Integer.parseInt(idaux);
            Papel papel = dbh.getPapel(ID_PAPEL);

            if(papel.getNomePapel().equals("")){
                tvIdPapel.setText("0");
                et_nomePapel.setText("");
                et_valCadastroPapel.setText("");

                Toaster("Não encontrado");
            }
            else{
                try{
                    dbh.excludePapel(ID_PAPEL);
                    int ID_PAPELPlus = ID_PAPEL+1;
                    dbh.updateIDPapel(ID_PAPELPlus);
                    Toaster("Papel excluido com sucesso!");
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toaster("Erro ao buscar!");
                }
            }
        }
    }
}