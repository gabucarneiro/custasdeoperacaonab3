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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*switch (i){
            case 1:{
            }

            case 2:{
                db.execSQL("ALTER TABLE PAPEL ADD OBSERVACAO VARCHAR(200);");
                db.execSQL("ALTER TABLE PAPEL RENAME OBSERVACAO TO NOTES;");
                }
        }*/
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
    public long addPapelPlusQnt(Papel papel){
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
    }

}
