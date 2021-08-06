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
        /*db.execSQL("CREATE TABLE PAPEL (ID INTEGER(3), PAPEL VARCHAR(6) NOT NULL," +
                "VALOR DOUBLE(10), QUANTIDADE INTEGER(10))");*/

        db.execSQL("CREATE TABLE PAPEL (ID INTEGER(3), PAPEL VARCHAR(6) NOT NULL, VALOR DOUBLE(10), QUANTIDADE INTEGER(10), FRACIONARIO INTEGER(1))");

        db.execSQL("CREATE TABLE CUSTAS (ID INTEGER(3), CORRETAGEM DOUBLE(10), CUSTODIA DOUBLE(10), LIQUIDACAO DOUBLE(10), NEGOCIACAO DOUBLE(10), ISS DOUBLE(10), CORRETAGEMFIXA INTEGER(1), CUSTODIAFIXA INTEGER(1), LIQUIDACAOFIXA INTEGER(1), NEGOCIACAOFIXA INTEGER(1), ISSFIXO INTEGER(1))");
        /*db.execSQL("CREATE TABLE CUSTAS (ID INTEGER(3), " +
                "CORRETAGEM DOUBLE(10), " +
                "CUSTODIA DOUBLE(10), " +
                "LIQUIDACAO DOUBLE(10), " +
                "NEGOCIACAO DOUBLE(10), " +
                "ISS DOUBLE(10))");*/
        db.execSQL("CREATE TABLE DATAS (ID INTEGER(3), CONFIRMACOMPRA INTEGER(1), DATACOMPRA INTEGER(2), MESCOMPRA INTEGER(2), ANOCOMPRA INTEGER(4), CONFIRMAVENDA INTEGER(1), DATAVENDA INTEGER(2), MESVENDA INTEGER(2), ANOVENDA INTEGER(4))");
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
        values.put("FRACIONARIO", papel.isFracionario());

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
            papel.setFracionario(papel.intToBool(cursor.getInt(4)));
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
            papel.setFracionario(papel.intToBool(cursor.getInt(4)));
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
            papel.setFracionario(papel.intToBool(cursor.getInt(4)));
        }else{
            papel.setId(0);
            papel.setNomePapel("");
            papel.setValor(0.0);
            papel.setQuantidade(0);
            papel.setFracionario(false);
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
        values.put("FRACIONARIO", papel.isFracionario());

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

//    TODO -------------------- DATAS -----------------------

    public long addDataCompra(Datas datas){
        SQLiteDatabase db = this.getWritableDatabase();
        Papel papel = new Papel();

        ContentValues values = new ContentValues();

        values.put("ID", datas.getId());
        values.put("CONFIRMACOMPRA", papel.boolToInt(datas.isConfirmaCompra()));
        if (datas.isConfirmaCompra()) {
            values.put("DATACOMPRA", datas.getDataCompra());
            values.put("MESCOMPRA", datas.getMesCompra());
            values.put("ANOCOMPRA", datas.getAnoCompra());
        }
        else {
            values.put("DATACOMPRA", 0);
            values.put("MESCOMPRA", 0);
            values.put("ANOCOMPRA", 0);
        }


        long id = db.insert("DATAS", null, values);

        db.close();
        return id;
    }

    public long addDataVenda(Datas datas){
        SQLiteDatabase db = this.getWritableDatabase();
        Papel papel = new Papel();

        ContentValues values = new ContentValues();

        values.put("ID", datas.getId());
        values.put("CONFIRMAVENDA", papel.boolToInt(datas.isConfirmaVenda()));
        if (datas.isConfirmaVenda()) {
            values.put("DATAVENDA", datas.getDataVenda());
            values.put("MESVENDA", datas.getMesVenda());
            values.put("ANOVENDA", datas.getAnoVenda());
        }
        else {
            values.put("DATAVENDA", 0);
            values.put("MESVENDA", 0);
            values.put("ANOVENDA", 0);
        }


        long id = db.insert("DATAS", null, values);

        db.close();
        return id;
    }

    public long addTempIdDatas(Datas datas){
        SQLiteDatabase db = this.getWritableDatabase();
        Papel papel = new Papel();

        ContentValues values = new ContentValues();

        /*MODIFICAR PARA UMA VERIFICAÇÃO MELHOR*/
        if(datas.isConfirmaCompra() && datas.getDataCompra()==0 && datas.getMesCompra()==0 && datas.getAnoCompra()==0 && datas.isConfirmaVenda() && datas.getDataVenda()==0 && datas.getMesVenda()==0 && datas.getAnoVenda()==0){
            values.put("ID", datas.getId());
            values.put("CONFIRMACOMPRA", 0);
            values.put("DATACOMPRA", 0);
            values.put("MESCOMPRA", 0);
            values.put("ANOCOMPRA", 0);
            values.put("CONFIRMAVENDA", 0);
            values.put("DATAVENDA", 0);
            values.put("MESVENDA", 0);
            values.put("ANOVENDA", 0);
        }
        else{
            values.put("CONFIRMACOMPRA", papel.boolToInt(datas.isConfirmaCompra()));
            values.put("DATACOMPRA", datas.getDataCompra());
            values.put("MESCOMPRA", datas.getMesCompra());
            values.put("ANOCOMPRA", datas.getAnoCompra());
            values.put("CONFIRMAVENDA", papel.boolToInt(datas.isConfirmaVenda()));
            values.put("DATAVENDA", datas.getDataVenda());
            values.put("MESVENDA", datas.getMesVenda());
            values.put("ANOVENDA", datas.getAnoVenda());
        }

        long id = db.insert("DATAS", null, values);

        db.close();
        return id;
    }

    public long updateTempIdDatasCompra(Datas datas, int id_datas){
        SQLiteDatabase db = this.getWritableDatabase();
        Papel papel = new Papel();

        ContentValues values = new ContentValues();

        values.put("CONFIRMACOMPRA", papel.boolToInt(datas.isConfirmaCompra()));
        if (datas.isConfirmaCompra()) {
            values.put("DATACOMPRA", datas.getDataCompra());
            values.put("MESCOMPRA", datas.getMesCompra());
            values.put("ANOCOMPRA", datas.getAnoCompra());
        }
        else {
            values.put("DATACOMPRA", 0);
            values.put("MESCOMPRA", 0);
            values.put("ANOCOMPRA", 0);
        }

        long id = db.update("DATAS", values, "id = ?", new String[]{String.valueOf(id_datas)});

        db.close();
        return id;
    }

    public long updateTempIdDatasVenda(Datas datas, int id_datas){
        SQLiteDatabase db = this.getWritableDatabase();
        Papel papel = new Papel();

        ContentValues values = new ContentValues();

        values.put("CONFIRMAVENDA", papel.boolToInt(datas.isConfirmaVenda()));
        if (datas.isConfirmaVenda()) {
            values.put("DATAVENDA", datas.getDataVenda());
            values.put("MESVENDA", datas.getMesVenda());
            values.put("ANOVENDA", datas.getAnoVenda());
        }
        else {
            values.put("DATAVENDA", 0);
            values.put("MESVENDA", 0);
            values.put("ANOVENDA", 0);
        }

        long id = db.update("DATAS", values, "id = ?", new String[]{String.valueOf(id_datas)});

        db.close();
        return id;
    }

    public long clearTempIdDatas(int id_datas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", id_datas);
        values.put("CONFIRMACOMPRA", 0);
        values.put("DATACOMPRA", 0);
        values.put("MESCOMPRA", 0);
        values.put("ANOCOMPRA", 0);
        values.put("CONFIRMAVENDA", 0);
        values.put("DATAVENDA", 0);
        values.put("MESVENDA", 0);
        values.put("ANOVENDA", 0);

        long id = db.update("DATAS", values, "id = ?", new String[]{String.valueOf(id_datas)});

        db.close();
        return id;
    }

    public Papel excludeDatas(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Datas datas = new Datas();
        Papel papel = new Papel();
        Cursor cursor = db.rawQuery("DELETE FROM DATAS WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            datas.setId(cursor.getInt(0));
            datas.setConfirmaCompra(papel.intToBool(cursor.getInt(1)));
            datas.setDataCompra(cursor.getInt(2));
            datas.setMesCompra(cursor.getInt(3));
            datas.setAnoCompra(cursor.getInt(4));
            datas.setConfirmaVenda(papel.intToBool(cursor.getInt(5)));
            datas.setDataVenda(cursor.getInt(6));
            datas.setMesVenda(cursor.getInt(7));
            datas.setAnoVenda(cursor.getInt(8));

        }

        cursor.close();
        db.close();
        return papel;
    }

    public void clearDBDatas(){
        SQLiteDatabase db = this.getWritableDatabase();

        Datas datas = new Datas();
        //Papel papel = new Papel();
        Cursor cursor = db.rawQuery("DELETE FROM DATAS WHERE ID = '0' AND CONFIRMACOMPRA = ''", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            datas.setId(cursor.getInt(0));
            datas.setConfirmaCompra(new Papel().intToBool(cursor.getInt(1)));
            datas.setDataCompra(cursor.getInt(2));
            datas.setMesCompra(cursor.getInt(3));
            datas.setAnoCompra(cursor.getInt(4));
            datas.setConfirmaVenda(new Papel().intToBool(cursor.getInt(5)));
            datas.setDataVenda(cursor.getInt(6));
            datas.setMesVenda(cursor.getInt(7));
            datas.setAnoVenda(cursor.getInt(8));
        }
        cursor.close();
        db.close();
    }

    public Datas getDatas(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Datas datas = new Datas();
        Cursor cursor = db.rawQuery("SELECT * FROM DATAS WHERE ID = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            datas.setId(cursor.getInt(0));
            datas.setConfirmaCompra(new Papel().intToBool(cursor.getInt(1)));
            datas.setDataCompra(cursor.getInt(2));
            datas.setMesCompra(cursor.getInt(3));
            datas.setAnoCompra(cursor.getInt(4));
            datas.setConfirmaVenda(new Papel().intToBool(cursor.getInt(5)));
            datas.setDataVenda(cursor.getInt(6));
            datas.setMesVenda(cursor.getInt(7));
            datas.setAnoVenda(cursor.getInt(8));
        }
        else{
            datas.setId(0);
            datas.setConfirmaCompra(false);
            datas.setDataCompra(0);
            datas.setMesCompra(0);
            datas.setAnoCompra(0);
            datas.setConfirmaVenda(false);
            datas.setDataVenda(0);
            datas.setMesVenda(0);
            datas.setAnoVenda(0);
        }
        cursor.close();
        db.close();
        return datas;
    }

    public long updateDatas(Datas datas, int id_datas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("CONFIRMACOMPRA", new Papel().boolToInt(datas.isConfirmaCompra()));
        values.put("DATACOMPRA", datas.getDataCompra());
        values.put("MESCOMPRA", datas.getMesCompra());
        values.put("ANOCOMPRA", datas.getAnoCompra());
        values.put("CONFIRMAVENDA", new Papel().boolToInt(datas.isConfirmaVenda()));
        values.put("DATAVENDA", datas.getDataVenda());
        values.put("MESVENDA", datas.getMesVenda());
        values.put("ANOVENDA", datas.getAnoVenda());

        long id = db.update("DATAS", values, "id = ?", new String[]{String.valueOf(id_datas)});

        db.close();
        return id;
    }
    public long updateIDDatas(int id_datas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        int id_DatasMinus = id_datas-1;
        values.put("ID", id_DatasMinus);

        long id = db.update("DATAS", values, "id = ?", new String[]{String.valueOf(id_datas)});

        db.close();
        return id;
    }
}
