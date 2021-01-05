package com.example.custasnasopb3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
                "VALOR DOUBLE(10));" );
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

        long id = db.insert("PAPEL", null, values);

        return id;
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
        }else{
            papel.setId(0);
            papel.setNomePapel("");
            papel.setValor(0.0);
        }
        return papel;
    }

    //FAZENDO UM CONTADOR DE CADASTROS NO BANCO DE DADOS
    public long contador(){
        SQLiteDatabase db = this.getReadableDatabase();

        long contagem = DatabaseUtils.queryNumEntries(db, banco_dados);
        return contagem;
    }

    public long updatePapel(Papel papel, int id_papel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("PAPEL", papel.getNomePapel());
        values.put("VALOR", papel.getValor());

        long id = db.update("PAPEL", values, "id = ?", new String[]{String.valueOf(id_papel)});

        return id;
    }
    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS PAPEL");
    }

}