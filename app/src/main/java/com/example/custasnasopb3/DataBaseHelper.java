 package com.example.custasnasopb3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static final String banco_dados = "Papel";

    public DataBaseHelper(Context context){
        super(context, banco_dados, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS PAPEL");
        db.execSQL("CREATE TABLE PAPEL (ID INTEGER(3), PAPEL VARCHAR(6) NOT NULL," +
                "VALOR DOUBLE(10), QUANTIDADE INTEGER(10))");

        db.execSQL("CREATE TABLE CUSTAS (ID INTEGER(3), CORRETAGEM DOUBLE(10), CUSTODIA DOUBLE(10), LIQUIDACAO DOUBLE(10), NEGOCIACAO DOUBLE(10), ISS DOUBLE(10), CORRETAGEMFIXA INTEGER(1), CUSTODIAFIXA INTEGER(1), LIQUIDACAOFIXA INTEGER(1), NEGOCIACAOFIXA INTEGER(1), ISSFIXO INTEGER(1))");
        /*db.execSQL("CREATE TABLE CUSTAS (ID INTEGER(3), " +
                "CORRETAGEM DOUBLE(10), " +
                "CUSTODIA DOUBLE(10), " +
                "LIQUIDACAO DOUBLE(10), " +
                "NEGOCIACAO DOUBLE(10), " +
                "ISS DOUBLE(10))");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        switch (i){
            case 1:{
            }

            case 2:{
                /*db.execSQL("ALTER TABLE PAPEL ADD OBSERVACAO VARCHAR(200);");
                db.execSQL("ALTER TABLE PAPEL RENAME OBSERVACAO TO NOTES;");*/

                //db.execSQL("CREATE TABLE CUSTAS (ID INTEGER(3), CORRETAGEM DOUBLE(10), CUSTODIA DOUBLE(10), LIQUIDACAO DOUBLE(10), NEGOCIACAO DOUBLE(10), ISS DOUBLE(10), CORRETAGEMFIXA INTEGER(1), CUSTODIAFIXA INTEGER(1), LIQUIDACAOFIXA INTEGER(1), NEGOCIACAOFIXA INTEGER(1), ISSFIXO INTEGER(1))");
                }
        }
    }
    public long addPapel(Papel papel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", papel.getId());
        values.put("PAPEL", papel.getNomePapel());
        values.put("VALOR", papel.getValor());
        values.put("QUANTIDADE", papel.getQuantidade());

        long id = db.insert("PAPEL", null, values);

        db.close();
        return id;
    }

    public Papel excludePapel(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Papel papel = new Papel();
        Cursor cursor = db.rawQuery("DELETE FROM PAPEL WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            papel.setId(cursor.getInt(0));
            papel.setNomePapel(cursor.getString(1));
            papel.setValor(cursor.getDouble(2));
            papel.setQuantidade(cursor.getInt(3));
        }
        /*else{
            papel.setId(0);
            papel.setNomePapel("");
            papel.setValor(0.0);
        }*/

        cursor.close();
        db.close();
        return papel;
    }


    //A FUNÇÃO CLEARDB() FOI CRIADA COM A INTENÇÃO DE DELETAR TODOS REGISTROS INVÁLIDOS
    public void clearDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        Papel papel = new Papel();
        Cursor cursor = db.rawQuery("DELETE FROM PAPEL WHERE ID = '0' AND PAPEL = ''", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            papel.setId(cursor.getInt(0));
            papel.setNomePapel(cursor.getString(1));
            papel.setValor(cursor.getDouble(2));
            papel.setQuantidade(cursor.getInt(3));
        }
        cursor.close();
        db.close();
    }

    public Papel getPapel(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Papel papel = new Papel();
        Cursor cursor = db.rawQuery("SELECT * FROM PAPEL WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            papel.setId(cursor.getInt(0));
            papel.setNomePapel(cursor.getString(1));
            papel.setValor(cursor.getDouble(2));
            papel.setQuantidade(cursor.getInt(3));
        }else{
            papel.setId(0);
            papel.setNomePapel("");
            papel.setValor(0.0);
            papel.setQuantidade(0);
        }
        cursor.close();
        db.close();
        return papel;
    }

    /*public Papel moveAllItems(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Papel papel = new Papel();
        List<Papel> tempList = new ArrayList<>();
        Cursor cursor1 = db.rawQuery("SELECT * FROM PAPEL WHERE ID > ?", new String[]{String.valueOf(id)});

        long contagem =cursor1.getCount();

        for (; id<=contagem;id++){
            papel.setId(cursor.getInt(0));
            papel.setNomePapel(cursor.getString(1));
            papel.setValor(cursor.getDouble(2));
        }

        Cursor cursor = db.query(banco_dados, new String[]);


    }*/

    //FAZENDO UM CONTADOR DE CADASTROS NO BANCO DE DADOS
    public long contador(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PAPEL", new String[]{});

        long contagem =cursor.getCount();

        /*SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PAPEL WHERE ID !=? AND PAPEL != ?", new String[]{"0",""});
        long contagem =0;
        contagem = cursor.getCount();*/

        /*if(cursor.getCount()>0){
            contagem = cursor.getCount();
            cursor.moveToFirst();
        }*/

        //long contagem = DatabaseUtils.queryNumEntries(db, banco_dados);
        cursor.close();
        db.close();
        return contagem;
    }

    //PEGANDO O ÚLTIMO ID COM NOME != DE NULL NO BANCO DE DADOS
    // ************ AINDA NÃO ESTÁ CORRETO - TESTAR E SUBSTITUIR O CONTADOR!!! ************

    public long idLastNomeNotNull(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PAPEL", new String[]{});

        long contagem =cursor.getCount();

        /*if(cursor.isLast()){
            contagem = cursor.getPosition();
        }
        for(int i=0; i>contagem; i++){

        }*/


        /*if(cursor.getCount()>0){
            contagem = cursor.getCount();
            cursor.moveToFirst();
        }*/

        //long contagem = DatabaseUtils.queryNumEntries(db, banco_dados);
        cursor.close();
        db.close();
        return contagem;
    }

    public long updatePapel(Papel papel, int id_papel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("PAPEL", papel.getNomePapel());
        values.put("VALOR", papel.getValor());
        values.put("QUANTIDADE",papel.getQuantidade());

        long id = db.update("PAPEL", values, "id = ?", new String[]{String.valueOf(id_papel)});

        db.close();
        return id;
    }
    public long updateIDPapel(int id_papel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        int id_papelMinus = id_papel-1;
        values.put("ID", id_papelMinus);

        long id = db.update("PAPEL", values, "id = ?", new String[]{String.valueOf(id_papel)});

        db.close();
        return id;
    }
    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS PAPEL");

        db.close();
    }



    /*________________________________________ PARAMETROS _____________________________________________________*/

    /*public long firstAddCustas(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", 999);
        values.put("CORRETAGEM", 0.0);
        values.put("CUSTODIA", 0.0);
        values.put("LIQUIDACAO", 0.0);
        values.put("NEGOCIACAO", 0.0);
        values.put("ISS", 0.0);

        values.put("CORRETAGEMFIXA", 1);
        values.put("CUSTODIAFIXA", 1);
        values.put("LIQUIDACAOFIXA", 0);
        values.put("NEGOCIACAOFIXA", 0);
        values.put("ISSFIXO", 0);

        long id = db.insert("CUSTAS", null, values);

        db.close();
        return id;
    }*/

    public long addCustas(Custas custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", custas.getId());
        values.put("CORRETAGEM", custas.getCorretagem());
        values.put("CUSTODIA", custas.getCustodia());
        values.put("LIQUIDACAO", custas.getTx_liquidacao());
        values.put("NEGOCIACAO", custas.getTx_negociacao());
        values.put("ISS", custas.getIss());

        values.put("CORRETAGEMFIXA", custas.boolToInt(custas.isCorretagemFixa()));
        values.put("CUSTODIAFIXA", custas.boolToInt(custas.isCustodiaFixa()));
        values.put("LIQUIDACAOFIXA", custas.boolToInt(custas.isTx_liquidacaoFixa()));
        values.put("NEGOCIACAOFIXA", custas.boolToInt(custas.isTx_negociacaoFixa()));
        values.put("ISSFIXO", custas.boolToInt(custas.isIssFixo()));


        long id = db.insert("CUSTAS", null, values);

        db.close();
        return id;
    }

    public long addTempIdCustas(Custas custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        /*MODIFICAR PARA UMA VERIFICAÇÃO MELHOR*/
        if(custas.getCorretagem()== 0.0 && custas.getCustodia()==0.0 && custas.getTx_liquidacao()==0.0 && custas.getTx_negociacao()==0.0 && custas.getIss()==0.0){
            values.put("ID", custas.getId());
            values.put("CORRETAGEM", 0.0);
            values.put("CUSTODIA", 0.0);
            values.put("LIQUIDACAO", 0.0);
            values.put("NEGOCIACAO", 0.0);
            values.put("ISS", 0.0);

            values.put("CORRETAGEMFIXA", 0);
            values.put("CUSTODIAFIXA", 0);
            values.put("LIQUIDACAOFIXA", 0);
            values.put("NEGOCIACAOFIXA", 0);
            values.put("ISSFIXO", 0);
        }
        else{
            values.put("ID", custas.getId());
            values.put("CORRETAGEM", custas.getCorretagem());
            values.put("CUSTODIA", custas.getCustodia());
            values.put("LIQUIDACAO", custas.getTx_liquidacao());
            values.put("NEGOCIACAO", custas.getTx_negociacao());
            values.put("ISS", custas.getIss());

            values.put("CORRETAGEMFIXA", custas.boolToInt(custas.isCorretagemFixa()));
            values.put("CUSTODIAFIXA", custas.boolToInt(custas.isCustodiaFixa()));
            values.put("LIQUIDACAOFIXA", custas.boolToInt(custas.isTx_liquidacaoFixa()));
            values.put("NEGOCIACAOFIXA", custas.boolToInt(custas.isTx_negociacaoFixa()));
            values.put("ISSFIXO", custas.boolToInt(custas.isIssFixo()));
        }

        long id = db.insert("CUSTAS", null, values);

        db.close();
        return id;
    }

    public long updateTempIdCustas(Custas custas, int id_custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("CORRETAGEM", custas.getCorretagem());
        values.put("CUSTODIA", custas.getCustodia());
        values.put("LIQUIDACAO",custas.getTx_liquidacao());
        values.put("NEGOCIACAO",custas.getTx_negociacao());
        values.put("ISS",custas.getIss());

        values.put("CORRETAGEMFIXA", custas.boolToInt(custas.isCorretagemFixa()));
        values.put("CUSTODIAFIXA", custas.boolToInt(custas.isCustodiaFixa()));
        values.put("LIQUIDACAOFIXA", custas.boolToInt(custas.isTx_liquidacaoFixa()));
        values.put("NEGOCIACAOFIXA", custas.boolToInt(custas.isTx_negociacaoFixa()));
        values.put("ISSFIXO", custas.boolToInt(custas.isIssFixo()));

        long id = db.update("CUSTAS", values, "id = ?", new String[]{String.valueOf(id_custas)});

        db.close();
        return id;
    }

    public long clearTempIdCustas(int id_custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", id_custas);
        values.put("CORRETAGEM", 0.0);
        values.put("CUSTODIA", 0.0);
        values.put("LIQUIDACAO", 0.0);
        values.put("NEGOCIACAO", 0.0);
        values.put("ISS", 0.0);

        values.put("CORRETAGEMFIXA", 0);
        values.put("CUSTODIAFIXA", 0);
        values.put("LIQUIDACAOFIXA", 0);
        values.put("NEGOCIACAOFIXA", 0);
        values.put("ISSFIXO", 0);

        long id = db.update("CUSTAS", values, "id = ?", new String[]{String.valueOf(id_custas)});

        db.close();
        return id;
    }

    public Custas excludeCustas(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Custas custas = new Custas();
        Cursor cursor = db.rawQuery("DELETE FROM CUSTAS WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            custas.setId(cursor.getInt(0));
            custas.setCorretagem(cursor.getDouble(1));
            custas.setCustodia(cursor.getDouble(2));
            custas.setTx_liquidacao(cursor.getDouble(3));
            custas.setTx_negociacao(cursor.getDouble(4));
            custas.setIss(cursor.getDouble(5));

            custas.setCorretagemFixa(custas.intToBool(cursor.getInt(6)));
            custas.setCustodiaFixa(custas.intToBool(cursor.getInt(7)));
            custas.setTx_liquidacaoFixa(custas.intToBool(cursor.getInt(8)));
            custas.setTx_negociacaoFixa(custas.intToBool(cursor.getInt(9)));
            custas.setIssFixo(custas.intToBool(cursor.getInt(10)));
        }

        cursor.close();
        db.close();
        return custas;
    }

    public void clearDBCustas(){
        SQLiteDatabase db = this.getWritableDatabase();

        Custas custas = new Custas();
        Cursor cursor = db.rawQuery("DELETE FROM CUSTAS WHERE ID = '0' AND CORRETAGEM = ''", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            custas.setId(cursor.getInt(0));
            custas.setCorretagem(cursor.getDouble(1));
            custas.setCustodia(cursor.getDouble(2));
            custas.setTx_liquidacao(cursor.getDouble(3));
            custas.setTx_negociacao(cursor.getDouble(4));
            custas.setIss(cursor.getDouble(5));

            custas.setCorretagemFixa(custas.intToBool(cursor.getInt(6)));
            custas.setCustodiaFixa(custas.intToBool(cursor.getInt(7)));
            custas.setTx_liquidacaoFixa(custas.intToBool(cursor.getInt(8)));
            custas.setTx_negociacaoFixa(custas.intToBool(cursor.getInt(9)));
            custas.setIssFixo(custas.intToBool(cursor.getInt(10)));
        }
        cursor.close();
        db.close();
    }

    public Custas getCustas(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Custas custas = new Custas();
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTAS WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            custas.setId(cursor.getInt(0));
            custas.setCorretagem(cursor.getDouble(1));
            custas.setCustodia(cursor.getDouble(2));
            custas.setTx_liquidacao(cursor.getDouble(3));
            custas.setTx_negociacao(cursor.getDouble(4));
            custas.setIss(cursor.getDouble(5));

            custas.setCorretagemFixa(custas.intToBool(cursor.getInt(6)));
            custas.setCustodiaFixa(custas.intToBool(cursor.getInt(7)));
            custas.setTx_liquidacaoFixa(custas.intToBool(cursor.getInt(8)));
            custas.setTx_negociacaoFixa(custas.intToBool(cursor.getInt(9)));
            custas.setIssFixo(custas.intToBool(cursor.getInt(10)));
        }
        else{
            custas.setId(0);
            custas.setCorretagem(0.0);
            custas.setCustodia(0.0);
            custas.setTx_liquidacao(0.0);
            custas.setTx_negociacao(0.0);
            custas.setIss(0.0);

            custas.setCorretagemFixa(custas.intToBool(0));
            custas.setCustodiaFixa(custas.intToBool(0));
            custas.setTx_liquidacaoFixa(custas.intToBool(0));
            custas.setTx_negociacaoFixa(custas.intToBool(0));
            custas.setIssFixo(custas.intToBool(0));
        }
        cursor.close();
        db.close();
        return custas;
    }

    public long updateCustas(Custas custas, int id_custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("CORRETAGEM", custas.getCorretagem());
        values.put("CUSTODIA", custas.getCustodia());
        values.put("LIQUIDACAO",custas.getTx_liquidacao());
        values.put("NEGOCIACAO",custas.getTx_negociacao());
        values.put("ISS",custas.getIss());

        values.put("CORRETAGEMFIXA", custas.boolToInt(custas.isCorretagemFixa()));
        values.put("CUSTODIAFIXA", custas.boolToInt(custas.isCustodiaFixa()));
        values.put("LIQUIDACAOFIXA", custas.boolToInt(custas.isTx_liquidacaoFixa()));
        values.put("NEGOCIACAOFIXA", custas.boolToInt(custas.isTx_negociacaoFixa()));
        values.put("ISSFIXO", custas.boolToInt(custas.isIssFixo()));

        long id = db.update("CUSTAS", values, "id = ?", new String[]{String.valueOf(id_custas)});

        db.close();
        return id;
    }
    public long updateIDCustas(int id_custas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        int id_CustasMinus = id_custas-1;
        values.put("ID", id_CustasMinus);

        long id = db.update("CUSTAS", values, "id = ?", new String[]{String.valueOf(id_custas)});

        db.close();
        return id;
    }
}
